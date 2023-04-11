
import java.awt.*;
import javax.swing.*;

class Tela extends JFrame {

    // constructor
    Tela() {
        super("xadrez");

        // create a empty canvas
        Canvas canvas = new Canvas() {

            // paint the canvas
            public void paint(Graphics gui) {
                // set color to black
                gui.setColor(Color.black);
                // draw a square
                for (int i = 0; i < 8; i++) {
                    for (int j = i % 2; j < 8; j += 2) {
                        gui.fillRect(100 * j, 100 * i, 100, 100);
                    }
                }
            }
        };

        // set background
        canvas.setBackground(Color.white);

        add(canvas);
        setSize(900, 900);
        setVisible(true);
    }

    // Main Method
    public static void main(String args[]) {
        Tela c = new Tela();
    }
}