package maquinaDeRegras;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import config.Config;
import gui.Sprite;
import pecas.Peca;
import utils.Cor;
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

    public Peca getPeca(int x, int y) {
        return this.posicoesPecas[x][y];
    }

    public Peca getPeca(Posicao p) {
        return this.posicoesPecas[p.x][p.y];
    }

    public ArrayList<Peca> getPecasPretas() {
        return pecasPretas;
    }

    public ArrayList<Peca> getPecasBrancas() {
        return pecasBrancas;
    }

    public ArrayList<Peca> getPecas(Cor cor) {
        return cor == Cor.BRANCO ? this.pecasBrancas : this.pecasPretas;
    }

    public ArrayList<Peca> getPecasAdversario(Cor cor) {
        return cor == Cor.BRANCO ? this.pecasPretas : this.pecasBrancas;
    }

    public void desenha(Graphics graphics, ImageObserver observer) {
        this.sprite.desenha(graphics, observer);
    }
}
