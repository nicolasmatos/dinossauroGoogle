public class Sprite extends DesenhoMovel {

    private int cena = 0;
    private int controlaVelocidade = 0;

    public Sprite(){
        super();
    }

    public Sprite(int numeroDeCenas, int x, int y, int vel){
        super(numeroDeCenas, x, y, vel);
    }

    public void animar(){
        cena += 1;
        if(cena == getImagens().length){ cena = 0; }
    }

    public void animarMaisLento(){
        controlaVelocidade+=1;
        if(controlaVelocidade>getVelocidade()){
            cena += 1;
            controlaVelocidade = 0;
            if(cena == getImagens().length){ cena = 0; }
        }
    }

    public int getCena() {
        return cena;
    }
    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getControlaVelocidade() {
        return controlaVelocidade;
    }
    public void setControlaVelocidade(int controlaVelocidade) {
        this.controlaVelocidade = controlaVelocidade;
    }
}
