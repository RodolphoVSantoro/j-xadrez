package utils;

public class Posicao {
    public int x;
    public int y;

    public Posicao(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String stringify() {
        return "(" + this.x + ", " + this.y + ")";
    }
}