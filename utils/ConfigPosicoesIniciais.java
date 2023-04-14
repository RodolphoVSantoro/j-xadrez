package utils;

import java.util.HashMap;

import pecas.IdPeca;

public final class ConfigPosicoesIniciais {
    public static HashMap<Cor, HashMap<IdPeca, Posicao[]>> inicializa() {
        HashMap<Cor, HashMap<IdPeca, Posicao[]>> Posicoes = new HashMap<Cor, HashMap<IdPeca, Posicao[]>>();
        Posicoes.put(Cor.BRANCO, Branco());
        Posicoes.put(Cor.PRETO, Preto());
        return Posicoes;
    }

    public static HashMap<IdPeca, Posicao[]> Preto() {
        HashMap<IdPeca, Posicao[]> posicoes = new HashMap<IdPeca, Posicao[]>();

        Posicao[] TORRES = { new Posicao(0, 0), new Posicao(7, 0) };
        posicoes.put(IdPeca.TORRE, TORRES);

        Posicao[] CAVALOS = { new Posicao(1, 0), new Posicao(6, 0) };
        posicoes.put(IdPeca.CAVALO, CAVALOS);

        Posicao[] BISPOS = { new Posicao(2, 0), new Posicao(5, 0) };
        posicoes.put(IdPeca.BISPO, BISPOS);

        Posicao[] RAINHA = { new Posicao(3, 0) };
        posicoes.put(IdPeca.RAINHA, RAINHA);

        Posicao[] REI = { new Posicao(4, 0) };
        posicoes.put(IdPeca.REI, REI);

        Posicao[] PEAO = {
                new Posicao(0, 1),
                new Posicao(1, 1),
                new Posicao(2, 1),
                new Posicao(3, 1),
                new Posicao(4, 1),
                new Posicao(5, 1),
                new Posicao(6, 1),
                new Posicao(7, 1)
        };
        posicoes.put(IdPeca.PEAO, PEAO);

        return posicoes;
    }

    public static HashMap<IdPeca, Posicao[]> Branco() {
        HashMap<IdPeca, Posicao[]> posicoes = new HashMap<IdPeca, Posicao[]>();

        Posicao[] TORRES = { new Posicao(0, 7), new Posicao(7, 7) };
        posicoes.put(IdPeca.TORRE, TORRES);

        Posicao[] CAVALOS = { new Posicao(1, 7), new Posicao(6, 7) };
        posicoes.put(IdPeca.CAVALO, CAVALOS);

        Posicao[] BISPOS = { new Posicao(2, 7), new Posicao(5, 7) };
        posicoes.put(IdPeca.BISPO, BISPOS);

        Posicao[] RAINHA = { new Posicao(3, 7) };
        posicoes.put(IdPeca.RAINHA, RAINHA);

        Posicao[] REI = { new Posicao(4, 7) };
        posicoes.put(IdPeca.REI, REI);

        Posicao[] PEAO = {
                new Posicao(0, 6),
                new Posicao(1, 6),
                new Posicao(2, 6),
                new Posicao(3, 6),
                new Posicao(4, 6),
                new Posicao(5, 6),
                new Posicao(6, 6),
                new Posicao(7, 6)
        };
        posicoes.put(IdPeca.PEAO, PEAO);

        return posicoes;
    }
}