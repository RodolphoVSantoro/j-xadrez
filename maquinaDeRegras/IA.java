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
    private int profundidadeConfigurada;
    private Movimento melhorMovimento;

    public IA(Cor cor, int nivelDificuldadeIA) {
        this.cor = cor;
        this.profundidadeConfigurada = nivelDificuldadeIA;
        this.melhorMovimento = null;
    }

    // Injetar dependencias tabuleiro e maquinaDeRegras
    public void setTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public void setMaquinaDeRegras(MaquinaDeRegras maquinaDeRegras) {
        this.maquinaDeRegras = maquinaDeRegras;
    }

    public Movimento getIAMovimento() {
        this.melhorMovimento = null;
        int profundidade = Config.PROFUNDIDADE_IA;
        this.minMax(profundidade);
        if (this.melhorMovimento == null) {
            throw new RuntimeException("IA falhou ao procurar movimento");
        }
        return this.melhorMovimento;
    }

    private void minMax(int profundidade) {
        if (this.cor == Cor.PRETO) {
            this.max(profundidade);
        } else {
            this.min(profundidade);
        }
    }

    private int max(int profundidade) {
        if (profundidade <= 0 || this.maquinaDeRegras.chegouFimDeJogo()) {
            return this.getValorTabuleiro();
        }
        int melhorValor = Integer.MIN_VALUE;
        ArrayList<Peca> pecas = this.tabuleiro.getPecas(this.cor);
        for (Peca peca : pecas) {
            for (Posicao posicao : peca.getMovimentosPossiveis()) {
                Movimento novoMovimento = new Movimento(peca, peca.getPosicaoTabuleiro(), posicao);
                boolean movimentou = this.maquinaDeRegras.executaMovimento(novoMovimento);
                if (!movimentou) {
                    throw new RuntimeException("Movimento inválido computando minMax");
                }

                int valor = -this.min(profundidade - 1);

                valor *= Math.random() / 10.0 + 1.0;
                if (valor > melhorValor) {
                    melhorValor = valor;
                    if (Cor.PRETO == this.cor) {
                        this.melhorMovimento = novoMovimento;
                    }
                }
                this.maquinaDeRegras.desfazUltimoMovimento();
            }
        }
        return melhorValor;
    }

    private int min(int profundidade) {
        if (profundidade <= 0 || this.maquinaDeRegras.chegouFimDeJogo()) {
            return this.getValorTabuleiro();
        }
        int melhorValor = Integer.MIN_VALUE;

        Cor adversarioIA = this.cor == Cor.BRANCO ? Cor.PRETO : Cor.BRANCO;
        ArrayList<Peca> pecas = this.tabuleiro.getPecas(adversarioIA);
        for (Peca peca : pecas) {
            for (Posicao posicao : peca.getMovimentosPossiveis()) {
                Movimento novoMovimento = new Movimento(peca, peca.getPosicaoTabuleiro(), posicao);
                boolean movimentou = this.maquinaDeRegras.executaMovimento(novoMovimento);
                if (!movimentou) {
                    throw new RuntimeException("Movimento inválido computando minMax");
                }

                int valorTabuleiro = -this.max(profundidade - 1);

                valorTabuleiro *= Math.random() / 10.0 + 1.0;
                if (valorTabuleiro > melhorValor) {
                    melhorValor = valorTabuleiro;
                    if (Cor.BRANCO == this.cor) {
                        this.melhorMovimento = novoMovimento;
                    }
                }
                this.maquinaDeRegras.desfazUltimoMovimento();
            }
        }
        return melhorValor;
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
}
