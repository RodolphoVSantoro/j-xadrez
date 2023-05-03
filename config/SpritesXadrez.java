package config;

import java.awt.Image;
import java.util.HashMap;

import pecas.TipoPeca;
import utils.Cor;
import utils.LeitorImagem;

public abstract class SpritesXadrez {
    private static void tentaAdicionarImagem(HashMap<TipoPeca, Image> map, TipoPeca tipoPeca, String path) {
        Image image = LeitorImagem.tentaLer(Config.CAMINHO_IMAGENS + path);
        map.put(tipoPeca, image);
    }

    private static HashMap<TipoPeca, Image> inicializaSpritesPecas(Cor cor) {
        HashMap<TipoPeca, Image> IMAGENS = new HashMap<TipoPeca, Image>();
        String path = cor == Cor.PRETO ? "pecas/preto/" : "pecas/branco/";
        tentaAdicionarImagem(IMAGENS, TipoPeca.PEAO, path + "peao.png");
        tentaAdicionarImagem(IMAGENS, TipoPeca.TORRE, path + "torre.png");
        tentaAdicionarImagem(IMAGENS, TipoPeca.CAVALO, path + "cavalo.png");
        tentaAdicionarImagem(IMAGENS, TipoPeca.BISPO, path + "bispo.png");
        tentaAdicionarImagem(IMAGENS, TipoPeca.DAMA, path + "dama.png");
        tentaAdicionarImagem(IMAGENS, TipoPeca.REI, path + "rei.png");

        return IMAGENS;
    }

    public static HashMap<Cor, HashMap<TipoPeca, Image>> inicializaSprites() {
        HashMap<Cor, HashMap<TipoPeca, Image>> IMAGENS_PECAS = new HashMap<Cor, HashMap<TipoPeca, Image>>();
        IMAGENS_PECAS.put(Cor.PRETO, inicializaSpritesPecas(Cor.PRETO));
        IMAGENS_PECAS.put(Cor.BRANCO, inicializaSpritesPecas(Cor.BRANCO));
        return IMAGENS_PECAS;
    };

}
