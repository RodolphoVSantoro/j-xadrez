package maquinaDeRegras;

import pecas.Peca;
import utils.Posicao;

public class Movimento {
    private Peca peca;
    private Posicao posicaoAnterior;
    private Posicao posicaoPosterior;
    private Peca pecaCapturada;
    private double valor;

    public Movimento(Peca peca, Posicao posicaoAnterior, Posicao posicaoPosterior,double valor) {
        this.peca = peca;
        this.posicaoAnterior = posicaoAnterior;
        this.posicaoPosterior = posicaoPosterior;
        this.pecaCapturada = null;
        this.valor=valor;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor+=valor;
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

    public String stringify() {
        String strigified = "Peca: " + this.peca.stringify() + '\n';
        strigified += "Posicao Anterior: " + this.posicaoAnterior.stringify() + '\n';
        strigified += "Posicao Posterior: " + this.posicaoPosterior.stringify() + '\n';
        if (this.pecaCapturada != null) {
            strigified += "Peca Capturada: " + this.pecaCapturada.stringify() + '\n';
        }
        return strigified;
    }
}
