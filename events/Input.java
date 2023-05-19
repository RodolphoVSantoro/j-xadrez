package events;

import java.awt.Canvas;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import config.Config;
import maquinaDeRegras.MaquinaDeRegras;
import maquinaDeRegras.Movimento;
import pecas.Peca;
import utils.Cor;
import utils.Posicao;

public class Input extends MouseAdapter{

    MaquinaDeRegras maquinaDeRegras;
    public Peca selecionada;
    public Canvas canvas;

    public Input(MaquinaDeRegras maquinaDeRegras, Canvas canvas){
        super();
        this.maquinaDeRegras = maquinaDeRegras;
        this.canvas = canvas;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       
        if(maquinaDeRegras.getTurno() == Cor.BRANCO){
            // Seleciona posição clicada pelo mouse
            int col = (e.getX() / Config.LARGURA_TABULEIRO) - 1;
            int linha = (e.getY() / Config.ALTURA_TABULEIRO) - 1;
            Posicao p = new Posicao(col, linha);
            Peca posicaoPeca = maquinaDeRegras.getTabuleiro().getPeca(p);
            // Se houver uma peça onde o mouse clicou, seleciona a peça
            if(posicaoPeca != null && ( posicaoPeca.getCor() == Cor.BRANCO && !posicaoPeca.getCapturado())){
                //System.out.println("vc selecinou uma peça valida");
                this.selecionada = posicaoPeca;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
        int col = (e.getX() / Config.LARGURA_TABULEIRO) - 1;
        int linha = (e.getY() / Config.ALTURA_TABULEIRO) - 1;
        Posicao novaPosicao = new Posicao(col, linha);

        // Se houver uma peça selecionada, define a posição da peça como a posiçao onde o jogador soltou o clique do mouse
        if(this.selecionada != null){
            Movimento movimento = new Movimento(this.selecionada, this.selecionada.getPosicaoTabuleiro(), novaPosicao, 0);
            if(this.selecionada.podeMover(novaPosicao)){
                this.maquinaDeRegras.executaMovimento(movimento,false);
                this.maquinaDeRegras.setTurno(Cor.PRETO);
                new PrintaHistorico(this.maquinaDeRegras).print();
            }
        }

        // Desseleciona a peça
        this.selecionada = null;
        canvas.repaint();
    }
    
}
