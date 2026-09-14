package poderes;

public class PowerRayos implements IPower {
    private int danio;

    public PowerRayos(int pDanio){
        this.danio = pDanio;
    }

    @Override
    public void dispararPoder(){
        System.out.println("Dispara rayoss");
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
