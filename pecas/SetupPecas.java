package pecas;

import java.util.ArrayList;
import java.util.HashMap;

import tabuleiro.Tabuleiro;
import utils.Config;
import utils.Cor;
import utils.Posicao;

public final class SetupPecas {
    public static ArrayList<Peca> setup(Cor cor, Tabuleiro tabuleiro) {
        ArrayList<Peca> pecas = new ArrayList<Peca>();

        HashMap<IdPeca, Posicao[]> configPosicoesIniciais = Config.POSICOES_INICIAIS.get(cor);

        for (Posicao posicao : configPosicoesIniciais.get(IdPeca.BISPO)) {
            pecas.add(new Bispo(posicao, cor, tabuleiro));
        }
        for (Posicao posicao : configPosicoesIniciais.get(IdPeca.CAVALO)) {
            pecas.add(new Cavalo(posicao, cor, tabuleiro));
        }
        for (Posicao posicao : configPosicoesIniciais.get(IdPeca.PEAO)) {
            pecas.add(new Peao(posicao, cor, tabuleiro));
        }
        for (Posicao posicao : configPosicoesIniciais.get(IdPeca.RAINHA)) {
            pecas.add(new Rainha(posicao, cor, tabuleiro));
        }
        for (Posicao posicao : configPosicoesIniciais.get(IdPeca.REI)) {
            pecas.add(new Rei(posicao, cor, tabuleiro));
        }
        for (Posicao posicao : configPosicoesIniciais.get(IdPeca.TORRE)) {
            pecas.add(new Torre(posicao, cor, tabuleiro));
        }
        return pecas;
    }
}
