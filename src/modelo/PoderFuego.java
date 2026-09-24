package modelo;

import constantes.IConstants;

public class PoderFuego implements IPower {
    private int danio; //daño actual del poder de fuego

    public PoderFuego(int pDanio){
        this.danio = pDanio; //inicializa el daño recibido del constructor
    }

    @Override
    public void dispararPoder(){
        System.out.println("Dispara Fuego"); //muestra que el poder de fuego fue utilizado
    }

    @Override 
    public int getDanio(){
        return this.danio; //devuelve el daño actual del poder
    }

    @Override 
    public void aumentarDanio(){
        if(this.danio < IConstants.MAX_DANIO_PODER){
            this.danio += IConstants.DANIO_AUMENTA; //sube el daño solo si no ha llegado al máximo
        }
    }
}
