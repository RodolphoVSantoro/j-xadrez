package maquinaDeRegras;

import java.util.ArrayList;

import pecas.Bispo;
import pecas.Cavalo;
import pecas.Dama;
import pecas.Peao;
import menu.Menu;
import pecas.Peca;
import pecas.Rei;
import pecas.TipoPeca;
import pecas.Torre;
import utils.Cor;
import utils.Posicao;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import config.Config;
import events.PrintaHistorico;

public class MaquinaDeRegras {
    private Tabuleiro tabuleiro;
    private Cor turno;
    private Cor jogador;
    private Cor adversario;
    private Historico historico;
    private IA IA;
    private JTextArea brancasTextArea;
    private JTextArea pretasTextArea;
    private JTextPane vez;
    private Menu menu;
    public boolean checkmate = false;

    //construtor para testes sem relacao com a interface
    public MaquinaDeRegras(Cor jogador, int nivelDificuldadeIA) {
        this.turno = Cor.BRANCO;
        this.jogador = jogador;
        this.adversario = jogador == Cor.BRANCO ? Cor.PRETO : Cor.BRANCO;
        this.IA = new IA(this.adversario, nivelDificuldadeIA);
        this.IA.setMaquinaDeRegras(this);
    }

    public MaquinaDeRegras(Cor jogador, int nivelDificuldadeIA, JTextArea brancasTextArea, JTextArea pretasTextArea, JTextPane vez, Menu menu) {
        this.turno = Cor.BRANCO;
        this.jogador = jogador;
        this.adversario = jogador == Cor.BRANCO ? Cor.PRETO : Cor.BRANCO;
        this.IA = new IA(this.adversario, nivelDificuldadeIA);
        this.brancasTextArea = brancasTextArea;
        this.pretasTextArea = pretasTextArea;
        this.vez = vez;
        this.menu = menu;
        this.IA.setMaquinaDeRegras(this);
    }


     public Tabuleiro getTabuleiro() {
        return this.tabuleiro;
    }

    public void setTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        this.historico = new Historico(this.tabuleiro.getPecas(Cor.BRANCO),this.tabuleiro.getPecas(Cor.PRETO));
        this.IA.setTabuleiro(this.tabuleiro);
    }

    public Cor getTurno(){
        return this.turno;
    } 

    public void setTurno(Cor cor){
         this.turno = cor;
    } 

    public Historico getHistorico() {
        return historico;
    }

    public void jogada() {
        if (this.turno == this.adversario) {
            this.moveIA();
            this.turno = this.jogador;
        }
    }


    /**
     * Este método verifica se o fim de jogo foi alcançado no tabuleiro de xadrez atual.
     * A condição de término do jogo é quando um rei está em checkmate, ou seja, está sob ataque e não pode fazer um movimento válido sem continuar em cheque.
     *
     * O método percorre todas as peças brancas e pretas do tabuleiro e verifica se o rei de cada cor está em cheque e não pode fazer um movimento válido.
     * 
     * Se um rei está em cheque e não pode fazer um movimento válido, então o jogo terminou.
     * 
     * @return Um array booleano com dois elementos. O primeiro elemento é verdadeiro se as peças brancas estão em cheque mate. O segundo elemento é verdadeiro se as peças pretas estão em cheque mate.
     * @throws CloneNotSupportedException Se um erro ocorrer durante a clonagem de uma peça.
     */
    public boolean[] chegouFimDeJogo() {
        ArrayList<Peca> pecaBranca = new ArrayList<Peca>();
        for(Peca peca : this.tabuleiro.getPecas(Cor.BRANCO)){
            try {
                if(peca.getTipoPeca()==TipoPeca.PEAO)pecaBranca.add((Peao) peca.clone());
                else if(peca.getTipoPeca()==TipoPeca.CAVALO)pecaBranca.add((Cavalo) peca.clone());
                else if(peca.getTipoPeca()==TipoPeca.BISPO)pecaBranca.add((Bispo) peca.clone());
                else if(peca.getTipoPeca()==TipoPeca.TORRE)pecaBranca.add((Torre) peca.clone());
                else if(peca.getTipoPeca()==TipoPeca.DAMA)pecaBranca.add((Dama) peca.clone());
                else if(peca.getTipoPeca()==TipoPeca.REI)pecaBranca.add((Rei) peca.clone());
            } catch (CloneNotSupportedException e) {
                
                e.printStackTrace();
            };
        }
        ArrayList<Peca> pecaPreto = new ArrayList<Peca>();
        for(Peca peca : this.tabuleiro.getPecas(Cor.PRETO)){
            try {
                if(peca.getTipoPeca()==TipoPeca.PEAO)pecaPreto.add((Peao) peca.clone());
                else if(peca.getTipoPeca()==TipoPeca.CAVALO)pecaPreto.add((Cavalo) peca.clone());
                else if(peca.getTipoPeca()==TipoPeca.BISPO)pecaPreto.add((Bispo) peca.clone());
                else if(peca.getTipoPeca()==TipoPeca.TORRE)pecaPreto.add((Torre) peca.clone());
                else if(peca.getTipoPeca()==TipoPeca.DAMA)pecaPreto.add((Dama) peca.clone());
                else if(peca.getTipoPeca()==TipoPeca.REI)pecaPreto.add((Rei) peca.clone());
            } catch (CloneNotSupportedException e) {
                
                e.printStackTrace();
            };
        }
        
        //instancia de tabuleiro necessaria para realizar processamento sobre as pecas
        Tabuleiro tabu = new Tabuleiro(pecaBranca, pecaPreto);
        
        
        Peca reiBranco = pecaBranca.stream().filter(p -> p.getTipoPeca() == TipoPeca.REI).findFirst().get();
        Peca reiPreto = pecaPreto.stream().filter(p -> p.getTipoPeca() == TipoPeca.REI).findFirst().get();

        boolean brancoPreso=pecaBranca.stream().allMatch(p->p.getMovimentosPossiveis(false).isEmpty()||p.capturada);
        
        boolean brancoCheck=pecaPreto.stream().anyMatch(p-> p.tipoPromocao!=TipoPeca.PEAO && !p.capturada && p.getMovimentosPossiveis(true).stream().anyMatch(m->m.x==reiBranco.getPosicaoTabuleiro().x && m.y==reiBranco.getPosicaoTabuleiro().y)) || this.tabuleiro.getPecas(Cor.PRETO).stream().anyMatch(p->p.tipoPromocao==TipoPeca.PEAO && p.capturada==false && (p.getPosicaoTabuleiro().x+1==reiBranco.getPosicaoTabuleiro().x || p.getPosicaoTabuleiro().x-1==reiBranco.getPosicaoTabuleiro().x)&& p.getPosicaoTabuleiro().y+p.dir==reiBranco.getPosicaoTabuleiro().y);

        boolean pretoPreso=pecaPreto.stream().allMatch(p->p.getMovimentosPossiveis(false).isEmpty()||p.capturada);
        boolean pretoCheck=pecaBranca.stream().anyMatch(p-> p.tipoPromocao!=TipoPeca.PEAO && !p.capturada && p.getMovimentosPossiveis(true).stream().anyMatch(m->m.x==reiPreto.getPosicaoTabuleiro().x && m.y==reiPreto.getPosicaoTabuleiro().y)) || this.tabuleiro.getPecas(Cor.BRANCO).stream().anyMatch(p->p.tipoPromocao==TipoPeca.PEAO && p.capturada==false && (p.getPosicaoTabuleiro().x+1==reiPreto.getPosicaoTabuleiro().x || p.getPosicaoTabuleiro().x-1==reiPreto.getPosicaoTabuleiro().x)&& p.getPosicaoTabuleiro().y+p.dir==reiPreto.getPosicaoTabuleiro().y);

        boolean[] resposta={brancoPreso&&brancoCheck, pretoCheck&&pretoPreso};
        return resposta;

    }

    /**
     * Este método verifica se um empate foi alcançado no tabuleiro de xadrez atual.
     * O jogo é considerado empatado se um dos seguintes for verdadeiro:
     * 1. Ambos os reis estão sozinhos no tabuleiro (não há outras peças).
     * 2. Ambos os lados têm apenas um rei e um bispo.
     * 3. Ambos os lados têm apenas um rei e um cavalo.
     * 4. Todas as peças de uma cor estão bloqueadas (não têm movimentos válidos) e seu rei não está em cheque.
     *
     * @return Verdadeiro se o jogo estiver empatado, falso caso contrário.
     * @throws CloneNotSupportedException Se um erro ocorrer durante a clonagem de uma peça.
     */
    public boolean empatouJogo() {
        ArrayList<Peca> pecaBranca = new ArrayList<Peca>();
        for(Peca peca : this.tabuleiro.getPecas(Cor.BRANCO)){
            try {
                if(peca.getTipoPeca()==TipoPeca.PEAO)pecaBranca.add((Peao) peca.clone());
                else if(peca.getTipoPeca()==TipoPeca.CAVALO)pecaBranca.add((Cavalo) peca.clone());
                else if(peca.getTipoPeca()==TipoPeca.BISPO)pecaBranca.add((Bispo) peca.clone());
                else if(peca.getTipoPeca()==TipoPeca.TORRE)pecaBranca.add((Torre) peca.clone());
                else if(peca.getTipoPeca()==TipoPeca.DAMA)pecaBranca.add((Dama) peca.clone());
                else if(peca.getTipoPeca()==TipoPeca.REI)pecaBranca.add((Rei) peca.clone());
            } catch (CloneNotSupportedException e) {
                
                e.printStackTrace();
            };
        }
        ArrayList<Peca> pecaPreto = new ArrayList<Peca>();
        for(Peca peca : this.tabuleiro.getPecas(Cor.PRETO)){
            try {
                if(peca.getTipoPeca()==TipoPeca.PEAO)pecaPreto.add((Peao) peca.clone());
                else if(peca.getTipoPeca()==TipoPeca.CAVALO)pecaPreto.add((Cavalo) peca.clone());
                else if(peca.getTipoPeca()==TipoPeca.BISPO)pecaPreto.add((Bispo) peca.clone());
                else if(peca.getTipoPeca()==TipoPeca.TORRE)pecaPreto.add((Torre) peca.clone());
                else if(peca.getTipoPeca()==TipoPeca.DAMA)pecaPreto.add((Dama) peca.clone());
                else if(peca.getTipoPeca()==TipoPeca.REI)pecaPreto.add((Rei) peca.clone());
            } catch (CloneNotSupportedException e) {
                
                e.printStackTrace();
            };
        }
        
        Tabuleiro tabu = new Tabuleiro(pecaBranca, pecaPreto);

        Peca reiBranco = pecaBranca.stream().filter(p -> p.getTipoPeca() == TipoPeca.REI).findFirst().get();
        Peca reiPreto = pecaPreto.stream().filter(p -> p.getTipoPeca() == TipoPeca.REI).findFirst().get();


        boolean presoBranco=pecaBranca.stream().allMatch(p->p.getMovimentosPossiveis(false).isEmpty()||p.capturada);
        
        boolean checkBranco=pecaPreto.stream().anyMatch(p-> p.tipoPromocao!=TipoPeca.PEAO && !p.capturada && p.getMovimentosPossiveis(true).stream().anyMatch(m->m.x==reiBranco.getPosicaoTabuleiro().x && m.y==reiBranco.getPosicaoTabuleiro().y)) || this.tabuleiro.getPecas(Cor.PRETO).stream().anyMatch(p->p.tipoPromocao==TipoPeca.PEAO && p.capturada==false && (p.getPosicaoTabuleiro().x+1==reiBranco.getPosicaoTabuleiro().x || p.getPosicaoTabuleiro().x-1==reiBranco.getPosicaoTabuleiro().x)&& p.getPosicaoTabuleiro().y+p.dir==reiBranco.getPosicaoTabuleiro().y);

        boolean reiSoloBranco = pecaBranca.stream().allMatch(p->p.tipoPeca==TipoPeca.REI||p.capturada);

        boolean bispoBranco=pecaBranca.stream().anyMatch(p->p.tipoPromocao==TipoPeca.BISPO && !p.capturada && pecaBranca.stream().allMatch(p2-> p2==p  || p2.tipoPeca==TipoPeca.REI || p2.capturada));

        boolean cavaloBranco=pecaBranca.stream().anyMatch(p->p.tipoPromocao==TipoPeca.CAVALO && !p.capturada && pecaBranca.stream().allMatch(p2-> p2==p || p2.tipoPeca==TipoPeca.REI || (p2.tipoPromocao==TipoPeca.CAVALO && !p2.capturada) || p2.capturada));





        boolean presoPreto=pecaPreto.stream().allMatch(p->p.getMovimentosPossiveis(false).isEmpty()||p.capturada);
        
        boolean checkPreto=pecaBranca.stream().anyMatch(p-> p.tipoPromocao!=TipoPeca.PEAO && !p.capturada && p.getMovimentosPossiveis(true).stream().anyMatch(m->m.x==reiPreto.getPosicaoTabuleiro().x && m.y==reiPreto.getPosicaoTabuleiro().y)) || this.tabuleiro.getPecas(Cor.BRANCO).stream().anyMatch(p->p.tipoPromocao==TipoPeca.PEAO && p.capturada==false && (p.getPosicaoTabuleiro().x+1==reiPreto.getPosicaoTabuleiro().x || p.getPosicaoTabuleiro().x-1==reiPreto.getPosicaoTabuleiro().x)&& p.getPosicaoTabuleiro().y+p.dir==reiPreto.getPosicaoTabuleiro().y);

        boolean reiSoloPreto = pecaPreto.stream().allMatch(p->p.tipoPeca==TipoPeca.REI||p.capturada);

        boolean bispoPreto = pecaPreto.stream().anyMatch(p->p.tipoPromocao==TipoPeca.BISPO && !p.capturada && pecaPreto.stream().allMatch(p2-> p2==p  || p2.tipoPeca==TipoPeca.REI || p2.capturada));

        boolean cavaloPreto = pecaPreto.stream().anyMatch(p->p.tipoPromocao==TipoPeca.CAVALO && !p.capturada && pecaPreto.stream().allMatch(p2-> p2==p || p2.tipoPeca==TipoPeca.REI || (p2.tipoPromocao==TipoPeca.CAVALO && !p2.capturada) || p2.capturada));

        return (  ((presoBranco && !checkBranco)||(presoPreto && !checkPreto))   ||  ( (reiSoloBranco||bispoBranco||cavaloBranco)  &&  (reiSoloPreto||bispoPreto||cavaloPreto) )  );

    }

    /**
     * Este método executa um movimento no tabuleiro de xadrez.
     * O movimento é considerado válido e será executado se a posição final estiver dentro das posições possíveis para a peça.
     * Se um movimento válido for realizado, o movimento e a peça potencialmente capturada serão adicionados ao histórico de movimentos.
     *
     * @param movimento O movimento a ser realizado.
     * @param ehIA Um booleano que indica se o movimento é realizado pela Inteligência Artificial.
     * @return Verdadeiro se o movimento for válido e for executado com sucesso, falso caso contrário.
     */
    public boolean executaMovimento(Movimento movimento,boolean ehIA) {
        Peca pecaMovimentando = movimento.getPeca();
        Posicao posicaoPosterior = movimento.getPosicaoPosterior();
        ArrayList<Posicao> posicoesValidas = pecaMovimentando.getMovimentosPossiveis(false);

        boolean posicaoValida = posicoesValidas.stream()
                .anyMatch(p -> p.x == posicaoPosterior.x && p.y == posicaoPosterior.y);
        if (posicaoValida) {
            Peca pecaCapturada = this.tabuleiro.movePeca(pecaMovimentando, posicaoPosterior,ehIA);
            pecaMovimentando.qtdMovimento+=1;
            if(movimento.movimentoDuplo){
                this.tabuleiro.movePeca(movimento.getPeca2(), movimento.getPosicaoPosterior2(), ehIA);
                if(pecaCapturada==null)movimento.getPeca2().qtdMovimento+=1;
            }
            this.historico.adicionaMovimento(movimento, pecaCapturada);
            if(!ehIA){
                if(this.getTurno() == Cor.BRANCO){
                    this.vez.setText("Vez da IA");
                    new PrintaHistorico(this).print(brancasTextArea);
                }
                else{
                    this.vez.setText("Vez de " + menu.getNome());
                    new PrintaHistorico(this).print(pretasTextArea);
                }
            }
            return true;
        }

        return false;
    }

    /**
     * Este método desfaz o último movimento realizado no tabuleiro de xadrez.
     * Se o último movimento foi um movimento duplo (roque), ambos os movimentos são desfeitos.
     * Caso uma peça tenha sido capturada no último movimento, ela é retornada ao tabuleiro.
     * Se não houver movimentos no histórico para desfazer, um erro será lançado.
     *
     * @param ehIA Um booleano que indica se o movimento a ser desfeito foi realizado pela Inteligência Artificial.
     * @throws Error Se não existir nenhum movimento no histórico para ser desfeito.
     */
    public void desfazUltimoMovimento(boolean ehIA) {
        Movimento ultimoMovimento = this.historico.getUltimoMovimento();
        if (ultimoMovimento == null) {
            throw new Error("Tentou desfazer sem movimento no historico");
        }
        ultimoMovimento.getPeca().qtdMovimento-=1;
        if(ultimoMovimento.movimentoDuplo){
            if(ultimoMovimento.getPecaCapturada()==null)ultimoMovimento.getPeca2().qtdMovimento-=1;
            this.tabuleiro.movePeca(ultimoMovimento.getPeca2(), ultimoMovimento.getPosicaoAnterior2(), ehIA);
        }
        this.tabuleiro.movePeca(ultimoMovimento.getPeca(), ultimoMovimento.getPosicaoAnterior(),ehIA);
        if (ultimoMovimento.getPecaCapturada() != null) {
            this.tabuleiro.recuperaPeca(ultimoMovimento.getPecaCapturada(), ultimoMovimento.getPosicaoPosterior(),ehIA);
        }
        this.historico.reverteMovimento();
    }

    /**
     * Este método controla o movimento da Inteligência Artificial (IA) no jogo de xadrez. 
     * O método busca o movimento que a IA deseja fazer, executa o movimento e em seguida muda o turno para o jogador humano.
     * Se a IA tentar realizar um movimento inválido, um erro será lançado.
     *
     * @throws Error Se a IA tenta executar um movimento inválido.
     */
    public void moveIA() {
        Movimento movimento = this.IA.getIAMovimento();
        if(movimento.getPeca()==null)System.out.println("IA derrotada");
        else{

            boolean iaMoveu = this.executaMovimento(movimento,false);
            if (!iaMoveu) {
                throw new Error("IA tentou movimento invalido");
            }
        }

        this.turno = Cor.BRANCO;

    }


    public void setIA(maquinaDeRegras.IA ia2) {
    }
}

