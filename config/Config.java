package config;

import java.awt.Color;
import java.awt.Image;
import java.util.HashMap;

import pecas.TipoPeca;
import utils.Cor;
import utils.LeitorImagem;
import utils.Posicao;
import utils.SpritesXadrez;

public abstract class Config {
    public static final int LARGURA_TELA = 1295;
    public static final int ALTURA_TELA = 838;

    public static final int ALTURA_MARGEM = 0;
    public static final int LARGURA_MARGEM = -100;

    public static final int LARGURA_PECA = 100;
    public static final int ALTURA_PECA = 100;

    public static final int LARGURA_TABULEIRO = 8;
    public static final int ALTURA_TABULEIRO = 8;


    public static final Color colorOver = new Color(179, 250, 160);
    public static final Color colorClick = new Color(152, 184, 144);
    public static final Color borderColor = new Color(30, 136, 56);

    public static final String TITULO = "Xadrez";
    public static final String CAMINHO_IMAGENS = "assets/images/";
    public static final Image IMAGEM_TABULEIRO = LeitorImagem.tentaLer(Config.CAMINHO_IMAGENS + "tabuleiro.png");
    public static final HashMap<Cor, HashMap<TipoPeca, Image>> IMAGENS_PECAS = SpritesXadrez.inicializaSprites();

    public final static HashMap<Cor, HashMap<TipoPeca, Posicao[]>> POSICOES_INICIAIS = ConfigPosicoesIniciais
            .inicializa();

}
