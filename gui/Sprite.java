package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import utils.Config;
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

    public void move(int x, int y) {
        this.posicao = new Posicao(x, y);
    }

    public void move(Posicao posicao) {
        this.posicao = new Posicao(posicao.x, posicao.y);
    }

    public void desenha(Graphics graphics, ImageObserver observer) {
        graphics.drawImage(this.image, this.posicao.x + Config.LARGURA_MARGEM, this.posicao.y + Config.ALTURA_MARGEM,
                observer);
    }
}
