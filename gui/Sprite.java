package gui;

import java.awt.Graphics;
import java.awt.Image;

import utils.Posicao;

public class Sprite {
    private Image image;
    public Posicao posicao;

    public Sprite(Image image, int x, int y) {
        this.image = image;
        this.posicao = new Posicao(x, y);
    }

    public Sprite(Image image, Posicao posicao) {
        this.image = image;
        this.posicao = new Posicao(posicao.x, posicao.y);
    }

    public void desenha(Graphics graphics) {
        graphics.drawImage(this.image, this.posicao.x, this.posicao.y, null);
    }
}
