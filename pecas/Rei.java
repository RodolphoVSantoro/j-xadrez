package pecas;

import utils.Posicao;

import java.util.ArrayList;

import utils.Cor;

public class Rei extends Peca {

    public Rei(Posicao posicao, Cor cor) {
        super(posicao, cor, TipoPeca.REI);
    }

    private static final int[][] MOVIMENTOS_POSSIVEIS = {
            { 1, 0 },
            { 1, -1 },
            { 0, -1 },
            { -1, -1 },
            { -1, 0 },
            { -1, 1 },
            { 0, 1 },
            { 1, 1 }
    };

    @Override
    public ArrayList<Posicao> getMovimentosPossiveis() {
        ArrayList<Posicao> movimentosPossiveis = new ArrayList<Posicao>();
        Posicao posicaoAtual = this.getPosicaoTabuleiro();

        for (int[] movimento : MOVIMENTOS_POSSIVEIS) {
            Posicao proximaPosicao = new Posicao(posicaoAtual.x + movimento[0], posicaoAtual.y + movimento[1]);
            this.reiAdicionaMovimentoPossivel(movimentosPossiveis, proximaPosicao);
        }

        return movimentosPossiveis;
    }

    private void reiAdicionaMovimentoPossivel(ArrayList<Posicao> movimentosPossiveis, Posicao proximaPosicao) {
        if (proximaPosicao.x < 8 && proximaPosicao.x >= 0 && proximaPosicao.y < 8 && proximaPosicao.y >= 0) {
            Peca peca = this.tabuleiro.getPeca(proximaPosicao);
            if (peca == null || this.podeCapturar(proximaPosicao)) {
                movimentosPossiveis.add(proximaPosicao);
            }
        }
    }
}
