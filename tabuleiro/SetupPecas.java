package tabuleiro;

import java.util.ArrayList;

import pecas.Bispo;
import pecas.Peca;

import utils.Config;
import utils.Cor;
import utils.Posicao;

public final class SetupPecas {
    public static ArrayList<Peca> setup(Cor cor) {
        ArrayList<Peca> pecas = new ArrayList<Peca>();
        for (Posicao posicao : Config.ConfigPosicoesIniciaisBranco.BISPOS) {
            pecas.add(new Bispo(posicao, cor, null));
        }
        for (Posicao posicao : Config.ConfigPosicoesIniciaisBranco.CAVALOS) {
            pecas.add(new Bispo(posicao, cor, null));
        }
        for (Posicao posicao : Config.ConfigPosicoesIniciaisBranco.PEAO) {
            pecas.add(new Bispo(posicao, cor, null));
        }
        for (Posicao posicao : Config.ConfigPosicoesIniciaisBranco.RAINHA) {
            pecas.add(new Bispo(posicao, cor, null));
        }
        for (Posicao posicao : Config.ConfigPosicoesIniciaisBranco.REI) {
            pecas.add(new Bispo(posicao, cor, null));
        }
        for (Posicao posicao : Config.ConfigPosicoesIniciaisBranco.TORRES) {
            pecas.add(new Bispo(posicao, cor, null));
        }
        return pecas;
    }
}
