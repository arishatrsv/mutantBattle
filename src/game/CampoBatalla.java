package game;

import constantes.IConstants;
import modelo.*;

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
        for(int i=0;i<pCantidad;i++){ //agrega un mutante aleatorio a cada equipo
            this.equipo1.agregarMutante(crearMutante("Rojo"+(i+1)));
            this.equipo2.agregarMutante(crearMutante("Azul"+(i+1)));
        }
        this.marcador.actualizar(this.equipo1,this.equipo2);//actualiza las estadísticas iniciales
    }

    private Mutante crearMutante(String pNombre){ //crea un mutante con valores aleatorios
    byte edad = (byte)(IConstants.MIN_EDAD + Math.random() * (IConstants.MAX_EDAD - IConstants.MIN_EDAD + 1));    int defensa=(int)(Math.random()*
            (IConstants.MAX_DEFENSA-IConstants.MIN_DEFENSA+1))
            +IConstants.MIN_DEFENSA;
    int posicionX=(int)(Math.random()*IConstants.ANCHO_CAMPOBATALLA);
    int posicionY=(int)(Math.random()*IConstants.ALTO_CAMPOBATALLA);

    int danio=(int)(Math.random()*
            (IConstants.MAX_PODER_DANIO-IConstants.MIN_PODER_DANIO+1))
            +IConstants.MIN_PODER_DANIO;
    IPower poder;
    int tipoPoder=(int)(Math.random()*IConstants.CANTIDAD_PODERES); //selecciona aleatoriamente uno de los cinco poderes
    if(tipoPoder==0){
        poder=new PoderHielo(danio);
    }
    else if(tipoPoder==1){
        poder=new PoderRayos(danio);
    }
    else if(tipoPoder==2){
        poder=new PoderTelepatia(danio);
    }
    else if(tipoPoder==3){
        poder=new PoderFuego(danio);
    }
    else{
        poder=new PoderRegeneracion(danio);
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