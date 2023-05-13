package menu;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public final class Tabuleiro extends JPanel{
    
    public int tam = 85;
    int linhas = 8;
    int cols = 8;
    
    // Criando cores a serem utilizadas no tabuleiro
    Color cor_1 = new Color (255,206,158);
    Color cor_2 = new Color (209,139,71);
    
    // Definindo tamanho do tabuleiro
    public Tabuleiro(){
        this.setPreferredSize(new Dimension(cols * tam, linhas * tam));
    }
    
    // Usando método do pacote JFrame para colorir o tabuleiro
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        
        for(int l = 0; l < linhas; l++){
            for(int c = 0; c < cols; c++){
                g2d.setColor((c+l) % 2 == 0 ? cor_1 : cor_2);
                g2d.fillRect(c * tam, l * tam, tam, tam);
            }
        }
    }
}
