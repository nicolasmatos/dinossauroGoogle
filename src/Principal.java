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

    Sprite dinossauro = new Sprite(2, xInicial, yInicial, 10);

    ImageIcon fundo = new ImageIcon("imagens/fundo.jpg");

    public void atualizar() {
        if (pulo > 0) {
            dinossauro.moverCima();
            pulo--;
        }else if (queda > 0) {
            dinossauro.moverBaixo();
            queda--;
        }
        /*
        if (pulo == 0 && queda == 0) {
            dinossauro.setImagem(dinoC1, 0);
            dinossauro.setImagem(dinoC2, 1);
        }*/
    }

    public void desenharObjetos() {
        Graphics g = getGraphics();
        Graphics bbg = backBuffer.getGraphics();
        int cena = dinossauro.getCena();
        //Desenhando o fundo
        bbg.drawImage(fundo.getImage(),0,0,this);
        bbg.drawImage(dinossauro.getImagem(cena).getImage(), dinossauro.getX(), dinossauro.getY(), this);
        dinossauro.animar();
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
        dinossauro.setImagem(dinoC1, 0);
        dinossauro.setImagem(dinoC2, 1);
    }

    public void run() {
        inicializar();
        while (true) {
            atualizar();
            desenharObjetos();
            try {
                Thread.sleep(1000/FPS);
            } catch (Exception e) {
                System.out.println("Thread interrompida!");
            }
        }
    }

    public static void main(String[] args) {
        Principal game = new Principal();
        Thread gameT = new Thread(game);
        gameT.start();
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == e.VK_UP){
            dinossauro.setImagem(dino, 0);
            dinossauro.setImagem(dino, 1);
            if(pulo == 0 && queda == 0){
                pulo = ALTPULO;
                queda = ALTPULO;
            }
        }
        if(e.getKeyCode() == e.VK_DOWN){
            dinossauro.setImagem(dinoA1, 0);
            dinossauro.setImagem(dinoA2, 1);
            if (pulo == 0 && queda == 0) {
                dinossauro.setY(yInicial + 28);
            }
            if(pulo > 0){
                dinossauro.setImagem(dino, 0);
                dinossauro.setImagem(dino, 1);
                queda = ALTPULO - pulo;
                pulo = 0;
            }
        }
    }
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == e.VK_UP){
            if (pulo != 0 || queda != 0) {
                dinossauro.setImagem(dino, 0);
                dinossauro.setImagem(dino, 1);
            }
            else{
                dinossauro.setImagem(dinoC1, 0);
                dinossauro.setImagem(dinoC2, 1);
                //dinossauro.setY(yInicial);
            }
        }

        if(e.getKeyCode() == e.VK_DOWN){
            dinossauro.setImagem(dinoC1, 0);
            dinossauro.setImagem(dinoC2, 1);
            if (pulo == 0 && queda == 0) {
                dinossauro.setY(yInicial);
            }
        }
    }
    public void keyTyped(KeyEvent e) {
    }
}