package poderes;

public class PowerRegeneracion implements IPower {
    private int danio;

    public PowerRegeneracion(int pDanio){
        this.danio = pDanio;
    }

    @Override
    public void dispararPoder(){
        System.out.println("Utiliza regeneración");
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
