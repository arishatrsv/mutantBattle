package constantes;

public interface IConstants {
    int INICIAL_ENERGIA = 100; //energía base con la que inicia cada mutante
    int MIN_TAMANO_EQUIPO = 3; //mínimo de mutantes por equipo
    int MAX_TAMANO_EQUIPO = 11; //máximo de mutantes por equipo
    int MIN_DEFENSA = 1; //defensa mínima posible
    int MAX_DEFENSA = 3; //defensa máxima posible
    double PROBABILIDAD_DEFENSA = 0.5; //50% de probabilidad de defenderse
    int MIN_PODER_DANIO = 1; //daño mínimo del poder
    int MAX_PODER_DANIO = 3; //daño máximo inicial del poder
    int DANIO_AUMENTA = 1; //cada aumento del poder suma 1 de daño
    int MAX_DANIO_PODER= 7; //tope máximo de daño que puede alcanzar un poder
    int CANTIDAD_PODERES = 5; //cantidad de tipos de poder disponibles
    int MIN_EDAD = 18; //edad mínima del mutante
    int MAX_EDAD = 60; //edad máxima del mutante
    int MISMA_VELOCIDAD = 6; //cantidad de píxeles que avanza en cada paso
    double ENCUENTRO_RADIO = 30.0; //distancia máxima para considerar que hubo encuentro
    int ALTO_VENTANA=600; //altura total de la ventana principal
    int ANCHO_VENTANA=800; //ancho total de la ventana principal
    int ALTO_CAMPOBATALLA = 600; //altura del área de batalla
    int ANCHO_CAMPOBATALLA = 800; //ancho del área de batalla
    int VIVOS_EQUIPO1 = 0; //posición del contador de vivos del equipo rojo
    int MUERTOS_EQUIPO1 = 1; //posición del contador de muertos del equipo rojo
    int VIVOS_EQUIPO2 = 2; //posición del contador de vivos del equipo azul
    int MUERTOS_EQUIPO2 = 3; //posición del contador de muertos del equipo azul
    int ACTUALIZACION_UI = 50; //la interfaz se actualiza cada 50 milisegundos
    int ALTO_ESTADISTICAS=70; //altura reservada para la barra superior con estadísticas
    int TAMANO_MUTANTE = 35; //tamaño visible del avatar del mutante
}
