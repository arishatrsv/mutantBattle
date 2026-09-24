package control;

import game.CampoBatalla;
import game.Equipo;
import modelo.Mutante;
import constantes.IConstants;

import java.util.ArrayList;
import java.util.List;

public class ControladorBatalla{
    private CampoBatalla campoBatalla; //referencia al entorno donde ocurre la batalla
    private AdministradorCombate administradorCombate; //encargado de calcular ataques y defensas
    private List<HiloMutante> hiloMutante; //lista de hilos que mueven a cada mutante

    //Constructor
    public ControladorBatalla(CampoBatalla pCampoBatalla, 
        AdministradorCombate pAdministradorCombate){
            this.campoBatalla = pCampoBatalla; //guarda el campo de batalla recibido
            this.administradorCombate = pAdministradorCombate; //guarda la lógica de combate
            this.hiloMutante = new ArrayList<>(); //crea la lista vacía de hilos
        }

    public void iniciarJuego(int pCantidad){
        generarEquipos(pCantidad); //crea los equipos con la cantidad indicada
        iniciarMovimiento(IConstants.ANCHO_CAMPOBATALLA,IConstants.ALTO_CAMPOBATALLA); //lanza los movimientos
        controlarBatalla(); //espera a que cada hilo termine
        finalizarJuego(); //detiene los hilos y muestra el ganador
    }  
    public void generarEquipos(int pCantidad){
        this.campoBatalla.crearEquipos(pCantidad); //delegación a la capa de juego
    }
    public void iniciarMovimiento(int pAncho,int pAlto){
        this.hiloMutante.clear(); //reinicia la lista antes de crear nuevos hilos
        for (Mutante mutante: this.campoBatalla.getEquipo1().getMutantes()){
            HiloMutante hilo = new HiloMutante(mutante, 
                this.campoBatalla, this.administradorCombate,pAncho, pAlto); //crea un hilo por mutante del equipo 1
            this.hiloMutante.add(hilo); //guarda el hilo para luego controlarlo
        }
        for (Mutante mutante: this.campoBatalla.getEquipo2().getMutantes()){
            HiloMutante hilo = new HiloMutante(mutante, 
                this.campoBatalla, this.administradorCombate,pAncho, pAlto); //crea un hilo por mutante del equipo 2
            this.hiloMutante.add(hilo); //lo agrega a la colección
        }
        for (HiloMutante hilo: this.hiloMutante){
            hilo.start(); //inicia cada hilo de movimiento y ataque
        }
    }
    public void controlarBatalla(){
        for(HiloMutante hilo: this.hiloMutante){
            try{hilo.join(); //espera a que cada hilo termine antes de seguir
            }catch (InterruptedException e){
                Thread.currentThread().interrupt(); //restablece la interrupción si hubo error
            }
        }
    }
    public void finalizarJuego(){
        for (HiloMutante hilo: this.hiloMutante){
            hilo.detener(); //detiene todos los hilos del juego
        }
        Equipo ganador= this.campoBatalla.obtenerGanador(); //consulta quién obtuvo la victoria
        if (ganador != null){
            System.out.println("La batalla ha terminado");
            System.out.println("El equipo ganador es: "+ ganador.getColor()); 
        }
    }
    public void nuevaBatalla(){
        this.hiloMutante.clear(); //limpia los hilos de la partida actual
        this.campoBatalla= new CampoBatalla(); //crea un nuevo campo listo para otra ronda
    }

    public CampoBatalla getCampoBatalla(){
        return campoBatalla; //devuelve el campo actual del juego
    }
}