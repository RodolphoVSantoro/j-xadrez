package utils;

import java.awt.Image;
import java.util.HashMap;

import pecas.IdPeca;

public abstract class Config {
    public static final int LARGURA_TELA = 1000;
    public static final int ALTURA_TELA = 1000;
    public static final int LARGURA_TABULEIRO = 8;
    public static final int ALTURA_TABULEIRO = 8;

    public static final String TITULO = "Xadrez";
    public static final String CAMINHO_IMAGENS = "assets/images/";
    public static final Image IMAGEM_TABULEIRO = LeitorImagem.tentaLer(Config.CAMINHO_IMAGENS + "tabuleiro.png");
    public static final HashMap<Cor, HashMap<IdPeca, Image>> IMAGENS_PECAS = SpritesXadrez.inicializaSprites();

    public final static class ConfigPosicoesIniciaisPreto {
        public static final Posicao[] TORRES = { new Posicao(0, 0), new Posicao(7, 0) };
        public static final Posicao[] CAVALOS = { new Posicao(1, 0), new Posicao(6, 0) };
        public static final Posicao[] BISPOS = { new Posicao(2, 0), new Posicao(5, 0) };
        public static final Posicao[] RAINHA = { new Posicao(3, 0) };
        public static final Posicao[] REI = { new Posicao(4, 0) };
        public static final Posicao[] PEAO = {
                new Posicao(0, 1),
                new Posicao(1, 1),
                new Posicao(2, 1),
                new Posicao(3, 1),
                new Posicao(4, 1),
                new Posicao(5, 1),
                new Posicao(6, 1),
                new Posicao(7, 1)
        };
    }

    public final static class ConfigPosicoesIniciaisBranco {
        public static final Posicao[] TORRES = { new Posicao(0, 7), new Posicao(7, 7) };
        public static final Posicao[] CAVALOS = { new Posicao(1, 7), new Posicao(6, 7) };
        public static final Posicao[] BISPOS = { new Posicao(2, 7), new Posicao(5, 7) };
        public static final Posicao[] RAINHA = { new Posicao(3, 7) };
        public static final Posicao[] REI = { new Posicao(4, 7) };
        public static final Posicao[] PEAO = {
                new Posicao(0, 6),
                new Posicao(1, 6),
                new Posicao(2, 6),
                new Posicao(3, 6),
                new Posicao(4, 6),
                new Posicao(5, 6),
                new Posicao(6, 6),
                new Posicao(7, 6)
        };
    }
}
