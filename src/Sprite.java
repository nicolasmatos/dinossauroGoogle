import javax.swing.ImageIcon;

public class Sprite {

    ImageIcon cenas[];	//VETOR DE IMAGENS,
    int x;					//AQUI É A COORDENADA X
    int y;					//AQUI É A COORDENADA Y
    int largura;			//LARGURA DA IMAGEM, CASO QUEIRA DEFINIR UMA
    int altura;				//ALTURA DA IMAGEM, CASO QUEIRA DEFINIR UMA
    int cena = 0;			//O INDICE DA CENA DA NOSSA SPRITE ANIMADA
    int controlaVelocidade = 0;
    int velocidade = 5;

    //ISSO AQUI EM BAIXO PRA QUEM NÃO SABE É UM CONSTRUTOR
    //QUANDO FORMOS CRIAR UM OBJETO A PARTIR DESSA CLASSE
    //TEREMOS QUE: INFORMAR O NÚMERO DE CENAS, E A SUA COORDENADA INICIAL
    //+ OU - ASSIM: new Sprite(3, 200, 300);

    public Sprite(int numeroDeCenas, int x, int y){
        cenas = new ImageIcon[numeroDeCenas];
        this.x = x;
        this.y = y;
    }

    //ESSE MÉTODO VAI ALTERNAR AS IMAGENS QUE COMPÕES NOSSA SPRITE
    //DEPENDENDO DO QUE VC PRETENDER FAZER, ESSE MÉTODO PODERIA
    //SER MAIS COMPLEXO!
    //TIPO: animarCorrer() animarChutar() animarPular()
    //E ESSES MÉTODOS SERIAM CHAMADOS AO CLICAR EM ALGUMA TECLA!!!
    //ESSA ANIMAÇÃO VAI FICAR MUITO RÁPIDA, PQ ELE MUDAR A IMAGEM DA CENA
    //A CADA 1/30 milissegundos
    public void animar(){
        cena += 1;
        if(cena == cenas.length){ cena = 0; }
    }

    //ESSE MÉTODO CONTROLA A VELOCIDADE DA ANIMAÇÃO
    //TENTEN ENTENDER ISSO AI...:
    //isso é um controle de tempo paralelo ao que já tem no game loop!!!!
    //o game loop vai rodar isso 30x a cada segundo
    //e esse método vai mudar a cena a cada, 5 vezes que o game loop for
    //executado!!!!
    public void animarMaisLento(){
        controlaVelocidade+=1;
        if(controlaVelocidade>velocidade){
            cena += 1;
            controlaVelocidade = 0;
            if(cena == cenas.length){ cena = 0; }
        }
    }

}
