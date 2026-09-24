package interfaz;

import control.AdministradorCombate;
import control.ControladorBatalla;
import game.CampoBatalla;

public class MainUI {
    public static void main(String[] args) {
        CampoBatalla campoBatalla = new CampoBatalla(); //crea el escenario base del juego
        AdministradorCombate administradorCombate = new AdministradorCombate(); //inicializa la lógica de combate
        ControladorBatalla controladorBatalla = new ControladorBatalla(
            campoBatalla, administradorCombate); //conecta el campo con el controlador
        BatallaUI interfaz = new BatallaUI(campoBatalla, controladorBatalla); //abre la ventana principal de la batalla
        interfaz.iniciarBatalla(); //solicita la cantidad de mutantes y empieza la partida
    }
}