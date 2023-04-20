package pecas;

import utils.Posicao;

import java.util.ArrayList;

import utils.Cor;

public class Bispo extends Peca {

    public Bispo(Posicao posicao, Cor cor) {
        super(posicao, cor, TipoPeca.BISPO);
    }
    @Override
    public ArrayList<Posicao> getMovimentosPossiveis() {
        ArrayList<Posicao> movimentosPossiveis = new ArrayList<Posicao>();
        Posicao posicaoAtual = this.getPosicaoTabuleiro();
        int j = 0;
        for(int i = posicaoAtual.x + 1;i < 8;i++){
            j++;
            if(i + j < 8){
                Posicao proximaPosicao = new Posicao(i, i + j);
                Peca peca = this.tabuleiro.getPeca(proximaPosicao);
                if(peca!=null) {
                    if(this.podeCapturar(proximaPosicao)){
                        movimentosPossiveis.add(proximaPosicao);
                    }
                    break;
                }
                movimentosPossiveis.add(proximaPosicao);
            }
            if(i - j >= 0){
                Posicao proximaPosicao = new Posicao(i, i - j);
                Peca peca = this.tabuleiro.getPeca(proximaPosicao);
                if(peca!=null) {
                    if(this.podeCapturar(proximaPosicao)){
                        movimentosPossiveis.add(proximaPosicao);
                    }
                    break;
                }
                movimentosPossiveis.add(proximaPosicao);
            }
        }
        for(int i = posicaoAtual.x - 1;i >= 0;i--){
            j++;
            if(i + j < 8){
                Posicao proximaPosicao = new Posicao(i, i + j);
                Peca peca = this.tabuleiro.getPeca(proximaPosicao);
                if(peca!=null) {
                    if(this.podeCapturar(proximaPosicao)){
                        movimentosPossiveis.add(proximaPosicao);
                    }
                    break;
                }
                movimentosPossiveis.add(proximaPosicao);
            }
            if(i - j >= 0){
                Posicao proximaPosicao = new Posicao(i, i - j);
                Peca peca = this.tabuleiro.getPeca(proximaPosicao);
                if(peca!=null) {
                    if(this.podeCapturar(proximaPosicao)){
                        movimentosPossiveis.add(proximaPosicao);
                    }
                    break;
                }
                movimentosPossiveis.add(proximaPosicao);
            }
        }
        return movimentosPossiveis;
    }
}
