package pecas;

import utils.Posicao;

import java.util.ArrayList;

import maquinaDeRegras.Tabuleiro;
import utils.Cor;

public class Rei extends Peca {

    public Rei(Posicao posicao, Cor cor) {
        super(posicao, cor, TipoPeca.REI, TipoPeca.REI);
    }

    private static final int[][] MOVIMENTOS_POSSIVEIS = {
            { 1, 0 },
            { 1, -1 },
            { 0, -1 },
            { -1, -1 },
            { -1, 0 },
            { -1, 1 },
            { 0, 1 },
            { 1, 1 }
    };

    @Override
    public ArrayList<Posicao> getMovimentosPossiveis(boolean pulaTeste) {
        ArrayList<Posicao> movimentosPossiveis = new ArrayList<Posicao>();
        Posicao posicaoAtual = this.getPosicaoTabuleiro();
        
        ArrayList<Posicao> movimentoEstatico = new ArrayList<Posicao>();
        for (int[] movimento : MOVIMENTOS_POSSIVEIS) {
            movimentoEstatico.add(new Posicao(movimento[0], movimento[1]));
            Posicao proximaPosicao = new Posicao(posicaoAtual.x + movimento[0], posicaoAtual.y + movimento[1]);
            this.reiAdicionaMovimentoPossivel(movimentosPossiveis, proximaPosicao);
        }
        if(this.qtdMovimento==0){
            Peca torre1 = this.tabuleiro.getPeca(0,0);
            Peca torre2 = this.tabuleiro.getPeca(0,7);
            Peca torre3 = this.tabuleiro.getPeca(7,0);
            Peca torre4 = this.tabuleiro.getPeca(7,7);

            if(torre1 != null && torre1.getCor()==this.getCor() && torre1.qtdMovimento==0){
                if(this.tabuleiro.getPeca(1, 0) == null && this.tabuleiro.getPeca(2, 0)==null && this.tabuleiro.getPeca(3, 0)==null){
                    movimentosPossiveis.add(new Posicao(posicaoAtual.x-2, posicaoAtual.y, posicaoAtual.x-1, posicaoAtual.y, 0, 0));
                }
            }
            if(torre2 != null && torre2.getCor()==this.getCor() && torre2.qtdMovimento==0){
                if(this.tabuleiro.getPeca(1, 7)==null && this.tabuleiro.getPeca(2, 7)==null && this.tabuleiro.getPeca(3, 7)==null){
                    movimentosPossiveis.add(new Posicao(posicaoAtual.x-2, posicaoAtual.y, posicaoAtual.x-1, posicaoAtual.y, 0, 7));
                }
            }
            if(torre3 != null && torre3.getCor()==this.getCor() && torre3.qtdMovimento==0){
                if(this.tabuleiro.getPeca(5, 0)==null && this.tabuleiro.getPeca(6, 0)==null){
                    movimentosPossiveis.add(new Posicao(posicaoAtual.x+2, posicaoAtual.y, posicaoAtual.x+1, posicaoAtual.y, 7, 0));
                }
            }
            if(torre4 != null && torre4.getCor()==this.getCor() && torre4.qtdMovimento==0){
                if(this.tabuleiro.getPeca(5, 7)==null && this.tabuleiro.getPeca(6, 7)==null){
                    movimentosPossiveis.add(new Posicao(posicaoAtual.x+2, posicaoAtual.y, posicaoAtual.x+1, posicaoAtual.y, 7, 7));
                }
            }
        }

        if(!pulaTeste){
            ArrayList<Peca> pecaBranca = new ArrayList<Peca>();
            for(Peca peca : this.tabuleiro.getPecas(Cor.BRANCO)){
                try {
                    if(peca.getTipoPeca()==TipoPeca.PEAO)pecaBranca.add((Peao) peca.clone());
                    if(peca.getTipoPeca()==TipoPeca.CAVALO)pecaBranca.add((Cavalo) peca.clone());
                    if(peca.getTipoPeca()==TipoPeca.BISPO)pecaBranca.add((Bispo) peca.clone());
                    if(peca.getTipoPeca()==TipoPeca.TORRE)pecaBranca.add((Torre) peca.clone());
                    if(peca.getTipoPeca()==TipoPeca.DAMA)pecaBranca.add((Dama) peca.clone());
                    if(peca.getTipoPeca()==TipoPeca.REI)pecaBranca.add((Rei) peca.clone());
                } catch (CloneNotSupportedException e) {
                    
                    e.printStackTrace();
                };
            }
            ArrayList<Peca> pecaPreto = new ArrayList<Peca>();
            for(Peca peca : this.tabuleiro.getPecas(Cor.PRETO)){
                try {
                    if(peca.getTipoPeca()==TipoPeca.PEAO)pecaPreto.add((Peao) peca.clone());
                    if(peca.getTipoPeca()==TipoPeca.CAVALO)pecaPreto.add((Cavalo) peca.clone());
                    if(peca.getTipoPeca()==TipoPeca.BISPO)pecaPreto.add((Bispo) peca.clone());
                    if(peca.getTipoPeca()==TipoPeca.TORRE)pecaPreto.add((Torre) peca.clone());
                    if(peca.getTipoPeca()==TipoPeca.DAMA)pecaPreto.add((Dama) peca.clone());
                    if(peca.getTipoPeca()==TipoPeca.REI)pecaPreto.add((Rei) peca.clone());
                } catch (CloneNotSupportedException e) {
                    
                    e.printStackTrace();
                };
            }
            
            Tabuleiro tabu = new Tabuleiro(pecaBranca, pecaPreto);
            
            ArrayList<Posicao> tiraMovimento = new ArrayList<Posicao>();

            int xMeu =this.getPosicaoTabuleiro().x;
            int yMeu =this.getPosicaoTabuleiro().y;
        
            tabu.posicoesPecas[xMeu][yMeu]=null;
    
            for (Posicao movimento : movimentosPossiveis){
                Peca pecaCapturada=tabu.posicoesPecas[movimento.x][movimento.y];
                if(pecaCapturada==null){
                    if(tabu.getPecas( this.getCor()==Cor.BRANCO?Cor.PRETO:Cor.BRANCO ).stream().anyMatch(p->p.tipoPromocao!=TipoPeca.PEAO && p.capturada==false && p.getMovimentosPossiveis(true).stream().anyMatch(m->m.x==movimento.x && m.y==movimento.y))) tiraMovimento.add(movimento);
                    else if(tabu.getPecas( this.getCor()==Cor.BRANCO?Cor.PRETO:Cor.BRANCO ).stream().anyMatch(p->p.tipoPromocao==TipoPeca.PEAO && p.capturada==false && (p.getPosicaoTabuleiro().x+1==movimento.x || p.getPosicaoTabuleiro().x-1==movimento.x)&& p.getPosicaoTabuleiro().y+p.dir==movimento.y)) tiraMovimento.add(movimento);

                }
                else{
                    tabu.posicoesPecas[movimento.x][movimento.y]=null;
                    if(tabu.getPecas( this.getCor()==Cor.BRANCO?Cor.PRETO:Cor.BRANCO ).stream().anyMatch(p->p!=pecaCapturada && p.tipoPromocao!=TipoPeca.PEAO && p.capturada==false && p.getMovimentosPossiveis(true).stream().anyMatch(m->m.x==movimento.x && m.y==movimento.y))) tiraMovimento.add(movimento);
                    else if(tabu.getPecas( this.getCor()==Cor.BRANCO?Cor.PRETO:Cor.BRANCO ).stream().anyMatch(p->p!=pecaCapturada && p.tipoPromocao==TipoPeca.PEAO && p.capturada==false && (p.getPosicaoTabuleiro().x+1==movimento.x || p.getPosicaoTabuleiro().x-1==movimento.x)&& p.getPosicaoTabuleiro().y+p.dir==movimento.y)) tiraMovimento.add(movimento);
                    tabu.posicoesPecas[movimento.x][movimento.y]=pecaCapturada;
                }
            }
            for (Posicao movimento : tiraMovimento){
                movimentosPossiveis.remove(movimento);
            }

        }

        return movimentosPossiveis;
    }

    private void reiAdicionaMovimentoPossivel(ArrayList<Posicao> movimentosPossiveis, Posicao proximaPosicao) {
        if (proximaPosicao.x < 8 && proximaPosicao.x >= 0 && proximaPosicao.y < 8 && proximaPosicao.y >= 0) {
            Peca peca = this.tabuleiro.getPeca(proximaPosicao);
            if (peca == null || this.podeCapturar(proximaPosicao)) {
                movimentosPossiveis.add(proximaPosicao);
            }
        }
    }
}
