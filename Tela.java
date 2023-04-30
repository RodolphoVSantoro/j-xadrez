
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
import pecas.TipoPeca;
import utils.Cor;
import utils.Posicao;

class Tela extends JFrame {

    private MaquinaDeRegras maquinaDeRegras;
    private Tabuleiro tabuleiro;
    private ArrayList<Peca> pecasBrancas;
    private ArrayList<Peca> pecasPretas;

    Tela() {
        super(Config.TITULO);
        initGame();

        Canvas canvas = new Canvas() {

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

    private void initGame() {
        this.maquinaDeRegras = new MaquinaDeRegras(Cor.BRANCO, 2);
        this.pecasBrancas = SetupPecas.setup(Cor.BRANCO);
        this.pecasPretas = SetupPecas.setup(Cor.PRETO);
        this.tabuleiro = new Tabuleiro(pecasBrancas, pecasPretas);
        this.maquinaDeRegras.setTabuleiro(tabuleiro);

        Peca peao0 = pecasBrancas.stream().filter(p -> p.getTipoPeca() == TipoPeca.PEAO).findFirst().get();
        Movimento movimento = new Movimento(peao0, peao0.getPosicaoTabuleiro(), new Posicao(0, 4));
        this.maquinaDeRegras.executaMovimento(movimento);

        this.maquinaDeRegras.moveIA();
    }

    // Main Method
    public static void main(String args[]) {
        Config.loadImages();
        Tela tela = new Tela();
        tela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}