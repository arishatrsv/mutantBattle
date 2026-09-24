package game;

public class MainGame {
    public static void main(String[] args){
        System.out.println("-------PRUEBA CAPA GAME------");
        CampoBatalla campoBatalla = new CampoBatalla();
        campoBatalla.crearEquipos(3); //Crear dos equipos de 3 mutantes
        Equipo equipo1 = campoBatalla.getEquipo1();
        Equipo equipo2 = campoBatalla.getEquipo2();
        System.out.println("\n--- Equipos creados ---");
        System.out.println("Equipo 1: " + equipo1.getColor());
        System.out.println("Mutantes: " + equipo1.getMutantes().size());
        System.out.println("Equipo 2: " + equipo2.getColor());
        System.out.println("Mutantes: " + equipo2.getMutantes().size());

        System.out.println("\n----- Marcador inicial -----");
        System.out.println("Equipo 1 vivos: " + campoBatalla.getMarcador().getVivosEquipo1());
        System.out.println("Equipo 1 muertos: " + campoBatalla.getMarcador().getMuertosEquipo1());
        System.out.println("Equipo 2 vivos: " + campoBatalla.getMarcador().getVivosEquipo2());
        System.out.println("Equipo 2 muertos: " + campoBatalla.getMarcador().getMuertosEquipo2());

        System.out.println("\n¿La batalla terminó?: " + campoBatalla.batallaTerminada());
        System.out.println("\n------FIN PRUEBA GAME------");
    }
}
