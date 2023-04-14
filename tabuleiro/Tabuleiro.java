package tabuleiro;

import utils.Config;

import java.awt.Graphics;

import gui.Sprite;

public class Tabuleiro {
    Sprite sprite;

    public Tabuleiro() {
        this.sprite = new Sprite(Config.IMAGEM_TABULEIRO, 0, 0);
    }

    public void desenha(Graphics graphics) {
        this.sprite.desenha(graphics);
    }
}
