import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Principal extends JFrame implements Runnable, KeyListener {

    BufferedImage backBuffer;
    int FPS = 15;
    private final int LARGURA = 1024;
    private final int ALTURA = 321;
    private final int ALTPULO = 10;
    private int xInicial = 100;
    private int yInicial = 195;
    int pulo = 0, queda = 0;


    ImageIcon dino = new ImageIcon("imagens/dino.png");
    ImageIcon dinoC1 = new ImageIcon("imagens/dinoC1.png");
    ImageIcon dinoC2 = new ImageIcon("imagens/dinoC2.png");
    ImageIcon dinoA1 = new ImageIcon("imagens/dinoA1.png");
    ImageIcon dinoA2 = new ImageIcon("imagens/dinoA2.png");
    ImageIcon dinoM = new ImageIcon("imagens/dinoM.png");

    Sprite vilao = new Sprite(2, xInicial, yInicial, 10);

    ImageIcon fundo = new ImageIcon("imagens/fundo.jpg");

    public void atualizar() {
        if (pulo > 0) {
            vilao.moverCima();
            pulo--;
        }else if (queda > 0) {
            vilao.moverBaixo();
            queda--;
        }
    }

    public void desenharGraficos() {
        Graphics g = getGraphics();
        Graphics bbg = backBuffer.getGraphics();
        int cena = vilao.getCena();
        //Desenhando o fundo
        bbg.drawImage(fundo.getImage(),0,0,this);
        bbg.drawImage(vilao.getImagem(cena).getImage(), vilao.getX(), vilao.getY(), this);
        vilao.animar();
        g.drawImage(backBuffer, 0, 0, this);
    }

    public void inicializar() {
        setTitle("Dinossauro Google");
        setSize(LARGURA, ALTURA);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
        backBuffer = new BufferedImage(LARGURA, ALTURA, BufferedImage.TYPE_INT_RGB);
        addKeyListener(this);

        //Carregando imagens da sprite
        vilao.setImagem(dinoC1, 0);
        vilao.setImagem(dinoC2, 1);
    }

    public void run() {
        inicializar();
        while (true) {
            atualizar();
            desenharGraficos();
            try {
                Thread.sleep(1000/FPS);
            } catch (Exception e) {
                System.out.println("Thread interrompida!");
            }
        }
    }

    public static void main(String[] args) {
        Principal game = new Principal();
        game.run();
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == e.VK_UP){
            vilao.setImagem(dino, 0);
            vilao.setImagem(dino, 1);
            if(pulo == 0 && queda == 0){
                pulo = ALTPULO;
                queda = ALTPULO;
            }
        }
        if(e.getKeyCode() == e.VK_DOWN){
            vilao.setImagem(dinoA1, 0);
            vilao.setImagem(dinoA2, 1);
            //vilao.setY(yInicial + 28);
            if(pulo > 0){
                queda = ALTPULO - pulo;
                pulo = 0;
            }
        }
    }
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == e.VK_UP){
            vilao.setImagem(dinoC1, 0);
            vilao.setImagem(dinoC2, 1);
            //vilao.setY(yInicial);
        }

        if(e.getKeyCode() == e.VK_DOWN){
            vilao.setImagem(dinoC1, 0);
            vilao.setImagem(dinoC2, 1);
            //vilao.setY(yInicial);
        }
    }
    public void keyTyped(KeyEvent e) {

    }
}