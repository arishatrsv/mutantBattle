//Desarrollado por:Hilary Aguilar y Arina Tarasova

package modelo;
import constantes.IConstants;

public class Mutante extends Persona{
    private int energia;
    private int defensa;
    private int posicionX;
    private int posicionY;
    private IPower poder;
    private int direccionX;
    private int direccionY;

    //Constructor con parámetros
    public Mutante(String pNombre, byte pEdad, int pEnergia, int pDefensa,
                    int pPosicionX, int pPosicionY, IPower pPoder) {
        super(pNombre, pEdad); //pEdad ya es byte
        this.energia = pEnergia;
        this.defensa = pDefensa;
        this.posicionX = pPosicionX;
        this.posicionY = pPosicionY;
        this.poder = pPoder;
        this.direccionX = obtenerDireccion();
        this.direccionY = obtenerDireccion();    }

    // métodos get para leer los valores de los atributos de la instancia
    public int getEnergia(){
        return this.energia;
    }

    public int getDefensa(){
        return this.defensa;
    }

    public int getPosicionX(){
        return this.posicionX;
    }

    public int getPosicionY(){
        return this.posicionY;
    }

    public IPower getPoder(){
        return this.poder;
    }

    // métodos set para modificar los valores de los atributos de la instancia
    public void setEnergia(int pEnergia){
        this.energia = pEnergia;
    }

    public void setDefensa(int pDefensa){
        this.defensa = pDefensa;
    }

    public void setPosicionX(int pPosicionX){
        this.posicionX = pPosicionX;
    }

    public void setPosicionY(int pPosicionY){
        this.posicionY = pPosicionY;
    }

    public void setPoder(IPower pPoder){
        this.poder = pPoder;
    }

    public boolean estaVivo(){ // Indica si el mutante todavía tiene energía
        return this.energia > 0;
    }

    public void recibirDanio(double pDanio){ // Reduce la energía del mutante al recibir daño
        this.energia -= pDanio;
        if (this.energia < 0) {
            this.energia = 0;
        }
    }

    public void mover(int pAncho, int pAlto){
        int limiteDerecho = pAncho;
        int limiteInferior = pAlto;
        this.posicionX += this.direccionX * IConstants.MISMA_VELOCIDAD;
        this.posicionY += this.direccionY * IConstants.MISMA_VELOCIDAD;
        if(this.posicionX <= 0 || this.posicionX >= limiteDerecho){
            this.direccionX *= -1;
        }
        if(this.posicionY <= 0 || this.posicionY >= limiteInferior){
            this.direccionY *= -1;
        }
        if(this.posicionX < 0){
            this.posicionX = 0;
        }
        if(this.posicionX > limiteDerecho){
            this.posicionX = limiteDerecho;
        }
        if(this.posicionY < 0){
            this.posicionY = 0;
        }
        if(this.posicionY > limiteInferior){
            this.posicionY = limiteInferior;
        }
    }

    private int obtenerDireccion(){
        int direccion = 0;
        while(direccion == 0){
            direccion = (int)(Math.random() * 3) - 1;
        }
        return direccion;
    }
}