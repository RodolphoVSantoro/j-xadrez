package pecas;

import utils.Posicao;

import java.util.ArrayList;

import utils.Cor;

public class Cavalo extends Peca {

    public Cavalo(Posicao posicao, Cor cor) {
        super(posicao, cor, TipoPeca.CAVALO);
    }

    @Override
    public ArrayList<Posicao> getMovimentosPossiveis() {
        ArrayList<Posicao> movimentosPossiveis = new ArrayList<Posicao>();
        Posicao posicaoAtual = this.getPosicaoTabuleiro();
        Posicao proximaPosicao = new Posicao(posicaoAtual.x + 2, posicaoAtual.y + 1);
        this.cavaloAdicionaMovimentoPossivel(movimentosPossiveis, proximaPosicao);

        proximaPosicao = new Posicao(posicaoAtual.x + 2, posicaoAtual.y + 1);
        this.cavaloAdicionaMovimentoPossivel(movimentosPossiveis, proximaPosicao);

        proximaPosicao = new Posicao(posicaoAtual.x + 2, posicaoAtual.y + 1);
        this.cavaloAdicionaMovimentoPossivel(movimentosPossiveis, proximaPosicao);

        proximaPosicao = new Posicao(posicaoAtual.x + 2, posicaoAtual.y + 1);
        this.cavaloAdicionaMovimentoPossivel(movimentosPossiveis, proximaPosicao);

        return movimentosPossiveis;
    }

    private void cavaloAdicionaMovimentoPossivel(ArrayList<Posicao> movimentosPossiveis, Posicao proximaPosicao) {
        if (proximaPosicao.x < 8 && proximaPosicao.x >= 0 && proximaPosicao.y < 8 && proximaPosicao.y >= 0) {
            Peca peca = this.tabuleiro.getPeca(proximaPosicao);
            if (peca == null || this.podeCapturar(proximaPosicao)) {
                movimentosPossiveis.add(proximaPosicao);
            }
        }
    }
}
