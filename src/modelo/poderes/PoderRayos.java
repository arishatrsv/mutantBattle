package poderes;

import constantes.IConstants;

public class PoderRayos implements IPower {
    private int danio;

    public PoderRayos(int pDanio){
        this.danio = pDanio;
    }

    @Override
    public void dispararPoder(){
        System.out.println("Dispara rayoss");
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
