
import java.awt.Color;
import java.awt.Canvas;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import config.Config;
import config.SetupPecas;
import events.Input;
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
    private Input input;
    

    Tela() {
        super();

        canvas = new Canvas() {

            public void paint(Graphics graphics) {
                graphics.setColor(Color.black);
                if(maquinaDeRegras != null){
                    tabuleiro.desenha(graphics, this);
                    pecasBrancas.forEach(peca -> peca.desenha(graphics, this));
                    pecasPretas.forEach(peca -> peca.desenha(graphics, this));
                }
            }
        };

        canvas.setBackground(Color.white);

        add(canvas);
        setSize(Config.LARGURA_TELA, Config.ALTURA_TELA);
        setVisible(true);
        setResizable(false);
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
        this.input = new Input(maquinaDeRegras, canvas);
        this.canvas.addMouseListener(input);
        //this.canvas.addMouseMotionListener(input);
    }

    private void gameLoop() throws Error, InterruptedException {
        // Thread.currentThread();
        // Peca peao0 = this.tabuleiro.getPeca(new Posicao(0, 6));
        // Movimento movimento = new Movimento(peao0, peao0.getPosicaoTabuleiro(), new Posicao(0, 4),0);
        // this.maquinaDeRegras.executaMovimento(movimento);
        
        // Thread.sleep(1000);
        // this.repaint();

        // this.maquinaDeRegras.moveIA();

        // Thread.sleep(1000);
        // this.repaint();

        // Peca peao1 = this.tabuleiro.getPeca(new Posicao(4, 6));
        // Movimento movimento1 = new Movimento(peao1, peao1.getPosicaoTabuleiro(), new Posicao(4, 4),0);
        // this.maquinaDeRegras.executaMovimento(movimento1);

        // Thread.sleep(1000);
        // this.repaint();

        // this.maquinaDeRegras.moveIA();
        
        // Thread.sleep(1000);
        // this.repaint();

        // Movimento movimento2 = new Movimento(peao1, peao1.getPosicaoTabuleiro(), new Posicao(4, 3),0);
        // this.maquinaDeRegras.executaMovimento(movimento2);
        
        // Thread.sleep(1000);
        // this.repaint();

        // this.maquinaDeRegras.moveIA();
        
        // Thread.sleep(1000);
        // this.repaint();

        // Peca torreE = this.tabuleiro.getPeca(new Posicao(0, 7));
        // Movimento movimento3 = new Movimento(torreE, torreE.getPosicaoTabuleiro(), new Posicao(0, 5),0);
        // this.maquinaDeRegras.executaMovimento(movimento3);
        
        // Thread.sleep(1000);
        // this.repaint();

        // this.maquinaDeRegras.moveIA();
        
        // Thread.sleep(1000);
        // this.repaint();

        // Movimento movimento4 = new Movimento(peao1, peao1.getPosicaoTabuleiro(), new Posicao(4, 2),0);
        // this.maquinaDeRegras.executaMovimento(movimento4);

        // Thread.sleep(1000);
        // this.repaint();

        // this.maquinaDeRegras.moveIA();
        
        // Thread.sleep(1000);
        // this.repaint();

        // System.out.println("cabou");
        long i=0;
        long timeOld=System.nanoTime() / 1000000;
        while(!this.maquinaDeRegras.chegouFimDeJogo()){       
            if(this.maquinaDeRegras.getTurno()==Cor.PRETO){
                this.maquinaDeRegras.moveIA();
                System.out.println("sua vez");
                this.repaint();
                
            };
            // if(i>=100){
                
            //     System.out.println("cabou "+i);
            //     timeOld=System.nanoTime() / 1000000;
            // };
            // i=(System.nanoTime() / 1000000)-timeOld;
        }
        System.out.println("vc venceu");

    }

    // Main Method
    public static void main(String args[]) throws InterruptedException, Error {
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