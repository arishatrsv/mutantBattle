package modelo;

public class MainModelo {
    public static void main(String[] args){
        System.out.println("----- PRUEBA CAPA MODELO -----"); //inicia la verificación de la lógica del modelo

        IPower poder = new PoderFuego(3); //Crear el poder
        System.out.println("Poder creado: Fuego"); //muestra el tipo de poder creado
        System.out.println("Daño inicial: " + poder.getDanio()); //muestra el daño base del poder

        poder.aumentarDanio(); //Probar aumento del poder
        System.out.println("Daño después de aumentar: "+ poder.getDanio()); //verifica la subida del daño

        Mutante mutante = new Mutante("Mutante Prueba", //Crear un mutante
            (byte) 20, 100, 2, 100, 100, poder); //da nombre, edad, energía, defensa y posición inicial

        System.out.println("\n--- Datos del mutante ---"); //muestra el bloque de información del personaje
        System.out.println("Nombre: " + mutante.getNombre()); //muestra su nombre
        System.out.println("Edad: " + mutante.getEdad()); //muestra la edad
        System.out.println("Energía: " + mutante.getEnergia()); //muestra la energía actual
        System.out.println("Defensa: " + mutante.getDefensa()); //muestra la defensa del mutante
        System.out.println("Daño del poder: " + mutante.getPoder().getDanio()); //muestra el daño de su poder
        System.out.println("Está vivo: " + mutante.estaVivo()); //verifica si la energía sigue siendo positiva

        System.out.println("\n--- Movimiento ---"); // Probar movimiento
        System.out.println("Posición inicial: (" +
                mutante.getPosicionX() + ", " +
                mutante.getPosicionY() + ")"); //imprime la posición antes de moverse
        mutante.mover(800, 450); //ejecuta un movimiento dentro del campo
        System.out.println("Posición después de mover: (" +
                mutante.getPosicionX() + ", " + mutante.getPosicionY() + ")"); //imprime la nueva posición

        
        System.out.println("\n--- Daño ---"); // Probar daño
        mutante.recibirDanio(20); //simulate recibir daño en combate
        System.out.println("Energía después de recibir 20 de daño: " + mutante.getEnergia()); //muestra energía restante
        System.out.println("Está vivo: " + mutante.estaVivo()); //verifica si sigue en pie

        
        System.out.println("\n--- Prueba de daño máximo ---"); // Probar aumento hasta el máximo
        while (mutante.getPoder().getDanio() < 7) {
            mutante.getPoder().aumentarDanio(); //sube el daño mientras no alcance el límite
        }
        System.out.println("Daño máximo alcanzado: " + mutante.getPoder().getDanio()); //muestra el tope final

        // Intentar superar el máximo
        mutante.getPoder().aumentarDanio();
        System.out.println("Después de intentar aumentar otra vez: " + mutante.getPoder().getDanio()); //confirma que el poder no supera el máximo
        System.out.println("\n------FIN PRUEBA MODELO ------"); //termina la prueba del modelo
    }
}
