package maquinaDeRegras;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import config.Config;
import gui.Sprite;
import pecas.Peca;
import utils.Cor;
import utils.Posicao;

public class Tabuleiro extends JPanel{
    private Sprite sprite;
    private HashMap<Cor, ArrayList<Peca>> pecas;
    private HashMap<Cor, ArrayList<Peca>> pecasCapturadas;
    public Peca[][] posicoesPecas;

    /**
     * Construtor da classe Tabuleiro.
     * Este construtor inicializa um novo tabuleiro com as peças brancas e pretas fornecidas.
     * Cada peça é associada a este tabuleiro e suas posições iniciais são registradas.
     * O tabuleiro também mantém um registro de peças capturadas por cor.
     *
     * @param pecasBrancas Um ArrayList contendo as peças brancas a serem colocadas no tabuleiro.
     * @param pecasPretas Um ArrayList contendo as peças pretas a serem colocadas no tabuleiro.
     */
    public Tabuleiro(ArrayList<Peca> pecasBrancas, ArrayList<Peca> pecasPretas) {;
        
        this.sprite = new Sprite(Config.IMAGEM_TABULEIRO, 0, 0);

        this.pecas = new HashMap<Cor, ArrayList<Peca>>();
        this.pecasCapturadas = new HashMap<Cor, ArrayList<Peca>>();

        pecasBrancas.forEach(p -> p.setTabuleiro(this));
        this.pecas.put(Cor.BRANCO, pecasBrancas);
        this.pecasCapturadas.put(Cor.BRANCO, new ArrayList<Peca>());

        pecasPretas.forEach(p -> p.setTabuleiro(this));
        this.pecas.put(Cor.PRETO, pecasPretas);
        this.pecasCapturadas.put(Cor.PRETO, new ArrayList<Peca>());

        this.posicoesPecas = new Peca[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.posicoesPecas[i][j] = null;
            }
        }

        this.pecas.get(Cor.BRANCO).forEach(peca -> {
            if(!peca.capturada){

                Posicao posicao = peca.getPosicaoTabuleiro();
                this.posicoesPecas[posicao.x][posicao.y] = peca;
            }
        });
        this.pecas.get(Cor.PRETO).forEach(peca -> {
            if(!peca.capturada){
                Posicao posicao = peca.getPosicaoTabuleiro();
                this.posicoesPecas[posicao.x][posicao.y] = peca;
                
            }
        });
    }

    public boolean posicaoDentroTabuleiro(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    public Peca getPeca(int x, int y) {
        if (!this.posicaoDentroTabuleiro(x, y)) {
            return null;
        }
        return this.posicoesPecas[x][y];
    }

    public Peca getPeca(Posicao p) {
        if (!this.posicaoDentroTabuleiro(p.x, p.y)) {
            return null;
        }
        return this.posicoesPecas[p.x][p.y];
    }

    public ArrayList<Peca> getPecas(Cor corPecas) {
        return this.pecas.get(corPecas);
    }

    public ArrayList<Peca> getPecasJogador(Cor corJogador) {
        return this.getPecas(corJogador);
    }

    public ArrayList<Peca> getPecasAdversario(Cor cor) {
        Cor corAdversario = cor == Cor.BRANCO ? Cor.PRETO : Cor.BRANCO;
        return this.getPecas(corAdversario);
    }

    /**
     * Método para mover uma peça para uma nova posição no tabuleiro. 
     * Este método atualiza a posição da peça no tabuleiro, limpa a posição anterior da peça
     * e, se houver uma peça na nova posição, captura essa peça.
     *
     * @param peca A peça a ser movida.
     * @param posicaoPosterior A nova posição da peça no tabuleiro.
     * @param ehIA Um booleano indicando se o movimento é controlado pela IA (Inteligência Artificial).
     *
     * @return Retorna a peça que foi capturada, se houver uma. Caso contrário, retorna null.
     */
    public Peca movePeca(Peca peca, Posicao posicaoPosterior,boolean ehIA) {
        Posicao posicaoAnterior = peca.getPosicaoTabuleiro();
        peca.setPosicaoTabuleiro(posicaoPosterior,ehIA);
        this.posicoesPecas[posicaoAnterior.x][posicaoAnterior.y] = null;
        Peca pecaCapturada = null;
        if (this.posicoesPecas[posicaoPosterior.x][posicaoPosterior.y] != null) {
            pecaCapturada = this.posicoesPecas[posicaoPosterior.x][posicaoPosterior.y];
            this.pecasCapturadas.get(pecaCapturada.getCor()).add(pecaCapturada);
            pecaCapturada.captura(ehIA);
        }
        this.posicoesPecas[posicaoPosterior.x][posicaoPosterior.y] = peca;
        return pecaCapturada;
    }

    /**
     * Método para recuperar uma peça capturada, colocando-a de volta em uma posição no tabuleiro.
     * Este método verifica se a peça está realmente capturada e se a posição para a qual a peça 
     * está sendo recuperada está vazia antes de prosseguir.
     *
     * @param peca A peça a ser recuperada.
     * @param posicao A posição no tabuleiro para onde a peça será colocada após a recuperação.
     * @param ehIA Um booleano indicando se a recuperação é controlada pela IA (Inteligência Artificial).
     * 
     * @throws IllegalArgumentException Se a peça não estiver capturada ou se a posição onde se deseja
     * recolocar a peça não estiver vazia.
     */
    public void recuperaPeca(Peca peca, Posicao posicao,boolean ehIA) {
        boolean pecaEstaCapturada = this.pecasCapturadas.get(peca.getCor()).stream().anyMatch(p -> p == peca);
        if (!pecaEstaCapturada) {
            throw new IllegalArgumentException("A peça não está capturada.");
        }
        boolean posicaoRecuperadaVazia = this.posicaoDentroTabuleiro(posicao.x, posicao.y)
                && this.posicoesPecas[posicao.x][posicao.y] != null;
        if (posicaoRecuperadaVazia) {
            throw new IllegalArgumentException("Peca tentou ser recuperada para uma posicao nao vazia.");
        }
        this.pecasCapturadas.get(peca.getCor()).remove(peca);
        peca.recupera(ehIA);
        this.posicoesPecas[posicao.x][posicao.y] = peca;
    }

    public void desenha(Graphics graphics, ImageObserver observer) {
        this.sprite.desenha(graphics, observer);
    }
}
