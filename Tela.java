
import java.awt.Color;
import java.awt.Canvas;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import config.Config;
import config.SetupPecas;
import maquinaDeRegras.MaquinaDeRegras;
import maquinaDeRegras.Movimento;
import maquinaDeRegras.Tabuleiro;

import java.util.ArrayList;

import pecas.Peca;
import utils.Cor;
import utils.Posicao;

class Tela extends JFrame {

    private Canvas canvas;
    private MaquinaDeRegras maquinaDeRegras;
    private Tabuleiro tabuleiro;
    private ArrayList<Peca> pecasBrancas;
    private ArrayList<Peca> pecasPretas;

    Tela() {
        super(Config.TITULO);

        canvas = new Canvas() {

            public void paint(Graphics graphics) {
                graphics.setColor(Color.black);
                tabuleiro.desenha(graphics, this);
                pecasBrancas.forEach(peca -> peca.desenha(graphics, this));
                pecasPretas.forEach(peca -> peca.desenha(graphics, this));
            }
        };

        canvas.setBackground(Color.white);

        add(canvas);
        setSize(Config.LARGURA_TELA, Config.ALTURA_TELA);
        setVisible(true);
    }

    public void repaint() {
        canvas.repaint();
    }

    private void initGame() {
        this.maquinaDeRegras = new MaquinaDeRegras(Cor.BRANCO, 2);
        this.pecasBrancas = SetupPecas.setup(Cor.BRANCO);
        this.pecasPretas = SetupPecas.setup(Cor.PRETO);
        this.tabuleiro = new Tabuleiro(pecasBrancas, pecasPretas);
        this.maquinaDeRegras.setTabuleiro(tabuleiro);
    }

    private void gameLoop() throws Error {
        Peca peao0 = this.tabuleiro.getPeca(new Posicao(0, 6));
        Movimento movimento = new Movimento(peao0, peao0.getPosicaoTabuleiro(), new Posicao(0, 4));
        this.maquinaDeRegras.executaMovimento(movimento);

        this.maquinaDeRegras.moveIA();

        Peca peao1 = this.tabuleiro.getPeca(new Posicao(4, 6));
        Movimento movimento1 = new Movimento(peao1, peao1.getPosicaoTabuleiro(), new Posicao(4, 4));
        this.maquinaDeRegras.executaMovimento(movimento1);

        this.maquinaDeRegras.moveIA();

        Movimento movimento2 = new Movimento(peao1, peao1.getPosicaoTabuleiro(), new Posicao(4, 3));
        this.maquinaDeRegras.executaMovimento(movimento2);

        this.maquinaDeRegras.moveIA();

        Peca torreE = this.tabuleiro.getPeca(new Posicao(0, 7));
        Movimento movimento3 = new Movimento(torreE, torreE.getPosicaoTabuleiro(), new Posicao(0, 5));
        this.maquinaDeRegras.executaMovimento(movimento3);

        this.maquinaDeRegras.moveIA();
    }

    // Main Method
    public static void main(String args[]) {
        Config.loadImages();
        Tela tela = new Tela();
        tela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        tela.initGame();
        tela.gameLoop();
        // while (true) {
        // tela.repaint();
        // try {
        // Thread.sleep(1000);
        // } catch (Exception e) {
        // }
        // }
    }
}