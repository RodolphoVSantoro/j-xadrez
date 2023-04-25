package menu;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import config.SetupPecas;
import pecas.Peca;
import tabuleiro.Tabuleiro;
import tabuleiro.Tela;
import utils.Cor;

public class Menu extends javax.swing.JFrame {

    /* Inicia um JFrame */
    public Menu() {
        initComponents();
    }

    /* Declaração de variáveis */
    private javax.swing.JPanel Fundo;
    private javax.swing.JLabel Imagem;
    private Botao iniciar;
    private javax.swing.JTextField inputNome;
    private javax.swing.JLabel labelNome;
    private Botao sair;

    /* Função que inicia componentes do JFrame */
    private void initComponents() {

        /* Instanciação das variáveis */
        Fundo = new javax.swing.JPanel();
        Imagem = new javax.swing.JLabel();
        labelNome = new javax.swing.JLabel();
        inputNome = new javax.swing.JTextField();
        iniciar = new Botao();
        sair = new Botao();

        /* Configurações do JFrame */
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Engenharia do Xadrez part. II");
        setBackground(new java.awt.Color(0, 0, 0));
        setResizable(false);

        /* Configuração da tela de fundo */
        Fundo.setBackground(new java.awt.Color(51, 51, 51));

        /* Configuração da imagem da tela inicial */
        /* Imagem por macrovector disponível em freepik.com*/
        Imagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("../assets/images/background.jpg")));
        Imagem.setText("jLabel2");

        /* Configuração do label */
        labelNome.setFont(new java.awt.Font("Segoe UI", 1, 32));
        labelNome.setForeground(new java.awt.Color(255, 255, 255));
        labelNome.setText("Digite seu nome");
        labelNome.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        /* Configuração do campo de nome */
        inputNome.setFont(new java.awt.Font("Segoe UI", 1, 32));
        inputNome.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inputNome.setAutoscrolls(false);
        inputNome.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        inputNome.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        inputNome.setMargin(new java.awt.Insets(3, 6, 3, 6));
        inputNome.setSelectionColor(new java.awt.Color(51, 51, 51));
        inputNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputNomeActionPerformed(evt);
            }
        });

        /* Configuração do botão de iniciar */
        iniciar.setForeground(new java.awt.Color(255, 255, 255));
        iniciar.setText("Iniciar");
        iniciar.setBorderColor(new java.awt.Color(242, 242, 242));
        iniciar.setBorderPainted(false);
        iniciar.setColor(new java.awt.Color(209,139,71));
        iniciar.setColorClick(new java.awt.Color(209,139,71));
        iniciar.setColorOver(new java.awt.Color(255,206,158));
        iniciar.setFont(new java.awt.Font("Consolas", 1, 24));
        iniciar.setRadius(10);
        iniciar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        iniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarActionPerformed(evt);
            }
        });

        /* Configuração do botão de sair */
        sair.setForeground(new java.awt.Color(255, 255, 255));
        sair.setText("Sair");
        sair.setToolTipText("");
        sair.setBorderColor(new java.awt.Color(242, 242, 242));
        sair.setBorderPainted(false);
        sair.setColor(new java.awt.Color(209,139,71));
        sair.setColorClick(new java.awt.Color(209,139,71));
        sair.setColorOver(new java.awt.Color(255,206,158));
        sair.setFont(new java.awt.Font("Consolas", 1, 24));
        sair.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sair.setRadius(10);
        sair.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        sair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sairMouseEntered(evt);
            }
        });
        sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairActionPerformed(evt);
            }
        });

        /* Criação de grupos de layout */
        javax.swing.GroupLayout FundoLayout = new javax.swing.GroupLayout(Fundo);
        Fundo.setLayout(FundoLayout);
        FundoLayout.setHorizontalGroup(
            FundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FundoLayout.createSequentialGroup()
                .addComponent(Imagem, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(FundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FundoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(FundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FundoLayout.createSequentialGroup()
                                .addComponent(inputNome, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FundoLayout.createSequentialGroup()
                                .addComponent(labelNome)
                                .addGap(42, 42, 42))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FundoLayout.createSequentialGroup()
                                .addGroup(FundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(sair, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(iniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(87, 87, 87))))
                    .addGroup(FundoLayout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        FundoLayout.setVerticalGroup(
            FundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Imagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(FundoLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(labelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(inputNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(iniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(sair, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Fundo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(Fundo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    /* Configurando ação do botão iniciar */
    private void iniciarActionPerformed(java.awt.event.ActionEvent evt) {
        
        /*var nome = inputNome.getText();
        teste.setText("Olá " + nome + "!");*/

        ArrayList<Peca> pecasBrancas = SetupPecas.setup(Cor.BRANCO);
        ArrayList<Peca> pecasPretas = SetupPecas.setup(Cor.PRETO);
        Tabuleiro tabuleiro = new Tabuleiro(pecasBrancas, pecasPretas);
        Tela tela = new Tela(tabuleiro, pecasBrancas, pecasPretas);
        tela.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        tela.setLocationRelativeTo(null);
    }

    /* Configurando ação do botão sair */
    private void sairActionPerformed(java.awt.event.ActionEvent evt) {
        JFrame confirmacao = new JFrame("Deseja realmente sair?");
        String[] opcao = new String[2];
        opcao[0] = "Sim";
        opcao[1] = "Não";
        var escolha = JOptionPane.showOptionDialog(null, "Deseja realmente sair?", "Engenharia de Xadrez part. II", 0, JOptionPane.INFORMATION_MESSAGE, null, opcao, null);
        if (escolha == 0) {
            System.exit(0);
        }
    }

    private void sairMouseEntered(java.awt.event.MouseEvent evt) {
        // TODO
    }

    private void inputNomeActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO 
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Cria e exibe o JFrame */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }
}
