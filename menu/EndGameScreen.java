package menu;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class EndGameScreen extends JFrame {

    private JPanel Fundo;
    private JLabel Imagem;
    private Botao confirma;
    private JLabel labelResultado;
    private JLabel jogarNovamente;
    private Botao cancela;
    private Menu menu;
    private Boolean resultado;
    private String win = "Parabéns! Você ganhou!";
    private String lose = "Que pena! Você perdeu!";

    public EndGameScreen(Menu menu, Boolean resultado) {
        this.menu = menu;
        this.resultado = resultado;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        Imagem = new JLabel();
        Fundo = new JPanel();
        labelResultado = new JLabel();
        confirma = new Botao();
        cancela = new Botao();
        jogarNovamente = new JLabel();
        Imagem = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Xadrez");
        setBackground(new Color(0, 0, 0));
        setResizable(false);

        Fundo.setBackground(new Color(44, 44, 44));

        /* Imagem por macrovector disponível em freepik.com */
        Imagem.setIcon(new ImageIcon("assets/images/endgame_background.png"));

        labelResultado.setFont(new Font("Segoe UI", 1, 32));
        labelResultado.setForeground(new Color(255, 255, 255));
        labelResultado.setText("Deseja jogar novamente?");
        labelResultado.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        labelResultado.setHorizontalAlignment(SwingConstants.CENTER);

        confirma.setBackground(new Color(0, 102, 0));
        confirma.setForeground(new Color(255, 255, 255));
        confirma.setText("Sim");
        confirma.setBorderColor(new Color(242, 242, 242));
        confirma.setBorderPainted(false);
        confirma.setColor(new Color(52, 138, 93));
        confirma.setColorClick(new Color(52, 138, 93, 200));
        confirma.setColorOver(new Color(52, 138, 93, 200));
        confirma.setFont(new Font("Consolas", 1, 24));
        confirma.setRadius(10);
        confirma.setVerticalAlignment(SwingConstants.BOTTOM);
        confirma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                confirmaActionPerformed(evt);
            }
        });

        cancela.setBackground(new Color(153, 51, 0));
        cancela.setForeground(new Color(255, 255, 255));
        cancela.setText("Não");
        cancela.setToolTipText("");
        cancela.setBorderColor(new Color(242, 242, 242));
        cancela.setBorderPainted(false);
        cancela.setColor(new Color(173, 44, 52));
        cancela.setColorClick(new Color(173, 44, 52, 200));
        cancela.setColorOver(new Color(173, 44, 52, 200));
        cancela.setFont(new Font("Consolas", 1, 24));
        cancela.setHorizontalTextPosition(SwingConstants.CENTER);
        cancela.setRadius(10);
        cancela.setVerticalAlignment(SwingConstants.BOTTOM);
        cancela.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cancelaActionPerformed(evt);
            }
        });

        jogarNovamente.setFont(new Font("Segoe UI", 1, 48));
        jogarNovamente.setForeground(new Color(255, 255, 255));
        jogarNovamente.setText(resultado ? win : lose);
        jogarNovamente.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        jogarNovamente.setHorizontalAlignment(SwingConstants.CENTER);

        javax.swing.GroupLayout FundoLayout = new javax.swing.GroupLayout(Fundo);
        Fundo.setLayout(FundoLayout);
        FundoLayout.setHorizontalGroup(
                FundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(FundoLayout.createSequentialGroup()
                                .addGap(646, 646, 646)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FundoLayout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(confirma, javax.swing.GroupLayout.PREFERRED_SIZE, 162,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cancela, javax.swing.GroupLayout.PREFERRED_SIZE, 162,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(105, 105, 105))
                        .addGroup(FundoLayout.createSequentialGroup()
                                .addGroup(FundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(FundoLayout.createSequentialGroup()
                                                .addGap(32, 32, 32)
                                                .addComponent(jogarNovamente))
                                        .addGroup(FundoLayout.createSequentialGroup()
                                                .addGap(114, 114, 114)
                                                .addComponent(labelResultado)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        FundoLayout.setVerticalGroup(
                FundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(FundoLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jogarNovamente, javax.swing.GroupLayout.PREFERRED_SIZE, 64,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57)
                                .addComponent(labelResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64,
                                        Short.MAX_VALUE)
                                .addGroup(FundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(confirma, javax.swing.GroupLayout.PREFERRED_SIZE, 47,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cancela, javax.swing.GroupLayout.PREFERRED_SIZE, 47,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(22, Short.MAX_VALUE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(120, Short.MAX_VALUE)
                                .addComponent(Fundo, javax.swing.GroupLayout.PREFERRED_SIZE, 601,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(123, 123, 123))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(Imagem)
                                        .addGap(0, 0, Short.MAX_VALUE))));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(Fundo, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(78, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(Imagem)
                                        .addGap(0, 0, Short.MAX_VALUE))));

        pack();
        setLocationRelativeTo(null);
    }

    private void confirmaActionPerformed(ActionEvent evt) {

        this.dispose();
        menu.inputNome.setText("");
        menu.setVisible(true);

    }

    private void cancelaActionPerformed(ActionEvent evt) {
        JFrame confirmacao = new JFrame("Deseja realmente cancelar?");
        String[] opcao = new String[2];
        opcao[0] = "Sim";
        opcao[1] = "Não";
        var escolha = JOptionPane.showOptionDialog(null, "Deseja realmente cancelar?", "Xadrez", 0,
                JOptionPane.INFORMATION_MESSAGE, null, opcao, null);
        if (escolha == 0) {
            System.exit(0);
        }
    }
}
