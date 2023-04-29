package pecas;

import utils.Posicao;

import java.util.ArrayList;

import utils.Cor;

public class Peao extends Peca {

    private boolean primeiroMovimento = true;

    public Peao(Posicao posicao, Cor cor) {
        super(posicao, cor, TipoPeca.PEAO);
    }

    @Override
    public ArrayList<Posicao> getMovimentosPossiveis() {
        if (this.getCor() == Cor.PRETO) {
            return this.getMovimentosPossiveisPeao(1);
        }
        return this.getMovimentosPossiveisPeao(-1);
    }

    private ArrayList<Posicao> getMovimentosPossiveisPeao(int dir) {
        ArrayList<Posicao> movimentosPossiveis = new ArrayList<Posicao>();
        Posicao posicaoAtual = this.getPosicaoTabuleiro();
        Posicao proximaPosicao = new Posicao(posicaoAtual.x, posicaoAtual.y + 1 * dir);
        Peca peca = this.tabuleiro.getPeca(proximaPosicao);
        if (peca == null) {
            movimentosPossiveis.add(proximaPosicao);
        }
        if (this.primeiroMovimento) {
            proximaPosicao = new Posicao(posicaoAtual.x, posicaoAtual.y + 2 * dir);
            peca = this.tabuleiro.getPeca(proximaPosicao);
            if (peca == null) {
                movimentosPossiveis.add(proximaPosicao);
            }
        }
        Posicao posicaoEsquerda = new Posicao(posicaoAtual.x + 1, posicaoAtual.y + 1 * dir);
        if (this.podeCapturar(posicaoEsquerda)) {
            movimentosPossiveis.add(posicaoEsquerda);
        }
        Posicao posicaoDireita = new Posicao(posicaoAtual.x - 1, posicaoAtual.y + 1 * dir);
        if (this.podeCapturar(posicaoDireita)) {
            movimentosPossiveis.add(posicaoDireita);
        }
        return movimentosPossiveis;
    }
}
