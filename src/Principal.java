import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Principal extends JFrame implements Runnable, KeyListener {

    BufferedImage backBuffer;
    int FPS = 25;
    private final int LARGURA = 1024;
    private final int ALTURA = 321;
    private final int ALTPULO = 15;
    private int xInicial = 100;
    private int yInicial = 195;
    private boolean status = true;
    int pulo = 0, queda = 0;

    ImageIcon dino = new ImageIcon("imagens/dino.png");
    ImageIcon dinoC1 = new ImageIcon("imagens/dinoC1.png");
    ImageIcon dinoC2 = new ImageIcon("imagens/dinoC2.png");
    ImageIcon dinoA1 = new ImageIcon("imagens/dinoA1.png");
    ImageIcon dinoA2 = new ImageIcon("imagens/dinoA2.png");
    ImageIcon dinoM = new ImageIcon("imagens/dinoM.png");
    ImageIcon ave1 = new ImageIcon("imagens/ave1.2.png");
    ImageIcon ave2 = new ImageIcon("imagens/ave2.2.png");
    ImageIcon caqui1 = new ImageIcon("imagens/caquito1.png");
    ImageIcon caqui2_1 = new ImageIcon("imagens/caquito2.1.png");
    ImageIcon caqui2_2 = new ImageIcon("imagens/caquito2.2.png");
    ImageIcon caqui3 = new ImageIcon("imagens/caquito3.png");

    Sprite dinossauro = new Sprite(2, xInicial, yInicial, 10, 64, 69);

    ArrayList<Sprite> desenhos = new ArrayList<Sprite>();

    Sprite aveAlto = new Ave(2, 3600, 135, 10, 84, 53);
    Sprite aveMeio = new Ave(2, 4200, 165, 10, 84, 53);
    Sprite aveBaixo = new Ave(2, 4800, 205, 10, 84, 53);

    Sprite caquito1 = new Caquito(2, 1000, 230, 10, 14, 19);
    Sprite caquito2_1 = new Caquito(2, 1600, 215, 10, 36, 48);
    Sprite caquito2_2 = new Caquito(2, 2200, 215, 10, 28, 46);
    Sprite caquito3 = new Caquito(2, 3000, 215, 10, 50, 48);

    Dimension dinoCD = new Dimension(64, 69);
    Dimension dinoAD = new Dimension(85, 41);

    ImageIcon fundo = new ImageIcon("imagens/fundoT.jpg");
    ImageIcon gameOver = new ImageIcon("imagens/game_over.jpg");

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
        if (status == true) {
            if (System.currentTimeMillis() % 1 == 0) {
                dinossauro.setPontuacao(dinossauro.getPontuacao() + 1);
            }

            Graphics g = getGraphics();
            Graphics bbg = backBuffer.getGraphics();

            int cena = dinossauro.getCena();

            //Desenhando o fundo
            bbg.drawImage(fundo.getImage(), 0, 0, this);

            bbg.drawRect(dinossauro.getX(), dinossauro.getY(), (int) dinossauro.getTamSprite().getWidth(), (int) dinossauro.getTamSprite().getHeight());

            for (Sprite desenho : desenhos) {
                bbg.drawImage(desenho.getImagem(cena).getImage(), desenho.getX(), desenho.getY(), this);
                desenho.animar();

                if (desenho instanceof Ave) {
                    if (desenho.getX() == 0) {
                        desenho.setX(2300);
                        switch (new Random().nextInt(3)) {
                            case 0:
                                desenho.setY(135);
                                break;
                            case 1:
                                desenho.setY(165);
                                break;
                            case 2:
                                desenho.setY(205);
                                break;
                        }
                    }
                    desenho.moverEsquerda();
                    desenho.moverRetangulo();
                }

                if (desenho instanceof Caquito) {
                    if (desenho.getX() == 0) {
                        desenho.setX(3400);

                        switch (new Random().nextInt(4)) {
                            case 0:
                                desenho.setImagem(caqui1, 0);
                                desenho.setImagem(caqui1, 1);
                                desenho.setY(230);
                                break;
                            case 1:
                                desenho.setImagem(caqui2_1, 0);
                                desenho.setImagem(caqui2_1, 1);
                                desenho.setY(215);
                                break;
                            case 2:
                                desenho.setImagem(caqui2_2, 0);
                                desenho.setImagem(caqui2_2, 1);
                                desenho.setY(215);
                            case 3:
                                desenho.setImagem(caqui3, 0);
                                desenho.setImagem(caqui3, 1);
                                desenho.setY(215);
                                break;
                        }
                    }
                    desenho.moverEsquerda();
                    desenho.moverRetangulo();
                }
            }

            try {
                colisao(dinossauro, aveAlto);
                colisao(dinossauro, aveMeio);
                colisao(dinossauro, aveBaixo);
            } catch (ColisaoException e) {
                dinossauro.setImagem(dinoM, 0);
                dinossauro.setImagem(dinoM, 1);
                g = getGraphics();
                bbg = backBuffer.getGraphics();
                bbg.drawImage(gameOver.getImage(),0,0,this);
                bbg.setFont(new Font("Arial", 1, 30));
                bbg.setColor(Color.WHITE);
                bbg.drawString("Pontuacao: " + Integer.toString(dinossauro.getPontuacao()), LARGURA/2 - 110, 60);
                dinossauro.animar();
                g.drawImage(backBuffer, 0, 0, this);
            }

            g.drawImage(backBuffer, 0, 0, this);
        }
    }

    public void colisao(Sprite a, Sprite b) throws ColisaoException {
        if (a.getTamSprite().intersects(b.getTamSprite())){
            status = false;
            throw new ColisaoException(a);
        }
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
        aveAlto.setImagem(ave1, 0);
        aveAlto.setImagem(ave2, 1);
        aveMeio.setImagem(ave1, 0);
        aveMeio.setImagem(ave2, 1);
        aveBaixo.setImagem(ave1, 0);
        aveBaixo.setImagem(ave2, 1);
        caquito1.setImagem(caqui1, 0);
        caquito1.setImagem(caqui1, 1);
        caquito2_1.setImagem(caqui2_1, 0);
        caquito2_1.setImagem(caqui2_1, 1);
        caquito2_2.setImagem(caqui2_2, 0);
        caquito2_2.setImagem(caqui2_2, 1);
        caquito3.setImagem(caqui3, 0);
        caquito3.setImagem(caqui3, 1);
        desenhos.add(dinossauro);
        desenhos.add(aveAlto);
        desenhos.add(aveMeio);
        desenhos.add(aveBaixo);
        desenhos.add(caquito1);
        desenhos.add(caquito2_1);
        desenhos.add(caquito2_2);
        desenhos.add(caquito3);
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
            dinossauro.getTamSprite().setSize(dinoCD);
            dinossauro.getTamSprite().setLocation(dinossauro.getX(), dinossauro.getY());
            if(pulo == 0 && queda == 0){
                pulo = ALTPULO;
                queda = ALTPULO;
            }
        }
        if(e.getKeyCode() == e.VK_DOWN){
            dinossauro.setImagem(dinoA1, 0);
            dinossauro.setImagem(dinoA2, 1);
            dinossauro.getTamSprite().setSize(dinoAD);
            dinossauro.getTamSprite().setLocation(dinossauro.getX(), dinossauro.getY());
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
                dinossauro.getTamSprite().setSize(dinoCD);
                dinossauro.getTamSprite().setLocation(dinossauro.getX(), dinossauro.getY());
            }
            else{
                dinossauro.setImagem(dinoC1, 0);
                dinossauro.setImagem(dinoC2, 1);
                dinossauro.getTamSprite().setSize(dinoCD);
                dinossauro.getTamSprite().setLocation(dinossauro.getX(), dinossauro.getY());
                //dinossauro.setY(yInicial);
            }
        }

        if(e.getKeyCode() == e.VK_DOWN){
            dinossauro.setImagem(dinoC1, 0);
            dinossauro.setImagem(dinoC2, 1);
            dinossauro.getTamSprite().setSize(dinoCD);
            dinossauro.getTamSprite().setLocation(dinossauro.getX(), dinossauro.getY() - 30);
            if (pulo == 0 && queda == 0) {
                dinossauro.setY(yInicial);
            }
        }
    }
    public void keyTyped(KeyEvent e) {
    }
}