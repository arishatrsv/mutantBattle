package game;

import modelo.Mutante;
import java.util.List;
import java.util.ArrayList;//para crear lista
import java.awt.Image;

public class Equipo {
    private String color;
    private Image simbolo; 
    private List<Mutante> mutantes;//solamente puede contener objetos de tipo Mutante

    //constructor
    public Equipo(String pColor, Image pSimbolo){
        this.color = pColor;
        this.simbolo = pSimbolo;
        this.mutantes = new ArrayList<>(); //crea la lista
    }

    public String getColor(){
        return this.color;
    }

    public void agregarMutante(Mutante pMutante){ //agrega un mutante al equipo
        this.mutantes.add(pMutante);
    }

    public List<Mutante> getMutantes(){ //devuelve la lista de mutantes
        return this.mutantes;
    }

    public int contarVivos(){ //cuenta los mutantes que siguen vivos
        int vivos = 0;
        for(Mutante mutante : this.mutantes){ //recorre todos los mutantes
            if(mutante.estaVivo()){
                vivos++;
            }
        }
        return vivos;
    }

    public int contarMuertos(){ //cuenta los mutantes que han muerto
        int muertos = 0;
        for(Mutante mutante : this.mutantes){ //recorre todos los mutantes
            if(!mutante.estaVivo()){ //si no esta vivo
                muertos++;
            }
        }
        return muertos;
    }

    public boolean estaEliminado(){ //indica si el equipo ya no tiene mutantes vivos
        return this.contarVivos() == 0; //retorna false si al menos un mutante esta vivo
    }
}