
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import config.Config;
import config.SetupPecas;
import events.Input;
import maquinaDeRegras.MaquinaDeRegras;
import maquinaDeRegras.Tabuleiro;
import menu.Botao;
import utils.Cor;

public class Menu extends JFrame {

    private JPanel Fundo;
    private JLabel Imagem;
    private Botao iniciar;
    static JTextField inputNome;
    private JLabel labelNome;
    private Botao sair;
    
    public Menu() {
        initComponents();
    }

    private void initComponents() {

        Fundo = new JPanel();
        Imagem = new JLabel();
        labelNome = new JLabel();
        inputNome = new JTextField();
        iniciar = new menu.Botao();
        sair = new menu.Botao();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Engenharia do Xadrez part. II");
        setBackground(new Color(0, 0, 0));
        setResizable(false);

        Fundo.setBackground(new Color(51, 51, 51));

        /* Imagem por macrovector disponível em freepik.com*/
        Imagem.setIcon(new ImageIcon(getClass().getResource("assets/images/background.jpg")));
        Imagem.setText("jLabel2");

        labelNome.setFont(new Font("Segoe UI", 1, 32));
        labelNome.setForeground(new Color(255, 255, 255));
        labelNome.setText("Digite seu nome");
        labelNome.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

        inputNome.setFont(new Font("Segoe UI", 1, 32));
        inputNome.setHorizontalAlignment(JTextField.CENTER);
        inputNome.setAutoscrolls(false);
        inputNome.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(204, 204, 204), new Color(204, 204, 204), new Color(204, 204, 204), new Color(204, 204, 204)));
        inputNome.setDisabledTextColor(new Color(255, 255, 255));
        inputNome.setMargin(new Insets(3, 6, 3, 6));
        inputNome.setSelectionColor(new Color(51, 51, 51));

        iniciar.setForeground(new Color(255, 255, 255));
        iniciar.setText("Iniciar");
        iniciar.setBorderColor(new Color(242, 242, 242));
        iniciar.setBorderPainted(false);
        iniciar.setColor(new Color(209, 139, 71));
        iniciar.setColorClick(new Color(209, 139, 71));
        iniciar.setColorOver(new Color(255, 206, 158));
        iniciar.setFont(new Font("Consolas", 1, 24));
        iniciar.setRadius(10);
        iniciar.setVerticalAlignment(SwingConstants.BOTTOM);
        iniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    iniciarActionPerformed(evt);
                } catch (InterruptedException | Error e) {
                    e.printStackTrace();
                }
            }
        });

        sair.setForeground(new Color(255, 255, 255));
        sair.setText("Sair");
        sair.setToolTipText("");
        sair.setBorderColor(new Color(242, 242, 242));
        sair.setBorderPainted(false);
        sair.setColor(new Color(209, 139, 71));
        sair.setColorClick(new Color(209, 139, 71));
        sair.setColorOver(new Color(255, 206, 158));
        sair.setFont(new Font("Consolas", 1, 24));
        sair.setHorizontalTextPosition(SwingConstants.CENTER);
        sair.setRadius(10);
        sair.setVerticalAlignment(SwingConstants.BOTTOM);
        sair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                sairActionPerformed(evt);
            }
        });

        GroupLayout FundoLayout = new GroupLayout(Fundo);
        Fundo.setLayout(FundoLayout);
        FundoLayout.setHorizontalGroup(
            FundoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(FundoLayout.createSequentialGroup()
                .addComponent(Imagem, GroupLayout.PREFERRED_SIZE, 499, GroupLayout.PREFERRED_SIZE)
                .addGroup(FundoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(FundoLayout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(FundoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.TRAILING, FundoLayout.createSequentialGroup()
                                .addComponent(inputNome, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))
                            .addGroup(GroupLayout.Alignment.TRAILING, FundoLayout.createSequentialGroup()
                                .addComponent(labelNome)
                                .addGap(42, 42, 42))
                            .addGroup(GroupLayout.Alignment.TRAILING, FundoLayout.createSequentialGroup()
                                .addGroup(FundoLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(sair, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(iniciar, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
                                .addGap(87, 87, 87))))
                    .addGroup(FundoLayout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        FundoLayout.setVerticalGroup(
            FundoLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(Imagem, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(FundoLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(labelNome, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(inputNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(iniciar, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(sair, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Fundo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(Fundo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void initGame(Tela tela) {
        tela.setMaquinaDeRegras(new MaquinaDeRegras(Cor.BRANCO, 2));
        tela.setPecasBrancas(SetupPecas.setup(Cor.BRANCO));
        tela.setPecasPretas(SetupPecas.setup(Cor.PRETO));
        tela.setTabuleiro(new Tabuleiro(tela.getPecasBrancas(), tela.getPecasPretas()));
        tela.getMaquinaDeRegras().setTabuleiro(tela.getTabuleiro());
        tela.setInput(new Input(tela.getMaquinaDeRegras(), tela.getCanvas()));
        tela.getCanvas().addMouseListener(tela.getInput());
    }

    private void gameLoop(Tela tela) throws Error, InterruptedException {
        
        while(!tela.getMaquinaDeRegras().chegouFimDeJogo()){       
            if(tela.getMaquinaDeRegras().getTurno() == Cor.PRETO){
                tela.getMaquinaDeRegras().moveIA();
                this.repaint();
            };
        }

    }     

    private void iniciarActionPerformed(ActionEvent evt) throws InterruptedException, Error {
        // Recebe o nome do usuário
        String nome = inputNome.getText();
    
        // Verifica se o usuário informou um nome
        if (nome.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite um nome válido.");
            return;
        }
    
        // Cria uma instância de tela
        Tela tela = new Tela();
        tela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.initGame(tela);
        //this.gameLoop(tela);
        System.out.println("Fim de jogo!");
    
        // Esconde o Menu
        setVisible(false);
    }

    private void sairActionPerformed(ActionEvent evt) {
        new JFrame("Deseja realmente sair?");
        String[] opcao = new String[2];
        opcao[0] = "Sim";
        opcao[1] = "Não";
        var escolha = JOptionPane.showOptionDialog(null, "Deseja realmente sair?", "Xadrez", 0, JOptionPane.INFORMATION_MESSAGE, null, opcao, null);
        if (escolha == 0) {
            System.exit(0);
        }
    }

    public static void main(String args[]) throws Error, InterruptedException {

        Config.loadImages();
        Menu menu = new Menu();
        menu.setVisible(true);
        
    }        
}
