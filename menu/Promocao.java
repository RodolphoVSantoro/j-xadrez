package menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
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
        //armazem para a caixa de dialogo da promocao persistir o valor selecionado antes de se encerrar
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

        InputStream stream = getClass().getResourceAsStream("/assets/images/promocao.png");
        BufferedImage image;
        try {
            image = ImageIO.read(stream);
            ImageIcon icon = new ImageIcon(image);
            jLabel1.setIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        jButton4.setBackground(new Color(242, 207, 159));
        InputStream stream2 = getClass().getResourceAsStream("/assets/images/pecas/branco_center/dama.png");
        BufferedImage image2;
        try {
            image2 = ImageIO.read(stream2);
            ImageIcon icon = new ImageIcon(image2);
            jButton4.setIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        jButton4.setBorder(null);
        jButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                damaActionPerformed(evt);
            }
        });

        cavalo.setBackground(new Color(242, 207, 159));
        InputStream stream3 = getClass().getResourceAsStream("/assets/images/pecas/branco_center/cavalo.png");
        BufferedImage image3;
        try {
            image3 = ImageIO.read(stream3);
            ImageIcon icon = new ImageIcon(image3);
            cavalo.setIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        cavalo.setBorder(null);
        cavalo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cavaloActionPerformed(evt);
            }
        });

        torre.setBackground(new Color(242, 207, 159));
        InputStream stream4 = getClass().getResourceAsStream("/assets/images/pecas/branco_center/torre.png");
        BufferedImage image4;
        try {
            image4 = ImageIO.read(stream4);
            ImageIcon icon = new ImageIcon(image4);
            torre.setIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        torre.setBorder(null);
        torre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                torreActionPerformed(evt);
            }
        });

        bispo.setBackground(new Color(242, 207, 159));
        InputStream stream5 = getClass().getResourceAsStream("/assets/images/pecas/branco_center/bispo.png");
        BufferedImage image5;
        try {
            image5 = ImageIO.read(stream5);
            ImageIcon icon = new ImageIcon(image5);
            bispo.setIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
