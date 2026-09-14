//Constructor: es el método que inicializa un objeto(instancia)
//java y en general los lenguales de POO, distinguen las firmas de los métodos
//por el nombre del método y el orden de los tipos de datos, NO DEL NOMBRE DE LOS PARÁMETROS,
//si no del DataType

//Desarrollado por:Hilary Aguilar y Arina Tarasova

package modelo;


public class Persona{
    private byte edad; // cuando no se especifica la visibilidad, el default es protected
    protected String nombre;

    //Constructor con parámetros
    public Persona(String pNombre, byte pEdad){
        this.edad = pEdad;
        this.nombre = pNombre;
    }

    // métodos get para leer los valores de los atributos de la instancia
    public String getNombre(){
        return this.nombre;
    }

    public byte getEdad(){
        return this.edad;
    }

    // métodos set para modificar los valores de los atributos de la instancia
    public void setNombre(String pNombre){
        this.nombre= pNombre;
    }
    public void setEdad(byte pEdad){
        this.edad= pEdad;
    }
}