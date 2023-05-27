package maquinaDeRegras;

import java.util.ArrayList;

import config.Config;
import pecas.Peca;
import pecas.TipoPeca;
import utils.Cor;
import utils.Posicao;

public class IA {
    private Cor cor;
    private Tabuleiro tabuleiro;
    private MaquinaDeRegras maquinaDeRegras;
    private Movimento melhorMovimento;

    public IA(Cor cor, int nivelDificuldadeIA) {
        this.cor = cor;
        this.melhorMovimento = null;
    }

    // Injetar dependencias tabuleiro e maquinaDeRegras
    public void setTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public void setMaquinaDeRegras(MaquinaDeRegras maquinaDeRegras) {
        this.maquinaDeRegras = maquinaDeRegras;
    }

    public Movimento getIAMovimento() {
        this.melhorMovimento = null;
        int profundidade = Config.PROFUNDIDADE_IA;
        this.melhorMovimento = this.minMax(profundidade,Integer.MIN_VALUE,Integer.MAX_VALUE,true);
        if (this.melhorMovimento == null) {
            throw new RuntimeException("IA falhou ao procurar movimento");
        }
        if(this.melhorMovimento.getPeca()!=null && this.melhorMovimento.getPeca().tipoPromocao == TipoPeca.PEAO && this.melhorMovimento.getPeca().qtdMovimento == 5) this.melhorMovimento.getPeca().promocao=9;
        return this.melhorMovimento;
    }

    private Movimento minMax(int profundidade,double alpha,double beta,boolean vez) {
        if (vez) {
            return this.max(profundidade,alpha,beta);
        } else {
            return this.min(profundidade,alpha,beta);
        }
    }

    private Movimento max(int profundidade,double alpha,double beta) {
        double alphatemp=alpha;
        double betatemp=beta;
        boolean para=false;
        boolean[] temp=this.maquinaDeRegras.chegouFimDeJogo();
        if (profundidade <= 0 || temp[0]||temp[1]) {
            if(temp[0])return new Movimento(null, null, null, Integer.MAX_VALUE);
            if(temp[1])return new Movimento(null, null, null, Integer.MIN_VALUE);
            return new Movimento(null, null, null, this.getValorTabuleiro());
        }
        Movimento melhorMovimento=new Movimento(null, null, null, Integer.MIN_VALUE);
        ArrayList<Peca> pecas = this.tabuleiro.getPecas(this.cor);
        for (Peca peca : pecas) {
            if(!peca.getCapturado()){
                for (Posicao posicao : peca.getMovimentosPossiveis(false)) {
                    if(peca.tipoPromocao == TipoPeca.PEAO && peca.qtdMovimento == 5) peca.promocao=9;
                    Movimento novoMovimento=null;
                    if(posicao.duplo){
                        Peca peca2 =this.tabuleiro.getPeca(posicao.xp,posicao.yp);
                        novoMovimento = new Movimento(peca, peca.getPosicaoTabuleiro(), new Posicao(posicao.x,posicao.y),peca2,peca2.getPosicaoTabuleiro(),new Posicao(posicao.x2, posicao.y2),0);
                    }else {
                        novoMovimento = new Movimento(peca, peca.getPosicaoTabuleiro(), posicao,0);
                    }
                    boolean movimentou = this.maquinaDeRegras.executaMovimento(novoMovimento,true);
                    if (!movimentou) {
                        throw new RuntimeException("Movimento inválido computando minMax "+novoMovimento.getPosicaoAnterior().x+" "+novoMovimento.getPosicaoAnterior().y+" "+novoMovimento.getPosicaoPosterior().x+" "+novoMovimento.getPosicaoPosterior().y);
                    };
                    novoMovimento.setValor(this.minMax(profundidade - 1,alphatemp,betatemp,false).getValor());

                    if(novoMovimento.getValor() > melhorMovimento.getValor() || ( novoMovimento.getValor() == melhorMovimento.getValor() && Math.random() > 0.5 ))melhorMovimento=novoMovimento;

                    this.maquinaDeRegras.desfazUltimoMovimento(true);

                    if(novoMovimento.getValor()>=alphatemp)alphatemp=novoMovimento.getValor();
                    if(betatemp<=alphatemp){
                        para=true;
                        break;
                    };
                }

            }
            if(para)break;
        }
        return melhorMovimento;
    }

    private Movimento min(int profundidade,double alpha,double beta) {
        double alphatemp=alpha;
        double betatemp=beta;
        boolean para=false;
        boolean[] temp=this.maquinaDeRegras.chegouFimDeJogo();
        if (profundidade <= 0 ||temp[0]||temp[1]) {
            if(temp[0])return new Movimento(null, null, null, Integer.MAX_VALUE);
            if(temp[1])return new Movimento(null, null, null, Integer.MIN_VALUE);
            return new Movimento(null, null, null, this.getValorTabuleiro());
        }
        Movimento melhorMovimento=new Movimento(null, null, null, Integer.MAX_VALUE);

        Cor adversarioIA = this.cor == Cor.BRANCO ? Cor.PRETO : Cor.BRANCO;
        ArrayList<Peca> pecas = this.tabuleiro.getPecas(adversarioIA);
        for (Peca peca : pecas) {
            if(!peca.getCapturado()){
                for (Posicao posicao : peca.getMovimentosPossiveis(false)) {
                    if(peca.tipoPromocao == TipoPeca.PEAO && peca.qtdMovimento == 5) peca.promocao=9;
                    Movimento novoMovimento=null;
                    if(posicao.duplo){
                        Peca peca2 =this.tabuleiro.getPeca(posicao.xp,posicao.yp);
                        novoMovimento = new Movimento(peca, peca.getPosicaoTabuleiro(), new Posicao(posicao.x,posicao.y),peca2,peca2.getPosicaoTabuleiro(),new Posicao(posicao.x2, posicao.y2),0);
                    }else {
                        novoMovimento = new Movimento(peca, peca.getPosicaoTabuleiro(), posicao,0);
                    }
                    boolean movimentou = this.maquinaDeRegras.executaMovimento(novoMovimento,true);
                    if (!movimentou) {
                        throw new RuntimeException("Movimento inválido computando minMax "+novoMovimento.getPosicaoAnterior().x+" "+novoMovimento.getPosicaoAnterior().y+" "+novoMovimento.getPosicaoPosterior().x+" "+novoMovimento.getPosicaoPosterior().y);
                    };
                    novoMovimento.setValor(this.minMax(profundidade - 1,alphatemp,betatemp,true).getValor());
                    if(novoMovimento.getValor() < melhorMovimento.getValor() || ( novoMovimento.getValor() == melhorMovimento.getValor() && Math.random() > 0.5 ))melhorMovimento=novoMovimento;
                    this.maquinaDeRegras.desfazUltimoMovimento(true);
                    if(novoMovimento.getValor()<=betatemp)betatemp=novoMovimento.getValor();
                    if(betatemp<=alphatemp){
                        para=true;
                        break;
                    };
                }
            }
            if(para)break;
        }
        return melhorMovimento;
    }

    private int getValorTabuleiro() {
        ArrayList<Peca> pecas = this.tabuleiro.getPecas(this.cor);
        ArrayList<Peca> pecasAdversario = this.tabuleiro.getPecasAdversario(this.cor);

        int valorPecas = 0;
        for (Peca peca : pecas) {
            if(!peca.getCapturado())valorPecas += Config.pontuacao.get(peca.tipoPromocao);
        }

        int valorPecasAdversario = 0;
        for (Peca peca : pecasAdversario) {
            if(!peca.getCapturado())valorPecasAdversario += Config.pontuacao.get(peca.tipoPromocao);
        }

        return valorPecas - valorPecasAdversario;
    }
}
