package config;

import java.util.HashMap;

import pecas.TipoPeca;

public class PontuacaoPeca {
    public static HashMap<TipoPeca, Integer> initPontuacoes() {
        HashMap<TipoPeca, Integer> pontuacao = new HashMap<TipoPeca, Integer>();
        pontuacao.put(TipoPeca.PEAO, 10);
        pontuacao.put(TipoPeca.CAVALO, 25);
        pontuacao.put(TipoPeca.BISPO, 25);
        pontuacao.put(TipoPeca.TORRE, 50);
        pontuacao.put(TipoPeca.DAMA, 100);
        pontuacao.put(TipoPeca.REI, 1000);
        return pontuacao;
    }
}
