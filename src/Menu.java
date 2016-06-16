package tutor14;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Menu {
    int cenario = -1;			//ESSA VARIAVEL INDICA EM QUE CENÁRIO ESTAMOS, -1(NENHUM CENÁRIO) (0,1,2,3...N) OUTROS CENÁRIOS
    int itemSelecionado = 0;	//ESSA VARIAVEL SERVIRÁ PARA INDICAR QUAL ITEM QUE ESTÁ SELECIONADO (o atual é zero que é o primeiro item do menu)
    String itens[];				//aqui vamos guardar os itens do nosso menu (jogar, opções, sair...etc)
    Graphics bbg;				//como nos outros métodos da classe game.java usamos essa varialvel para desenhar elementos na tela
    boolean ativo;				//indica se o menu está ativo ou não (ao inicar o jogo desativamos o menu, para o controlemos durante o jogo)
    int x;						//coordenada x do menu
    int y;						//coordenada y do menu
    int tamanhoDaFonte = 20;	//nem precisa explicar não é ?
    int distanciaEntreItens = 15;//distância entre cada item do menu!
    Font fonte = new Font("Arial", Font.BOLD, tamanhoDaFonte);//a fonte do nosso menu
    Color corSelecionado = new Color(255, 0, 0); // COR VERMELHA para o item selecionado
    Color corNaoSelecionado = new Color(0, 0, 0); // COR PRETA para o item que não está selecionado
    //isso é o construtor ao criarmos o objeto do tipo Menu, teremos que informar o número de itens as coordenadas e se ele estará ativo ou não!
    public Menu(int numeroDeItens, int x, int y, boolean ativo) {
        itens = new String[numeroDeItens];
        this.x = x;
        this.y = y;
        this.ativo = ativo;
    }
    //esse metodo só chamará o método controlaMenu se ele estiver ativo!
    public void controlar(KeyEvent e) {
        if (ativo) {
            controlarMenu(e);
        }
    }
    //esse método nos fará retornar ao menu ao pressinarmos "Esc"
    public void voltarAoMenu(KeyEvent e){
        //se tecla pressionada for igual a "Esc"
        if(e.getKeyCode() == e.VK_ESCAPE){
            cenario = -1;  //com isso sai dos cenários em que estivermos...
            ativo = true;  //e reativa o menu para que o possamos controlar novamente...
        }
    }
    //esse método controlará o nosso menu através do teclado
    //ele é chamando dentro do método controla()
    private void controlarMenu(KeyEvent e) {
        //se pressionar a tecla "cima" diminui 1 em itemSelecionado
        if (e.getKeyCode() == e.VK_UP) {
            itemSelecionado -= 1;
        }
        //se pressionar a tecla "baixo" aumenta 1 em itemSelecionado
        if (e.getKeyCode() == e.VK_DOWN) {
            itemSelecionado += 1;
        }
        //isso aqui é para controlar os limites extrmos do menu
        //caso eu esteja com 1º item selecionado e pressione para cima, ele vai para a ultima opção
        //caso eu esteja com o ultimo item selecionado e pressione para baixo, ele vai para primeira opção
        if (itemSelecionado >= itens.length) {
            itemSelecionado = 0;
        }
        if (itemSelecionado < 0) {
            itemSelecionado = itens.length - 1;
        }
        //se pressionar a tecla Enter ele muda o valor de cenario para o item que está selecionado
        //isso fará mudar de cenário e desativará o menu para que ele não seja mais controlado!
        if(e.getKeyCode() == e.VK_ENTER){
            cenario = itemSelecionado;
            ativo = false;
        }
    }
    //esse método irá desenhar o nosso menu na tela!
    //a um loop (for) que irá listar todos os itens que está guardado em itens[]
    //um calculo é feito para a coordenada y de cada intem do nosso menu, para que eles fiquem
    //um distante do outro, então caso y = 10, o item 0 será: 10+(0*(20+15)) = 10
    //para o item 1 será: 10+(1*(20+15)) = 45
    //para o item 2 será: 10+(2*(20+15)) = 80
    //para o item 3 será: 10+(3*(20+15)) = 115, e assim por diante...
    public void desenharMenu() {
        bbg.setFont(fonte);//seta a fonte que definimos bem acima na declaração de variáveis
        for (int i = 0; i < itens.length; i++) {// aqui é o inicio do nosso loop
            if(itemSelecionado == i){//se ele estiver selecionado muda a cor para vermelho e desenha o item na tela
                bbg.setColor(corSelecionado);
                bbg.drawString(itens[i], x, y+(i*(tamanhoDaFonte+distanciaEntreItens)));
            }else{//se não estiver selecionado muda a cor para preto e desenha o item na tela
                bbg.setColor(corNaoSelecionado);
                bbg.drawString(itens[i], x, y+(i*(tamanhoDaFonte+distanciaEntreItens)));
            }
        }
    }

}
