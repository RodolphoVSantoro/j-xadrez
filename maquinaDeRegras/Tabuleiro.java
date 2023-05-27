package maquinaDeRegras;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import config.Config;
import gui.Sprite;
import pecas.Peca;
import utils.Cor;
import utils.Posicao;

public class Tabuleiro extends JPanel{
    private Sprite sprite;
    private HashMap<Cor, ArrayList<Peca>> pecas;
    private HashMap<Cor, ArrayList<Peca>> pecasCapturadas;
    private Peca[][] posicoesPecas;

    public Tabuleiro(ArrayList<Peca> pecasBrancas, ArrayList<Peca> pecasPretas) {;
        
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

    public boolean posicaoDentroTabuleiro(int x, int y) {
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

    public ArrayList<Peca> getPecas(Cor corPecas) {
        return this.pecas.get(corPecas);
    }

    public ArrayList<Peca> getPecasJogador(Cor corJogador) {
        return this.getPecas(corJogador);
    }

    public ArrayList<Peca> getPecasAdversario(Cor cor) {
        Cor corAdversario = cor == Cor.BRANCO ? Cor.PRETO : Cor.BRANCO;
        return this.getPecas(corAdversario);
    }

    /*
     * Move a peça para a posição posterior
     * e retorna referência para
     * a peça capturada, se houver
     * captura.
     * Assume que a posição posterior é válida.
     */
    public Peca movePeca(Peca peca, Posicao posicaoPosterior,boolean ehIA) {
        Posicao posicaoAnterior = peca.getPosicaoTabuleiro();
        peca.setPosicaoTabuleiro(posicaoPosterior,ehIA);
        this.posicoesPecas[posicaoAnterior.x][posicaoAnterior.y] = null;
        Peca pecaCapturada = null;
        if (this.posicoesPecas[posicaoPosterior.x][posicaoPosterior.y] != null) {
            pecaCapturada = this.posicoesPecas[posicaoPosterior.x][posicaoPosterior.y];
            this.pecasCapturadas.get(pecaCapturada.getCor()).add(pecaCapturada);
            pecaCapturada.captura(ehIA);
        }
        this.posicoesPecas[posicaoPosterior.x][posicaoPosterior.y] = peca;
        return pecaCapturada;
    }

    public void recuperaPeca(Peca peca, Posicao posicao,boolean ehIA) {
        boolean pecaEstaCapturada = this.pecasCapturadas.get(peca.getCor()).stream().anyMatch(p -> p == peca);
        if (!pecaEstaCapturada) {
            throw new IllegalArgumentException("A peça não está capturada.");
        }
        boolean posicaoRecuperadaVazia = this.posicaoDentroTabuleiro(posicao.x, posicao.y)
                && this.posicoesPecas[posicao.x][posicao.y] != null;
        if (posicaoRecuperadaVazia) {
            throw new IllegalArgumentException("Peca tentou ser recuperada para uma posicao nao vazia.");
        }
        this.pecasCapturadas.get(peca.getCor()).remove(peca);
        peca.recupera(ehIA);
        this.posicoesPecas[posicao.x][posicao.y] = peca;
    }

    public void desenha(Graphics graphics, ImageObserver observer) {
        this.sprite.desenha(graphics, observer);
    }
}
