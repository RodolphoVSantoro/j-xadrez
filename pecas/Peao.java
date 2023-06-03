package pecas;

import utils.Posicao;

import java.util.ArrayList;

import config.Config;
import gui.Sprite;
import utils.Cor;

public class Peao extends Peca {

    private int promocaoAtual = 0;

    private Sprite peao;

    private Sprite dama;

    private Sprite torre;

    private Sprite bispo;

    private Sprite cavalo;

    public Peao(Posicao posicao, Cor cor) {
        super(posicao, cor, TipoPeca.PEAO, TipoPeca.PEAO);
        this.dama = new Sprite(Config.IMAGENS_PECAS.get(this.getCor()).get(TipoPeca.DAMA), this.getPosicaoTabuleiro());
        this.peao = this.sprite;
        this.torre = new Sprite(Config.IMAGENS_PECAS.get(this.getCor()).get(TipoPeca.TORRE),
                this.getPosicaoTabuleiro());
        this.bispo = new Sprite(Config.IMAGENS_PECAS.get(this.getCor()).get(TipoPeca.BISPO),
                this.getPosicaoTabuleiro());
        this.cavalo = new Sprite(Config.IMAGENS_PECAS.get(this.getCor()).get(TipoPeca.CAVALO),
                this.getPosicaoTabuleiro());
        this.dir = this.getCor() == Cor.PRETO ? 1 : -1;
    }

    private static final int[][] MOVIMENTOS_POSSIVEIS = {
            { 2, 1 },
            { 2, -1 },
            { -2, -1 },
            { -2, 1 },
            { -1, 2 },
            { 1, 2 },
            { 1, -2 },
            { -1, -2 }
    };

    @Override
    public ArrayList<Posicao> getMovimentosPossiveis(boolean pulaTeste) {
        ArrayList<Posicao> movimentosPossiveis = new ArrayList<Posicao>();
        if (this.tipoPromocao == TipoPeca.PEAO) {
            movimentosPossiveis = this.getMovimentosPossiveisPeao();
        } else {
            if (this.tipoPromocao == TipoPeca.DAMA)
                movimentosPossiveis = this.getMovimentosPossiveisDama();
            if (this.tipoPromocao == TipoPeca.TORRE)
                movimentosPossiveis = this.getMovimentosPossiveisTorre();
            if (this.tipoPromocao == TipoPeca.BISPO)
                movimentosPossiveis = this.getMovimentosPossiveisBispo();
            if (this.tipoPromocao == TipoPeca.CAVALO)
                movimentosPossiveis = this.getMovimentosPossiveisCavalo();
        }

        if (!pulaTeste)
            this.checaValidadeMovimento(movimentosPossiveis);

        return movimentosPossiveis;

    }

    private ArrayList<Posicao> getMovimentosPossiveisPeao() {
        ArrayList<Posicao> movimentosPossiveis = new ArrayList<Posicao>();
        Posicao posicaoAtual = this.getPosicaoTabuleiro();
        Posicao proximaPosicao = new Posicao(posicaoAtual.x, posicaoAtual.y + 1 * this.dir);
        Peca peca = this.tabuleiro.getPeca(proximaPosicao);
        boolean posicaoValida = this.tabuleiro.posicaoDentroTabuleiro(proximaPosicao.x, proximaPosicao.y);
        if (peca == null && posicaoValida) {
            movimentosPossiveis.add(proximaPosicao);
            if ((this.qtdMovimento == 0)) {
                proximaPosicao = new Posicao(posicaoAtual.x, posicaoAtual.y + this.dir, posicaoAtual.x,
                        posicaoAtual.y + 2 * this.dir, posicaoAtual.x, posicaoAtual.y);
                peca = this.tabuleiro.getPeca(proximaPosicao.x2, proximaPosicao.y2);
                posicaoValida = this.tabuleiro.posicaoDentroTabuleiro(proximaPosicao.x2, proximaPosicao.y2);
                if (peca == null && posicaoValida) {
                    movimentosPossiveis.add(proximaPosicao);
                }
            }
        }
        Posicao posicaoEsquerda = new Posicao(posicaoAtual.x + 1, posicaoAtual.y + 1 * this.dir);
        if (this.podeCapturar(posicaoEsquerda)) {
            movimentosPossiveis.add(posicaoEsquerda);
        }
        Posicao posicaoDireita = new Posicao(posicaoAtual.x - 1, posicaoAtual.y + 1 * this.dir);
        if (this.podeCapturar(posicaoDireita)) {
            movimentosPossiveis.add(posicaoDireita);
        }
        if (this.historico.getUltimoMovimento() != null) {
            if (this.historico.getUltimoMovimento().movimentoDuplo) {
                int xtemp = this.historico.getUltimoMovimento().getPosicaoPosterior2().x;
                int ytemp = this.historico.getUltimoMovimento().getPosicaoPosterior2().y;
                if (this.historico.getUltimoMovimento().getPeca().tipoPromocao == TipoPeca.PEAO &&
                        ytemp == posicaoAtual.y &&
                        (xtemp == posicaoAtual.x + 1 || xtemp == posicaoAtual.x - 1)) {
                    Posicao passante = new Posicao(xtemp, ytemp, xtemp, ytemp + this.dir, posicaoAtual.x,
                            posicaoAtual.y);
                    movimentosPossiveis.add(passante);
                }
            }
        }

        return movimentosPossiveis;
    }

    private ArrayList<Posicao> getMovimentosPossiveisDama() {
        ArrayList<Posicao> movimentosPossiveis = new ArrayList<Posicao>();

        this.addMovimentosEsquerda(movimentosPossiveis, this.posicaoTabuleiro);
        this.addMovimentosDireita(movimentosPossiveis, this.posicaoTabuleiro);
        this.addMovimentosCima(movimentosPossiveis, this.posicaoTabuleiro);
        this.addMovimentosBaixo(movimentosPossiveis, this.posicaoTabuleiro);
        this.addMovimentoDiagonal(movimentosPossiveis, this.posicaoTabuleiro, 1, 1);
        this.addMovimentoDiagonal(movimentosPossiveis, this.posicaoTabuleiro, 1, -1);
        this.addMovimentoDiagonal(movimentosPossiveis, this.posicaoTabuleiro, -1, 1);
        this.addMovimentoDiagonal(movimentosPossiveis, this.posicaoTabuleiro, -1, -1);

        return movimentosPossiveis;
    }

    private ArrayList<Posicao> getMovimentosPossiveisTorre() {
        ArrayList<Posicao> movimentosPossiveis = new ArrayList<Posicao>();

        this.addMovimentosEsquerda(movimentosPossiveis, posicaoTabuleiro);
        this.addMovimentosDireita(movimentosPossiveis, posicaoTabuleiro);
        this.addMovimentosCima(movimentosPossiveis, posicaoTabuleiro);
        this.addMovimentosBaixo(movimentosPossiveis, posicaoTabuleiro);

        return movimentosPossiveis;
    }

    private ArrayList<Posicao> getMovimentosPossiveisBispo() {
        ArrayList<Posicao> movimentosPossiveis = new ArrayList<Posicao>();

        this.addMovimentoDiagonal(movimentosPossiveis, posicaoTabuleiro, 1, 1);
        this.addMovimentoDiagonal(movimentosPossiveis, posicaoTabuleiro, 1, -1);
        this.addMovimentoDiagonal(movimentosPossiveis, posicaoTabuleiro, -1, 1);
        this.addMovimentoDiagonal(movimentosPossiveis, posicaoTabuleiro, -1, -1);
        return movimentosPossiveis;
    }

    private ArrayList<Posicao> getMovimentosPossiveisCavalo() {
        ArrayList<Posicao> movimentosPossiveis = new ArrayList<Posicao>();
        Posicao posicaoAtual = this.getPosicaoTabuleiro();

        for (int[] movimento : MOVIMENTOS_POSSIVEIS) {
            Posicao proximaPosicao = new Posicao(posicaoAtual.x + movimento[0], posicaoAtual.y + movimento[1]);
            this.cavaloAdicionaMovimentoPossivel(movimentosPossiveis, proximaPosicao);
        }

        return movimentosPossiveis;
    }

    private void cavaloAdicionaMovimentoPossivel(ArrayList<Posicao> movimentosPossiveis, Posicao proximaPosicao) {
        if (proximaPosicao.x < 8 && proximaPosicao.x >= 0 && proximaPosicao.y < 8 && proximaPosicao.y >= 0) {
            Peca peca = this.tabuleiro.getPeca(proximaPosicao);
            if (peca == null || this.podeCapturar(proximaPosicao)) {
                movimentosPossiveis.add(proximaPosicao);
            }
        }
    }

    private boolean addPosicaoValida(Posicao proximaPosicao, ArrayList<Posicao> movimentosPossiveis) {
        if (!this.tabuleiro.posicaoDentroTabuleiro(proximaPosicao.x, proximaPosicao.y)) {
            return false;
        }
        Peca peca = this.tabuleiro.getPeca(proximaPosicao);
        if (peca != null) {
            if (this.podeCapturar(proximaPosicao)) {
                movimentosPossiveis.add(proximaPosicao);
            }
            return false;
        }
        movimentosPossiveis.add(proximaPosicao);
        return true;
    }

    private void addMovimentosDireita(ArrayList<Posicao> movimentosPossiveis, Posicao posicaoAtual) {
        boolean podeContinuar = true;
        for (int i = posicaoAtual.x + 1; i < 8 && podeContinuar; i++) {
            Posicao proximaPosicao = new Posicao(i, posicaoAtual.y);
            podeContinuar = this.addPosicaoValida(proximaPosicao, movimentosPossiveis);
        }
    }

    private void addMovimentosEsquerda(ArrayList<Posicao> movimentosPossiveis, Posicao posicaoAtual) {
        boolean podeContinuar = true;
        for (int i = posicaoAtual.x - 1; i >= 0 && podeContinuar; i--) {
            Posicao proximaPosicao = new Posicao(i, posicaoAtual.y);
            podeContinuar = this.addPosicaoValida(proximaPosicao, movimentosPossiveis);
        }
    }

    private void addMovimentosBaixo(ArrayList<Posicao> movimentosPossiveis, Posicao posicaoAtual) {
        boolean podeContinuar = true;
        for (int i = posicaoAtual.y + 1; i < 8 && podeContinuar; i++) {
            Posicao proximaPosicao = new Posicao(posicaoAtual.x, i);
            podeContinuar = this.addPosicaoValida(proximaPosicao, movimentosPossiveis);
        }
    }

    private void addMovimentosCima(ArrayList<Posicao> movimentosPossiveis, Posicao posicaoAtual) {
        boolean podeContinuar = true;
        for (int i = posicaoAtual.y - 1; i >= 0 && podeContinuar; i--) {
            Posicao proximaPosicao = new Posicao(posicaoAtual.x, i);
            podeContinuar = this.addPosicaoValida(proximaPosicao, movimentosPossiveis);
        }
    }

    private void addMovimentoDiagonal(ArrayList<Posicao> movimentosPossiveis, Posicao posicaoAtual, int direcaoX,
            int direcaoY) {
        boolean podeContinuar = true;
        int i = posicaoAtual.x + direcaoX;
        int j = posicaoAtual.y + direcaoY;
        while (i < 8 && i >= 0 && j < 8 && j >= 0 && podeContinuar) {
            Posicao proximaPosicao = new Posicao(i, j);
            podeContinuar = this.addPosicaoValida(proximaPosicao, movimentosPossiveis);
            i += direcaoX;
            j += direcaoY;
        }
    }

    @Override
    public void setPosicaoTabuleiro(Posicao posicaoTabuleiro, boolean ehIA) {
        this.posicaoTabuleiro = new Posicao(posicaoTabuleiro.x, posicaoTabuleiro.y);
        int spriteX = posicaoTabuleiro.x * Config.LARGURA_PECA;
        int spriteY = posicaoTabuleiro.y * Config.ALTURA_PECA;
        this.atualizaSprite();
        if (!ehIA) {
            this.sprite.move(spriteX, spriteY);

        }
    }

    public void atualizaSprite() {
        if (this.qtdMovimento < 6 && this.promocaoAtual != 0) {
            this.peao.move(this.sprite.posicao);
            this.sprite = this.peao;
            this.tipoPromocao = TipoPeca.PEAO;
            this.promocaoAtual = 0;
            this.promocao = 0;
        }
        if (this.promocao != this.promocaoAtual) {
            if (this.promocao == 9) {
                this.dama.move(this.sprite.posicao);
                this.sprite = this.dama;
                this.tipoPromocao = TipoPeca.DAMA;
            }
            if (this.promocao == 5) {
                this.torre.move(this.sprite.posicao);
                this.sprite = this.torre;
                this.tipoPromocao = TipoPeca.TORRE;
            }
            if (this.promocao == 4) {
                this.bispo.move(this.sprite.posicao);
                this.sprite = this.bispo;
                this.tipoPromocao = TipoPeca.BISPO;
            }
            if (this.promocao == 3) {
                this.cavalo.move(this.sprite.posicao);
                this.sprite = this.cavalo;
                this.tipoPromocao = TipoPeca.CAVALO;
            }
        }
        promocaoAtual = promocao;

    }

}
