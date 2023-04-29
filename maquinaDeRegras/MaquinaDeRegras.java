package maquinaDeRegras;

import java.util.ArrayList;

import pecas.Peca;
import pecas.TipoPeca;
import utils.Box;
import utils.Cor;
import utils.Posicao;

public class MaquinaDeRegras {
    private Tabuleiro tabuleiro;
    private Cor jogador;
    private Cor adversario;
    private boolean IA;
    private Box<Integer> nivelDificuldadeIA;
    private Historico historico;

    public MaquinaDeRegras(Cor jogador) {
        this.historico = new Historico();
        this.jogador = jogador;
        this.adversario = jogador == Cor.BRANCO ? Cor.PRETO : Cor.BRANCO;
        this.IA = false;
        this.nivelDificuldadeIA = new Box<Integer>();
    }

    public MaquinaDeRegras(Cor jogador, int nivelDificuldadeIA) {
        this.historico = new Historico();
        this.jogador = jogador;
        this.adversario = jogador == Cor.BRANCO ? Cor.PRETO : Cor.BRANCO;
        this.IA = true;
        this.nivelDificuldadeIA = new Box<Integer>(Integer.valueOf(nivelDificuldadeIA));
    }

    /**
     * Usar injeção de dependência
     */
    public void setTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public boolean chegouFimDeJogo() {
        Peca reiBranco = this.tabuleiro.getPecas(Cor.BRANCO).stream().filter(p -> p.getTipoPeca() == TipoPeca.REI)
                .findFirst()
                .get();
        Peca reiPreto = this.tabuleiro.getPecas(Cor.PRETO).stream().filter(p -> p.getTipoPeca() == TipoPeca.REI)
                .findFirst()
                .get();

        if (reiBranco == null || reiPreto == null) {
            return true;
        }

        return false;
    }

    public boolean executaMovimento(Movimento movimento) {
        Peca pecaMovimentando = movimento.getPeca();
        Posicao posicaoPosterior = movimento.getPosicaoPosterior();

        ArrayList<Posicao> posicoesValidas = pecaMovimentando.getMovimentosPossiveis();
        if (posicoesValidas.contains(posicaoPosterior)) {
            Peca pecaCapturada = this.tabuleiro.movePeca(pecaMovimentando, posicaoPosterior);
            this.historico.adicionaMovimento(movimento, pecaCapturada);
            return true;
        }

        return false;
    }

    public void desfazUltimoMovimento() {
        Movimento ultimoMovimento = this.historico.getUltimoMovimento();
        if (ultimoMovimento == null) {
            throw new Error("Tentou desfazer sem movimento no historico");
        }
        this.tabuleiro.movePeca(ultimoMovimento.getPeca(), ultimoMovimento.getPosicaoAnterior());
        if (ultimoMovimento.getPecaCapturada() != null) {
            this.tabuleiro.recuperaPeca(ultimoMovimento.getPecaCapturada(), ultimoMovimento.getPosicaoPosterior());
        }
        this.historico.reverteMovimento();
    }
}
