package control;

import constantes.IConstants;
import modelo.Mutante;

public class AdministradorCombate {
    private double radioEncuentro; //radio mínimo para considerar que dos mutantes se encontraron

    //constructor
    public AdministradorCombate(){
        this.radioEncuentro = IConstants.ENCUENTRO_RADIO; //define la distancia para detectar un encuentro
    }

    public boolean detectarEncuentro(Mutante pMutante1, Mutante pMutante2){ //comprueba si dos mutantes están suficientemente cerca
        double diferenciaX = pMutante1.getPosicionX() - pMutante2.getPosicionX(); //separación en horizontal
        double diferenciaY = pMutante1.getPosicionY() - pMutante2.getPosicionY(); //separación en vertical
        double distancia = Math.sqrt(diferenciaX * diferenciaX + diferenciaY * diferenciaY); //aplica la fórmula de distancia euclidiana
        return distancia <= this.radioEncuentro; //indica si están dentro del radio de encuentro
    }
    public boolean decidirDefensa(){ //decide aleatoriamente si el mutante se defiende
        return Math.random() < IConstants.PROBABILIDAD_DEFENSA; //true significa que se defiende, 0,5 para que sea 50% probabilidad
    }
    public double calcularDanio(Mutante pAtacante, Mutante pDefensor, boolean pDefiende){ //calcula el daño que recibe el defensor
        int danio = pAtacante.getPoder().getDanio(); //daño base del poder del atacante
        if(pDefiende){
            return (double)danio / pDefensor.getDefensa(); //si se defiende, el daño se divide entre su defensa
        }
        return danio; //si no se defiende, recibe todo el daño
    }
    public void aplicarDanio(Mutante pDefensor, double pDanio){ //aplica el daño al defensor
        pDefensor.recibirDanio(pDanio); //actualiza la energía del mutante y evita valores negativos
    }
    public void aumentarDanioPoder(Mutante pAtacante){ //aumenta el daño del poder
        pAtacante.getPoder().aumentarDanio(); //sube el poder del atacante si recibió daño
    }

    public void ejecutarEncuentro(Mutante pMutante1, Mutante pMutante2){ //ejecuta un ataque entre dos mutantes
        if(!pMutante1.estaVivo() || !pMutante2.estaVivo()){
            return; //no ejecuta el encuentro si alguno ya está muerto
        }
        boolean mutante1Defiende = decidirDefensa(); //decide si el primer mutante se protege
        boolean mutante2Defiende = decidirDefensa(); //decide si el segundo mutante se protege
        if(!mutante1Defiende){
            atacar(pMutante1, pMutante2, mutante2Defiende); //ataca al segundo si no se defendió
        }
        if(!mutante2Defiende && pMutante1.estaVivo()){
            atacar(pMutante2, pMutante1, mutante1Defiende); //ataca al primero si todavía permanece vivo
        }
    }
    private void atacar(Mutante pAtacante, Mutante pDefensor, boolean pDefiende){
        double energiaAntes = pDefensor.getEnergia(); //guarda la energía antes del golpe
        double danio = calcularDanio(pAtacante, pDefensor, pDefiende); //calcula cuánto daño hará el ataque
        aplicarDanio(pDefensor, danio); //aplica el daño real al defensor
        if(pDefensor.getEnergia() < energiaAntes){
            aumentarDanioPoder(pAtacante); //si el defensor perdió energía, el atacante fortalece su poder
        }
    }
}