package modelo;

import constantes.IConstants;

public class PoderRegeneracion implements IPower {
    private int danio; //daño actual del poder de regeneración

    public PoderRegeneracion(int pDanio){
        this.danio = pDanio; //inicializa el daño base del poder
    }

    @Override
    public void dispararPoder(){
        System.out.println("Utiliza regeneración"); //muestra cuándo se usa la habilidad de regeneración
    }

    @Override 
    public int getDanio(){
        return this.danio; //devuelve el daño actual del poder
    }

    @Override 
    public void aumentarDanio(){
        if(this.danio < IConstants.MAX_DANIO_PODER){
            this.danio += IConstants.DANIO_AUMENTA; //sube el daño si aún no alcanza el tope
        }
    }
}
