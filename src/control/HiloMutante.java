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
    private List<Mutante> enemigosAnteriores;  // Guarda los enemigos que estaban dentro del radio en la iteración anterior

    public HiloMutante( //constructor
            Mutante pMutante,
            CampoBatalla pCampoBatalla,
            AdministradorCombate pAdministradorCombate){
        this.mutante = pMutante;
        this.campoBatalla = pCampoBatalla;
        this.administradorCombate = pAdministradorCombate;
        this.activo = true;
        this.enemigosAnteriores= new ArrayList<>();
    }

    @Override
    public void run(){ //inicia el hilo
        correr();
    }

    public void correr(){ //controla el movimiento y los encuentros
        while(this.activo && this.mutante.estaVivo() && !this.campoBatalla.batallaTerminada()){
            this.mutante.mover(IConstants.ANCHO_CAMPOBATALLA,IConstants.ALTO_CAMPOBATALLA); //Mueve al mutante
            List<Mutante> enemigos = detectarEnemigos(); //detecta enemigos dentro del radio
            for(Mutante enemigo : enemigos){ //Busca enemigos dentro del radio
                // El encuentro ocurre solamente cuando entra al radio y no estaba dentro anteriormente
                if(!this.enemigosAnteriores.contains(enemigo)&&
                    enemigo.estaVivo()&& esResponsableDelEncuentro(enemigo)){ 
                    this.administradorCombate.ejecutarEncuentro(this.mutante, enemigo);
                    this.campoBatalla.getMarcador().actualizar(
                        this.campoBatalla.getEquipo1(), this.campoBatalla.getEquipo2());
                }
            }
            this.enemigosAnteriores = enemigosActuales; // Guarda los enemigos que están actualmente cerca
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
    private boolean esResponsableDelEncuentro(Mutante pEnemigo) {
        return this.mutante.getNombre().compareTo(pEnemigo.getNombre()) < 0;
    }
}