package maquinaDeRegras;

import pecas.Peca;
import utils.Posicao;

public class Movimento {
    private Peca peca;
    private Posicao posicaoAnterior;
    private Posicao posicaoPosterior;
    private Peca pecaCapturada;

    public Movimento(Peca peca, Posicao posicaoAnterior, Posicao posicaoPosterior) {
        this.peca = peca;
        this.posicaoAnterior = posicaoAnterior;
        this.posicaoPosterior = posicaoPosterior;
        this.pecaCapturada = null;
    }

    public Movimento(Peca peca, Posicao posicaoAnterior, Posicao posicaoPosterior, Peca pecaCapturada) {
        this.peca = peca;
        this.posicaoAnterior = posicaoAnterior;
        this.posicaoPosterior = posicaoPosterior;
        this.pecaCapturada = pecaCapturada;
    }

    public Peca getPecaCapturada() {
        return pecaCapturada;
    }

    public Peca getPeca() {
        return peca;
    }

    public Posicao getPosicaoAnterior() {
        return posicaoAnterior;
    }

    public Posicao getPosicaoPosterior() {
        return posicaoPosterior;
    }
}
