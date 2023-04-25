package config;

import java.util.HashMap;

import pecas.TipoPeca;
import utils.Cor;
import utils.Posicao;

public final class ConfigPosicoesIniciais {
    public static HashMap<Cor, HashMap<TipoPeca, Posicao[]>> inicializa() {
        HashMap<Cor, HashMap<TipoPeca, Posicao[]>> Posicoes = new HashMap<Cor, HashMap<TipoPeca, Posicao[]>>();
        Posicoes.put(Cor.BRANCO, Branco());
        Posicoes.put(Cor.PRETO, Preto());
        return Posicoes;
    }

    public static HashMap<TipoPeca, Posicao[]> Preto() {
        HashMap<TipoPeca, Posicao[]> posicoes = new HashMap<TipoPeca, Posicao[]>();

        Posicao[] TORRES = { new Posicao(4, 0), new Posicao(11, 0) };
        posicoes.put(TipoPeca.TORRE, TORRES);

        Posicao[] CAVALOS = { new Posicao(5, 0), new Posicao(10, 0) };
        posicoes.put(TipoPeca.CAVALO, CAVALOS);

        Posicao[] BISPOS = { new Posicao(6, 0), new Posicao(9, 0) };
        posicoes.put(TipoPeca.BISPO, BISPOS);

        Posicao[] DAMA = { new Posicao(7, 0) };
        posicoes.put(TipoPeca.DAMA, DAMA);

        Posicao[] REI = { new Posicao(8, 0) };
        posicoes.put(TipoPeca.REI, REI);

        Posicao[] PEAO = {
                new Posicao(4, 1),
                new Posicao(5, 1),
                new Posicao(6, 1),
                new Posicao(7, 1),
                new Posicao(8, 1),
                new Posicao(9, 1),
                new Posicao(10, 1),
                new Posicao(11, 1)
        };
        posicoes.put(TipoPeca.PEAO, PEAO);

        return posicoes;
    }

    public static HashMap<TipoPeca, Posicao[]> Branco() {
        HashMap<TipoPeca, Posicao[]> posicoes = new HashMap<TipoPeca, Posicao[]>();

        Posicao[] TORRES = { new Posicao(4, 7), new Posicao(11, 7) };
        posicoes.put(TipoPeca.TORRE, TORRES);

        Posicao[] CAVALOS = { new Posicao(5, 7), new Posicao(10, 7) };
        posicoes.put(TipoPeca.CAVALO, CAVALOS);

        Posicao[] BISPOS = { new Posicao(6, 7), new Posicao(9, 7) };
        posicoes.put(TipoPeca.BISPO, BISPOS);

        Posicao[] DAMA = { new Posicao(7, 7) };
        posicoes.put(TipoPeca.DAMA, DAMA);

        Posicao[] REI = { new Posicao(8, 7) };
        posicoes.put(TipoPeca.REI, REI);

        Posicao[] PEAO = {
                new Posicao(4, 6),
                new Posicao(5, 6),
                new Posicao(6, 6),
                new Posicao(7, 6),
                new Posicao(8, 6),
                new Posicao(9, 6),
                new Posicao(10, 6),
                new Posicao(11, 6)
        };
        posicoes.put(TipoPeca.PEAO, PEAO);

        return posicoes;
    }
}