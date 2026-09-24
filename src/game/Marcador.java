package game;

import constantes.IConstants;

public class Marcador { //guarda las estadisticas de los equipos
    private int[] estadisticas; //arreglo con los contadores de vivos y muertos para ambos equipos

    //constructor
    public Marcador(){
        this.estadisticas = new int[4]; //por las 4 constantes que ya tenemos, arreglo de 4 posiciones
    } //se crea asi: [0,0,0,0]

    public void actualizar(Equipo pEquipo1, Equipo pEquipo2){ //actualiza las estadísticas de los dos equipos
        this.estadisticas[IConstants.VIVOS_EQUIPO1] = pEquipo1.contarVivos(); //cantidad actual de vivos del equipo 1
        this.estadisticas[IConstants.MUERTOS_EQUIPO1] = pEquipo1.contarMuertos(); //cantidad actual de muertos del equipo 1
        this.estadisticas[IConstants.VIVOS_EQUIPO2] = pEquipo2.contarVivos(); //cantidad actual de vivos del equipo 2
        this.estadisticas[IConstants.MUERTOS_EQUIPO2] = pEquipo2.contarMuertos(); //cantidad actual de muertos del equipo 2
    }

    public int getVivosEquipo1(){ //devuelve los vivos del equipo 1
        return this.estadisticas[IConstants.VIVOS_EQUIPO1]; //consulta la posición 0 del arreglo
    }

    public int getMuertosEquipo1(){ //devuelve los muertos del equipo 1
        return this.estadisticas[IConstants.MUERTOS_EQUIPO1]; //consulta la posición 1 del arreglo
    }

    public int getVivosEquipo2(){ //devuelve los vivos del equipo 2
        return this.estadisticas[IConstants.VIVOS_EQUIPO2]; //consulta la posición 2 del arreglo
    }

    public int getMuertosEquipo2(){ //devuelve los muertos del equipo 2
        return this.estadisticas[IConstants.MUERTOS_EQUIPO2]; //consulta la posición 3 del arreglo
    }
}