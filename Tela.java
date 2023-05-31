
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.ScrollPane;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.plaf.DimensionUIResource;

import config.Config;
import config.SetupPecas;
import events.Input;
import maquinaDeRegras.MaquinaDeRegras;
import maquinaDeRegras.Tabuleiro;
import menu.Menu;
import pecas.Peca;
import pecas.TipoPeca;
import utils.Cor;
import utils.Posicao;
import menu.EndGameScreen;

class Tela extends JFrame {

    private Canvas canvas;
    private MaquinaDeRegras maquinaDeRegras;
    private Tabuleiro tabuleiro;
    private ArrayList<Peca> pecasBrancas;
    private ArrayList<Peca> pecasPretas;
    private ArrayList<Posicao> possiveis;
    private Input input;
    private JTextArea brancasTextArea = new javax.swing.JTextArea();
    private JTextArea pretasTextArea = new javax.swing.JTextArea();
    
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
                                          (possiveis.get(i).y + 1) * Config.ALTURA_TABULEIRO,
                                           Config.LARGURA_TABULEIRO,
                                           Config.ALTURA_TABULEIRO);
                        
                    }
                }
            }
        };
        
        canvas.setBackground(new Color(18, 18, 18));

        JLabel capturadas = new JLabel();
        capturadas.setIcon(new ImageIcon("assets/images/capturadas.png"));
        
        JScrollPane brancasScrollPane = new JScrollPane();
        JScrollPane pretasScrollPane = new JScrollPane();
        JLabel fundoHistorico = new JLabel();

        setPreferredSize(new DimensionUIResource(180, 800));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        brancasScrollPane.setBackground(new Color(255, 208, 156));
        brancasScrollPane.setBorder(null);
        brancasScrollPane.setForeground(new Color(255, 208, 156));
        brancasScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        this.brancasTextArea.setEditable(false);
        this.brancasTextArea.setBackground(new Color(255, 208, 156));
        this.brancasTextArea.setColumns(20);
        this.brancasTextArea.setFont(new Font("Segoe UI Bold", 0, 16)); 
        this.brancasTextArea.setRows(20);
        this.brancasTextArea.setBorder(null);
        this.brancasTextArea.setCaretColor(new Color(255, 208, 156));
        this.brancasTextArea.setDisabledTextColor(new Color(255, 208, 156));
        this.brancasTextArea.setSelectedTextColor(new Color(255, 208, 156));
        this.brancasTextArea.setSelectionColor(new Color(255, 208, 156));
        brancasScrollPane.setViewportView(this.brancasTextArea);

        getContentPane().add(brancasScrollPane);
        brancasScrollPane.setBounds(1130, 146, 60, 560);
        
        pretasScrollPane.setBackground(new Color(255, 208, 156));
        pretasScrollPane.setBorder(null);
        pretasScrollPane.setForeground(new Color(255, 208, 156));
        pretasScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        this.pretasTextArea.setEditable(false);
        this.pretasTextArea.setBackground(new Color(255, 208, 156));
        this.pretasTextArea.setColumns(20);
        this.pretasTextArea.setFont(new Font("Segoe UI Bold", 0, 16)); 
        this.pretasTextArea.setRows(20);
        this.pretasTextArea.setBorder(null);
        this.pretasTextArea.setCaretColor(new Color(255, 208, 156));
        this.pretasTextArea.setDisabledTextColor(new Color(255, 208, 156));
        this.pretasTextArea.setSelectedTextColor(new Color(255, 208, 156));
        this.pretasTextArea.setSelectionColor(new Color(255, 208, 156));
        pretasScrollPane.setViewportView(this.pretasTextArea);

        getContentPane().add(pretasScrollPane);
        pretasScrollPane.setBounds(1200, 146, 58, 560);

        fundoHistorico.setIcon(new ImageIcon("assets/images/historico.png"));
        getContentPane().add(fundoHistorico);
        fundoHistorico.setBounds(0, 0, 180, 800);
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(capturadas)
                .addComponent(canvas)
                .addComponent(fundoHistorico))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(capturadas)
                    .addComponent(canvas)
                    .addComponent(fundoHistorico)))
        );

        pack();

        setSize(Config.LARGURA_TELA, Config.ALTURA_TELA);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initGame() {
        this.setMaquinaDeRegras(new MaquinaDeRegras(Cor.BRANCO, 2, this.brancasTextArea, this.pretasTextArea));
        this.setPecasBrancas(SetupPecas.setup(Cor.BRANCO));
        this.setPecasPretas(SetupPecas.setup(Cor.PRETO));
        this.setTabuleiro(new Tabuleiro(this.getPecasBrancas(), this.getPecasPretas()));
        this.getMaquinaDeRegras().setTabuleiro(this.getTabuleiro());
        this.setInput(new Input(this.getMaquinaDeRegras(), this.getCanvas()));
        this.getCanvas().addMouseListener(this.getInput());
    }

    private void gameLoop() throws Error, InterruptedException {
        
        while(!this.getMaquinaDeRegras().chegouFimDeJogo()){       
            if(this.getMaquinaDeRegras().getTurno() == Cor.PRETO){
                this.getMaquinaDeRegras().moveIA();
                this.repaint();
            }           
        }

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
                Peca reiDerrotado = tela.maquinaDeRegras.getTabuleiro().getPecas(Cor.PRETO).stream().filter(p -> p.getTipoPeca() == TipoPeca.REI).findFirst().get();
                Boolean resultado = reiDerrotado.getCapturado();
                tela.dispose();
                menu.setInicia(false);
                EndGameScreen endGame = new EndGameScreen(menu, resultado);
                endGame.setVisible(true);
            }
        }
    }
}