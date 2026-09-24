package modelo;

import constantes.IConstants;

public class PoderRayos implements IPower {
    private int danio; //daño actual del poder de rayos

    public PoderRayos(int pDanio){
        this.danio = pDanio; //inicializa el daño con el valor recibido
    }

    @Override
    public void dispararPoder(){
        System.out.println("Dispara rayoss"); //muestra el uso del poder de rayos
    }

    @Override 
    public int getDanio(){
        return this.danio; //devuelve el daño actual del poder
    }

    @Override 
    public void aumentarDanio(){
        if(this.danio < IConstants.MAX_DANIO_PODER){
            this.danio += IConstants.DANIO_AUMENTA; //incrementa el daño en 1 unidad
        }
    }
}
