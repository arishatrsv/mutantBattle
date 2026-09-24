package interfaz;

import constantes.IConstants;
import game.CampoBatalla;
import game.Equipo;
import modelo.Mutante;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;//para el fondo

public class VerCampoBatalla extends JPanel implements Observer{ //consulta el CampoBatalla y será la encargada de representar sus datos.
    private CampoBatalla campoBatalla; //referencia al modelo del campo que se va a dibujar
    private Image fondo;
    public VerCampoBatalla(CampoBatalla pCampoBatalla){
        this.campoBatalla = pCampoBatalla; //guarda el campo que se mostrará en pantalla
        this.fondo = new ImageIcon("imagenes/fondo.png").getImage();
    }

    @Override 
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics); //llama a la pintura base del panel
        graphics.drawImage(this.fondo, 0, 0, this.getWidth(), this.getHeight(), null);//dibuja el fondo
        if (this.campoBatalla == null){
            return; //si no hay tablero, no se dibuja nada
        }

        dibujarBarraSuperior(graphics); //dibuja la información de ambos equipos
        dibujarSimbolos(graphics); //muestra los iconos de cada equipo
        dibujarEquipo(graphics, this.campoBatalla.getEquipo1(), Color.RED); //pinta los mutantes rojos
        dibujarEquipo(graphics, this.campoBatalla.getEquipo2(), Color.BLUE); //pinta los mutantes azules
        dibujarGanador(graphics); //si hay ganador, muestra el mensaje final
    }

    private void dibujarEquipo(Graphics graphics, Equipo pEquipo, Color pColor){
        if (pEquipo == null){
            return; //si el equipo no existe, no dibuja nada
        }

        for (Mutante mutante : pEquipo.getMutantes()){
            if (mutante.estaVivo()){
                int x= mutante.getPosicionX(); //obtiene la coordenada horizontal
                int y= mutante.getPosicionY(); //obtiene la coordenada vertical
                int tamano = IConstants.TAMANO_MUTANTE; //tamaño del círculo visual

            //dibuja el mutante
            graphics.setColor(pColor);
            graphics.fillOval(x, y, tamano, tamano);

            //dibuja el borde
            graphics.setColor(Color.DARK_GRAY);
            graphics.drawOval(x, y, tamano, tamano);

            //dibuja la energía
            graphics.setFont(new Font("Arial", Font.BOLD, 12));
            graphics.setColor(Color.WHITE);
            graphics.drawString(String.valueOf((int)mutante.getEnergia()), x + 8, y + 22);

            //dibuja la barra de energía
            int anchoBarra = tamano;
            int energia = (int)mutante.getEnergia();
            int anchoEnergia = (int)(anchoBarra * energia / 100.0);

            //dibuja el fondo de la barra
            graphics.setColor(Color.DARK_GRAY);
            graphics.fillRect(x, y + tamano + 3, anchoBarra, 5);

            //cambia el color según la energía
            if(energia >= 70){
                graphics.setColor(Color.GREEN);
            }else if(energia >= 30){
                graphics.setColor(Color.YELLOW);
            }else{
                graphics.setColor(Color.RED);
            }

            graphics.fillRect(x, y + tamano + 3, anchoEnergia, 5);
            }
        }
    }

    private void dibujarBarraSuperior(Graphics graphics){ //Dibuja una barra superior para que se vea más ordenado
        graphics.setColor(new Color(20, 20, 25, 180)); //fondo para la cabecera visual
        graphics.fillRect(0,0,this.getWidth(),IConstants.ALTO_ESTADISTICAS); //rellena la barra superior
        graphics.setColor(Color.DARK_GRAY); //color para la línea divisoria
        graphics.drawLine(0,IConstants.ALTO_ESTADISTICAS,this.getWidth(),IConstants.ALTO_ESTADISTICAS); //traza la separación con el campo
        graphics.setFont(new Font("Arial", Font.BOLD, 16));
        
        graphics.setColor(Color.RED); //pone el texto del equipo rojo en rojo
        graphics.drawString("EQUIPO ROJO",90,25); //etiqueta del equipo rojo
        graphics.setColor(Color.BLUE); //pone el texto del equipo azul en azul
        graphics.drawString("EQUIPO AZUL",this.getWidth() - 230,25); //etiqueta del equipo azul

        graphics.setFont(new Font("Arial", Font.PLAIN, 14));
        graphics.setColor(Color.WHITE); //texto blanco para las estadísticas
        graphics.drawString("Vivos: " +this.campoBatalla.getMarcador().getVivosEquipo1() +
            "    Muertos: " +this.campoBatalla.getMarcador().getMuertosEquipo1(),90,50); //muestra estado del equipo rojo

        graphics.drawString("Vivos: " +this.campoBatalla.getMarcador().getVivosEquipo2() +
            "    Muertos: " +this.campoBatalla.getMarcador().getMuertosEquipo2(),this.getWidth() - 230,50); //muestra estado del equipo azul
    }

    private void dibujarSimbolos(Graphics graphics){
        if(this.campoBatalla.getEquipo1() != null){
            Image simboloRojo = this.campoBatalla.getEquipo1().getSimbolo(); //obtiene el símbolo del equipo rojo
            if(simboloRojo != null){graphics.drawImage(
                    simboloRojo,20,8,60,60,null); //dibuja el símbolo del equipo rojo en la barra superior
            }
        }
        if(this.campoBatalla.getEquipo2() != null){
            Image simboloAzul = this.campoBatalla.getEquipo2().getSimbolo(); //obtiene el símbolo del equipo azul
            if(simboloAzul != null){graphics.drawImage(
                    simboloAzul,this.getWidth() - 80,8,60,60,null); //dibuja el símbolo del equipo azul
            }
        }
    }

    private void dibujarGanador(Graphics graphics){
        if(this.campoBatalla.batallaTerminada()){
            Equipo ganador= this.campoBatalla.obtenerGanador(); //obtiene el equipo ganador si la partida ya terminó
            if (ganador != null){
                graphics.setColor(new Color(20, 20, 25, 220)); //fondo semi-transparente para el panel del ganador
                int ancho = 420; //ancho del cartel final
                int alto = 120; //alto del cartel final
                int x = (this.getWidth() - ancho) / 2; //centra el cartel horizontalmente
                int y = (this.getHeight() - alto) / 2; //centra el cartel verticalmente

                graphics.fillRoundRect(x, y, ancho, alto, 20, 20); //dibuja fondo redondeado del ganador
                graphics.setColor(Color.WHITE); //color del borde del mensaje final
                graphics.drawRoundRect(x, y, ancho, alto, 20, 20); //traza el borde redondeado

                graphics.setColor(Color.WHITE);
                graphics.setFont(new Font("Arial", Font.BOLD, 24));
                graphics.drawString("BATALLA TERMINADA", x + 85, y + 40); //titulo del mensaje
                graphics.setFont(new Font("Arial", Font.BOLD, 22));
                graphics.setColor(ganador.getColor().equals("Rojo")? Color.RED: Color.BLUE); //elige el color del equipo ganador
                graphics.drawString("GANADOR " + ganador.getColor(), x + 115, y + 80); //muestra el nombre del equipo ganador
            }
        }

    }

    @Override 
    public void actualizar(){
        this.repaint(); //redibuja el panel cada vez que cambia el estado del juego
    }

    public CampoBatalla getCampoBatalla(){
        return this.campoBatalla; //devuelve el tablero actual para la UI
    }

    public int getAncho(){
        return this.getWidth(); //retorna el ancho del panel
    }

    public int getAlto(){
        return this.getHeight(); //retorna la altura del panel
    }
}
