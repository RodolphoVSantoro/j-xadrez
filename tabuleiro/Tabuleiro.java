package tabuleiro;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import config.Config;
import gui.Sprite;
import pecas.Peca;

public class Tabuleiro {
    Sprite sprite;
    ArrayList<Peca> pecasBrancas;
    ArrayList<Peca> pecasPretas;

    public Tabuleiro(ArrayList<Peca> pecasBrancas, ArrayList<Peca> pecasPretas) {
        this.sprite = new Sprite(Config.IMAGEM_TABULEIRO, 0, 0);
        this.pecasBrancas = pecasBrancas;
        this.pecasPretas = pecasPretas;
        this.pecasBrancas.forEach(p -> p.setTabuleiro(this));
        this.pecasPretas.forEach(p -> p.setTabuleiro(this));
    }

    public void desenha(Graphics graphics, ImageObserver observer) {
        this.sprite.desenha(graphics, observer);
    }
}
