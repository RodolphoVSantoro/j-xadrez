package tests.maquinaDeRegras;

import javax.swing.JTextArea;
import javax.swing.JTextPane;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import config.Config;
import maquinaDeRegras.MaquinaDeRegras;
import maquinaDeRegras.Tabuleiro;
import menu.Menu;
import pecas.Bispo;
import pecas.Cavalo;
import pecas.Dama;
import pecas.Peao;
import pecas.Peca;
import pecas.Rei;
import pecas.Torre;
import utils.Cor;
import utils.Posicao;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

public class MaquinaDeRegrasUnitTests {
    private MaquinaDeRegras maquinaDeRegras;
    private Tabuleiro tabuleiroMock;
    private Rei rei;
    private Rei reiP;

    @Before
    public void setUp() {

        Config.LOAD_FAKE_IMAGES = true;
        Config.loadImages();
        // Configurar objetos mock
        tabuleiroMock = Mockito.mock(Tabuleiro.class);
        this.rei = new Rei(new Posicao(4, 7), Cor.BRANCO);

        this.reiP = new Rei(new Posicao(4, 0), Cor.PRETO);
        // Criar instância da classe a ser testada
        maquinaDeRegras = new MaquinaDeRegras(Cor.BRANCO, 3);
        maquinaDeRegras.setTabuleiro(tabuleiroMock);
    }

    @Test
    public void testGetTabuleiro() {
        Tabuleiro result = maquinaDeRegras.getTabuleiro();
        assertEquals(tabuleiroMock, result);
    }

    @Test
    public void testSetTurno() {
        Cor cor = Cor.PRETO;
        maquinaDeRegras.setTurno(cor);
        assertEquals(cor, maquinaDeRegras.getTurno());
    }

    @Test
    public void testChegouFimDeJogo() throws CloneNotSupportedException {

        Torre torre = new Torre(new Posicao(0, 7), Cor.PRETO);
        Dama dama = new Dama(new Posicao(0, 6), Cor.PRETO);
        Peao peao = new Peao(new Posicao(0, 5), Cor.PRETO);
        Bispo bispo = new Bispo(new Posicao(0, 4), Cor.PRETO);
        Cavalo cavalo = new Cavalo(new Posicao(0, 3), Cor.PRETO);

        ArrayList<Peca> vetorMock = new ArrayList<Peca>();
        ArrayList<Peca> vetorMock2 = new ArrayList<Peca>();

        vetorMock.add(torre);
        vetorMock.add(dama);
        vetorMock.add(cavalo);
        vetorMock.add(bispo);
        vetorMock.add(peao);
        vetorMock.add(reiP);

        vetorMock2.add(rei);

        when(tabuleiroMock.getPecas(Cor.PRETO)).thenReturn(vetorMock);
        when(tabuleiroMock.getPecas(Cor.BRANCO)).thenReturn(vetorMock2);

        boolean[] vetor = maquinaDeRegras.chegouFimDeJogo();

        assertEquals(true, vetor[0]);
        assertEquals(false, vetor[1]);

    }

    @Test
    public void testChegouFimDeJogo2() throws CloneNotSupportedException {
        Torre torre = new Torre(new Posicao(0, 0), Cor.BRANCO);
        Dama dama = new Dama(new Posicao(0, 1), Cor.BRANCO);
        Peao peao = new Peao(new Posicao(0, 2), Cor.BRANCO);
        Bispo bispo = new Bispo(new Posicao(0, 3), Cor.BRANCO);
        Cavalo cavalo = new Cavalo(new Posicao(0, 4), Cor.BRANCO);

        ArrayList<Peca> vetorMock = new ArrayList<Peca>();
        ArrayList<Peca> vetorMock2 = new ArrayList<Peca>();

        vetorMock.add(torre);
        vetorMock.add(dama);
        vetorMock.add(cavalo);
        vetorMock.add(bispo);
        vetorMock.add(peao);
        vetorMock.add(rei);

        vetorMock2.add(reiP);

        when(tabuleiroMock.getPecas(Cor.BRANCO)).thenReturn(vetorMock);
        when(tabuleiroMock.getPecas(Cor.PRETO)).thenReturn(vetorMock2);

        boolean[] vetor = maquinaDeRegras.chegouFimDeJogo();

        assertEquals(true, vetor[1]);
        assertEquals(false, vetor[0]);
    }

}
