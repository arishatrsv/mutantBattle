package control;

import game.CampoBatalla;

public class MainControl {
    public static void main(String[] args){
        System.out.println("------PRUEBA CAPA CONTROL------");
        CampoBatalla campoBatalla = new CampoBatalla();
        AdministradorCombate administradorCombate = new AdministradorCombate();
        ControladorBatalla controladorBatalla = new ControladorBatalla(campoBatalla, administradorCombate);

        System.out.println("Iniciar Batalla de prueba");
        controladorBatalla.iniciarJuego(3);
        if (campoBatalla.obtenerGanador() != null){
            System.out.println("Ganador: "+ campoBatalla.obtenerGanador().getColor());
        }
        System.out.println("------FIN PRUEBA CONTROL------");
    }
}
