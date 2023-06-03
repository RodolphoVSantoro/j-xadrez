package maquinaDeRegras;

import pecas.Peca;
import utils.Posicao;

public class Movimento {
    private Peca peca;
    private Posicao posicaoAnterior;
    private Posicao posicaoPosterior;
    private Peca peca2;
    private Posicao posicaoAnterior2;
    private Posicao posicaoPosterior2;
    private Peca pecaCapturada;
    private double valor;
    public boolean movimentoDuplo=false;

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

    public Movimento(Peca peca, Posicao posicaoAnterior, Posicao posicaoPosterior,Peca peca2, Posicao posicaoAnterior2, Posicao posicaoPosterior2,double valor) {
        this.movimentoDuplo=true;
        this.peca = peca;
        this.posicaoAnterior = posicaoAnterior;
        this.posicaoPosterior = posicaoPosterior;
        this.peca2 = peca2;
        this.posicaoAnterior2 = posicaoAnterior2;
        this.posicaoPosterior2 = posicaoPosterior2;
        this.pecaCapturada = null;
        this.valor=valor;
    }

    public Movimento(Peca peca, Posicao posicaoAnterior, Posicao posicaoPosterior,Peca peca2, Posicao posicaoAnterior2, Posicao posicaoPosterior2, Peca pecaCapturada) {
        this.movimentoDuplo=true;
        this.peca = peca;
        this.posicaoAnterior = posicaoAnterior;
        this.posicaoPosterior = posicaoPosterior;
        this.peca2 = peca2;
        this.posicaoAnterior2 = posicaoAnterior2;
        this.posicaoPosterior2 = posicaoPosterior2;
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

    public Peca getPeca2() {
        return peca2;
    }

    public Posicao getPosicaoAnterior2() {
        return posicaoAnterior2;
    }

    public Posicao getPosicaoPosterior2() {
        return posicaoPosterior2;
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
