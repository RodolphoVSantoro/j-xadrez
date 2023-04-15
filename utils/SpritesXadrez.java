package utils;

import java.awt.Image;
import java.util.HashMap;

import config.Config;
import pecas.TipoPeca;

public abstract class SpritesXadrez {
    private static void tentaAdicionarImagem(HashMap<TipoPeca, Image> map, TipoPeca id, String path) {
        Image image = LeitorImagem.tentaLer(Config.CAMINHO_IMAGENS + path);
        map.put(id, image);
    }

    private static HashMap<TipoPeca, Image> inicializaSpritesBrancos() {
        HashMap<TipoPeca, Image> IMAGENS_PECAS_BRANCAS = new HashMap<TipoPeca, Image>();

        tentaAdicionarImagem(IMAGENS_PECAS_BRANCAS, TipoPeca.PEAO, "peao_preto.png");
        tentaAdicionarImagem(IMAGENS_PECAS_BRANCAS, TipoPeca.TORRE, "torre_branca.png");
        tentaAdicionarImagem(IMAGENS_PECAS_BRANCAS, TipoPeca.CAVALO, "cavalo_branco.png");
        tentaAdicionarImagem(IMAGENS_PECAS_BRANCAS, TipoPeca.BISPO, "bispo_branco.png");
        tentaAdicionarImagem(IMAGENS_PECAS_BRANCAS, TipoPeca.DAMA, "dama_branca.png");
        tentaAdicionarImagem(IMAGENS_PECAS_BRANCAS, TipoPeca.REI, "rei_branco.png");

        return IMAGENS_PECAS_BRANCAS;
    }

    private static HashMap<TipoPeca, Image> inicializaSpritesPretos() {
        HashMap<TipoPeca, Image> IMAGENS_PECAS_PRETAS = new HashMap<TipoPeca, Image>();

        tentaAdicionarImagem(IMAGENS_PECAS_PRETAS, TipoPeca.PEAO, "peao_branco.png");
        tentaAdicionarImagem(IMAGENS_PECAS_PRETAS, TipoPeca.TORRE, "torre_preta.png");
        tentaAdicionarImagem(IMAGENS_PECAS_PRETAS, TipoPeca.CAVALO, "cavalo_preto.png");
        tentaAdicionarImagem(IMAGENS_PECAS_PRETAS, TipoPeca.BISPO, "bispo_preto.png");
        tentaAdicionarImagem(IMAGENS_PECAS_PRETAS, TipoPeca.DAMA, "dama_preta.png");
        tentaAdicionarImagem(IMAGENS_PECAS_PRETAS, TipoPeca.REI, "rei_preto.png");

        return IMAGENS_PECAS_PRETAS;
    }

    public static HashMap<Cor, HashMap<TipoPeca, Image>> inicializaSprites() {
        HashMap<Cor, HashMap<TipoPeca, Image>> IMAGENS_PECAS = new HashMap<Cor, HashMap<TipoPeca, Image>>();
        IMAGENS_PECAS.put(Cor.PRETO, inicializaSpritesPretos());
        IMAGENS_PECAS.put(Cor.BRANCO, inicializaSpritesBrancos());
        return IMAGENS_PECAS;
    };

}
