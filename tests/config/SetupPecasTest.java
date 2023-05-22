package tests.config;

import org.junit.Before;
import org.junit.Test;

import config.Config;
import config.SetupPecas;
import pecas.Bispo;
import pecas.*;
import utils.Cor;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class SetupPecasTest {
    private ArrayList<Peca> pecasBrancas;
    private ArrayList<Peca> pecasPretas;

    private void testePorClasse(ArrayList<Peca> pecas, Class<?> classe, int quantidade) {
        List<Peca> pecasDaClasse = pecas.stream().filter(p -> p.getClass() == classe).collect(Collectors.toList());
        assertEquals(quantidade, pecasDaClasse.size());
    }

    private void testeAmbasCores(Class<?> classe, int quantidade) {
        testePorClasse(this.pecasBrancas, classe, quantidade);
        testePorClasse(this.pecasPretas, classe, quantidade);
    }

    @Before
    public void setUpTest() {
        Config.LOAD_FAKE_IMAGES = true;
        Config.loadImages();
        pecasBrancas = SetupPecas.setup(Cor.BRANCO);
        pecasPretas = SetupPecas.setup(Cor.PRETO);
    }

    @Test
    public void setupPecas() {
        assertEquals(16, pecasBrancas.size());
        assertEquals(16, pecasPretas.size());
    }

    @Test
    public void setupPecasPeoes() {
        testeAmbasCores(Peao.class, 8);
    }

    @Test
    public void setupPecasRei() {
        testeAmbasCores(Rei.class, 1);
    }

    @Test
    public void setupPecasDama() {
        testeAmbasCores(Dama.class, 1);
    }

    @Test
    public void setupPecasBispos() {
        testeAmbasCores(Bispo.class, 2);
    }

    @Test
    public void setupPecasCavalo() {
        testeAmbasCores(Cavalo.class, 2);
    }

    @Test
    public void setupPecasTorre() {
        testeAmbasCores(Torre.class, 2);
    }
    // test case for valid pawn capture
    @Test
    public void testPecaPeaoCaptura() {
        Peao peao = new Peao(new utils.Posicao(0, 0), Cor.BRANCO);
        Peca peaozinho = new Peao();
        Torre torre = new Torre(new utils.Posicao(1, 1), Cor.PRETO);
        assertEquals(true, peao.podeCapturar(torre.getPosicaoTabuleiro()));
    }
    // test case for invalid pawn capture
    @Test
    public void testPecaPeaoNaoCaptura() {
        Peao peao = new Peao(new utils.Posicao(0, 0), Cor.BRANCO);
        Torre torre = new Torre(new utils.Posicao(0, 1), Cor.PRETO);
        assertEquals(false, peao.podeCapturar(torre.getPosicaoTabuleiro()));
    }
    // test case for valid pawn movement
    @Test
    public void testPecaPeaoMovimento() {
        Peao peao = new Peao(new utils.Posicao(0, 0), Cor.BRANCO);
        assertEquals(true, peao.podeMover(new utils.Posicao(0, 1)));
    }
    // test case for invalid pawn movement
    @Test
    public void testPecaPeaoNaoMovimento() {
        Peao peao = new Peao(new utils.Posicao(0, 0), Cor.BRANCO);
        assertEquals(false, peao.podeMover(new utils.Posicao(1, 1)));
    }
    // test case for valid rook movement
    @Test
    public void testPecaTorreMovimento() {
        Torre torre = new Torre(new utils.Posicao(0, 0), Cor.BRANCO);
        assertEquals(true, torre.podeMover(new utils.Posicao(0, 1)));
    }
    // test case for invalid rook movement
    @Test
    public void testPecaTorreNaoMovimento() {
        Torre torre = new Torre(new utils.Posicao(0, 0), Cor.BRANCO);
        assertEquals(false, torre.podeMover(new utils.Posicao(1, 1)));
    }
    // test case for valid knight movement
    @Test
    public void testPecaCavaloMovimento() {
        Cavalo cavalo = new Cavalo(new utils.Posicao(0, 0), Cor.BRANCO);
        assertEquals(true, cavalo.podeMover(new utils.Posicao(1, 2)));
    }
    // test case for invalid knight movement
    @Test
    public void testPecaCavaloNaoMovimento() {
        Cavalo cavalo = new Cavalo(new utils.Posicao(0, 0), Cor.BRANCO);
        assertEquals(false, cavalo.podeMover(new utils.Posicao(1, 1)));
    }
    // test case for valid bishop movement
    @Test
    public void testPecaBispoMovimento() {
        Bispo bispo = new Bispo(new utils.Posicao(0, 0), Cor.BRANCO);
        assertEquals(true, bispo.podeMover(new utils.Posicao(1, 1)));
    }
    // test case for invalid bishop movement
    @Test
    public void testPecaBispoNaoMovimento() {
        Bispo bispo = new Bispo(new utils.Posicao(0, 0), Cor.BRANCO);
        assertEquals(false, bispo.podeMover(new utils.Posicao(1, 2)));
    }
    // test case for valid queen movement
    @Test
    public void testPecaDamaMovimento() {
        Dama dama = new Dama(new utils.Posicao(0, 0), Cor.BRANCO);
        assertEquals(true, dama.podeMover(new utils.Posicao(1, 1)));
    }
    // test case for invalid queen movement
    @Test
    public void testPecaDamaNaoMovimento() {
        Dama dama = new Dama(new utils.Posicao(0, 0), Cor.BRANCO);
        assertEquals(false, dama.podeMover(new utils.Posicao(1, 2)));
    }
    // test case for valid king movement
    @Test
    public void testPecaReiMovimento() {
        Rei rei = new Rei(new utils.Posicao(0, 0), Cor.BRANCO);
        assertEquals(true, rei.podeMover(new utils.Posicao(1, 1)));
    }
    // test case for invalid king movement
    @Test
    public void testPecaReiNaoMovimento() {
        Rei rei = new Rei(new utils.Posicao(0, 0), Cor.BRANCO);
        assertEquals(false, rei.podeMover(new utils.Posicao(1, 2)));
    }
    

}

