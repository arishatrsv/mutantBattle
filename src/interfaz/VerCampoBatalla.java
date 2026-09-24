package interfaz;

//import constantes.IConstants;
import game.CampoBatalla;
import game.Equipo;
import modelo.Mutante;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class VerCampoBatalla extends JPanel implements Observer{ //consulta el CampoBatalla y será la encargada de representar sus datos.
    private CampoBatalla campoBatalla; 
    public VerCampoBatalla(CampoBatalla pCampoBatalla){
        this.campoBatalla = pCampoBatalla;
    }

    @Override 
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        if (this.campoBatalla == null){
            return;
        }

        dibujarSimbolos(graphics);
        dibujarEquipo(graphics, this.campoBatalla.getEquipo1(), Color.RED);
        dibujarEquipo(graphics, this.campoBatalla.getEquipo2(), Color.BLUE);
        dibujarMarcador(graphics);
    }

    private void dibujarEquipo(Graphics graphics, Equipo pEquipo, Color pColor){
        if (pEquipo == null){
            return;
        }

        graphics.setColor(pColor);
        for (Mutante mutante : pEquipo.getMutantes()){
            if (mutante.estaVivo()){
                int x= mutante.getPosicionX();
                int y= mutante.getPosicionY();

                graphics.fillOval(x,y,40,40);
                graphics.setColor(Color.BLACK);
                graphics.drawString(String.valueOf((int)mutante.getEnergia()), x, y - 5);
                graphics.setColor(pColor);
            }
        }
    }

    private void dibujarSimbolos(Graphics graphics){
        if(this.campoBatalla.getEquipo1() != null){
            Image simboloRojo = this.campoBatalla.getEquipo1().getSimbolo();
            if(simboloRojo != null){graphics.drawImage(
                    simboloRojo,20,25,60,60,null);
            }
        }
        if(this.campoBatalla.getEquipo2() != null){
            Image simboloAzul = this.campoBatalla.getEquipo2().getSimbolo();
            if(simboloAzul != null){graphics.drawImage(
                    simboloAzul,720,25,60,60,null);
            }
        }
    }

    private void dibujarMarcador(Graphics graphics){
        graphics.setColor(Color.BLACK);
        graphics.drawString("Equipo 1 - Vivos: " + this.campoBatalla.getMarcador().getVivosEquipo1()
            + "Muertos: " + this.campoBatalla.getMarcador().getMuertosEquipo1(),20,20 );
        graphics.drawString("Equipo 2 - Vivos: " + this.campoBatalla.getMarcador().getVivosEquipo2()
            + "Muertos: " + this.campoBatalla.getMarcador().getMuertosEquipo2(),250,20 );
        
        if (this.campoBatalla.batallaTerminada()){
            Equipo ganador= this.campoBatalla.obtenerGanador();
            if (ganador != null){
                graphics.drawString("Ganador: "+ ganador.getColor(),20,60);
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
