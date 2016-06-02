import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.awt.Graphics;

public class Fundo extends JFrame {

    private DesenhoMovel f;
    BufferedImage backBuffer;
    int FPS = 30;
    private final int LARGURA = 1000;
    private final int ALTURA = 720;
    private int xFundo = 50;
    private int yFundo = 250;

    public void atualizar() {

    }

    public void desenharFundo() {
        Graphics g = getGraphics();
        g.clearRect(0, 0, 1000, 1000);
        f.moverEsquerda();
        g.drawImage(f.getImagem(), f.getX(), f.getY(), null);
    }

    public void inicializar() {
        setTitle("Titulo do Jogo!");
        setSize(LARGURA, ALTURA);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        backBuffer = new BufferedImage(LARGURA, ALTURA, BufferedImage.TYPE_INT_RGB);
        f = new DesenhoMovel(Toolkit.getDefaultToolkit().getImage("fundo.jpg"), xFundo++, yFundo, 10);

        Graphics g = getGraphics();
        g.drawImage(Toolkit.getDefaultToolkit().getImage("fundo.jpg"), f.getX(), f.getY(), null);
    }

    public void run() {
        inicializar();
        while (true) {
            atualizar();
            desenharFundo();
            try {
                Thread.sleep(1000/FPS);
            } catch (Exception e) {
                System.out.println("Thread interrompida!");
            }
        }
    }

    public static void main(String[] args) {
        Fundo game = new Fundo();
        game.run();
    }
}