package modelo;

import constantes.IConstants;

public class PoderHielo implements IPower {
    private int danio; //daño actual del poder de hielo

    public PoderHielo(int pDanio){
        this.danio = pDanio; //inicializa el daño con el valor recibido
    }

    @Override
    public void dispararPoder(){
        System.out.println("Dispara hielooo"); //muestra el uso del poder de hielo
    }

    @Override 
    public int getDanio(){
        return this.danio; //devuelve el daño actual del poder
    }

    @Override 
    public void aumentarDanio(){
        if(this.danio < IConstants.MAX_DANIO_PODER){
            this.danio += IConstants.DANIO_AUMENTA; //incrementa el daño hasta el máximo permitido
        }
    }
}
