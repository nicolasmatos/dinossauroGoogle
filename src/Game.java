import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Game extends JFrame implements Runnable, KeyListener {

    BufferedImage backBuffer;
    int FPS = 15;
    int janelaW = 1024;
    int janelaH = 321;

    Sprite vilao = new Sprite(2, 100, 185);

    ImageIcon fundo = new ImageIcon("fundoN.jpg");

    public void atualizar() {

    }

    public void desenharGraficos() {
        Graphics g = getGraphics();
        Graphics bbg = backBuffer.getGraphics();

        //Desenhando o fundo
        bbg.drawImage(fundo.getImage(),0,0,this);
        bbg.drawImage(vilao.cenas[vilao.cena].getImage(), vilao.x, vilao.y, this);
        vilao.animar();
        g.drawImage(backBuffer, 0, 0, this);
    }

    public void inicializar() {
        setTitle("Dinossauro Google");
        setSize(janelaW, janelaH);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
        backBuffer = new BufferedImage(janelaW, janelaH, BufferedImage.TYPE_INT_RGB);
        addKeyListener(this);

        //Carregando imagens da sprite
        vilao.cenas[0] = new ImageIcon("dinoC1.png");
        vilao.cenas[1] = new ImageIcon("dinoC2.png");
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
        Game game = new Game();
        game.run();
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == e.VK_UP){
            vilao.cenas[0] = new ImageIcon("dino.png");
            vilao.cenas[1] = new ImageIcon("dino.png");
        }
        if(e.getKeyCode() == e.VK_DOWN){
            vilao.cenas[0] = new ImageIcon("dinoA1.png");
            vilao.cenas[1] = new ImageIcon("dinoA2.png");
            vilao.y = 213;
        }
    }
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == e.VK_UP){
            vilao.cenas[0] = new ImageIcon("dinoC1.png");
            vilao.cenas[1] = new ImageIcon("dinoC2.png");
            vilao.y = 185;
        }

        if(e.getKeyCode() == e.VK_DOWN){
            vilao.cenas[0] = new ImageIcon("dinoC1.png");
            vilao.cenas[1] = new ImageIcon("dinoC2.png");
            vilao.y = 185;
        }
    }
    public void keyTyped(KeyEvent e) {

    }
}