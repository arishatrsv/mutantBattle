package modelo;

import constantes.IConstants;

public class PoderFuego implements IPower {
    private int danio;

    public PoderFuego(int pDanio){
        this.danio = pDanio;
    }

    @Override
    public void dispararPoder(){
        System.out.println("Dispara Fuego");
    }

    @Override 
    public int getDanio(){
        return this.danio;
    }

    @Override 
    public void aumentarDanio(){
        if(this.danio < IConstants.MAX_DANIO_PODER){
            this.danio += IConstants.DANIO_AUMENTA;
        }
    }
}
