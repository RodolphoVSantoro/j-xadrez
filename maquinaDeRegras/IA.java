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
    private int profundidadeConfigurada;
    private Movimento melhorMovimento;

    public IA(Cor cor, int nivelDificuldadeIA) {
        this.cor = cor;
        this.profundidadeConfigurada = nivelDificuldadeIA;
        this.melhorMovimento = null;
    }

    // Injetar dependencias tabuleiro e maquinaDeRegras
    public void setTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public void setMaquinaDeRegras(MaquinaDeRegras maquinaDeRegras) {
        this.maquinaDeRegras = maquinaDeRegras;
    }


    /**
     * Este método é usado para calcular o melhor movimento possível para a Inteligência Artificial (IA),
     * utilizando o algoritmo MinMax com um determinado nível de profundidade definido em configurações.
     * Além disso, verifica se a peça a ser movida está qualificada para uma promoção, promovendo-a se necessário.
     *
     * @return O melhor movimento a ser realizado pela IA. 
     * @throws RuntimeException se a IA não conseguir encontrar um movimento válido.
     */
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

    /**
     * Este método implementa a parte principal do algoritmo MinMax, que é um algoritmo de busca recursiva usado 
     * para tomada de decisões. O algoritmo navega pela árvore de jogo, 
     * alternando entre níveis "min" e "max" representando os movimentos dos dois jogadores.
     *
     * @param profundidade É o nível de profundidade até o qual o algoritmo MinMax deve pesquisar na árvore do jogo.
     * @param alpha É o melhor valor encontrado até agora no caminho de busca para o jogador MAX.
     * @param beta É o melhor valor encontrado até agora no caminho de busca para o jogador MIN.
     * @param vez Um valor booleano que determina de quem é a vez, verdadeiro para MAX e falso para MIN.
     * @return Retorna o melhor movimento encontrado para o jogador cuja vez é dada.
     */
    private Movimento minMax(int profundidade,double alpha,double beta,boolean vez) {
        if (vez) {
            return this.max(profundidade,alpha,beta);
        } else {
            return this.min(profundidade,alpha,beta);
        }
    }

    /**
     * Este método implementa a função MAX do algoritmo MinMax. Este método é chamado quando é a vez do jogador MAX.
     * O objetivo é maximizar o valor da função de avaliação, ou seja, encontrar o movimento que dá ao jogador MAX a maior pontuação possível.
     *
     * @param profundidade É a profundidade máxima de busca na árvore de jogos.
     * @param alpha É o melhor valor encontrado até agora no caminho de busca para o jogador MAX.
     * @param beta É o melhor valor encontrado até agora no caminho de busca para o jogador MIN.
     * @return Retorna o movimento que maximiza a função de avaliação para o jogador MAX.
     */
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

    /**
     * Este método implementa a função MIN do algoritmo MinMax. Este método é chamado quando é a vez do jogador MIN.
     * O objetivo é minimizar o valor da função de avaliação, ou seja, encontrar o movimento que dá ao jogador MIN a menor pontuação possível.
     *
     * @param profundidade É a profundidade máxima de busca na árvore de jogos.
     * @param alpha É o melhor valor encontrado até agora no caminho de busca para o jogador MAX.
     * @param beta É o melhor valor encontrado até agora no caminho de busca para o jogador MIN.
     * @return Retorna o movimento que minimiza a função de avaliação para o jogador MIN.
     */
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


    /**
     * Este método avalia o valor do tabuleiro para a IA, calculando a diferença entre o valor das peças da IA e as do adversário.
     * O valor de cada peça é determinado por um conjunto predefinido de pontuações disponíveis em 'Config.pontuacao'.
     * As peças capturadas são excluídas da avaliação.
     *
     * @return Retorna a diferença entre o valor das peças da IA e o valor das peças do adversário.
     */
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
