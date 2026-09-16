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

    public void ejecutarEncuentro(Mutante pAtacante, Mutante pDefensor){ //ejecuta un ataque entre dos mutantes
        if(!pAtacante.estaVivo() || !pDefensor.estaVivo()){
            return; //no ejecuta el encuentro si alguno ya está muerto
        }
        boolean seDefiende = decidirDefensa(); //el defensor decide si se defiende
        double energiaAntes = pDefensor.getEnergia(); //guarda la energía antes del ataque
        double danio = calcularDanio(pAtacante, pDefensor, seDefiende); //calcula el daño
        aplicarDanio(pDefensor, danio); //aplica el daño al defensor
        if(pDefensor.getEnergia() < energiaAntes){
            aumentarDanioPoder(pAtacante); //aumenta el poder si el ataque redujo la energía
        }
    }
}