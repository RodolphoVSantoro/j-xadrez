
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import config.Config;
import config.SetupPecas;
import events.Input;
import maquinaDeRegras.MaquinaDeRegras;
import maquinaDeRegras.Tabuleiro;
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
                    possiveis = input.selecionada.getMovimentosPossiveis(false);
                    for(int i = 0; i < possiveis.size(); i++){
                        graphics.setColor(new Color(68, 180, 57, 190));
                        if(possiveis.get(i).duplo){
                            graphics.fillRect((possiveis.get(i).x2 + 1) * Config.LARGURA_TABULEIRO, 
                                              (possiveis.get(i).y2 + 1) * Config.LARGURA_TABULEIRO,
                                               Config.LARGURA_TABULEIRO,
                                               Config.LARGURA_TABULEIRO);
                        }
                        graphics.fillRect((possiveis.get(i).x + 1) * Config.LARGURA_TABULEIRO, 
                                            (possiveis.get(i).y + 1) * Config.LARGURA_TABULEIRO,
                                            Config.LARGURA_TABULEIRO,
                                            Config.LARGURA_TABULEIRO);
                        
                    }
                }
            }
        };

        canvas.setBackground(Color.white);

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
        boolean checkmate=false;
        while(!checkmate&&!this.maquinaDeRegras.empatouJogo()){       
            if(this.maquinaDeRegras.getTurno()==Cor.PRETO){
                this.maquinaDeRegras.moveIA();
                //System.out.println("sua vez");
                this.repaint();
                boolean[] temp=this.maquinaDeRegras.chegouFimDeJogo();
                this.maquinaDeRegras.checkmate=temp[0]||temp[1];
                
            };
            checkmate=this.maquinaDeRegras.checkmate;
            Thread.sleep(200);
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