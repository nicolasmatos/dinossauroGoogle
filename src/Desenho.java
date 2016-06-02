import java.awt.Image;

public class Desenho {
	
	private int x;
	private int y;
	private Image imagem;
	
	public Desenho()
	{
		super();
	}

	public Desenho(Image imagem, int x, int y) 
	{
		super();
		this.setImagem(imagem);
		this.setX(x);
		this.setY(y);
	}

	public void setX(int x) 
	{
		if (x >= 0)
		{
			this.x = x;
		}
	}

	public int getX() 
	{
		return x;
	}

	public void setY(int y) 
	{
		this.y = y;
	}

	public int getY() 
	{
		return y;
	}
	
	public void setImagem(Image imagem) 
	{
		this.imagem = imagem;
	}

	public Image getImagem() 
	{
		return imagem;
	}
}
