
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import config.Config;
import config.SetupPecas;
import events.Input;
import maquinaDeRegras.MaquinaDeRegras;
import maquinaDeRegras.Tabuleiro;
import menu.Menu;
import menu.Promocao;
import pecas.Peca;
import pecas.TipoPeca;
import utils.Cor;
import utils.Posicao;
import menu.EndGameScreen;

public class Tela extends JFrame {

    private Canvas canvas;
    private MaquinaDeRegras maquinaDeRegras;
    private Tabuleiro tabuleiro;
    private ArrayList<Peca> pecasBrancas;
    private ArrayList<Peca> pecasPretas;
    private ArrayList<Posicao> possiveis;
    private Input input;
    private Promocao promocao;
    
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

    /**
     * Este é o construtor para a classe 'Tela'. Ele configura a interface do usuário, criando um canvas no qual as peças e o tabuleiro do jogo são desenhadas.
     * Esse Canvas é configurado para destacar possíveis movimentos de peças quando uma peça é selecionada.
     * Além disso, este construtor inclui imagens que mostram as peças capturadas e o histórico do jogo.
     * Configura também o layout da tela, definindo seu tamanho, tornando-a visível, centrando-a e impedindo o redimensionamento.
     */
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
                if(input != null && input.selecionada != null){
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
        
        canvas.setBackground(new Color(18, 18, 18));

        JLabel capturadas = new JLabel();
        capturadas.setIcon(new ImageIcon("assets/images/capturadas.png"));
        
        JLabel historico = new JLabel();
        historico.setIcon(new ImageIcon("assets/images/historico.png"));
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(capturadas)
                .addComponent(canvas)
                .addComponent(historico))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(capturadas)
                    .addComponent(canvas)
                    .addComponent(historico)))
        );

        pack();

        setSize(Config.LARGURA_TELA, Config.ALTURA_TELA);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initGame() {
        this.setMaquinaDeRegras(new MaquinaDeRegras(Cor.BRANCO, 2));
        this.setPecasBrancas(SetupPecas.setup(Cor.BRANCO));
        this.setPecasPretas(SetupPecas.setup(Cor.PRETO));
        this.setTabuleiro(new Tabuleiro(this.getPecasBrancas(), this.getPecasPretas()));
        this.getMaquinaDeRegras().setTabuleiro(this.getTabuleiro());
        this.setInput(new Input(this.getMaquinaDeRegras(), this.getCanvas()));
        this.getInput().setPromocaoDialog(new Promocao(this));
        this.getCanvas().addMouseListener(this.getInput());
    }

    private void gameLoop() throws Error, InterruptedException {
        boolean checkmate = false;
        while(!checkmate){       
            if(this.maquinaDeRegras.getTurno()==Cor.PRETO && false){
                this.maquinaDeRegras.moveIA();
                //System.out.println("sua vez");
                this.repaint();
                boolean[] temp=this.maquinaDeRegras.chegouFimDeJogo();
                this.maquinaDeRegras.checkmate=temp[0]||temp[1];
                
            };
            boolean[] temp=this.maquinaDeRegras.chegouFimDeJogo();
            this.maquinaDeRegras.checkmate=temp[0]||temp[1];
            checkmate=this.maquinaDeRegras.checkmate;
            Thread.sleep(200);
        }
       // System.out.println("vc venceu");

    }

    public void repaint() {
        canvas.repaint();
    }    
    
    // Main Method
    public static void main(String args[]) throws InterruptedException, Error {
        Config.loadImages();
        Menu menu = new Menu();
        menu.setVisible(true);
        while(!menu.getInicia()){
            Thread.sleep(500);
            if(menu.getInicia()){
                Tela tela = new Tela();
                tela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                tela.initGame();
                tela.gameLoop();
                boolean[] resultados = tela.maquinaDeRegras.chegouFimDeJogo();
                boolean resultado = resultados[0] ? false : true;
                tela.dispose();
                menu.setInicia(false);
                System.out.println(resultado);
                EndGameScreen endGame = new EndGameScreen(menu, resultado);
                endGame.setVisible(true);
            }
        }
    }
}