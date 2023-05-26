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

        Posicao[] TORRES = { new Posicao(1, 3), new Posicao(7, 0) };
        posicoes.put(TipoPeca.TORRE, TORRES);

        Posicao[] CAVALOS = { new Posicao(1, 0), new Posicao(6, 0) };
        posicoes.put(TipoPeca.CAVALO, CAVALOS);

        Posicao[] BISPOS = { new Posicao(2, 0), new Posicao(5, 0) };
        posicoes.put(TipoPeca.BISPO, BISPOS);

        Posicao[] DAMA = { new Posicao(3, 0) };
        posicoes.put(TipoPeca.DAMA, DAMA);

        Posicao[] REI = { new Posicao(4, 0) };
        posicoes.put(TipoPeca.REI, REI);

        Posicao[] PEAO = {
                new Posicao(0, 2),
                new Posicao(1, 1),
                new Posicao(2, 1),
                new Posicao(3, 1),
                new Posicao(4, 1),
                new Posicao(5, 1),
                new Posicao(6, 1),
                new Posicao(7, 1)
        };
        posicoes.put(TipoPeca.PEAO, PEAO);

        return posicoes;
    }

    public static HashMap<TipoPeca, Posicao[]> Branco() {
        HashMap<TipoPeca, Posicao[]> posicoes = new HashMap<TipoPeca, Posicao[]>();

        Posicao[] TORRES = { new Posicao(0, 7), new Posicao(7, 7) };
        posicoes.put(TipoPeca.TORRE, TORRES);

        Posicao[] CAVALOS = { new Posicao(1, 7), new Posicao(6, 7) };
        posicoes.put(TipoPeca.CAVALO, CAVALOS);

        Posicao[] BISPOS = { new Posicao(2, 7), new Posicao(5, 7) };
        posicoes.put(TipoPeca.BISPO, BISPOS);

        Posicao[] DAMA = { new Posicao(3, 7) };
        posicoes.put(TipoPeca.DAMA, DAMA);

        Posicao[] REI = { new Posicao(4, 7) };
        posicoes.put(TipoPeca.REI, REI);

        Posicao[] PEAO = {
                new Posicao(0, 1),
                new Posicao(1, 6),
                new Posicao(2, 6),
                new Posicao(3, 6),
                new Posicao(4, 6),
                new Posicao(5, 6),
                new Posicao(6, 6),
                new Posicao(7, 6)
        };
        posicoes.put(TipoPeca.PEAO, PEAO);

        return posicoes;
    }
}