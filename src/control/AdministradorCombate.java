package control;

import constantes.IConstants;
import modelo.Mutante;

public class AdministradorCombate {
    private double radioEncuentro;

    //constructor
    public AdministradorCombate(){
        this.radioEncuentro = IConstants.ENCUENTRO_RADIO; //define la distancia para detectar un encuentro
    }

    public boolean detectarEncuentro(Mutante pMutante1, Mutante pMutante2){ //comprueba si dos mutantes están suficientemente cerca
        double diferenciaX = pMutante1.getPosicionX() - pMutante2.getPosicionX();
        double diferenciaY = pMutante1.getPosicionY() - pMutante2.getPosicionY();
        double distancia = Math.sqrt(diferenciaX * diferenciaX + diferenciaY * diferenciaY);
        return distancia <= this.radioEncuentro; //indica si están dentro del radio de encuentro
    }
    public boolean decidirDefensa(){ //decide aleatoriamente si el mutante se defiende
        return Math.random() < 0.5; //true significa que se defiende, 0,5 para que sea 50% probabilidad
    }
    public double calcularDanio(Mutante pAtacante, Mutante pDefensor, boolean pDefiende){ //calcula el daño que recibe el defensor
        int danio = pAtacante.getPoder().getDanio();
        if(pDefiende){
            return (double)danio / pDefensor.getDefensa(); //si se defiende, el daño se divide entre su defensa
        }
        return danio; //si no se defiende, recibe todo el daño
    }
    public void aplicarDanio(Mutante pDefensor, double pDanio){ //aplica el daño al defensor
        pDefensor.recibirDanio(pDanio);
    }
    public void aumentarDanioPoder(Mutante pAtacante){ //aumenta el daño del poder del atacante
        pAtacante.getPoder().aumentarDanio();
    }

    public void ejecutarEncuentro(Mutante pMutante1, Mutante pMutante2){ //ejecuta un ataque entre dos mutantes
        if(!pMutante1.estaVivo() || !pMutante2.estaVivo()){
            return; //no ejecuta el encuentro si alguno ya está muerto
        }
        boolean mutante1Defiende = decidirDefensa();
        boolean mutante2Defiende = decidirDefensa();
        if(!mutante1Defiende){
            atacar(pMutante1, pMutante2, mutante2Defiende);
        }
        if(!mutante2Defiende && pMutante1.estaVivo()){
            atacar(pMutante2, pMutante1, mutante1Defiende);
        }
    }
    private void atacar(Mutante pAtacante, Mutante pDefensor, boolean pDefiende){
        double energiaAntes = pDefensor.getEnergia();
        double danio = calcularDanio(pAtacante, pDefensor, pDefiende);
        aplicarDanio(pDefensor, danio);
        if(pDefensor.getEnergia() < energiaAntes){
            aumentarDanioPoder(pAtacante);
        }
    }
}