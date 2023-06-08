
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.text.DefaultCaret;

import config.Config;
import config.SetupPecas;
import events.Input;
import maquinaDeRegras.MaquinaDeRegras;
import maquinaDeRegras.Tabuleiro;
import menu.EndGameScreen;
import menu.Menu;
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
    private JTextArea brancasTextArea = new javax.swing.JTextArea();
    private JTextArea pretasTextArea = new javax.swing.JTextArea();
    private JTextPane vez;
    private JPanel fundoCapturadas = new JPanel();

    private JLabel[] spritesCapturados = new JLabel[12];

    private JLabel reiBranco = new JLabel();
    private JLabel damaBranca = new JLabel();
    private JLabel bispoBranco = new JLabel();
    private JLabel cavaloBranco = new JLabel();
    private JLabel torreBranca = new JLabel();
    private JLabel peaoBranco = new JLabel();

    private JLabel reiPreto = new JLabel();
    private JLabel damaPreta = new JLabel();
    private JLabel bispoPreto = new JLabel();
    private JLabel cavaloPreto = new JLabel();
    private JLabel torrePreta = new JLabel();
    private JLabel peaoPreto = new JLabel();
    
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

    Tela(Menu menu) {
        super();

        // Cria canvas que contém tabuleiro e peças
        canvas = new Canvas() {

            // Pinta tabuleiro e posiciona peças
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
                                          (possiveis.get(i).y + 1) * Config.ALTURA_TABULEIRO,
                                           Config.LARGURA_TABULEIRO,
                                           Config.ALTURA_TABULEIRO);
                    }
                }
            }
        };
        

/* ---- CONFIG INDICAÇÃO DE VEZ --------------------------------------------------------------------------------------------------- */        
        
        canvas.setBackground(new Color(18, 18, 18));
        this.vez = new JTextPane();
        this.vez.setEditable(false);
        this.vez.setBounds(320, 25, 700, 40);
        this.vez.setFont(new Font("Segoe UI Bold", 0, 24)); 
        this.vez.setBackground(new Color(18, 18, 18));
        this.vez.setCaretColor(new Color(18, 18, 18));
        this.vez.setForeground(Color.WHITE);
        this.vez.setText("Vez de " + menu.getNome());
        add(this.vez);

/* ---- CONFIG TELA DE CAPTURADAS ------------------------------------------------------------------------------------------------- */        

        JLabel capturadas = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        fundoCapturadas.setBackground(new Color(102, 102, 102));
        fundoCapturadas.setLayout(null);

        capturadas.setIcon(new ImageIcon("assets/images/capturadas.png"));
        fundoCapturadas.add(capturadas);
        capturadas.setBounds(0, 0, 298, 800);

        getContentPane().add(fundoCapturadas);
        fundoCapturadas.setBounds(0, 0, 300, 800);
        fundoCapturadas.setPreferredSize(new Dimension(290, 800));

/* ---- SPRITES PEÇAS BRANCAS CAPTURADAS) ----------------------------------------------------------------------------------------- */

        reiBranco.setIcon(new ImageIcon("assets/images/pecas/branco/rei.png"));
        fundoCapturadas.add(reiBranco);
        reiBranco.setBounds(54, 141, 70, 70);
        reiBranco.setVisible(false);

        spritesCapturados[0] = reiBranco;

        damaBranca.setIcon(new ImageIcon("assets/images/pecas/branco/dama.png"));
        fundoCapturadas.add(damaBranca);
        damaBranca.setBounds(54, 241, 70, 70);
        damaBranca.setVisible(false);

        spritesCapturados[1] = damaBranca;
        
        bispoBranco.setIcon(new ImageIcon("assets/images/pecas/branco/bispo.png"));
        fundoCapturadas.add(bispoBranco);
        bispoBranco.setBounds(53, 350, 70, 70);
        bispoBranco.setVisible(false);

        spritesCapturados[2] = bispoBranco;

        cavaloBranco.setIcon(new ImageIcon("assets/images/pecas/branco/cavalo.png"));
        fundoCapturadas.add(cavaloBranco);
        cavaloBranco.setBounds(55, 450, 70, 70);
        cavaloBranco.setVisible(false);

        spritesCapturados[3] = cavaloBranco;

        torreBranca.setIcon(new ImageIcon("assets/images/pecas/branco/torre.png"));
        fundoCapturadas.add(torreBranca);
        torreBranca.setBounds(54, 548, 70, 70);
        torreBranca.setVisible(false);

        spritesCapturados[4] = torreBranca;

        peaoBranco.setIcon(new ImageIcon("assets/images/pecas/branco/peao.png"));
        fundoCapturadas.add(peaoBranco);
        peaoBranco.setBounds(55, 635, 70, 70);
        peaoBranco.setVisible(false);

        spritesCapturados[5] = peaoBranco;

/* ---- SPRITES PEÇAS PRETAS CAPTURADAS) ------------------------------------------------------------------------------------------ */

        reiPreto.setIcon(new ImageIcon("assets/images/pecas/Preto/rei.png"));
        fundoCapturadas.add(reiPreto);
        reiPreto.setBounds(166, 142, 70, 70);
        reiPreto.setVisible(false);

        spritesCapturados[6] = reiPreto;

        damaPreta.setIcon(new ImageIcon("assets/images/pecas/Preto/dama.png"));
        fundoCapturadas.add(damaPreta);
        damaPreta.setBounds(166, 242, 70, 70);
        damaPreta.setVisible(false);
        
        spritesCapturados[7] = damaPreta;

        bispoPreto.setIcon(new ImageIcon("assets/images/pecas/Preto/bispo.png"));
        fundoCapturadas.add(bispoPreto);
        bispoPreto.setBounds(166, 350, 70, 70);
        bispoPreto.setVisible(false);

        spritesCapturados[8] = bispoPreto;

        cavaloPreto.setIcon(new ImageIcon("assets/images/pecas/Preto/cavalo.png"));
        fundoCapturadas.add(cavaloPreto);
        cavaloPreto.setBounds(165, 450, 70, 70);
        cavaloPreto.setVisible(false);

        spritesCapturados[9] = cavaloPreto;

        torrePreta.setIcon(new ImageIcon("assets/images/pecas/Preto/torre.png"));
        fundoCapturadas.add(torrePreta);
        torrePreta.setBounds(166, 548, 70, 70);
        torrePreta.setVisible(false);

        spritesCapturados[10] = torrePreta;

        peaoPreto.setIcon(new ImageIcon("assets/images/pecas/Preto/peao.png"));
        fundoCapturadas.add(peaoPreto);
        peaoPreto.setBounds(165, 635, 70, 70);
        peaoPreto.setVisible(false);

        spritesCapturados[11] = peaoPreto;

/* ---- CONFIG TELA HISTORICO ----------------------------------------------------------------------------------------------------- */
        
        JScrollPane brancasScrollPane = new JScrollPane();
        JScrollPane pretasScrollPane = new JScrollPane();
        JLabel fundoHistorico = new JLabel();

        setPreferredSize(new DimensionUIResource(180, 800));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        fundoHistorico.setIcon(new ImageIcon("assets/images/historico2.png"));
        getContentPane().add(fundoHistorico);
        fundoHistorico.setBounds(0, 0, 180, 800);

/* ---- CONFIG TELA HISTORICO (PEÇAS BRANCAS) -------------------------------------------------------------------------------------- */     

        brancasScrollPane.setBackground(new Color(255, 208, 156));
        brancasScrollPane.setBorder(null);
        brancasScrollPane.setForeground(new Color(255, 208, 156));
        brancasScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        brancasScrollPane.getVerticalScrollBar().setBackground(new Color(255, 208, 156));

        DefaultCaret caretBrancas = (DefaultCaret)brancasTextArea.getCaret();
        caretBrancas.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

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
        brancasScrollPane.setBounds(1100, 143, 75, 577);

/* ---- CONFIG TELA HISTORICO (PEÇAS PRETAS) --------------------------------------------------------------------------------------- */  
        
        pretasScrollPane.setBackground(new Color(255, 208, 156));
        pretasScrollPane.setBorder(null);
        pretasScrollPane.setForeground(new Color(255, 208, 156));
        pretasScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        pretasScrollPane.getVerticalScrollBar().setBackground(new Color(255, 208, 156));
        pretasScrollPane.getVerticalScrollBar().setAutoscrolls(true);

        DefaultCaret caretPretas = (DefaultCaret)pretasTextArea.getCaret();
        caretPretas.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

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
        pretasScrollPane.setBounds(1180, 143, 75, 577);

/* ---- CONFIG LAYOUT DA TELA ----------------------------------------------------------------------------------------------------- */  
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(fundoCapturadas)
                .addComponent(canvas)
                .addComponent(fundoHistorico))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(fundoCapturadas)
                    .addComponent(canvas)
                    .addComponent(fundoHistorico)))
        );

        pack();

        setSize(Config.LARGURA_TELA, Config.ALTURA_TELA);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
    }


    // Método que inicializa os parâmetros que são utilizados durante um jogo 
    private void initGame(Menu menu) {
        this.setMaquinaDeRegras(new MaquinaDeRegras(Cor.BRANCO, 3, this.brancasTextArea, this.pretasTextArea, this.vez, menu));
        this.setPecasBrancas(SetupPecas.setup(Cor.BRANCO));
        this.setPecasPretas(SetupPecas.setup(Cor.PRETO));
        this.setTabuleiro(new Tabuleiro(this.getPecasBrancas(), this.getPecasPretas()));
        this.getMaquinaDeRegras().setTabuleiro(this.getTabuleiro());
        this.setInput(new Input(this.getMaquinaDeRegras(), this.getCanvas(), this.spritesCapturados));
        this.getCanvas().addMouseListener(this.getInput());
    }

    // Método responsável por atualizar a lógica do jogo e renderizar os gráficos em um loop contínuo.
    private void gameLoop() throws Error, InterruptedException {
        boolean checkmate=false;
        while(!checkmate&&!this.maquinaDeRegras.empatouJogo()){       
            if(this.maquinaDeRegras.getTurno()==Cor.PRETO){
                this.maquinaDeRegras.moveIA(this.spritesCapturados);
                this.repaint();
                boolean[] temp=this.maquinaDeRegras.chegouFimDeJogo();
                this.maquinaDeRegras.checkmate=temp[0]||temp[1];
                
            };
            checkmate=this.maquinaDeRegras.checkmate;
            Thread.sleep(200);
        }
    } 

    // Método utilizado para atualizar graficamente o tabuleiro e as peças 
    public void repaint() {
        canvas.repaint();
    }    
    
    // Método Principal
    public static void main(String args[]) throws InterruptedException, Error {
        // Carrega imagens do tabuleiro e das peças
        Config.loadImages();
        // Cria um novo menu
        Menu menu = new Menu();
        // Mostra o menu
        menu.setVisible(true);
        // Não permite que o jogo comece enquanto não for autorizado pelo usuário através do menu  
        while(!menu.getInicia()){
            // Espera um tempo para ver se o usuário realizou alguma ação 
            Thread.sleep(500);
            if(menu.getInicia()){
                // Mostra a tela de jogo
                Tela tela = new Tela(menu);
                // Define que a tela de jogo deverá fechar se o usuário clicar no botão que fecha a janela 
                tela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                // Inicia componentes do jogo 
                tela.initGame(menu);
                // Inicia loop do jogo
                tela.gameLoop();
                // Verifica o resultado do jogo
                boolean[] resultados = tela.maquinaDeRegras.chegouFimDeJogo();
                boolean resultado = resultados[0] ? false : true;
                // -----------------------------------------------------------
                // Descarta tela de jogo
                tela.dispose();
                // Proíbe que um novo jogo recomece a não ser que o usuário indique o contrário
                menu.setInicia(false);
                // Cria tela de fim de jogo
                EndGameScreen endGame = new EndGameScreen(menu, resultado);
                // Mostra tela de fim de jogo
                endGame.setVisible(true);
            }
        }
    }
}
