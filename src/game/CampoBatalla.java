package game;

import constantes.IConstants;
import modelo.*;

public class CampoBatalla {
    private Equipo equipo1; //equipo rojo o primer bando
    private Equipo equipo2; //equipo azul o segundo bando
    private Marcador marcador; //estadísticas actuales de la partida

    //constructor
    public CampoBatalla(){
        this.marcador = new Marcador(); //el constructor crea solo el marcador porque aun no hay equipos
    }

    public void crearEquipos(int pCantidad){ //crea los dos equipos con la cantidad indicada
        if(pCantidad< IConstants.MIN_TAMANO_EQUIPO||pCantidad> IConstants.MAX_TAMANO_EQUIPO){
            //comprueba que la cantidad esté entre 3 y 11
            return;
        }
        this.equipo1=new Equipo("Rojo", null); //inicializa el primer equipo con el color rojo
        this.equipo2=new Equipo("Azul", null); //inicializa el segundo equipo con el color azul
        for(int i=0;i<pCantidad;i++){ //agrega un mutante aleatorio a cada equipo
            this.equipo1.agregarMutante(crearMutante("Rojo"+(i+1))); //crea y agrega un mutante rojo
            this.equipo2.agregarMutante(crearMutante("Azul"+(i+1))); //crea y agrega un mutante azul
        }
        this.marcador.actualizar(this.equipo1,this.equipo2);//actualiza las estadísticas iniciales
    }

    private Mutante crearMutante(String pNombre){ //crea un mutante con valores aleatorios
    byte edad = (byte)(IConstants.MIN_EDAD + Math.random() * (IConstants.MAX_EDAD - IConstants.MIN_EDAD + 1)); //edad aleatoria dentro del rango permitido
    int defensa=(int)(Math.random()*
            (IConstants.MAX_DEFENSA-IConstants.MIN_DEFENSA+1))
            +IConstants.MIN_DEFENSA; //defensa aleatoria entre 1 y 3
    int posicionX=(int)(Math.random()*IConstants.ANCHO_CAMPOBATALLA); //posición X aleatoria en el ancho del campo
    int posicionY=(int)(Math.random()*IConstants.ALTO_CAMPOBATALLA); //posición Y aleatoria en la altura del campo

    int danio=(int)(Math.random()*
            (IConstants.MAX_PODER_DANIO-IConstants.MIN_PODER_DANIO+1))
            +IConstants.MIN_PODER_DANIO; //daño base aleatorio del poder
    IPower poder;
    int tipoPoder=(int)(Math.random()*IConstants.CANTIDAD_PODERES); //selecciona aleatoriamente uno de los cinco poderes
    if(tipoPoder==0){
        poder=new PoderHielo(danio); //si obtiene 0, usa hielo
    }
    else if(tipoPoder==1){
        poder=new PoderRayos(danio); //si obtiene 1, usa rayos
    }
    else if(tipoPoder==2){
        poder=new PoderTelepatia(danio); //si obtiene 2, usa telepatía
    }
    else if(tipoPoder==3){
        poder=new PoderFuego(danio); //si obtiene 3, usa fuego
    }
    else{
        poder=new PoderRegeneracion(danio); //si obtiene 4, usa regeneración
    }
    return new Mutante( //crea el mutante con los valores generados
            pNombre,
            edad,
            IConstants.INICIAL_ENERGIA,
            defensa,
            posicionX,
            posicionY,
            poder
    );
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
        if(equipo1 == null || equipo2 == null){
            return false; //todavía no hay partida inicializada
        }
        return this.equipo1.estaEliminado()||this.equipo2.estaEliminado(); //equipo 1 OR equipo 2 esta eliminado
    }

    public Equipo obtenerGanador(){ //devuelve el equipo ganador
        if(this.equipo1.estaEliminado()){
            return this.equipo2; //el que no está eliminado es el ganador
        }
        if(this.equipo2.estaEliminado()){
            return this.equipo1; //el que no está eliminado es el ganador
        }
        return null; //significa que todavía no existe el ganador
    }
}