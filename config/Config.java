package config;

import java.awt.Image;
import java.util.HashMap;

import pecas.TipoPeca;
import utils.Cor;
import utils.LeitorImagem;
import utils.Posicao;

public abstract class Config {
    public static final int LARGURA_TELA = 1000;
    public static final int ALTURA_TELA = 1000;

    public static final int ALTURA_MARGEM = 100;
    public static final int LARGURA_MARGEM = 100;

    public static final int LARGURA_PECA = 100;
    public static final int ALTURA_PECA = 100;

    public static final int LARGURA_TABULEIRO = 8;
    public static final int ALTURA_TABULEIRO = 8;

    public static int PROFUNDIDADE_IA = 3;

    public static final String TITULO = "Xadrez";
    public static final String CAMINHO_IMAGENS = "assets/images/";
    public static final Image IMAGEM_TABULEIRO = LeitorImagem.tentaLer(Config.CAMINHO_IMAGENS + "tabuleiro.png");
    public static final HashMap<Cor, HashMap<TipoPeca, Image>> IMAGENS_PECAS = SpritesXadrez.inicializaSprites();

    public final static HashMap<Cor, HashMap<TipoPeca, Posicao[]>> POSICOES_INICIAIS = ConfigPosicoesIniciais
            .inicializa();
    public static final HashMap<TipoPeca, Integer> pontuacao = PontuacaoPeca.initPontuacoes();
}
