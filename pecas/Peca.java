package pecas;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import config.Config;
import gui.Sprite;
import utils.Cor;
import utils.Posicao;
import tabuleiro.Tabuleiro;

public abstract class Peca {
    private Cor cor;
    private TipoPeca id;
    private Posicao posicaoTabuleiro;
    private boolean capturada;
    private Tabuleiro tabuleiro;
    private Sprite sprite;

    public Peca(Posicao posicaoTabuleiro, Cor cor, TipoPeca id) {
        this.capturada = false;
        this.tabuleiro = null;

        this.id = id;
        this.cor = cor;
        Image image = Config.IMAGENS_PECAS.get(this.cor).get(this.id);

        this.sprite = new Sprite(image, posicaoTabuleiro);
        this.setPosicaoTabuleiro(posicaoTabuleiro);
    }

    public void setTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    // deve ser chamada por tentaMover
    private void setPosicaoTabuleiro(Posicao posicaoTabuleiro) {
        this.posicaoTabuleiro = posicaoTabuleiro;
        int spriteX = posicaoTabuleiro.x * Config.LARGURA_PECA;
        int spriteY = posicaoTabuleiro.y * Config.ALTURA_PECA;
        this.sprite.move(spriteX, spriteY);
    }

    public Posicao getPosicaoTabuleiro() {
        return new Posicao(this.posicaoTabuleiro.x, this.posicaoTabuleiro.y);
    }

    protected abstract boolean podeMover(Posicao posicao);

    protected abstract boolean podeCapturar(Posicao posicao);

    protected abstract Peca tentaCapturar(Posicao posicao);

    public abstract boolean tentaMover(Posicao posicao);

    // Chamar para mostrar os movimentos possíveis
    // caso a o jogador escolha uma posição inválida
    public ArrayList<Posicao> getMovimentosPossiveis() {
        ArrayList<Posicao> movimentosPossiveis = new ArrayList<Posicao>();
        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                Posicao posicao = new Posicao(linha, coluna);
                if (this.podeMover(posicao)) {
                    movimentosPossiveis.add(posicao);
                }
            }
        }
        return movimentosPossiveis;
    }

    public void desenha(Graphics graphics, ImageObserver observer) {
        this.sprite.desenha(graphics, observer);
    }
}
