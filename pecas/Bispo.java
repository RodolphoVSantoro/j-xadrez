package pecas;

import utils.Posicao;
import tabuleiro.Tabuleiro;
import utils.Cor;

public class Bispo extends Peca {

    public Bispo(Posicao posicao, Cor cor, Tabuleiro tabuleiro) {
        super(posicao, cor, TipoPeca.BISPO, tabuleiro);
    }

    @Override
    protected boolean podeMover(Posicao posicao) {
        return true;
    }

    @Override
    protected boolean podeCapturar(Posicao posicao) {
        return false;
    }

    @Override
    protected Peca tentaCapturar(Posicao posicao) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tentaCapturar'");
    }

    @Override
    public boolean tentaMover(Posicao posicao) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tentaMover'");
    }

}
