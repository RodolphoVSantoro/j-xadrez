package utils;

import java.awt.Image;

import javax.imageio.ImageIO;

public abstract class LeitorImagem {
    public static Image tentaLer(String path) {
        try {
            return ImageIO.read(new java.io.File(path));
        } catch (java.io.IOException e) {
            System.out.println("Erro ao ler imagem: " + path);
            throw new RuntimeException(e);
        }
    }
}
