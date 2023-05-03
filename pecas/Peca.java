package pecas;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import config.Config;
import gui.Sprite;
import maquinaDeRegras.Tabuleiro;
import utils.Cor;
import utils.Posicao;

public abstract class Peca {
    private Cor cor;
    private TipoPeca tipoPeca;
    protected Posicao posicaoTabuleiro;
    protected boolean capturada;
    protected Tabuleiro tabuleiro;
    private Sprite sprite;

    public Peca(Posicao posicaoTabuleiro, Cor cor, TipoPeca tipoPeca) {
        this.capturada = false;
        this.tabuleiro = null;

        this.tipoPeca = tipoPeca;
        this.cor = cor;
        Image image = Config.IMAGENS_PECAS.get(this.cor).get(this.tipoPeca);

        this.sprite = new Sprite(image, posicaoTabuleiro);
        this.setPosicaoTabuleiro(posicaoTabuleiro);
    }

    public void setTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public void setPosicaoTabuleiro(Posicao posicaoTabuleiro) {
        this.posicaoTabuleiro = new Posicao(posicaoTabuleiro.x, posicaoTabuleiro.y);
        int spriteX = posicaoTabuleiro.x * Config.LARGURA_PECA;
        int spriteY = posicaoTabuleiro.y * Config.ALTURA_PECA;
        this.sprite.move(spriteX, spriteY);
    }

    public Posicao getPosicaoTabuleiro() {
        return new Posicao(this.posicaoTabuleiro.x, this.posicaoTabuleiro.y);
    }

    public boolean getCapturado() {
        return this.capturada;
    }

    public void captura() {
        this.capturada = true;
        this.sprite.move(-100, -100);
    }

    public void recupera() {
        this.capturada = false;
        this.sprite.move(this.posicaoTabuleiro.x * Config.LARGURA_PECA, this.posicaoTabuleiro.y * Config.ALTURA_PECA);
    }

    /*
     * Diz se a peca em determinada posiçao é capturável
     * Sem considerar peças entre ambas
     */
    protected boolean podeCapturar(Posicao posicaoNova) {
        Peca peca = this.tabuleiro.getPeca(posicaoNova.x, posicaoNova.y);
        return peca != null && peca.getCor() != this.getCor();
    }

    /*
     * Move caso a casa for um movimento possível
     */
    public boolean podeMover(Posicao posicao) {
        // TODO: Salvar e só computar uma vez por jogada se ficar pesado
        ArrayList<Posicao> posicoesValidas = this.getMovimentosPossiveis();
        for (Posicao posicaoNova : posicoesValidas) {
            if (posicaoNova.x == posicao.x && posicaoNova.y == posicao.y) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna todos os movimentos possiveis de uma peça
     */
    public abstract ArrayList<Posicao> getMovimentosPossiveis();

    public Cor getCor() {
        return cor;
    }

    public TipoPeca getTipoPeca() {
        return tipoPeca;
    }

    public String stringify() {
        return this.tipoPeca.toString() + " " + this.cor.toString() + " " + this.posicaoTabuleiro.stringify();
    }

    public void desenha(Graphics graphics, ImageObserver observer) {
        this.sprite.desenha(graphics, observer);
    }
}
