package poderes;

public class PowerTelepatia implements IPower {
    private int danio;

    public PowerTelepatia(int pDanio){
        this.danio = pDanio;
    }

    @Override
    public void dispararPoder(){
        System.out.println("Utiliza telepatía");
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