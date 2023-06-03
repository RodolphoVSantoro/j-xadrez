package utils;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import config.Config;

public abstract class LeitorImagem {
    /**
     * Este método estático tenta carregar uma imagem do caminho especificado. Se o carregamento de imagens falsas 
     * for habilitado na configuração (Config.LOAD_FAKE_IMAGES), ele retorna uma imagem em branco.
     * Se um erro ocorrer durante a leitura da imagem, uma mensagem de erro será impressa e uma RuntimeException será lançada.
     *
     * @param path Uma string que representa o caminho do arquivo de imagem a ser carregado.
     * @return Retorna a imagem carregada. Se a opção de carregar imagens falsas estiver ativada, retorna uma imagem em branco.
     * @throws RuntimeException se ocorrer um erro ao ler a imagem.
     */
    public static Image tentaLer(String path) {
        if (Config.LOAD_FAKE_IMAGES) {
            return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try {
            return ImageIO.read(new java.io.File(path));
        } catch (java.io.IOException e) {
            System.err.println("Erro ao ler imagem: " + path);
            throw new RuntimeException(e);
        }
    }
}
