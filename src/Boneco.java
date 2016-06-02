import java.awt.Image;

public class Boneco extends DesenhoMovel {
	private final int LARGURA = 1000;
	private final int ALTURA = 720;
	private static int pontuacao = 0;
	
	private boolean andaDireita = true;
	private boolean andaEsquerda = true;
	private boolean andaCima = true;
	private boolean andaBaixo = true;
	
	public Boneco() 
	{
		super();
	}

	public Boneco(Image imagem, int x, int y, int vel)
	{
		super(imagem, x, y, vel);
	}

	public void checaMovimento() {
        if (getY() >= ALTURA - 196) 
        {
        	andaBaixo = false;
        }

        if (getY() < 40) 
        {
        	andaCima = false;
        }

        if (getX() >= LARGURA - 146) 
        {
        	andaDireita = false;
        }

        if (getX() < 0) 
        {
        	andaEsquerda = false;
        }
    }

	public boolean isAndaDireita() {
		return andaDireita;
	}

	public void setAndaDireita(boolean andaDireita) {
		this.andaDireita = andaDireita;
	}

	public boolean isAndaEsquerda() {
		return andaEsquerda;
	}

	public void setAndaEsquerda(boolean andaEsquerda) {
		this.andaEsquerda = andaEsquerda;
	}

	public boolean isAndaCima() {
		return andaCima;
	}

	public void setAndaCima(boolean andaCima) {
		this.andaCima = andaCima;
	}

	public boolean isAndaBaixo() {
		return andaBaixo;
	}

	public void setAndaBaixo(boolean andaBaixo) {
		this.andaBaixo = andaBaixo;
	}

	
	public static int getPontuacao() {
		return pontuacao;
	}

	public static void setPontuacao(int pontuacao) {
		Boneco.pontuacao = pontuacao;
	}

}
