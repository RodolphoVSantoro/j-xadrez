package pecas;

import utils.Posicao;

import java.util.ArrayList;

import utils.Cor;

public class Rei extends Peca {

    public Rei(Posicao posicao, Cor cor) {
        super(posicao, cor, TipoPeca.REI);
    }
    @Override
    public ArrayList<Posicao> getMovimentosPossiveis() {
        ArrayList<Posicao> movimentosPossiveis = new ArrayList<Posicao>();
        Posicao posicaoAtual = this.getPosicaoTabuleiro();
        for(int i = posicaoAtual.x - 1;i <= posicaoAtual.x + 1 && i >= 0 && i < 8;i++) {
            Posicao proximaPosicao = new Posicao(i, posicaoAtual.y);
            Peca peca = this.tabuleiro.getPeca(proximaPosicao);
            if(peca != null && this.podeCapturar(proximaPosicao)) {
                movimentosPossiveis.add(proximaPosicao);
            }
        }
        for(int i = posicaoAtual.y - 1;i <= posicaoAtual.y + 1  && i >= 0 && i < 8;i++) {
            Posicao proximaPosicao = new Posicao(posicaoAtual.x, i);
            Peca peca = this.tabuleiro.getPeca(proximaPosicao);
            if(peca != null && this.podeCapturar(proximaPosicao)) {
                movimentosPossiveis.add(proximaPosicao);
            }
        }
        return movimentosPossiveis;
    }
}
