package pecas;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import gui.Sprite;
import utils.Cor;
import utils.Posicao;
import utils.Config;
import tabuleiro.Tabuleiro;

public abstract class Peca {
    private Cor cor;
    private IdPeca id;
    private Posicao posicao;
    private boolean capturada;
    private Tabuleiro tabuleiro;
    private Sprite sprite;

    public Peca(Posicao posicao, Cor cor, IdPeca id, Tabuleiro tabuleiro) {
        this.cor = cor;
        this.id = id;
        this.posicao = posicao;
        this.capturada = false;
        this.tabuleiro = tabuleiro;
        Image image = Config.IMAGENS_PECAS.get(this.cor).get(this.id);
        this.sprite = new Sprite(image, posicao);
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

    public void desenha(Graphics graphics) {
        this.sprite.desenha(graphics);
    }
}
