package maquinaDeRegras;

import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextPane;

import events.PrintaHistorico;
import menu.Menu;
import pecas.Peca;
import pecas.TipoPeca;
import utils.Cor;
import utils.Posicao;

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

    public MaquinaDeRegras(Cor jogador) {
        this.historico = new Historico();
        this.turno = Cor.BRANCO;
        this.jogador = jogador;
        this.adversario = jogador == Cor.BRANCO ? Cor.PRETO : Cor.BRANCO;
        this.partidaComIA = false;
        this.IA = null;
    }

    public MaquinaDeRegras(Cor jogador, int nivelDificuldadeIA, JTextArea brancasTextArea, JTextArea pretasTextArea, JTextPane vez, Menu menu) {
        this.historico = new Historico();
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

    public boolean chegouFimDeJogo() {
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
        ArrayList<Posicao> posicoesValidas = pecaMovimentando.getMovimentosPossiveis();

        boolean posicaoValida = posicoesValidas.stream()
                .anyMatch(p -> p.x == posicaoPosterior.x && p.y == posicaoPosterior.y);
        if (posicaoValida) {
            Peca pecaCapturada = this.tabuleiro.movePeca(pecaMovimentando, posicaoPosterior,ehIA);
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
        this.tabuleiro.movePeca(ultimoMovimento.getPeca(), ultimoMovimento.getPosicaoAnterior(),ehIA);
        if (ultimoMovimento.getPecaCapturada() != null) {
            this.tabuleiro.recuperaPeca(ultimoMovimento.getPecaCapturada(), ultimoMovimento.getPosicaoPosterior(),ehIA);
        }
        this.historico.reverteMovimento();
    }

    public void moveIA() {
        if (this.partidaComIA) {
            Movimento movimento = this.IA.getIAMovimento();
            boolean iaMoveu = this.executaMovimento(movimento,false);
            if (!iaMoveu) {
                throw new Error("IA tentou movimento invalido");
            }
        }
        this.turno = Cor.BRANCO;
    }
}
