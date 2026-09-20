package interfaz;

import control.AdministradorCombate;
import control.ControladorBatalla;
import game.CampoBatalla;

public class MainUI {
    public static void main(String[] args) {
        CampoBatalla campoBatalla = new CampoBatalla();
        AdministradorCombate administradorCombate = new AdministradorCombate();
        ControladorBatalla controladorBatalla = new ControladorBatalla(
            campoBatalla, administradorCombate);
        BatallaUI interfaz = new BatallaUI(campoBatalla, controladorBatalla);
        interfaz.iniciarBatalla();
    }
}