package maquinaDeRegras;

import pecas.Peca;
import pecas.TipoPeca;
import utils.Cor;

public class MaquinaDeRegras {
    private Tabuleiro tabuleiro;
    private Cor jogador;
    private Cor adversario;
    private boolean IA;

    public boolean chegouFimDeJogo(Tabuleiro tabuleiro) {
        Peca reiBranco = tabuleiro.getPecasBrancas().stream().filter(p -> p.getTipoPeca() == TipoPeca.REI).findFirst()
                .get();
        Peca reiPreto = tabuleiro.getPecasPretas().stream().filter(p -> p.getTipoPeca() == TipoPeca.REI).findFirst()
                .get();
        if (reiBranco == null || reiPreto == null) {
            return true;
        }
        return false;
    }

    public boolean movimenta(Movimento movimento) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public void desfazMovimento(Movimento movimento) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
