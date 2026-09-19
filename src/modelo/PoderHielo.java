package modelo;

import constantes.IConstants;

public class PoderHielo implements IPower {
    private int danio;

    public PoderHielo(int pDanio){
        this.danio = pDanio;
    }

    @Override
    public void dispararPoder(){
        System.out.println("Dispara hielooo");
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
