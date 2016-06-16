import java.io.*;

public class ColisaoException extends Exception{

    public ColisaoException() {

    }
    public ColisaoException(Sprite a) {
        File file = new File("C:\\Dev\\Ideia\\dinossauroGoogle\\testesArquivos.txt");

        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(a.getPontuacao());

            oos.close();
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
