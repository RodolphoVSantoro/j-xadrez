package menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

import utils.ArmazemInt;

public class Promocao extends JDialog {

    private JButton jButton4;
    private JButton cavalo;
    private JButton torre;
    private JButton bispo;
    private JLabel jLabel1;
    private JPanel jPanel1;
    private int valor;
    private boolean podeContinuar;
    private ArmazemInt promocao;

    public Promocao(JFrame t) {
        super(t, "Promocao", true);
        this.setLocationRelativeTo(null);
        initComponents();
        this.promocao = ArmazemInt.getInstance();
    }

    public void setPodeContinuar(boolean podeContinuar) {
        this.podeContinuar = podeContinuar;
    }

    public boolean getPodeContinuar() {
        return false;
    }

    public int setValor(int valor){
        return this.valor = valor;
    }

    public int getValor(){
        return this.valor;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        jButton4 = new JButton();
        cavalo = new JButton();
        torre = new JButton();
        bispo = new JButton();

        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setBackground(new Color(242, 207, 159));

        jPanel1.setBackground(new Color(242, 207, 159));

        jLabel1.setIcon(new ImageIcon("assets/images/promocao.png"));

        jButton4.setBackground(new Color(242, 207, 159));
        jButton4.setIcon(new ImageIcon("assets/images/pecas/branco_center/dama.png"));
        jButton4.setBorder(null);
        jButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                damaActionPerformed(evt);
            }
        });

        cavalo.setBackground(new Color(242, 207, 159));
        cavalo.setIcon(new ImageIcon("assets/images/pecas/branco_center/cavalo.png"));
        cavalo.setBorder(null);
        cavalo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cavaloActionPerformed(evt);
            }
        });

        torre.setBackground(new Color(242, 207, 159));
        torre.setIcon(new ImageIcon("assets/images/pecas/branco_center/torre.png"));
        torre.setBorder(null);
        torre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                torreActionPerformed(evt);
            }
        });

        bispo.setBackground(new Color(242, 207, 159));
        bispo.setIcon(new ImageIcon("assets/images/pecas/branco_center/bispo.png"));
        bispo.setBorder(null);
        bispo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bispoActionPerformed(evt);
            }
        });

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addComponent(torre)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bispo)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cavalo)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(torre, GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4, GroupLayout.Alignment.TRAILING)
                    .addComponent(bispo, GroupLayout.Alignment.TRAILING)
                    .addComponent(cavalo, GroupLayout.Alignment.TRAILING))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void damaActionPerformed(ActionEvent evt) {
        this.setPodeContinuar(true);
        this.setValor(9);
        this.promocao.setValue(9);
        this.dispose();
    }

    private void torreActionPerformed(ActionEvent evt) {
        this.setPodeContinuar(true);
        this.setValor(5);
        this.promocao.setValue(5);
        this.dispose();
    } 
    private void cavaloActionPerformed(ActionEvent evt) {
        this.setPodeContinuar(true);
        this.setValor(3);
        this.promocao.setValue(3);
        this.dispose();
    } 
    private void bispoActionPerformed(ActionEvent evt) {
        this.setPodeContinuar(true);
        this.setValor(4);
        this.promocao.setValue(4);
        this.dispose();
    }    
}
