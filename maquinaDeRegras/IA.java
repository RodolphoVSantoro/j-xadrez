package maquinaDeRegras;

import java.util.ArrayList;

import config.Config;
import pecas.Peca;
import utils.Cor;
import utils.Posicao;

public class IA {
    private Cor cor;
    private Tabuleiro tabuleiro;
    private MaquinaDeRegras maquinaDeRegras;

    public IA(Cor cor) {
        this.cor = cor;
    }

    // Injetar dependencias tabuleiro e maquinaDeRegras
    public void setTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public void setMaquinaDeRegras(MaquinaDeRegras maquinaDeRegras) {
        this.maquinaDeRegras = maquinaDeRegras;
    }

    public Movimento getIAMovimento() {
        int profundidade = Config.PROFUNDIDADE_IA;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        boolean maximizando = true;
        MovimentoPontuado movimentoPontuado = this.minMax(null, profundidade, alpha, beta, maximizando);
        return movimentoPontuado.movimento;
    }

    private MovimentoPontuado minMax(Movimento movimento, int profundidade, int alpha, int beta, boolean maximizando) {
        if (profundidade == 0 || maquinaDeRegras.chegouFimDeJogo()) {
            return new MovimentoPontuado(movimento, this.getValorTabuleiro());
        }
        if (maximizando) {
            return this.max(profundidade, alpha, beta);
        } else {
            return this.min(profundidade, alpha, beta);
        }
    }

    private MovimentoPontuado max(int profundidade, int alpha, int beta) {
        int maxEval = Integer.MIN_VALUE;
        ArrayList<Peca> pecas = this.tabuleiro.getPecas(this.cor);
        MovimentoPontuado movimentoPontuado = null;

        for (Peca peca : pecas) {
            for (Posicao posicao : peca.getMovimentosPossiveis()) {
                Movimento novoMovimento = new Movimento(peca, peca.getPosicaoTabuleiro(), posicao);
                boolean movimentou = maquinaDeRegras.executaMovimento(novoMovimento);
                if (!movimentou) {
                    throw new RuntimeException("Movimento inválido");
                }

                movimentoPontuado = this.minMax(novoMovimento, profundidade - 1, alpha, beta, false);
                movimentoPontuado.pontuacao *= Math.random() / 10.0 + 1.0;
                maxEval = Math.max(maxEval, movimentoPontuado.pontuacao);
                alpha = Math.max(alpha, movimentoPontuado.pontuacao);

                maquinaDeRegras.desfazUltimoMovimento();

                if (beta <= alpha) {
                    return movimentoPontuado;
                }
            }
        }
        return movimentoPontuado;

    }

    private MovimentoPontuado min(int profundidade, int alpha, int beta) {
        int minEval = Integer.MAX_VALUE;
        ArrayList<Peca> pecas = this.tabuleiro.getPecasAdversario(this.cor);
        MovimentoPontuado movimentoPontuado = null;

        for (Peca peca : pecas) {
            for (Posicao posicao : peca.getMovimentosPossiveis()) {
                Movimento novoMovimento = new Movimento(peca, peca.getPosicaoTabuleiro(), posicao);
                boolean movimentou = maquinaDeRegras.executaMovimento(novoMovimento);
                if (!movimentou) {
                    throw new RuntimeException("Movimento inválido");
                }

                movimentoPontuado = this.minMax(novoMovimento, profundidade - 1, alpha, beta, true);
                movimentoPontuado.pontuacao *= Math.random() / 10.0 + 1.0;
                minEval = Math.min(minEval, movimentoPontuado.pontuacao);
                beta = Math.min(beta, movimentoPontuado.pontuacao);

                maquinaDeRegras.desfazUltimoMovimento();

                if (beta <= alpha) {
                    return movimentoPontuado;
                }
            }
        }
        return movimentoPontuado;
    }

    private int getValorTabuleiro() {
        ArrayList<Peca> pecas = this.tabuleiro.getPecas(this.cor);
        ArrayList<Peca> pecasAdversario = this.tabuleiro.getPecasAdversario(this.cor);

        int valorPecas = 0;
        for (Peca peca : pecas) {
            valorPecas += Config.pontuacao.get(peca.getTipoPeca());
        }

        int valorPecasAdversario = 0;
        for (Peca peca : pecasAdversario) {
            valorPecasAdversario += Config.pontuacao.get(peca.getTipoPeca());
        }

        return valorPecas - valorPecasAdversario;
    }

    private class MovimentoPontuado {
        public Movimento movimento;
        public int pontuacao;

        public MovimentoPontuado(Movimento movimento, int pontuacao) {
            this.movimento = movimento;
            this.pontuacao = pontuacao;
        }
    }
}