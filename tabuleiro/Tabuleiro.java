package tabuleiro;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import config.Config;
import gui.Sprite;
import pecas.Peca;
import utils.Posicao;

public class Tabuleiro {
    private Sprite sprite;
    private ArrayList<Peca> pecasBrancas;
    private ArrayList<Peca> pecasBrancasCapturadas;
    private ArrayList<Peca> pecasPretas;
    private ArrayList<Peca> pecasPretasCapturadas;
    private Peca[][] posicoesPecas;
    private Historico historico;

    public Tabuleiro(ArrayList<Peca> pecasBrancas, ArrayList<Peca> pecasPretas) {
        this.sprite = new Sprite(Config.IMAGEM_TABULEIRO, 0, 0);
        this.historico = new Historico();

        this.pecasBrancas = pecasBrancas;
        this.pecasBrancasCapturadas = new ArrayList<Peca>();
        this.pecasBrancas.forEach(p -> p.setTabuleiro(this));

        this.pecasPretas = pecasPretas;
        this.pecasPretasCapturadas = new ArrayList<Peca>();
        this.pecasPretas.forEach(p -> p.setTabuleiro(this));

        this.posicoesPecas = new Peca[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.posicoesPecas[i][j] = null;
            }
        }
        this.pecasBrancas.forEach(peca -> {
            Posicao posicao = peca.getPosicaoTabuleiro();
            this.posicoesPecas[posicao.x][posicao.y] = peca;
        });
        this.pecasPretas.forEach(peca -> {
            Posicao posicao = peca.getPosicaoTabuleiro();
            this.posicoesPecas[posicao.x][posicao.y] = peca;
        });
    }

    private boolean posicaoDentroTabuleiro(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    public Peca getPeca(int x, int y) {
        if (!this.posicaoDentroTabuleiro(x, y)) {
            return null;
        }
        return this.posicoesPecas[x][y];
    }

    public Peca getPeca(Posicao p) {
        if (!this.posicaoDentroTabuleiro(p.x, p.y)) {
            return null;
        }
        return this.posicoesPecas[p.x][p.y];
    }

    public void desenha(Graphics graphics, ImageObserver observer) {
        this.sprite.desenha(graphics, observer);
    }
}
