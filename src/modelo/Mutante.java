//Desarrollado por:Hilary Aguilar y Arina Tarasova

package modelo;
import poderes.*;

public class Mutante extends Persona{
    private double energia;
    private int defensa;
    private int posicionX;
    private int posicionY;
    private IPower poder;

    //Constructor con parámetros
    public Mutante(String pNombre, byte pEdad, double pEnergia, int pDefensa,
                   int pPosicionX, int pPosicionY, IPower pPoder) {
        super(pNombre, (byte) pEdad);
        this.energia = pEnergia;
        this.defensa = pDefensa;
        this.posicionX = pPosicionX;
        this.posicionY = pPosicionY;
        this.poder = pPoder;
    }

    // métodos get para leer los valores de los atributos de la instancia
    public double getEnergia(){
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
    public void setEnergia(double pEnergia){
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

    public void mover(int pAncho, int pAlto){ // Mueve al mutante dentro de los límites del campo de batalla
        //falta
    }
}