package menu;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
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
    private String win =  "Parabéns! Você ganhou!";
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

        Fundo.setBackground(new Color(51, 51, 51));

        /* Imagem por macrovector disponível em freepik.com*/
        Imagem.setIcon(new ImageIcon(getClass().getResource("../assets/images/endgame_background.png")));

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

        GroupLayout FundoLayout = new GroupLayout(Fundo);
        Fundo.setLayout(FundoLayout);
        FundoLayout.setHorizontalGroup(
            FundoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(FundoLayout.createSequentialGroup()
                .addGap(646, 646, 646)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, FundoLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(confirma, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancela, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
            .addGroup(FundoLayout.createSequentialGroup()
                .addGroup(FundoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(FundoLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jogarNovamente))
                    .addGroup(FundoLayout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(labelResultado)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        FundoLayout.setVerticalGroup(
            FundoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(FundoLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jogarNovamente, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(labelResultado, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(FundoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(confirma, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancela, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(Fundo, GroupLayout.PREFERRED_SIZE, 623, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(Imagem, GroupLayout.PREFERRED_SIZE, 844, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(Fundo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(Imagem, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void confirmaActionPerformed(ActionEvent evt) {
        
        this.dispose();
        menu.inputNome.setText("");
        menu.setVisible(true);
        
    }

    private void cancelaActionPerformed(ActionEvent evt) {
        JFrame confirmacao = new JFrame("Deseja realmente sair?");
        String[] opcao = new String[2];
        opcao[0] = "Sim";
        opcao[1] = "Não";
        var escolha = JOptionPane.showOptionDialog(null, "Deseja realmente sair?", "Xadrez", 0, JOptionPane.INFORMATION_MESSAGE, null, opcao, null);
        if (escolha == 0) {
            System.exit(0);
        }
    }
}
