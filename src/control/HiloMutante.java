package control;

import game.CampoBatalla;
import game.Equipo;
import modelo.Mutante;
import java.util.ArrayList;
import java.util.List;
import constantes.IConstants;

public class HiloMutante extends Thread {
    private Mutante mutante; //mutante que este hilo controla
    private CampoBatalla campoBatalla; //referencia al campo global
    private AdministradorCombate administradorCombate; //reglas de ataque y defensa
    private boolean activo; //estado del hilo
    private int ancho; //ancho del campo de movimiento
    private int alto; //alto del campo de movimiento

    public HiloMutante( //constructor
            Mutante pMutante,
            CampoBatalla pCampoBatalla,
            AdministradorCombate pAdministradorCombate,
            int pAncho,
            int pAlto){
        this.mutante = pMutante; //asigna el mutante que se moverá
        this.campoBatalla = pCampoBatalla; //guarda el tablero actual
        this.administradorCombate = pAdministradorCombate; //guarda la lógica de combate
        this.ancho = pAncho; //guarda el ancho del campo
        this.alto = pAlto; //guarda el alto del campo
        this.activo = true; //el hilo empieza activo
    }

    @Override
    public void run(){ //inicia el hilo
        correr(); //ejecuta el ciclo de movimiento y ataques
    }

    public void correr(){ //controla el movimiento y los encuentros
        while(this.activo && this.mutante.estaVivo() && !this.campoBatalla.batallaTerminada()){
            this.mutante.mover(this.ancho,this.alto); //mueve al mutante dentro del campo
            List<Mutante> enemigos = detectarEnemigos(); //detecta enemigos dentro del radio
            for(Mutante enemigo : enemigos){ 
                if(enemigo.estaVivo()){ //solo ataca si el enemigo aún sigue vivo
                    this.administradorCombate.ejecutarEncuentro(this.mutante, enemigo); //resuelve el ataque entre ambos
                    this.campoBatalla.getMarcador().actualizar(
                        this.campoBatalla.getEquipo1(), this.campoBatalla.getEquipo2()); //refresca vidas y muertos
                }
            }
            try{
                Thread.sleep(IConstants.ACTUALIZACION_UI); //pausa la ejecución para dar tiempo a la vista
            } catch (InterruptedException e){
                Thread.currentThread().interrupt(); //si hubo interrupción, cierra el hilo
                this.activo = false;
            }
        }
    }

    public void detener(){ //detiene el hilo
        this.activo = false; //marca el hilo como inactivo para salir del ciclo
    }

    public List<Mutante> detectarEnemigos(){ //busca enemigos dentro del radio de encuentro
        List<Mutante> enemigos = new ArrayList<>(); //lista temporal de adversarios detectados
        Equipo equipoEnemigo;
        if(this.campoBatalla.getEquipo1().getMutantes().contains(this.mutante)){
            equipoEnemigo= this.campoBatalla.getEquipo2(); //si es del equipo 1, su enemigo es el equipo 2
        }else{
            equipoEnemigo= this.campoBatalla.getEquipo1(); //si es del equipo 2, su enemigo es el equipo 1
        }    
        for(Mutante enemigo : equipoEnemigo.getMutantes()){
            if (enemigo.estaVivo() && this.administradorCombate.detectarEncuentro(
                this.mutante,enemigo)) { //comprueba si está cerca y vivo
                enemigos.add(enemigo); //agrega a la lista de atacantes posibles
            }
        }
        return enemigos; //devuelve los enemigos dentro del radio
    }
}