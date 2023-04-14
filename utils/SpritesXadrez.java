package utils;

import java.awt.Image;
import java.util.HashMap;

import pecas.IdPeca;

public abstract class SpritesXadrez {
    private static void tentaAdicionarImagem(HashMap<IdPeca, Image> map, IdPeca id, String path) {
        Image image = LeitorImagem.tentaLer(Config.CAMINHO_IMAGENS + path);
        map.put(id, image);
    }

    private static HashMap<IdPeca, Image> inicializaSpritesBrancos() {
        HashMap<IdPeca, Image> IMAGENS_PECAS_BRANCAS = new HashMap<IdPeca, Image>();

        tentaAdicionarImagem(IMAGENS_PECAS_BRANCAS, IdPeca.PEAO, "peao_preto.png");
        tentaAdicionarImagem(IMAGENS_PECAS_BRANCAS, IdPeca.TORRE, "torre_branca.png");
        tentaAdicionarImagem(IMAGENS_PECAS_BRANCAS, IdPeca.CAVALO, "cavalo_branco.png");
        tentaAdicionarImagem(IMAGENS_PECAS_BRANCAS, IdPeca.BISPO, "bispo_branco.png");
        tentaAdicionarImagem(IMAGENS_PECAS_BRANCAS, IdPeca.RAINHA, "rainha_branca.png");
        tentaAdicionarImagem(IMAGENS_PECAS_BRANCAS, IdPeca.REI, "rei_branco.png");

        return IMAGENS_PECAS_BRANCAS;
    }

    private static HashMap<IdPeca, Image> inicializaSpritesPretos() {
        HashMap<IdPeca, Image> IMAGENS_PECAS_PRETAS = new HashMap<IdPeca, Image>();

        tentaAdicionarImagem(IMAGENS_PECAS_PRETAS, IdPeca.PEAO, "peao_branco.png");
        tentaAdicionarImagem(IMAGENS_PECAS_PRETAS, IdPeca.TORRE, "torre_preta.png");
        tentaAdicionarImagem(IMAGENS_PECAS_PRETAS, IdPeca.CAVALO, "cavalo_preto.png");
        tentaAdicionarImagem(IMAGENS_PECAS_PRETAS, IdPeca.BISPO, "bispo_preto.png");
        tentaAdicionarImagem(IMAGENS_PECAS_PRETAS, IdPeca.RAINHA, "rainha_preta.png");
        tentaAdicionarImagem(IMAGENS_PECAS_PRETAS, IdPeca.REI, "rei_preto.png");

        return IMAGENS_PECAS_PRETAS;
    }

    public static HashMap<Cor, HashMap<IdPeca, Image>> inicializaSprites() {
        HashMap<Cor, HashMap<IdPeca, Image>> IMAGENS_PECAS = new HashMap<Cor, HashMap<IdPeca, Image>>();
        IMAGENS_PECAS.put(Cor.PRETO, inicializaSpritesPretos());
        IMAGENS_PECAS.put(Cor.BRANCO, inicializaSpritesBrancos());
        return IMAGENS_PECAS;
    };

}
