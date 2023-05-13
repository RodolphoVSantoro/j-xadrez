
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;

import config.Config;
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
    
    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public MaquinaDeRegras getMaquinaDeRegras() {
        return maquinaDeRegras;
    }

    public void setMaquinaDeRegras(MaquinaDeRegras maquinaDeRegras) {
        this.maquinaDeRegras = maquinaDeRegras;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public ArrayList<Peca> getPecasBrancas() {
        return pecasBrancas;
    }

    public void setPecasBrancas(ArrayList<Peca> pecasBrancas) {
        this.pecasBrancas = pecasBrancas;
    }

    public ArrayList<Peca> getPecasPretas() {
        return pecasPretas;
    }

    public void setPecasPretas(ArrayList<Peca> pecasPretas) {
        this.pecasPretas = pecasPretas;
    }

    public ArrayList<Posicao> getPossiveis() {
        return possiveis;
    }

    public void setPossiveis(ArrayList<Posicao> possiveis) {
        this.possiveis = possiveis;
    }

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }

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
}