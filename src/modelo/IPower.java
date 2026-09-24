package modelo;

public interface IPower {
    void dispararPoder(); //ejecuta la habilidad especial del poder
    int getDanio();  //devuelve el daño actual del poder
    void aumentarDanio(); //aumenta el daño del poder cuando el mutante gana fuerza
}