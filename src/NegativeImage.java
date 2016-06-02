import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class NegativeImage {

    public static void main(String[] args) {
        new NegativeImage();
    }

    public NegativeImage() {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame("Test");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        private BufferedImage img;

        public TestPane() {
            try {
                img = ImageIO.read(new File("fundoN.jpg"));
            } catch (IOException ex) {
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(1024, 321);
        }

        int x1 = 0, x2 = 1025;
        int y = 1;
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(x2 == 0) {
                x1 = 1;
                x2 = 1025;
            }
            x1--;
            g.drawImage(img, x1, y, this);
            x2--;
            g.drawImage(img, x2, y, this);
            repaint();
        }
    }
}