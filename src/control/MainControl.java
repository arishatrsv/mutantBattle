package control;

import game.CampoBatalla;

public class MainControl {
    public static void main(String[] args){
        System.out.println("------PRUEBA CAPA CONTROL------"); //inicia la ejecución de prueba del controlador
        CampoBatalla campoBatalla = new CampoBatalla(); //crea el escenario de batalla
        AdministradorCombate administradorCombate = new AdministradorCombate(); //crea el gestor de combates
        ControladorBatalla controladorBatalla = new ControladorBatalla(campoBatalla, administradorCombate); //une el campo con la lógica del juego

        System.out.println("Iniciar Batalla de prueba"); //muestra el comienzo de la prueba
        controladorBatalla.iniciarJuego(3); //ejecuta una batalla con tres mutantes por equipo
        if (campoBatalla.obtenerGanador() != null){
            System.out.println("Ganador: "+ campoBatalla.obtenerGanador().getColor()); //muestra el equipo vencedor
        }
        System.out.println("------FIN PRUEBA CONTROL------"); //cierra la prueba de la capa control
    }
}
