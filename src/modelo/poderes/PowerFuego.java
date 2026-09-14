package poderes;

public class PowerFuego implements IPower {
    private int danio;

    public PowerFuego(int pDanio){
        this.danio = pDanio;
    }

    @Override
    public void dispararPoder(){
        System.out.println("Dispara Fuego");
    }

    @Override 
    public int getDanio(){
        return this.danio;
    }

    @Override 
    public void aumentarDanio(){
        this.danio ++;
    }
}
