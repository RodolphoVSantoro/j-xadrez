package pecas;

import utils.Posicao;

import java.util.ArrayList;

import utils.Cor;

public class Dama extends Peca {

    public Dama(Posicao posicao, Cor cor) {
        super(posicao, cor, TipoPeca.DAMA);
    }

    @Override
    public ArrayList<Posicao> getMovimentosPossiveis() {
        ArrayList<Posicao> movimentosPossiveis = new ArrayList<Posicao>();

        this.addMovimentosEsquerda(movimentosPossiveis, posicaoTabuleiro);
        this.addMovimentosDireita(movimentosPossiveis, posicaoTabuleiro);
        this.addMovimentosCima(movimentosPossiveis, posicaoTabuleiro);
        this.addMovimentosBaixo(movimentosPossiveis, posicaoTabuleiro);
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

    private void addMovimentosDireita(ArrayList<Posicao> movimentosPossiveis, Posicao posicaoAtual) {
        boolean podeContinuar = true;
        for (int i = posicaoAtual.x + 1; i < 8 && podeContinuar; i++) {
            Posicao proximaPosicao = new Posicao(i, posicaoAtual.y);
            podeContinuar = this.addPosicaoValida(proximaPosicao, movimentosPossiveis);
        }
    }

    private void addMovimentosEsquerda(ArrayList<Posicao> movimentosPossiveis, Posicao posicaoAtual) {
        boolean podeContinuar = true;
        for (int i = posicaoAtual.x - 1; i >= 0 && podeContinuar; i--) {
            Posicao proximaPosicao = new Posicao(i, posicaoAtual.y);
            podeContinuar = this.addPosicaoValida(proximaPosicao, movimentosPossiveis);
        }
    }

    private void addMovimentosBaixo(ArrayList<Posicao> movimentosPossiveis, Posicao posicaoAtual) {
        boolean podeContinuar = true;
        for (int i = posicaoAtual.y + 1; i < 8 && podeContinuar; i++) {
            Posicao proximaPosicao = new Posicao(posicaoAtual.x, i);
            podeContinuar = this.addPosicaoValida(proximaPosicao, movimentosPossiveis);
        }
    }

    private void addMovimentosCima(ArrayList<Posicao> movimentosPossiveis, Posicao posicaoAtual) {
        boolean podeContinuar = true;
        for (int i = posicaoAtual.y - 1; i >= 0 && podeContinuar; i--) {
            Posicao proximaPosicao = new Posicao(posicaoAtual.x, i);
            podeContinuar = this.addPosicaoValida(proximaPosicao, movimentosPossiveis);
        }
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
