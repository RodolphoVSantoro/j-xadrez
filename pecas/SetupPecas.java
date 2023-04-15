package pecas;

import java.util.ArrayList;
import java.util.HashMap;

import config.Config;
import tabuleiro.Tabuleiro;
import utils.Cor;
import utils.Posicao;

public final class SetupPecas {
    public static ArrayList<Peca> setup(Cor cor, Tabuleiro tabuleiro) {
        ArrayList<Peca> pecas = new ArrayList<Peca>();

        HashMap<TipoPeca, Posicao[]> configPosicoesIniciais = Config.POSICOES_INICIAIS.get(cor);

        for (Posicao posicao : configPosicoesIniciais.get(TipoPeca.BISPO)) {
            pecas.add(new Bispo(posicao, cor, tabuleiro));
        }
        for (Posicao posicao : configPosicoesIniciais.get(TipoPeca.CAVALO)) {
            pecas.add(new Cavalo(posicao, cor, tabuleiro));
        }
        for (Posicao posicao : configPosicoesIniciais.get(TipoPeca.PEAO)) {
            pecas.add(new Peao(posicao, cor, tabuleiro));
        }
        for (Posicao posicao : configPosicoesIniciais.get(TipoPeca.DAMA)) {
            pecas.add(new Dama(posicao, cor, tabuleiro));
        }
        for (Posicao posicao : configPosicoesIniciais.get(TipoPeca.REI)) {
            pecas.add(new Rei(posicao, cor, tabuleiro));
        }
        for (Posicao posicao : configPosicoesIniciais.get(TipoPeca.TORRE)) {
            pecas.add(new Torre(posicao, cor, tabuleiro));
        }
        return pecas;
    }
}
