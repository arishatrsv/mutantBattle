package game;

import constantes.IConstants;

public class CampoBatalla {
    private Equipo equipo1;
    private Equipo equipo2;
    private Marcador marcador;

    //constructor
    public CampoBatalla(){
        this.marcador = new Marcador(); //el constructor crea solo el marcador porque aun no hay equipos
    }

    public void crearEquipos(int pCantidad){ //crea los dos equipos con la cantidad indicada
        if(pCantidad<IConstants.MIN_TAMANO_EQUIPO||pCantidad>IConstants.MAX_TAMANO_EQUIPO){
            //comprueba que la cantidad esté entre 3 y 11
            return;
        }
        this.equipo1=new Equipo("Rojo", "R");
        this.equipo2=new Equipo("Azul", "A");
    }

    public Equipo getEquipo1(){ //devuelve el equipo 1
        return this.equipo1;
    }

    public Equipo getEquipo2(){ //devuelve el equipo 2
        return this.equipo2;
    }

    public Marcador getMarcador(){ //devuelve el marcador
        return this.marcador;
    }

    public boolean batallaTerminada(){ //indica si uno de los equipos fue eliminado
        return this.equipo1.estaEliminado()||this.equipo2.estaEliminado(); //equipo 1 OR equipo 2 esta eliminado
    }

    public Equipo obtenerGanador(){ //devuelve el equipo ganador
        if(this.equipo1.estaEliminado()){
            return this.equipo2; //el que no esta eliminado
        }
        if(this.equipo2.estaEliminado()){
            return this.equipo1;
        }
        return null; //significa que todavía no existe el ganador
    }
}