package game;

import modelo.Mutante;
import java.util.List;
import java.util.ArrayList;//para crear lista
import java.awt.Image;

public class Equipo {
    private String color; //nombre del equipo, por ejemplo rojo o azul
    private Image simbolo; //imagen que representa al equipo
    private List<Mutante> mutantes;//solamente puede contener objetos de tipo Mutante

    //constructor
    public Equipo(String pColor, Image pSimbolo){
        this.color = pColor; //guarda el nombre del equipo
        this.simbolo = pSimbolo; //guarda la imagen asociada
        this.mutantes = new ArrayList<>(); //crea la lista de mutantes vacía
    }

    public String getColor(){
        return this.color; //devuelve el color del equipo
    }

    public Image getSimbolo(){
        return this.simbolo; //devuelve el símbolo del equipo
    }

    public void setSimbolo(Image pSimbolo){
        this.simbolo = pSimbolo; //actualiza la imagen del equipo
    }

    public void agregarMutante(Mutante pMutante){ //agrega un mutante al equipo
        this.mutantes.add(pMutante); //lo coloca al final de la lista
    }

    public List<Mutante> getMutantes(){ //devuelve la lista de mutantes
        return this.mutantes; //permite recorrer o consultar a todos los integrantes
    }

    public int contarVivos(){ //cuenta los mutantes que siguen vivos
        int vivos = 0; //inicializa el contador
        for(Mutante mutante : this.mutantes){ //recorre todos los mutantes
            if(mutante.estaVivo()){ //si la energía es mayor que cero
                vivos++; //incrementa el número de vivos
            }
        }
        return vivos; //devuelve la cantidad total de mutantes activos
    }

    public int contarMuertos(){ //cuenta los mutantes que han muerto
        int muertos = 0; //inicializa el contador
        for(Mutante mutante : this.mutantes){ //recorre todos los mutantes
            if(!mutante.estaVivo()){ //si no está vivo
                muertos++; //incrementa el número de muertos
            }
        }
        return muertos; //devuelve la cantidad total de fallecidos
    }

    public boolean estaEliminado(){ //indica si el equipo ya no tiene mutantes vivos
        return this.contarVivos() == 0; //retorna true si el equipo está completamente eliminado
    }
}