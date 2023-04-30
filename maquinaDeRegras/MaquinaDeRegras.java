package maquinaDeRegras;

import java.util.ArrayList;

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

    public MaquinaDeRegras(Cor jogador) {
        this.historico = new Historico();
        this.turno = Cor.BRANCO;
        this.jogador = jogador;
        this.adversario = jogador == Cor.BRANCO ? Cor.PRETO : Cor.BRANCO;
        this.partidaComIA = false;
        this.IA = null;
    }

    public MaquinaDeRegras(Cor jogador, int nivelDificuldadeIA) {
        this.historico = new Historico();
        this.turno = Cor.BRANCO;
        this.jogador = jogador;
        this.adversario = jogador == Cor.BRANCO ? Cor.PRETO : Cor.BRANCO;
        this.partidaComIA = true;
        this.IA = new IA(this.adversario, nivelDificuldadeIA);
        this.IA.setMaquinaDeRegras(this);
    }

    /**
     * Usar injeção de dependência
     */
    public void setTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        this.IA.setTabuleiro(this.tabuleiro);
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

        if (reiBranco == null || reiPreto == null) {
            return true;
        }

        return false;
    }

    public boolean executaMovimento(Movimento movimento) {
        Peca pecaMovimentando = movimento.getPeca();
        Posicao posicaoPosterior = movimento.getPosicaoPosterior();
        ArrayList<Posicao> posicoesValidas = pecaMovimentando.getMovimentosPossiveis();

        boolean posicaoValida = posicoesValidas.stream()
                .anyMatch(p -> p.x == posicaoPosterior.x && p.y == posicaoPosterior.y);
        if (posicaoValida) {
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

    public void moveIA() {
        if (this.partidaComIA) {
            Movimento movimento = this.IA.getIAMovimento();
            boolean iaMoveu = this.executaMovimento(movimento);
            if (!iaMoveu) {
                throw new Error("IA tentou movimento invalido");
            }
        }
    }
}