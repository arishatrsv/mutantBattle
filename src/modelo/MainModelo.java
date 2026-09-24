package modelo;

public class MainModelo {
    public static void main(String[] args){
        System.out.println("----- PRUEBA CAPA MODELO -----");

        IPower poder = new PoderFuego(3); //Crear el poder
        System.out.println("Poder creado: Fuego");
        System.out.println("Daño inicial: " + poder.getDanio());

        poder.aumentarDanio(); //Probar aumento del poder
        System.out.println("Daño después de aumentar: "+ poder.getDanio());

        Mutante mutante = new Mutante("Mutante Prueba", //Crear un mutante
          (byte) 20, 100, 2, 100, 100, poder);

        System.out.println("\n--- Datos del mutante ---");
        System.out.println("Nombre: " + mutante.getNombre());
        System.out.println("Edad: " + mutante.getEdad());
        System.out.println("Energía: " + mutante.getEnergia());
        System.out.println("Defensa: " + mutante.getDefensa());
        System.out.println("Daño del poder: " + mutante.getPoder().getDanio());
        System.out.println("Está vivo: " + mutante.estaVivo());

        System.out.println("\n--- Movimiento ---"); // Probar movimiento
        System.out.println("Posición inicial: (" +
                mutante.getPosicionX() + ", " +
                mutante.getPosicionY() + ")");
        mutante.mover(800, 450);
        System.out.println("Posición después de mover: (" +
                mutante.getPosicionX() + ", " + mutante.getPosicionY() + ")");

          
        System.out.println("\n--- Daño ---"); // Probar daño
        mutante.recibirDanio(20);
        System.out.println("Energía después de recibir 20 de daño: " + mutante.getEnergia());
        System.out.println("Está vivo: " + mutante.estaVivo());

        
        System.out.println("\n--- Prueba de daño máximo ---"); // Probar aumento hasta el máximo
        while (mutante.getPoder().getDanio() < 7) {
            mutante.getPoder().aumentarDanio();
        }
        System.out.println("Daño máximo alcanzado: " + mutante.getPoder().getDanio());

        // Intentar superar el máximo
        mutante.getPoder().aumentarDanio();
        System.out.println("Después de intentar aumentar otra vez: " + mutante.getPoder().getDanio());
        System.out.println("\n------FIN PRUEBA MODELO ------");
    }
}
