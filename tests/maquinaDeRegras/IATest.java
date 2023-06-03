package tests.maquinaDeRegras;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import config.Config;
import config.SetupPecas;
import maquinaDeRegras.IA;
import maquinaDeRegras.MaquinaDeRegras;
import maquinaDeRegras.Movimento;
import maquinaDeRegras.Tabuleiro;
import pecas.Peca;
import utils.Cor;
import utils.Posicao;

public class IATest {

    private MaquinaDeRegras maquinaDeRegras;
    private Tabuleiro tabuleiro;
    private IA ia;
    private ArrayList<Peca> pecasBrancas;
    private ArrayList<Peca> pecasPretas;

    @Before
    public void setUpTest() {
        Config.LOAD_FAKE_IMAGES = true;
        Config.loadImages();
        pecasBrancas = SetupPecas.setup(Cor.BRANCO);
        pecasPretas = SetupPecas.setup(Cor.PRETO);
        tabuleiro = new Tabuleiro(pecasBrancas, pecasPretas);
        maquinaDeRegras = new MaquinaDeRegras(Cor.BRANCO, 3);
    }

    public void testeMovimentoIA(int xJogador, int yJogador, Peca peca, int xEsperado, int yEsperado,
            int nivelDificuldadeIA) {
        this.ia = new IA(Cor.BRANCO, nivelDificuldadeIA);
        this.maquinaDeRegras.setTabuleiro(this.tabuleiro);
        this.maquinaDeRegras.setIA(this.ia);
        this.ia.setMaquinaDeRegras(this.maquinaDeRegras);
        this.ia.setTabuleiro(this.tabuleiro);

        Movimento movimentoJogador = new Movimento(peca, peca.getPosicaoTabuleiro(),
                new Posicao(xJogador, yJogador), null);
        maquinaDeRegras.executaMovimento(movimentoJogador, false);
        Movimento movimentoIA = ia.getIAMovimento();
        assertEquals(xEsperado, movimentoIA.getPosicaoPosterior().x);
        assertEquals(yEsperado, movimentoIA.getPosicaoPosterior().y);
    }

    @Test
    public void testePeao() {
        int x = 2;
        int y = 3;
        Peca peao = this.tabuleiro.getPeca(x, 1);
        testeMovimentoIA(x, y, peao, 7, 5, 6);
    }
}