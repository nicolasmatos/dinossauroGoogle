import javax.swing.ImageIcon;

public class DesenhoMovel extends Desenho {
	
	private int velocidade;

	public DesenhoMovel() 
	{
		super();
	}

	public DesenhoMovel(int qtdImagens, int x, int y, int vel) {
		super(qtdImagens, x, y);
		this.setVelocidade(vel);
	}

	public void setVelocidade(int velocidade) {
		if (velocidade >= 0)
		{
			this.velocidade = velocidade;
		}
	}
	public float getVelocidade() 
	{
		return velocidade;
	}
	
	public void moverDireita()
	{
		this.setX(this.getX() + velocidade);
	}
	public void moverEsquerda()
	{
		this.setX(this.getX() - velocidade);
	}
	public void moverBaixo()
	{
		this.setY(this.getY() + velocidade);
	}
	public void moverCima()
	{
		this.setY(this.getY() - velocidade);
	}
}