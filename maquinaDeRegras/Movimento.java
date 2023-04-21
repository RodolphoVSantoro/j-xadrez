package maquinaDeRegras;

import pecas.Peca;
import utils.Posicao;

public class Movimento{
    private Peca peca;
    private Posicao posicaoAnterior;
    private Posicao posicaoPosterior;
    Movimento(Peca peca, Posicao posicaoAnterior, Posicao posicaoPosterior){
        this.peca = peca;
        this.posicaoAnterior = posicaoAnterior;
        this.posicaoPosterior = posicaoPosterior;
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
