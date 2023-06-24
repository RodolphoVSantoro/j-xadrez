package tests.maquinaDeRegras;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import config.Config;
import config.SetupPecas;
import maquinaDeRegras.MaquinaDeRegras;
import maquinaDeRegras.Tabuleiro;
import pecas.Bispo;
import pecas.Cavalo;
import pecas.Dama;
import pecas.Peao;
import pecas.Peca;
import pecas.Rei;
import pecas.Torre;
import utils.Cor;
import utils.Posicao;

public class ReiTest2 {

    private Rei rei;
    private Rei reiP;
    private Tabuleiro tabuleiro;

    @Before
    public void setUp() {

        Config.LOAD_FAKE_IMAGES = true;
        Config.loadImages();

        this.rei = new Rei(new Posicao(4, 7), Cor.BRANCO);

        this.reiP = new Rei(new Posicao(4, 0), Cor.PRETO);
    }

    @Test
    public void testGetMovimentosPossiveis1() throws CloneNotSupportedException {

        Torre torre = new Torre(new Posicao(5, 0), Cor.PRETO);
        Dama dama = new Dama(new Posicao(4, 1), Cor.PRETO);
        Peao peao = new Peao(new Posicao(4, 6), Cor.PRETO);
        Bispo bispo = new Bispo(new Posicao(7, 1), Cor.PRETO);
        Cavalo cavalo = new Cavalo(new Posicao(1, 1), Cor.PRETO);
        Peao peao2 = new Peao(new Posicao(2, 5), Cor.PRETO);
        Torre torre1 = new Torre(new Posicao(0, 0), Cor.PRETO);
        Torre torre2 = new Torre(new Posicao(0, 7), Cor.BRANCO);
        Torre torre3 = new Torre(new Posicao(7, 0), Cor.PRETO);
        Torre torre4 = new Torre(new Posicao(7, 7), Cor.BRANCO);
        Torre torre5 = new Torre(new Posicao(3, 6), Cor.PRETO);

        ArrayList<Peca> pecasBrancas = new ArrayList<Peca>();
        pecasBrancas.add(rei);
        pecasBrancas.add(torre2);
        pecasBrancas.add(torre4);

        ArrayList<Peca> pecasPretas = new ArrayList<Peca>();
        pecasPretas.add(reiP);
        pecasPretas.add(dama);
        pecasPretas.add(bispo);
        pecasPretas.add(cavalo);
        pecasPretas.add(peao);
        pecasPretas.add(peao2);
        pecasPretas.add(torre);
        pecasPretas.add(torre1);
        pecasPretas.add(torre3);
        pecasPretas.add(torre5);

        this.tabuleiro = new Tabuleiro(pecasBrancas, pecasPretas);

        rei.setTabuleiro(tabuleiro);
        reiP.setTabuleiro(tabuleiro);
        torre.setTabuleiro(tabuleiro);
        bispo.setTabuleiro(tabuleiro);
        cavalo.setTabuleiro(tabuleiro);
        dama.setTabuleiro(tabuleiro);
        peao.setTabuleiro(tabuleiro);
        peao2.setTabuleiro(tabuleiro);

        ArrayList<Posicao> movimentosPossiveis = rei.getMovimentosPossiveis(false);

        // Verificar se a lista de movimentos possíveis está correta
        assertEquals(2, movimentosPossiveis.size());

    }

    @Test
    public void testGetMovimentosPossiveis2() throws CloneNotSupportedException {

        Torre torre = new Torre(new Posicao(5, 7), Cor.BRANCO);
        Dama dama = new Dama(new Posicao(4, 7), Cor.BRANCO);
        Peao peao = new Peao(new Posicao(4, 1), Cor.BRANCO);
        Peao peao2 = new Peao(new Posicao(2, 2), Cor.BRANCO);

        Bispo bispo = new Bispo(new Posicao(7, 6), Cor.BRANCO);
        Cavalo cavalo = new Cavalo(new Posicao(1, 6), Cor.BRANCO);

        Torre torre1 = new Torre(new Posicao(0, 0), Cor.PRETO);
        Torre torre2 = new Torre(new Posicao(0, 7), Cor.BRANCO);
        Torre torre3 = new Torre(new Posicao(7, 0), Cor.PRETO);
        Torre torre4 = new Torre(new Posicao(7, 7), Cor.BRANCO);
        Torre torre5 = new Torre(new Posicao(3, 1), Cor.BRANCO);

        ArrayList<Peca> pecasBrancas = new ArrayList<Peca>();
        pecasBrancas.add(rei);
        pecasBrancas.add(torre2);
        pecasBrancas.add(torre4);
        pecasBrancas.add(dama);
        pecasBrancas.add(bispo);
        pecasBrancas.add(cavalo);
        pecasBrancas.add(peao);
        pecasBrancas.add(peao2);
        pecasBrancas.add(torre);
        pecasBrancas.add(torre5);

        ArrayList<Peca> pecasPretas = new ArrayList<Peca>();
        pecasPretas.add(reiP);
        pecasPretas.add(torre1);
        pecasPretas.add(torre3);

        this.tabuleiro = new Tabuleiro(pecasBrancas, pecasPretas);

        rei.setTabuleiro(tabuleiro);
        reiP.setTabuleiro(tabuleiro);
        torre.setTabuleiro(tabuleiro);
        bispo.setTabuleiro(tabuleiro);
        cavalo.setTabuleiro(tabuleiro);
        dama.setTabuleiro(tabuleiro);
        peao.setTabuleiro(tabuleiro);
        peao2.setTabuleiro(tabuleiro);

        ArrayList<Posicao> movimentosPossiveis = reiP.getMovimentosPossiveis(false);

        // Verificar se a lista de movimentos possíveis está correta
        assertEquals(2, movimentosPossiveis.size());

    }

}
