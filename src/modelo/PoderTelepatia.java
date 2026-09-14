package modelo;

import constantes.IConstants;

public class PoderTelepatia implements IPower {
    private int danio;

    public PoderTelepatia(int pDanio){
        this.danio = pDanio;
    }

    @Override
    public void dispararPoder(){
        System.out.println("Utiliza telepatía");
    }

    @Override 
    public int getDanio(){
        return this.danio;
    }

    @Override 
    public void aumentarDanio(){
        if(this.danio < 7){
            this.danio += IConstants.DANIO_AUMENTA;
        }
    }
}