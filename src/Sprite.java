import java.awt.*;

public class Sprite extends DesenhoMovel {

    private int cena = 0;
    private int controlaVelocidade = 0;
    private int pontuacao = 0;
    private Rectangle tamSprite;

    public Sprite(){
        super();
    }

    public Sprite(int numeroDeCenas, int x, int y, int vel, int largura, int altura){
        super(numeroDeCenas, x, y, vel);
        tamSprite = new Rectangle(x, y, largura, altura);
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

    public int getPontuacao() {
        return pontuacao;
    }
    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Rectangle getTamSprite() {
        return tamSprite;
    }
    public void setTamSprite(Rectangle tamSprite) {
        this.tamSprite = tamSprite;
    }

    public void moverRetangulo () {
        tamSprite.setLocation(this.getX(), this.getY());
    }
}
