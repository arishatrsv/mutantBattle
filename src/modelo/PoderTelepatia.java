package modelo;

import constantes.IConstants;

public class PoderTelepatia implements IPower {
    private int danio; //daño actual del poder de telepatía

    public PoderTelepatia(int pDanio){
        this.danio = pDanio; //inicializa el daño base del poder
    }

    @Override
    public void dispararPoder(){
        System.out.println("Utiliza telepatía"); //muestra cuándo el mutante usa telepatía
    }

    @Override 
    public int getDanio(){
        return this.danio; //devuelve el daño disponible del poder
    }

    @Override 
    public void aumentarDanio(){
        if(this.danio < IConstants.MAX_DANIO_PODER){
            this.danio += IConstants.DANIO_AUMENTA; //incrementa el daño si todavía no alcanza el máximo
        }
    }
}