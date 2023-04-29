package pecas;

import utils.Posicao;

import java.util.ArrayList;

import utils.Cor;

public class Bispo extends Peca {

    public Bispo(Posicao posicao, Cor cor) {
        super(posicao, cor, TipoPeca.BISPO);
    }

    @Override
    public ArrayList<Posicao> getMovimentosPossiveis() {
        ArrayList<Posicao> movimentosPossiveis = new ArrayList<Posicao>();

        this.addMovimentoDiagonal(movimentosPossiveis, posicaoTabuleiro, 1, 1);
        this.addMovimentoDiagonal(movimentosPossiveis, posicaoTabuleiro, 1, -1);
        this.addMovimentoDiagonal(movimentosPossiveis, posicaoTabuleiro, -1, 1);
        this.addMovimentoDiagonal(movimentosPossiveis, posicaoTabuleiro, -1, -1);
        return movimentosPossiveis;
    }

    /**
     * Só chamar se já validou que não tem
     * peça no caminho
     */
    private boolean addPosicaoValida(Posicao proximaPosicao, ArrayList<Posicao> movimentosPossiveis) {
        Peca peca = this.tabuleiro.getPeca(proximaPosicao);
        if (peca != null) {
            if (this.podeCapturar(proximaPosicao)) {
                movimentosPossiveis.add(proximaPosicao);
            }
            return false;
        }
        movimentosPossiveis.add(proximaPosicao);
        return true;
    }

    private void addMovimentoDiagonal(ArrayList<Posicao> movimentosPossiveis, Posicao posicaoAtual, int direcaoX,
            int direcaoY) {
        boolean podeContinuar = true;
        int i = posicaoAtual.x + direcaoX;
        int j = posicaoAtual.y + direcaoY;
        while (i < 8 && i >= 0 && j < 8 && j >= 0 && podeContinuar) {
            Posicao proximaPosicao = new Posicao(i, j);
            podeContinuar = this.addPosicaoValida(proximaPosicao, movimentosPossiveis);
            i += direcaoX;
            j += direcaoY;
        }
    }
}
