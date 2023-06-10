package tests.config;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import config.Config;
import config.SetupPecas;
import maquinaDeRegras.Tabuleiro;
import pecas.Peca;
import pecas.TipoPeca;
import utils.Cor;
import utils.Posicao;

public class SetupTabuleiroTest {
    private ArrayList<Peca> pecasBrancas;
    private ArrayList<Peca> pecasPretas;
    private Tabuleiro tabuleiro;

    @Before
    public void setUpTest() {
        Config.LOAD_FAKE_IMAGES = true;
        Config.loadImages();
        pecasBrancas = SetupPecas.setup(Cor.BRANCO);
        pecasPretas = SetupPecas.setup(Cor.PRETO);
        tabuleiro = new Tabuleiro(pecasBrancas, pecasPretas);
    }

    @Test
    public void setupPecasPosicoesPeao() {
        for (int i = 0; i < 8; i++) {
            assertEquals(TipoPeca.PEAO, tabuleiro.getPeca(new Posicao(i, 1)).tipoPeca);
            assertEquals(TipoPeca.PEAO, tabuleiro.getPeca(new Posicao(i, 6)).tipoPeca);
        }
    }

    @Test
    public void setupPecasBispos() {
        assertEquals(TipoPeca.BISPO, tabuleiro.getPeca(new Posicao(2, 0)).tipoPeca);
        assertEquals(TipoPeca.BISPO, tabuleiro.getPeca(new Posicao(5, 0)).tipoPeca);
        assertEquals(TipoPeca.BISPO, tabuleiro.getPeca(new Posicao(2, 7)).tipoPeca);
        assertEquals(TipoPeca.BISPO, tabuleiro.getPeca(new Posicao(5, 7)).tipoPeca);
    }

    @Test
    public void setupPecasCavalos() {
        assertEquals(TipoPeca.CAVALO, tabuleiro.getPeca(new Posicao(1, 0)).tipoPeca);
        assertEquals(TipoPeca.CAVALO, tabuleiro.getPeca(new Posicao(6, 0)).tipoPeca);
        assertEquals(TipoPeca.CAVALO, tabuleiro.getPeca(new Posicao(1, 7)).tipoPeca);
        assertEquals(TipoPeca.CAVALO, tabuleiro.getPeca(new Posicao(6, 7)).tipoPeca);
    }

    @Test
    public void setupPecasTorres() {
        assertEquals(TipoPeca.TORRE, tabuleiro.getPeca(new Posicao(0, 0)).tipoPeca);
        assertEquals(TipoPeca.TORRE, tabuleiro.getPeca(new Posicao(7, 0)).tipoPeca);
        assertEquals(TipoPeca.TORRE, tabuleiro.getPeca(new Posicao(0, 7)).tipoPeca);
        assertEquals(TipoPeca.TORRE, tabuleiro.getPeca(new Posicao(7, 7)).tipoPeca);
    }

    @Test
    public void setupPecasRei() {
        assertEquals(TipoPeca.REI, tabuleiro.getPeca(new Posicao(4, 0)).tipoPeca);
        assertEquals(TipoPeca.REI, tabuleiro.getPeca(new Posicao(4, 7)).tipoPeca);
    }

    @Test
    public void setupPecasRainha() {
        assertEquals(TipoPeca.DAMA, tabuleiro.getPeca(new Posicao(3, 0)).tipoPeca);
        assertEquals(TipoPeca.DAMA, tabuleiro.getPeca(new Posicao(3, 7)).tipoPeca);
    }

    @Test
    public void setupPecasPosicoesVazias() {
        for (int i = 0; i < 8; i++) {
            assertEquals(null, tabuleiro.getPeca(new Posicao(i, 2)));
            assertEquals(null, tabuleiro.getPeca(new Posicao(i, 3)));
            assertEquals(null, tabuleiro.getPeca(new Posicao(i, 4)));
            assertEquals(null, tabuleiro.getPeca(new Posicao(i, 5)));
        }
    }
}