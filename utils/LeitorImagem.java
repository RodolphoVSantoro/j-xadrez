package utils;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import config.Config;

public abstract class LeitorImagem {
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
