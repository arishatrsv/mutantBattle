package poderes;

public class PowerHielo implements IPower {
    private int danio;

    public PowerHielo(int pDanio){
        this.danio = pDanio;
    }

    @Override
    public void dispararPoder(){
        System.out.println("Dispara hielooo");
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
