import java.io.*;

public class ColisaoException extends Exception{

    public ColisaoException() {

    }
    public ColisaoException(Sprite a) {
        File file = new File("C:\\Dev\\Ideia\\dinossauroGoogle\\pontos.txt");

        try {
            FileOutputStream fos = new FileOutputStream(file);
            DataOutputStream dos = new DataOutputStream(fos);

            dos.writeChars("Pontos: ");
            dos.writeInt(a.getPontuacao());

            dos.close();
            fos.close();
        }
        catch (FileNotFoundException e1) {
            System.out.println("Arquivo nao encontrado");
        }
        catch (IOException e) {
            System.out.println("Erro de IO");
        }
    }
}
