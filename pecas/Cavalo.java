package pecas;

import utils.Posicao;
import utils.Cor;

public class Cavalo extends Peca {

    public Cavalo(Posicao posicao, Cor cor) {
        super(posicao, cor, TipoPeca.CAVALO);
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
