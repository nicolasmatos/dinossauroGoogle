import javax.swing.ImageIcon;

public class Desenho {
	
	private int x;
	private int y;
	private ImageIcon imagens[];
	
	public Desenho()
	{
		super();
	}

	public Desenho(int qtdImagens, int x, int y) {
		super();
		imagens = new ImageIcon[qtdImagens];
		this.setX(x);
		this.setY(y);
	}

	public void setX(int x) {
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

	public void setImagem(ImageIcon imagem, int pos) {
		this.imagens[pos] = imagem;
	}
	public ImageIcon getImagem(int pos) {
		return imagens[pos];
	}
	public ImageIcon[] getImagens() {
		return imagens;
	}
}
