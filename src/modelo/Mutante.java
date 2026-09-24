//Desarrollado por:Hilary Aguilar y Arina Tarasova

package modelo;
import constantes.IConstants;

public class Mutante extends Persona{
    private int energia; //salud actual del mutante
    private int defensa; //valor que reduce el daño recibido
    private int posicionX; //coordenada horizontal del mutante en el campo
    private int posicionY; //coordenada vertical del mutante en el campo
    private IPower poder; //habilidad especial del mutante
    private int direccionX; //sentido horizontal del movimiento
    private int direccionY; //sentido vertical del movimiento

    //Constructor con parámetros
    public Mutante(String pNombre, byte pEdad, int pEnergia, int pDefensa,
                    int pPosicionX, int pPosicionY, IPower pPoder) {
        super(pNombre, pEdad); //pEdad ya es byte
        this.energia = pEnergia; //guarda la energía inicial
        this.defensa = pDefensa; //guarda la defensa inicial
        this.posicionX = pPosicionX; //guarda la posición X inicial
        this.posicionY = pPosicionY; //guarda la posición Y inicial
        this.poder = pPoder; //asigna el poder especial
        this.direccionX = obtenerDireccion(); //elige aleatoriamente la dirección horizontal
        this.direccionY = obtenerDireccion(); //elige aleatoriamente la dirección vertical
    }

    // métodos get para leer los valores de los atributos de la instancia
    public int getEnergia(){
        return this.energia; //devuelve la energía actual
    }

    public int getDefensa(){
        return this.defensa; //devuelve la defensa del mutante
    }

    public int getPosicionX(){
        return this.posicionX; //devuelve la posición X
    }

    public int getPosicionY(){
        return this.posicionY; //devuelve la posición Y
    }

    public IPower getPoder(){
        return this.poder; //devuelve el poder asociado
    }

    // métodos set para modificar los valores de los atributos de la instancia
    public void setEnergia(int pEnergia){
        this.energia = pEnergia; //actualiza la energía
    }

    public void setDefensa(int pDefensa){
        this.defensa = pDefensa; //actualiza la defensa
    }

    public void setPosicionX(int pPosicionX){
        this.posicionX = pPosicionX; //actualiza la posición X
    }

    public void setPosicionY(int pPosicionY){
        this.posicionY = pPosicionY; //actualiza la posición Y
    }

    public void setPoder(IPower pPoder){
        this.poder = pPoder; //cambia el poder del mutante
    }

    public boolean estaVivo(){ // Indica si el mutante todavía tiene energía
        return this.energia > 0; //un mutante vive mientras su energía sea mayor a cero
    }

    public void recibirDanio(double pDanio){ // Reduce la energía del mutante al recibir daño
        this.energia -= pDanio; //resta el daño recibido
        if (this.energia < 0) {
            this.energia = 0; //evita que la energía quede negativa
        }
    }

    public void mover(int pAncho, int pAlto){
        int limiteDerecho = pAncho - IConstants.TAMANO_MUTANTE; //máximo X antes de salir del campo
        int limiteInferior = pAlto - IConstants.TAMANO_MUTANTE; //máximo Y antes de salir del campo
        this.posicionX += this.direccionX * IConstants.MISMA_VELOCIDAD; //avanza horizontalmente
        this.posicionY += this.direccionY * IConstants.MISMA_VELOCIDAD; //avanza verticalmente
        if(this.posicionX <= 0 || this.posicionX >= limiteDerecho){
            this.direccionX *= -1; //invierte dirección si llega al borde horizontal
        }
        if(this.posicionY <= IConstants.ALTO_ESTADISTICAS
            || this.posicionY >= limiteInferior){
            this.direccionY *= -1; //invierte dirección si llega al borde vertical
        }
        if(this.posicionX < 0){
            this.posicionX = 0; //corrige la posición si sale por la izquierda
        }
        if(this.posicionX > limiteDerecho){
            this.posicionX = limiteDerecho; //corrige la posición si sale por la derecha
        }
        if(this.posicionY < IConstants.ALTO_ESTADISTICAS){
            this.posicionY = IConstants.ALTO_ESTADISTICAS; //deja espacio para la barra superior
        }
        if(this.posicionY > limiteInferior){
            this.posicionY = limiteInferior; //corrige la posición si sale por abajo
        }
    }

    private int obtenerDireccion(){
        int direccion = 0; //valor inicial para forzar la aleatoriedad
        while(direccion == 0){
            direccion = (int)(Math.random() * 3) - 1; //genera -1, 0 o 1; si es 0 vuelve a intentar
        }
        return direccion; //devuelve -1 o 1 para mover en una sola dirección
    }
}