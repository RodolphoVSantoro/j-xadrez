package pecas;

import utils.Posicao;

import java.util.ArrayList;

import utils.Cor;

public class Torre extends Peca {

    public Torre(Posicao posicao, Cor cor) {
        super(posicao, cor, TipoPeca.TORRE, TipoPeca.TORRE);
    }

    @Override
    public ArrayList<Posicao> getMovimentosPossiveis(boolean pulaTeste) {
        ArrayList<Posicao> movimentosPossiveis = new ArrayList<Posicao>();

        this.addMovimentosEsquerda(movimentosPossiveis, posicaoTabuleiro);
        this.addMovimentosDireita(movimentosPossiveis, posicaoTabuleiro);
        this.addMovimentosCima(movimentosPossiveis, posicaoTabuleiro);
        this.addMovimentosBaixo(movimentosPossiveis, posicaoTabuleiro);

        if (!pulaTeste)
            this.checaValidadeMovimento(movimentosPossiveis);

        return movimentosPossiveis;
    }

    /**
     * Só chamar se já validou que não tem
     * peça no caminho
     */
    private boolean addPosicaoValida(Posicao proximaPosicao, ArrayList<Posicao> movimentosPossiveis) {
        if (!this.tabuleiro.posicaoDentroTabuleiro(proximaPosicao.x, proximaPosicao.y)) {
            return false;
        }
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
}
