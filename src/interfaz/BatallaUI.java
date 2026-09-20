package interfaz;

import constantes.IConstants;
import control.ControladorBatalla;
import game.CampoBatalla;
import java.awt.BorderLayout;
import javax.swing.JButton; //para el boton

import javax.swing.JFrame;
import javax.swing.JOptionPane; //para que el usuario ingrese el dato
import javax.swing.Timer;

public class BatallaUI extends JFrame{
    private VerCampoBatalla vista;
    private Timer temporizador;
    private ControladorBatalla controlador;
    private JButton botonNuevaBatalla;
    private boolean batallaFinalizada;

    public BatallaUI(CampoBatalla pCampoBatalla, ControladorBatalla pControladorBatalla){ //Crea la ventana donde se verá el juego
        this.vista = new VerCampoBatalla(pCampoBatalla);
        this.controlador = pControladorBatalla;
        this.batallaFinalizada = false;
        this.setTitle("Mutant Battle");
        this.setSize(IConstants.ANCHO_VENTANA,IConstants.ALTO_VENTANA);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(this.vista);
        this.botonNuevaBatalla = new JButton("Iniciar nueva batalla");//crea el botón
        this.botonNuevaBatalla.setVisible(false);//hace que al principio no se vea el boton
        this.botonNuevaBatalla.addActionListener(e -> iniciarNuevaBatalla()); //cuando presione el botón, ejecutar iniciarNuevaBatalla
        this.add(this.botonNuevaBatalla, BorderLayout.SOUTH); //lo coloca abajo de la ventana
        this.temporizador = new Timer(IConstants.ACTUALIZACION_UI, e-> this.actualizar());
        this.temporizador.start();
        this.setVisible(true);
    }

    public void actualizar(){
        this.vista.actualizar();
        if (this.vista.getCampoBatalla().batallaTerminada()&& !this.batallaFinalizada){
            this.batallaFinalizada = true;
            this.temporizador.stop();
            this.botonNuevaBatalla.setVisible(true);
        }
    } 
    public void iniciarBatalla(){
        int cantidad = recibirCantidadEquipo();
        iniciarBatalla(cantidad);
    }

    public void iniciarBatalla(int pCantidad){
        Thread hiloBatalla = new Thread(() -> {
            this.controlador.iniciarJuego(pCantidad);
        });
        hiloBatalla.start();
    }

    private void cambiarVista(){
        remove(vista);
        vista = new VerCampoBatalla(controlador.getCampoBatalla());
        add(vista);
        revalidate();
        repaint();
    }

    public void nuevaBatalla(int pCantidad){
        controlador.nuevaBatalla();
        cambiarVista();
        iniciarBatalla(pCantidad);
    }

    private void iniciarNuevaBatalla(){
        int cantidad = recibirCantidadEquipo();
        this.botonNuevaBatalla.setVisible(false);
        this.batallaFinalizada = false;
        this.controlador.nuevaBatalla();
        this.cambiarVista();
        this.temporizador.start();
        this.iniciarBatalla(cantidad);
    }

    private int recibirCantidadEquipo(){
        int cantidad = 0;
        boolean cantidadValida = false;
        while(!cantidadValida){
            String entrada = javax.swing.JOptionPane.showInputDialog("Digite la cantidad de mutantes por equipo (3-11):");
            if(entrada == null){
                System.exit(0);
            }
            try{
                cantidad = Integer.parseInt(entrada);
                if(cantidad >= IConstants.MIN_TAMANO_EQUIPO && cantidad <= IConstants.MAX_TAMANO_EQUIPO){
                    cantidadValida = true;
                }else{
                    JOptionPane.showMessageDialog(null, "La cantidad debe estar entre 3 y 11.");
                }
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Digite un número válido.");
            }
        }
        return cantidad;
    }
}
