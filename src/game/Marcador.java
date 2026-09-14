package game;

import constantes.IConstants;

public class Marcador { //guarda las estadisticas de los equipos
    private int[] estadisticas;

    //constructor
    public Marcador(){
        this.estadisticas = new int[4]; //por las 4 constantes que ya tenemos, arreglo de 4 posiciones
    } //se crea asi: [0,0,0,0]

    public void actualizar(Equipo pEquipo1, Equipo pEquipo2){ //actualiza las estadísticas de los dos equipos
        this.estadisticas[IConstants.VIVOS_EQUIPO1] = pEquipo1.contarVivos();
        this.estadisticas[IConstants.MUERTOS_EQUIPO1] = pEquipo1.contarMuertos();
        this.estadisticas[IConstants.VIVOS_EQUIPO2] = pEquipo2.contarVivos();
        this.estadisticas[IConstants.MUERTOS_EQUIPO2] = pEquipo2.contarMuertos();
    }

    public int getVivosEquipo1(){ //devuelve los vivos del equipo 1
        return this.estadisticas[IConstants.VIVOS_EQUIPO1];
    }

    public int getMuertosEquipo1(){ //devuelve los muertos del equipo 1
        return this.estadisticas[IConstants.MUERTOS_EQUIPO1];
    }

    public int getVivosEquipo2(){ //devuelve los vivos del equipo 2
        return this.estadisticas[IConstants.VIVOS_EQUIPO2];
    }

    public int getMuertosEquipo2(){ //devuelve los muertos del equipo 2
        return this.estadisticas[IConstants.MUERTOS_EQUIPO2];
    }
}