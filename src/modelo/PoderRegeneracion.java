package modelo;

import constantes.IConstants;

public class PoderRegeneracion implements IPower {
    private int danio;

    public PoderRegeneracion(int pDanio){
        this.danio = pDanio;
    }

    @Override
    public void dispararPoder(){
        System.out.println("Utiliza regeneración");
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
