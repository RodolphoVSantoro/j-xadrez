
import java.awt.Color;
import java.awt.Canvas;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import java.util.ArrayList;

import pecas.Peca;
import utils.Config;
import utils.Cor;
import tabuleiro.SetupPecas;
import tabuleiro.Tabuleiro;

class Tela extends JFrame {

    // constructor
    Tela(Tabuleiro tabuleiro, ArrayList<Peca> pecasBrancas, ArrayList<Peca> pecasPretas) {
        super("xadrez");

        // create a empty canvas
        Canvas canvas = new Canvas() {

            // paint the canvas
            public void paint(Graphics graphics) {
                // set color to black
                graphics.setColor(Color.black);
                // draw a square
                tabuleiro.desenha(graphics);
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
        ArrayList<Peca> pecasBrancas = SetupPecas.setup(Cor.BRANCO);
        ArrayList<Peca> pecasPretas = SetupPecas.setup(Cor.PRETO);
        Tela tela = new Tela(tabuleiro, pecasBrancas, pecasPretas);
        tela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}