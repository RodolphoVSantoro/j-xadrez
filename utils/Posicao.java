package utils;

public class Posicao {
    public int x;
    public int y;
    public int x2 = -200;
    public int y2 = -200;
    public int xp = -200;
    public int yp = -200;
    public boolean duplo = false;

    public Posicao(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Posicao(int x, int y,int x2,int y2,int xp,int yp) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        this.xp = xp;
        this.yp = yp;
        this.duplo=true;
    }

    public Posicao(int x, int y, int x2, int y2, int xp, int yp) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        this.xp = xp;
        this.yp = yp;
        this.duplo = true;
    }

    public String stringify() {
        return "(" + this.x + ", " + this.y + ")";
    }
}