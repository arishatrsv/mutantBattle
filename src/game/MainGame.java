package game;

public class MainGame {
    public static void main(String[] args){
        System.out.println("-------PRUEBA CAPA GAME------"); //inicia la prueba de la lógica del juego
        CampoBatalla campoBatalla = new CampoBatalla(); //crea el escenario principal
        campoBatalla.crearEquipos(3); //Crear dos equipos de 3 mutantes
        Equipo equipo1 = campoBatalla.getEquipo1(); //guarda el primer equipo
        Equipo equipo2 = campoBatalla.getEquipo2(); //guarda el segundo equipo
        System.out.println("\n--- Equipos creados ---"); //muestra el inicio de la creación
        System.out.println("Equipo 1: " + equipo1.getColor()); //imprime el nombre del equipo rojo
        System.out.println("Mutantes: " + equipo1.getMutantes().size()); //muestra cuántos mutantes hay en el equipo 1
        System.out.println("Equipo 2: " + equipo2.getColor()); //imprime el nombre del equipo azul
        System.out.println("Mutantes: " + equipo2.getMutantes().size()); //muestra cuántos mutantes hay en el equipo 2

        System.out.println("\n----- Marcador inicial -----"); //muestra el estado inicial de la partida
        System.out.println("Equipo 1 vivos: " + campoBatalla.getMarcador().getVivosEquipo1()); //vivos del equipo 1
        System.out.println("Equipo 1 muertos: " + campoBatalla.getMarcador().getMuertosEquipo1()); //muertos del equipo 1
        System.out.println("Equipo 2 vivos: " + campoBatalla.getMarcador().getVivosEquipo2()); //vivos del equipo 2
        System.out.println("Equipo 2 muertos: " + campoBatalla.getMarcador().getMuertosEquipo2()); //muertos del equipo 2

        System.out.println("\n¿La batalla terminó?: " + campoBatalla.batallaTerminada()); //verifica si ya hubo un ganador
        System.out.println("\n------FIN PRUEBA GAME------"); //finaliza la prueba de la capa game
    }
}
