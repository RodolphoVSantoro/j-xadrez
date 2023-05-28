package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import utils.ArmazemInt;

public class Promocao extends javax.swing.JDialog {

    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setBackground(new java.awt.Color(242, 207, 159));

        jPanel1.setBackground(new java.awt.Color(242, 207, 159));

        jLabel1.setIcon(new javax.swing.ImageIcon("assets/images/promocao.png"));

        jButton4.setBackground(new java.awt.Color(242, 207, 159));
        jButton4.setIcon(new javax.swing.ImageIcon("assets/images/pecas/branco_center/dama.png"));
        jButton4.setBorder(null);
        jButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                damaActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(242, 207, 159));
        jButton5.setIcon(new javax.swing.ImageIcon("assets/images/pecas/branco_center/cavalo.png"));
        jButton5.setBorder(null);
        jButton5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cavaloActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(242, 207, 159));
        jButton6.setIcon(new javax.swing.ImageIcon("assets/images/pecas/branco_center/torre.png"));
        jButton6.setBorder(null);
        jButton6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                torreActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(242, 207, 159));
        jButton7.setIcon(new javax.swing.ImageIcon("assets/images/pecas/branco_center/bispo.png"));
        jButton7.setBorder(null);
        jButton7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bispoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
