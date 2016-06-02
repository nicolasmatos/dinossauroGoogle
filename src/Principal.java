import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Principal extends JFrame implements ActionListener {

	private DesenhoMovel f;
	private final int LARGURA = 1000;
	private final int ALTURA = 720;
	private int xFundo = 50;
	private int yFundo = 250;
	private Timer timer;
	
	public Principal()
	{
		super("Bem-vindos!");
		this.setSize(LARGURA, ALTURA);
		this.setVisible(true);
		this.setBackground(Color.white);
		
		timer = new Timer(100, this);
        timer.start();

        getDefaultCloseOperation();

		f = new DesenhoMovel(Toolkit.getDefaultToolkit().getImage("fundo.jpg"), xFundo++, yFundo, 10);

		Graphics g = getGraphics();
		g.drawImage(Toolkit.getDefaultToolkit().getImage("fundo.jpg"), f.getX(), f.getY(), null);
	}
	
	public static void main(String[] args)
	{
		Principal app = new Principal();
	}

    @Override
   	public void paint(Graphics g)
   	{
	    g.clearRect(0, 0, 1000, 1000);
		f.moverEsquerda();
	    g.drawImage(f.getImagem(), f.getX(), f.getY(), null);
   }

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
}