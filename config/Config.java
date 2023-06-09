package config;

import java.awt.Image;
import java.util.HashMap;

import pecas.TipoPeca;
import utils.Cor;
import utils.LeitorImagem;
import utils.Posicao;

public abstract class Config {

    public static final int LARGURA_TELA = 1300;
    public static final int ALTURA_TELA = 800;

    public static final int ALTURA_MARGEM = 80;
    public static final int LARGURA_MARGEM = 80;

    public static final int LARGURA_PECA = 80;
    public static final int ALTURA_PECA = 80;

    public static final int LARGURA_TABULEIRO = 80;
    public static final int ALTURA_TABULEIRO = 80;

    public static int PROFUNDIDADE_IA = 3;

    public static final String TITULO = "Xadrez";
    public static final String CAMINHO_IMAGENS = "assets/images/";
    public static Image IMAGEM_TABULEIRO = null;
    public static HashMap<Cor, HashMap<TipoPeca, Image>> IMAGENS_PECAS = null;

    public static boolean LOAD_FAKE_IMAGES = false;

    public static void loadImages() {
        IMAGEM_TABULEIRO = LeitorImagem.tentaLer(Config.CAMINHO_IMAGENS + "tabuleiro.png");
        IMAGEM_TABULEIRO = IMAGEM_TABULEIRO.getScaledInstance(640, 640, Image.SCALE_SMOOTH);
        IMAGENS_PECAS = SpritesXadrez.inicializaSprites();
    }

    public static final HashMap<Cor, HashMap<TipoPeca, Posicao[]>> POSICOES_INICIAIS = ConfigPosicoesIniciais
            .inicializa();
    public static final HashMap<TipoPeca, Integer> pontuacao = PontuacaoPeca.initPontuacoes();
}
