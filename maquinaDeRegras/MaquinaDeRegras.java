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
import events.PrintaHistorico;

public class MaquinaDeRegras {
    private Tabuleiro tabuleiro;
    private Cor turno;
    private Cor jogador;
    private Cor adversario;
    private boolean partidaComIA;
    private Historico historico;
    private IA IA;
    private JTextArea brancasTextArea;
    private JTextArea pretasTextArea;
    private JTextPane vez;
    private Menu menu;

    public boolean checkmate=false;

    public MaquinaDeRegras(Cor jogador) {
        this.turno = Cor.BRANCO;
        this.jogador = jogador;
        this.adversario = jogador == Cor.BRANCO ? Cor.PRETO : Cor.BRANCO;
        this.partidaComIA = false;
        this.IA = null;
    }


    public MaquinaDeRegras(Cor jogador, int nivelDificuldadeIA, JTextArea brancasTextArea, JTextArea pretasTextArea, JTextPane vez, Menu menu) {
        this.turno = Cor.BRANCO;
        this.jogador = jogador;
        this.adversario = jogador == Cor.BRANCO ? Cor.PRETO : Cor.BRANCO;
        this.partidaComIA = true;
        this.IA = new IA(this.adversario, nivelDificuldadeIA);
        this.brancasTextArea = brancasTextArea;
        this.pretasTextArea = pretasTextArea;
        this.vez = vez;
        this.menu = menu;
        this.IA.setMaquinaDeRegras(this);
    }

    /**
     * Usar injeção de dependência
     */

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
        if (this.partidaComIA && this.turno == this.adversario) {
            this.moveIA();
            this.turno = this.jogador;
        }
    }

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
        

        //Tabuleiro faz acoes necessarias na peca em seu construtor;
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

    public boolean empatouJogo() {
        Peca reiBranco = this.tabuleiro.getPecas(Cor.BRANCO).stream().filter(p -> p.getTipoPeca() == TipoPeca.REI)
                .findFirst()
                .get();
        Peca reiPreto = this.tabuleiro.getPecas(Cor.PRETO).stream().filter(p -> p.getTipoPeca() == TipoPeca.REI)
                .findFirst()
                .get();

        if (reiBranco.getCapturado() || reiPreto.getCapturado()) {
            return true;
        }

        return false;
    }

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

    public void moveIA() {
        if (this.partidaComIA) {
            Movimento movimento = this.IA.getIAMovimento();
            if(movimento.getPeca()==null)System.out.println("morri");
            else{

                boolean iaMoveu = this.executaMovimento(movimento,false);
                if (!iaMoveu) {
                    throw new Error("IA tentou movimento invalido");
                }
            }
        }
        this.turno = Cor.BRANCO;
    }
}
