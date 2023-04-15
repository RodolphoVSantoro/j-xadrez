package utils;

import java.awt.Image;
import java.util.HashMap;

import config.Config;
import pecas.TipoPeca;

public abstract class SpritesXadrez {
    private static void tentaAdicionarImagem(HashMap<TipoPeca, Image> map, TipoPeca tipoPeca, String path) {
        Image image = LeitorImagem.tentaLer(Config.CAMINHO_IMAGENS + path);
        map.put(tipoPeca, image);
    }

    private static HashMap<TipoPeca, Image> inicializaSpritesPecas(Cor cor) {
        HashMap<TipoPeca, Image> IMAGENS_PECAS_BRANCAS = new HashMap<TipoPeca, Image>();
        String path = cor == Cor.PRETO ? "pecas/preto/" : "pecas/branco/";
        tentaAdicionarImagem(IMAGENS_PECAS_BRANCAS, TipoPeca.PEAO, path + "peao.png");
        tentaAdicionarImagem(IMAGENS_PECAS_BRANCAS, TipoPeca.TORRE, path + "torre.png");
        tentaAdicionarImagem(IMAGENS_PECAS_BRANCAS, TipoPeca.CAVALO, path + "cavalo.png");
        tentaAdicionarImagem(IMAGENS_PECAS_BRANCAS, TipoPeca.BISPO, path + "bispo.png");
        tentaAdicionarImagem(IMAGENS_PECAS_BRANCAS, TipoPeca.DAMA, path + "dama.png");
        tentaAdicionarImagem(IMAGENS_PECAS_BRANCAS, TipoPeca.REI, path + "rei.png");

        return IMAGENS_PECAS_BRANCAS;
    }

    public static HashMap<Cor, HashMap<TipoPeca, Image>> inicializaSprites() {
        HashMap<Cor, HashMap<TipoPeca, Image>> IMAGENS_PECAS = new HashMap<Cor, HashMap<TipoPeca, Image>>();
        IMAGENS_PECAS.put(Cor.PRETO, inicializaSpritesPecas(Cor.PRETO));
        IMAGENS_PECAS.put(Cor.BRANCO, inicializaSpritesPecas(Cor.BRANCO));
        return IMAGENS_PECAS;
    };

}
