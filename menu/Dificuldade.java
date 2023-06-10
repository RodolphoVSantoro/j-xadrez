package menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.plaf.DimensionUIResource;

import config.Config;

public class Dificuldade extends JFrame {

    
    private JButton botaoDificil;
    private JButton botaoFacil;
    private JButton botaoMedio;
    private JPanel fundo;
    private JLabel telaDificuldade;
    private Menu menu;

    public Dificuldade(Menu menu) {
        this.menu = menu;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        fundo = new JPanel();
        telaDificuldade = new JLabel();
        botaoDificil = new JButton();
        botaoMedio = new JButton();
        botaoFacil = new JButton();

        setPreferredSize(new Dimension(420, 420));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        telaDificuldade.setIcon(new ImageIcon("assets/images/Dificuldade.png"));

        fundo.setPreferredSize(new DimensionUIResource(400, 400));

        botaoFacil.setBackground(new Color(46, 34, 26));
        botaoFacil.setFont(new Font("Segoe UI", 1, 36)); 
        botaoFacil.setForeground(new Color(255, 210, 154));
        botaoFacil.setText("Fácil");
        botaoFacil.setBorder(null);
        botaoFacil.setFocusPainted(false);
        botaoFacil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                facilActionPerformed(evt);
            }
        });

        botaoMedio.setBackground(new Color(46, 34, 26));
        botaoMedio.setFont(new Font("Segoe UI", 1, 36)); 
        botaoMedio.setForeground(new Color(255, 210, 154));
        botaoMedio.setText("Médio");
        botaoMedio.setBorder(null);
        botaoMedio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                medioActionPerformed(evt);
            }
        });
        
        botaoDificil.setBackground(new Color(46, 34, 26));
        botaoDificil.setFont(new Font("Segoe UI", 1, 36)); 
        botaoDificil.setForeground(new Color(255, 210, 154));
        botaoDificil.setText("Difícil");
        botaoDificil.setBorder(null);
        botaoDificil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dificilActionPerformed(evt);
            }
        });

        GroupLayout fundoLayout = new GroupLayout(fundo);
        fundo.setLayout(fundoLayout);
        fundoLayout.setHorizontalGroup(
            fundoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(fundoLayout.createSequentialGroup()
                .addGroup(fundoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(fundoLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(botaoFacil, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
                    .addGroup(fundoLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(botaoMedio, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
                    .addGroup(fundoLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(botaoDificil, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
                    .addComponent(telaDificuldade))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        fundoLayout.setVerticalGroup(
            fundoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, fundoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(fundoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(fundoLayout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(botaoFacil, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(botaoMedio, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(botaoDificil, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                    .addComponent(telaDificuldade)))
        );

        getContentPane().add(fundo);
        fundo.setBounds(0, 0, 400, 400);

        pack();
    }

    private void facilActionPerformed(ActionEvent evt) {
        Config.PROFUNDIDADE_IA = 1;
        menu.setInicia(true);
        this.dispose();
    }

    private void medioActionPerformed(ActionEvent evt) {
        Config.PROFUNDIDADE_IA = 3;
        menu.setInicia(true);
        this.dispose();
    }

    private void dificilActionPerformed(ActionEvent evt) {
        Config.PROFUNDIDADE_IA = 5;
        menu.setInicia(true);
        this.dispose();
    }
}
