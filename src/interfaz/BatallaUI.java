package interfaz;

import constantes.IConstants;
import control.ControladorBatalla;
import game.CampoBatalla;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import javax.swing.JButton; //para el boton

import javax.swing.JFrame;
import javax.swing.JOptionPane; //para que el usuario ingrese el dato
import javax.swing.Timer;

public class BatallaUI extends JFrame{
    private VerCampoBatalla vista; //panel que dibuja el campo de batalla
    private Timer temporizador; //actualiza la pantalla mientras corre la partida
    private ControladorBatalla controlador; //controlador que gestiona la lógica del juego
    private JButton botonNuevaBatalla; //botón para iniciar otra ronda
    private boolean batallaFinalizada; //bandera que indica si ya terminó la batalla actual

    public BatallaUI(CampoBatalla pCampoBatalla, ControladorBatalla pControladorBatalla){ //Crea la ventana donde se verá el juego
        this.vista = new VerCampoBatalla(pCampoBatalla); //crea la vista del campo
        this.controlador = pControladorBatalla; //guarda el control asociado
        this.batallaFinalizada = false; //la partida aún no ha finalizado
        this.setTitle("Mutant Battle"); //nombre de la ventana
        this.setSize(IConstants.ANCHO_VENTANA,IConstants.ALTO_VENTANA); //tamaño de la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //cerrar la app cuando termine
        this.add(this.vista); //agrega el panel principal a la ventana
        this.botonNuevaBatalla = new JButton("Iniciar nueva batalla");//crea el botón
        this.botonNuevaBatalla.setVisible(false);//hace que al principio no se vea el boton
        this.botonNuevaBatalla.addActionListener(e -> iniciarNuevaBatalla()); //cuando presione el botón, ejecutar iniciarNuevaBatalla
        this.add(this.botonNuevaBatalla, BorderLayout.SOUTH); //lo coloca abajo de la ventana
        this.temporizador = new Timer(IConstants.ACTUALIZACION_UI, e-> this.actualizar()); //actualiza la vista cada 50 ms
        this.temporizador.start(); //arranca el refresco visual
        this.setVisible(true); //muestra la ventana al usuario
    }

    public void actualizar(){
        this.vista.actualizar(); //pide repintar el panel del campo
        if (this.vista.getCampoBatalla().batallaTerminada()&& !this.batallaFinalizada){
            this.batallaFinalizada = true; //habilita la bandera de fin de partida
            this.temporizador.stop(); //detiene el reloj visual
            this.botonNuevaBatalla.setVisible(true); //muestra el botón para repetir la batalla
        }
    } 
    public void iniciarBatalla(){
        int cantidad = recibirCantidadEquipo(); //pide cuántos mutantes habrá por equipo
        iniciarBatalla(cantidad); //lanza la partida solicitada
    }

    public void iniciarBatalla(int pCantidad){
        Thread hiloBatalla = new Thread(() -> {
            this.controlador.generarEquipos(pCantidad); //crea los equipos con la cantidad indicada

            ImageIcon iconoRojo = new ImageIcon("imagenes/rojo.png");
            ImageIcon iconoAzul = new ImageIcon("imagenes/azul.png");
            Image imagenRojo = iconoRojo.getImage(); //convierte la imagen a formato que puede dibujarse
            Image imagenAzul = iconoAzul.getImage(); //convierte la imagen a formato que puede dibujarse

            this.controlador.getCampoBatalla().getEquipo1().setSimbolo(imagenRojo); //asigna el símbolo rojo al equipo 1
            this.controlador.getCampoBatalla().getEquipo2().setSimbolo(imagenAzul); //asigna el símbolo azul al equipo 2

            this.controlador.iniciarMovimiento(this.vista.getWidth(),this.vista.getHeight()); //arranca el movimiento de cada mutante
            this.controlador.controlarBatalla(); //espera a que todos los hilos terminen
            this.controlador.finalizarJuego(); //detiene la partida y determina el ganador
        });
        hiloBatalla.start(); //inicia el hilo que gestiona la batalla
    }

    private void cambiarVista(){
        remove(vista); //elimina la vista actual del panel
        vista = new VerCampoBatalla(controlador.getCampoBatalla()); //crea una nueva vista con el campo nuevo
        add(vista); //agrega la nueva vista
        revalidate(); //recalcula el layout de la ventana
        repaint(); //redibuja la interfaz completa
    }

    public void nuevaBatalla(int pCantidad){
        controlador.nuevaBatalla(); //resetea el estado interno del campo
        cambiarVista(); //reemplaza la vista del tablero
        iniciarBatalla(pCantidad); //comienza otra batalla
    }

    private void iniciarNuevaBatalla(){
        int cantidad = recibirCantidadEquipo(); //solicita la cantidad para la nueva partida
        this.botonNuevaBatalla.setVisible(false); //oculta el botón de nueva batalla
        this.batallaFinalizada = false; //habilita otra ronda
        this.controlador.nuevaBatalla(); //reinicia el campo y los hilos
        this.cambiarVista(); //actualiza la vista
        this.temporizador.start(); //reactiva el refresco visual
        this.iniciarBatalla(cantidad); //arranca la nueva batalla
    }

    private int recibirCantidadEquipo(){
        int cantidad = 0; //valor inicial antes de validar
        boolean cantidadValida = false; //bandera que confirma una entrada correcta
        while(!cantidadValida){
            String entrada = javax.swing.JOptionPane.showInputDialog("Digite la cantidad de mutantes por equipo (3-11):"); //solicita la cantidad al usuario
            if(entrada == null){
                System.exit(0); //si cancela, termina la aplicación
            }
            try{
                cantidad = Integer.parseInt(entrada); //convierte la entrada a entero
                if(cantidad >= IConstants.MIN_TAMANO_EQUIPO && cantidad <= IConstants.MAX_TAMANO_EQUIPO){
                    cantidadValida = true; //la cantidad está dentro del rango válido
                }else{
                    JOptionPane.showMessageDialog(null, "La cantidad debe estar entre 3 y 11."); //muestra error si se sale del rango
                }
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Digite un número válido."); //muestra error si no es un número
            }
        }
        return cantidad; //devuelve la cantidad validada
    }
}
