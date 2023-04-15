package tabuleiro;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

import config.Config;
import gui.Sprite;

public class Tabuleiro {
    Sprite sprite;

    public Tabuleiro() {
        this.sprite = new Sprite(Config.IMAGEM_TABULEIRO, 0, 0);
    }

    public void desenha(Graphics graphics, ImageObserver observer) {
        this.sprite.desenha(graphics, observer);
    }
}
