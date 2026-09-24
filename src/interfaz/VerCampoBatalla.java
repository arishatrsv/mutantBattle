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

public class VerCampoBatalla extends JPanel implements Observer{ //consulta el CampoBatalla y será la encargada de representar sus datos.
    private CampoBatalla campoBatalla; 
    public VerCampoBatalla(CampoBatalla pCampoBatalla){
        this.campoBatalla = pCampoBatalla;
        this.setBackground(new Color(235, 238, 242));
    }

    @Override 
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        if (this.campoBatalla == null){
            return;
        }

        dibujarBarraSuperior(graphics);
        dibujarSimbolos(graphics);
        dibujarEquipo(graphics, this.campoBatalla.getEquipo1(), Color.RED);
        dibujarEquipo(graphics, this.campoBatalla.getEquipo2(), Color.BLUE);
        dibujarGanador(graphics);
    }

    private void dibujarEquipo(Graphics graphics, Equipo pEquipo, Color pColor){
        if (pEquipo == null){
            return;
        }

        for (Mutante mutante : pEquipo.getMutantes()){
            if (mutante.estaVivo()){
                int x= mutante.getPosicionX();
                int y= mutante.getPosicionY();
                int tamano = 35;

                graphics.setColor(pColor);
                graphics.fillOval(x,y,tamano,tamano);
                graphics.setFont(new Font("Arial",Font.BOLD,12)); //Energía del mutante
                graphics.drawString(String.valueOf((int)mutante.getEnergia()), x + 8, y - 5);
                graphics.setColor(pColor);
            }
        }
    }

    private void dibujarBarraSuperior(Graphics graphics){ //Dibuja una barra superior para que se vea más ordenado
        graphics.setColor(new Color(210, 214, 220));
        graphics.fillRect(0,0,this.getWidth(),IConstants.ALTO_ESTADISTICAS);
        graphics.setColor(Color.DARK_GRAY);
        graphics.drawLine(0,IConstants.ALTO_ESTADISTICAS,this.getWidth(),IConstants.ALTO_ESTADISTICAS);
        graphics.setFont(new Font("Arial", Font.BOLD, 16));
        
        graphics.setColor(Color.RED);
        graphics.drawString("EQUIPO ROJO",90,25);
        graphics.setColor(Color.BLUE);
        graphics.drawString("EQUIPO AZUL",this.getWidth() - 230,25);

        graphics.setFont(new Font("Arial", Font.PLAIN, 14));
        graphics.setColor(Color.BLACK);
        graphics.drawString("Vivos: " +this.campoBatalla.getMarcador().getVivosEquipo1() +
            "    Muertos: " +this.campoBatalla.getMarcador().getMuertosEquipo1(),90,50);

        graphics.drawString("Vivos: " +this.campoBatalla.getMarcador().getVivosEquipo2() +
            "    Muertos: " +this.campoBatalla.getMarcador().getMuertosEquipo2(),this.getWidth() - 230,50);
    }

    private void dibujarSimbolos(Graphics graphics){
        if(this.campoBatalla.getEquipo1() != null){
            Image simboloRojo = this.campoBatalla.getEquipo1().getSimbolo();
            if(simboloRojo != null){graphics.drawImage(
                    simboloRojo,20,8,60,60,null);
            }
        }
        if(this.campoBatalla.getEquipo2() != null){
            Image simboloAzul = this.campoBatalla.getEquipo2().getSimbolo();
            if(simboloAzul != null){graphics.drawImage(
                    simboloAzul,this.getWidth() - 80,8,60,60,null);
            }
        }
    }

    private void dibujarGanador(Graphics graphics){
        if(this.campoBatalla.batallaTerminada()){
            Equipo ganador= this.campoBatalla.obtenerGanador();
            if (ganador != null){
                graphics.setColor(new Color(255, 255, 255, 220));            
                int ancho = 420;
                int alto = 120;
                int x = (this.getWidth() - ancho) / 2;
                int y = (this.getHeight() - alto) / 2;

                graphics.fillRoundRect(x, y, ancho, alto, 20, 20);
                graphics.setColor(Color.DARK_GRAY);
                graphics.drawRoundRect(x, y, ancho, alto, 20, 20);

                graphics.setFont(new Font("Arial", Font.BOLD, 24));
                graphics.drawString("BATALLA TERMINADA", x + 85, y + 40);
                graphics.setFont(new Font("Arial", Font.BOLD, 22));
                graphics.setColor(ganador.getColor().equals("Rojo")? Color.RED: Color.BLUE);
                graphics.drawString("GANADOR " + ganador.getColor(), x + 115, y + 80);
            }
        }

    }

    @Override 
    public void actualizar(){
        this.repaint();
    }

    public CampoBatalla getCampoBatalla(){
        return this.campoBatalla;
    }

    public int getAncho(){
        return this.getWidth();
    }

    public int getAlto(){
        return this.getHeight();
    }
}
