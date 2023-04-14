
import java.awt.Color;
import java.awt.Canvas;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import java.util.ArrayList;

import pecas.Peca;
import pecas.SetupPecas;
import utils.Config;
import utils.Cor;
import tabuleiro.Tabuleiro;

class Tela extends JFrame {

    // constructor
    Tela(Tabuleiro tabuleiro, ArrayList<Peca> pecasBrancas, ArrayList<Peca> pecasPretas) {
        super(Config.TITULO);

        // create a empty canvas
        Canvas canvas = new Canvas() {

            // paint the canvas
            public void paint(Graphics graphics) {
                // set color to black
                graphics.setColor(Color.black);
                // draw a square
                tabuleiro.desenha(graphics, this);
                pecasBrancas.forEach(peca -> peca.desenha(graphics, this));
                pecasPretas.forEach(peca -> peca.desenha(graphics, this));
            }
        };

        // set background
        canvas.setBackground(Color.white);

        add(canvas);
        setSize(Config.LARGURA_TELA, Config.ALTURA_TELA);
        setVisible(true);
    }

    // Main Method
    public static void main(String args[]) {
        Tabuleiro tabuleiro = new Tabuleiro();
        ArrayList<Peca> pecasBrancas = SetupPecas.setup(Cor.BRANCO, tabuleiro);
        ArrayList<Peca> pecasPretas = SetupPecas.setup(Cor.PRETO, tabuleiro);
        Tela tela = new Tela(tabuleiro, pecasBrancas, pecasPretas);
        tela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}