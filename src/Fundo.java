import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Fundo implements Runnable {

    public static void main(String[] args) {
        Fundo fundo = new Fundo();
        Thread fundoT = new Thread(fundo);
        fundoT.start();
    }

    public void inicializar() {
        JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(new FundoPanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void run() {
        inicializar();
    }

    public class FundoPanel extends JPanel {

        private int fundoX = 0;
        private int fundoCompX = 1025;


        private BufferedImage backBuffer;
        DesenhoMovel fundo = new DesenhoMovel(1, fundoX, 1, 1);
        DesenhoMovel fundoComp = new DesenhoMovel(1, fundoCompX, 1, 1);
        ImageIcon imgFundo = new ImageIcon("imagens/fundo.jpg");

        public FundoPanel() {
            fundo.setImagem(imgFundo, 0);
            fundoComp.setImagem(imgFundo, 0);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(1024, 321);
        }

        int x1 = fundoX, x2 = fundoCompX;
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(x2 == 0) {
                x1 = fundoX + 1;
                x2 = fundoCompX;
            }
            x1--;
            g.drawImage(fundo.getImagem(0).getImage(), x1, fundo.getY(), this);
            x2--;
            g.drawImage(fundoComp.getImagem(0).getImage(), x2, fundoComp.getY(), this);
            repaint();
        }
    }
}