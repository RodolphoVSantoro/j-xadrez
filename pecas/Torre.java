package pecas;

import utils.Posicao;
import utils.Cor;

public class Torre extends Peca {

    public Torre(Posicao posicao, Cor cor) {
        super(posicao, cor, TipoPeca.TORRE);
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
