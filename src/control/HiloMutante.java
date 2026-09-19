package control;

import game.CampoBatalla;
import game.Equipo;
import modelo.Mutante;
import java.util.ArrayList;
import java.util.List;
import constantes.IConstants;

public class HiloMutante extends Thread {
    private Mutante mutante;
    private CampoBatalla campoBatalla;
    private AdministradorCombate administradorCombate;
    private boolean activo;

    public HiloMutante( //constructor
            Mutante pMutante,
            CampoBatalla pCampoBatalla,
            AdministradorCombate pAdministradorCombate){
        this.mutante = pMutante;
        this.campoBatalla = pCampoBatalla;
        this.administradorCombate = pAdministradorCombate;
        this.activo = true;
    }

    @Override
    public void run(){ //inicia el hilo
        correr();
    }

    public void correr(){ //controla el movimiento y los encuentros
        while(this.activo && this.mutante.estaVivo() && !this.campoBatalla.batallaTerminada()){
            this.mutante.mover(IConstants.ANCHO_CAMPOBATALLA,IConstants.ALTO_CAMPOBATALLA);
            List<Mutante> enemigos = detectarEnemigos();
            for(Mutante enemigo : enemigos){
                if(enemigo.estaVivo()){
                    this.administradorCombate.ejecutarEncuentro(this.mutante, enemigo);
                }
            }
        }
    }

    public void detener(){ //detiene el hilo
        this.activo = false;
    }

    public List<Mutante> detectarEnemigos(){ //busca enemigos dentro del radio de encuentro
        List<Mutante> enemigos = new ArrayList<>();
        Equipo equipoEnemigo;
        if(this.campoBatalla.getEquipo1().getMutantes().contains(this.mutante)){
            equipoEnemigo= this.campoBatalla.getEquipo2();
        }else{
            equipoEnemigo= this.campoBatalla.getEquipo1();
        }    
        for(Mutante enemigo : equipoEnemigo.getMutantes()){
            if (enemigo.estaVivo() && this.administradorCombate.detectarEncuentro(
                this.mutante,enemigo)) {
                enemigos.add(enemigo);   
            }
        }
        return enemigos;
    }
}