package control;

import game.CampoBatalla;
import game.Equipo;
import modelo.Mutante;
import constantes.IConstants;

import java.util.ArrayList;
import java.util.List;

public class ControladorBatalla{
    private CampoBatalla campoBatalla;
    private AdministradorCombate administradorCombate;
    private List<HiloMutante> hiloMutante;

    //Constructor
    public ControladorBatalla(CampoBatalla pCampoBatalla, 
        AdministradorCombate pAdministradorCombate){
            this.campoBatalla = pCampoBatalla;
            this.administradorCombate = pAdministradorCombate;
            this.hiloMutante = new ArrayList<>();
        }

    public void iniciarJuego(int pCantidad){
        generarEquipos(pCantidad);
        iniciarMovimiento(IConstants.ANCHO_CAMPOBATALLA,IConstants.ALTO_CAMPOBATALLA);
        controlarBatalla();
        finalizarJuego();
    }  
    public void generarEquipos(int pCantidad){
        this.campoBatalla.crearEquipos(pCantidad);
    }
    public void iniciarMovimiento(int pAncho,int pAlto){
        this.hiloMutante.clear();
        for (Mutante mutante: this.campoBatalla.getEquipo1().getMutantes()){
            HiloMutante hilo = new HiloMutante(mutante, 
                this.campoBatalla, this.administradorCombate,pAncho, pAlto);
            this.hiloMutante.add(hilo);
        }
        for (Mutante mutante: this.campoBatalla.getEquipo2().getMutantes()){
            HiloMutante hilo = new HiloMutante(mutante, 
                this.campoBatalla, this.administradorCombate,pAncho, pAlto);
            this.hiloMutante.add(hilo);
        }
        for (HiloMutante hilo: this.hiloMutante){
            hilo.start();
        }
    }
    public void controlarBatalla(){
        for(HiloMutante hilo: this.hiloMutante){
            try{hilo.join();
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
    public void finalizarJuego(){
        for (HiloMutante hilo: this.hiloMutante){
            hilo.detener();
        }
        Equipo ganador= this.campoBatalla.obtenerGanador();
        if (ganador != null){
            System.out.println("La batalla ha terminado");
            System.out.println("El equipo ganador es: "+ ganador.getColor()); 
        }
    }
    public void nuevaBatalla(){
        this.hiloMutante.clear();
        this.campoBatalla= new CampoBatalla();
    }

    public CampoBatalla getCampoBatalla(){
        return campoBatalla;
    }
}