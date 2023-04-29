package maquinaDeRegras;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.HashMap;

import config.Config;
import gui.Sprite;
import pecas.Peca;
import utils.Cor;
import utils.Posicao;

public class Tabuleiro {
    private Sprite sprite;
    private HashMap<Cor, ArrayList<Peca>> pecas;
    private HashMap<Cor, ArrayList<Peca>> pecasCapturadas;
    private Peca[][] posicoesPecas;

    public Tabuleiro(ArrayList<Peca> pecasBrancas, ArrayList<Peca> pecasPretas) {
        this.sprite = new Sprite(Config.IMAGEM_TABULEIRO, 0, 0);

        this.pecas = new HashMap<Cor, ArrayList<Peca>>();
        this.pecasCapturadas = new HashMap<Cor, ArrayList<Peca>>();

        pecasBrancas.forEach(p -> p.setTabuleiro(this));
        this.pecas.put(Cor.BRANCO, pecasBrancas);
        this.pecasCapturadas.put(Cor.BRANCO, new ArrayList<Peca>());

        pecasPretas.forEach(p -> p.setTabuleiro(this));
        this.pecas.put(Cor.PRETO, pecasPretas);
        this.pecasCapturadas.put(Cor.PRETO, new ArrayList<Peca>());

        this.posicoesPecas = new Peca[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.posicoesPecas[i][j] = null;
            }
        }

        this.pecas.get(Cor.BRANCO).forEach(peca -> {
            Posicao posicao = peca.getPosicaoTabuleiro();
            this.posicoesPecas[posicao.x][posicao.y] = peca;
        });
        this.pecas.get(Cor.PRETO).forEach(peca -> {
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

    public ArrayList<Peca> getPecas(Cor corPecas) {
        return this.getPecas(corPecas);
    }

    public ArrayList<Peca> getPecasJogador(Cor corJogador) {
        return this.getPecas(corJogador);
    }

    public ArrayList<Peca> getPecasAdversario(Cor cor) {
        Cor corAdversario = cor == Cor.BRANCO ? Cor.PRETO : Cor.BRANCO;
        return this.getPecas(corAdversario);
    }

    public void movePeca(Peca peca, Posicao posicaoPosterior) {
        Posicao posicaoAnterior = peca.getPosicaoTabuleiro();
        peca.setPosicaoTabuleiro(posicaoPosterior);
        this.posicoesPecas[posicaoAnterior.x][posicaoAnterior.y] = null;
        if (this.posicoesPecas[posicaoPosterior.x][posicaoPosterior.y] != null) {
            Peca pecaCapturada = this.posicoesPecas[posicaoPosterior.x][posicaoPosterior.y];
            this.pecasCapturadas.get(Cor.BRANCO).add(pecaCapturada);
        }
        this.posicoesPecas[posicaoPosterior.x][posicaoPosterior.y] = peca;
    }

    public void desenha(Graphics graphics, ImageObserver observer) {
        this.sprite.desenha(graphics, observer);
    }
}
