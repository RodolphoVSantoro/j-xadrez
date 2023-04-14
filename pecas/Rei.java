package pecas;

import utils.Posicao;
import utils.Cor;

import tabuleiro.Tabuleiro;

public class Rei extends Peca {

    public Rei(Posicao posicao, Cor cor, Tabuleiro tabuleiro) {
        super(posicao, cor, IdPeca.REI, tabuleiro);
    }

    @Override
    protected boolean podeMover(Posicao posicao) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'podeMover'");
    }

    @Override
    protected boolean podeCapturar(Posicao posicao) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'podeCapturar'");
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
