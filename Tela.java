
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
    private ArrayList<Posicao> possiveis;
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
                
                // Highlight
                if(input.selecionada != null){
                    possiveis = input.selecionada.getMovimentosPossiveis();
                    for(int i = 0; i < possiveis.size(); i++){
                        graphics.setColor(new Color(68, 180, 57, 190));
                        graphics.fillRect((possiveis.get(i).x + 1) * Config.LARGURA_TABULEIRO, 
                                          (possiveis.get(i).y + 1) * Config.LARGURA_TABULEIRO,
                                           Config.LARGURA_TABULEIRO,
                                           Config.LARGURA_TABULEIRO);
                        
                    }
                }
            }
        };

        canvas.setBackground(new Color(60, 40, 15));

        add(canvas);
        setSize(Config.LARGURA_TELA, Config.ALTURA_TELA);
        setVisible(true);
        setLocationRelativeTo(null);
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
    }

    private void gameLoop() throws Error, InterruptedException {
        
        while(!this.maquinaDeRegras.chegouFimDeJogo()){       
            if(this.maquinaDeRegras.getTurno()==Cor.PRETO){
                this.maquinaDeRegras.moveIA();
                //System.out.println("sua vez");
                this.repaint();
                
            };
        }
       // System.out.println("vc venceu");

    }
    
    // Main Method
    public static void main(String args[]) throws InterruptedException, Error {
        Config.loadImages();
        Tela tela = new Tela();
        tela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        tela.initGame();
        tela.gameLoop();
        System.exit(ABORT);
    }
}