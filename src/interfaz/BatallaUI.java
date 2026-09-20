package interfaz;

import constantes.IConstants;
import control.ControladorBatalla;
import game.CampoBatalla;

import javax.swing.JFrame;
import javax.swing.Timer;

public class BatallaUI extends JFrame{
    private VerCampoBatalla vista;
    private Timer temporizador;
    private ControladorBatalla controlador;

    public BatallaUI(CampoBatalla pCampoBatalla, ControladorBatalla pControladorBatalla){ //Crea la ventana donde se verá el juego
        this.vista = new VerCampoBatalla(pCampoBatalla);
        this.controlador = pControladorBatalla;

        this.setTitle("Mutant Battle");
        this.setSize(IConstants.ANCHO_CAMPOBATALLA,IConstants.ALTO_CAMPOBATALLA);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(this.vista);
        this.temporizador = new Timer(IConstants.ACTUALIZACION_UI, e-> this.actualizar());
        this.temporizador.start();
        this.setVisible(true);
    }

    public void actualizar(){
        this.vista.actualizar();
        if (this.vista.getCampoBatalla().batallaTerminada()){
            this.temporizador.stop();
        }
    } 

    public void iniciarBatalla(int pCantidad){
        Thread hiloBatalla = new Thread(() -> {
            this.controlador.iniciarJuego(pCantidad);
        });
        hiloBatalla.start();
    }
}
