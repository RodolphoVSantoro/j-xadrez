package tests.maquinaDeRegras;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import config.Config;
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

public class ReiTest {
    private Rei rei;
    private Rei reiP;
    private Tabuleiro tabuleiroMock;

    @Before
    public void setUp() {
        Config.LOAD_FAKE_IMAGES = true;
        Config.loadImages();

        this.rei = new Rei(new Posicao(4, 7), Cor.BRANCO);

        this.reiP = new Rei(new Posicao(4, 0), Cor.PRETO);

        tabuleiroMock = Mockito.mock(Tabuleiro.class);
        rei.setTabuleiro(tabuleiroMock);
        reiP.setTabuleiro(tabuleiroMock);
    }

    @Test
    public void testGetMovimentosPossiveis1() throws CloneNotSupportedException {

        // Mock de dados para testar o comportamento esperado
        Peca pecaMock1 = Mockito.mock(Peca.class);
        Peca pecaMock2 = Mockito.mock(Peca.class);
        Peca pecaMock3 = Mockito.mock(Peca.class);
        Peca pecaMock4 = Mockito.mock(Peca.class);

        when(tabuleiroMock.getPeca(0, 0)).thenReturn(pecaMock1);
        when(tabuleiroMock.getPeca(0, 7)).thenReturn(pecaMock2);
        when(tabuleiroMock.getPeca(7, 0)).thenReturn(pecaMock3);
        when(tabuleiroMock.getPeca(7, 7)).thenReturn(pecaMock4);

        when(tabuleiroMock.getPeca(1, 0)).thenReturn(null);
        when(tabuleiroMock.getPeca(1, 7)).thenReturn(null);
        when(tabuleiroMock.getPeca(2, 0)).thenReturn(null);
        when(tabuleiroMock.getPeca(2, 7)).thenReturn(null);
        when(tabuleiroMock.getPeca(3, 0)).thenReturn(null);
        when(tabuleiroMock.getPeca(3, 7)).thenReturn(null);
        when(tabuleiroMock.getPeca(5, 0)).thenReturn(null);
        when(tabuleiroMock.getPeca(5, 7)).thenReturn(null);
        when(tabuleiroMock.getPeca(6, 0)).thenReturn(null);
        when(tabuleiroMock.getPeca(6, 7)).thenReturn(null);

        when(tabuleiroMock.getPeca(new Posicao(5, 7))).thenReturn(null);
        when(tabuleiroMock.getPeca(new Posicao(3, 7))).thenReturn(null);
        when(tabuleiroMock.getPeca(new Posicao(3, 6))).thenReturn(null);
        when(tabuleiroMock.getPeca(new Posicao(4, 6))).thenReturn(null);
        when(tabuleiroMock.getPeca(new Posicao(5, 6))).thenReturn(null);
        when(tabuleiroMock.getPeca(new Posicao(5, 0))).thenReturn(null);
        when(tabuleiroMock.getPeca(new Posicao(3, 0))).thenReturn(null);
        when(tabuleiroMock.getPeca(new Posicao(3, 1))).thenReturn(null);
        when(tabuleiroMock.getPeca(new Posicao(4, 1))).thenReturn(null);
        when(tabuleiroMock.getPeca(new Posicao(5, 6))).thenReturn(null);

        when(pecaMock1.getCor()).thenReturn(Cor.PRETO);
        when(pecaMock2.getCor()).thenReturn(Cor.BRANCO);
        when(pecaMock3.getCor()).thenReturn(Cor.PRETO);
        when(pecaMock4.getCor()).thenReturn(Cor.BRANCO);

        Torre torre = new Torre(new Posicao(5, 0), Cor.PRETO);
        Dama dama = new Dama(new Posicao(4, 1), Cor.PRETO);
        Peao peao = new Peao(new Posicao(4, 6), Cor.PRETO);
        Peao peao2 = new Peao(new Posicao(3, 5), Cor.PRETO);
        Bispo bispo = new Bispo(new Posicao(7, 1), Cor.PRETO);
        Cavalo cavalo = new Cavalo(new Posicao(1, 1), Cor.PRETO);

        ArrayList<Peca> vetorMock = new ArrayList<Peca>();
        ArrayList<Peca> vetorMock2 = new ArrayList<Peca>();
        vetorMock.add(torre);
        vetorMock.add(dama);
        vetorMock.add(cavalo);
        vetorMock.add(bispo);
        vetorMock.add(peao);
        vetorMock.add(reiP);
        vetorMock.add(peao2);

        when(tabuleiroMock.getPecas(Cor.PRETO)).thenReturn(vetorMock);
        when(tabuleiroMock.getPecas(Cor.BRANCO)).thenReturn(vetorMock2);

        ArrayList<Posicao> movimentosPossiveis = rei.getMovimentosPossiveis(false);

        // Verificar se a lista de movimentos possíveis está correta
        assertEquals(3, movimentosPossiveis.size());

    }

    @Test
    public void testGetMovimentosPossiveis2() throws CloneNotSupportedException {

        // Mock de dados para testar o comportamento esperado
        Peca pecaMock1 = Mockito.mock(Peca.class);
        Peca pecaMock2 = Mockito.mock(Peca.class);
        Peca pecaMock3 = Mockito.mock(Peca.class);
        Peca pecaMock4 = Mockito.mock(Peca.class);

        when(tabuleiroMock.getPeca(0, 0)).thenReturn(pecaMock1);
        when(tabuleiroMock.getPeca(0, 7)).thenReturn(pecaMock2);
        when(tabuleiroMock.getPeca(7, 0)).thenReturn(pecaMock3);
        when(tabuleiroMock.getPeca(7, 7)).thenReturn(pecaMock4);

        when(tabuleiroMock.getPeca(1, 0)).thenReturn(null);
        when(tabuleiroMock.getPeca(1, 7)).thenReturn(null);
        when(tabuleiroMock.getPeca(2, 0)).thenReturn(null);
        when(tabuleiroMock.getPeca(2, 7)).thenReturn(null);
        when(tabuleiroMock.getPeca(3, 0)).thenReturn(null);
        when(tabuleiroMock.getPeca(3, 7)).thenReturn(null);
        when(tabuleiroMock.getPeca(5, 0)).thenReturn(null);
        when(tabuleiroMock.getPeca(5, 7)).thenReturn(null);
        when(tabuleiroMock.getPeca(6, 0)).thenReturn(null);
        when(tabuleiroMock.getPeca(6, 7)).thenReturn(null);

        when(tabuleiroMock.getPeca(new Posicao(5, 7))).thenReturn(null);
        when(tabuleiroMock.getPeca(new Posicao(3, 7))).thenReturn(null);
        when(tabuleiroMock.getPeca(new Posicao(3, 6))).thenReturn(null);
        when(tabuleiroMock.getPeca(new Posicao(4, 6))).thenReturn(null);
        when(tabuleiroMock.getPeca(new Posicao(5, 6))).thenReturn(null);
        when(tabuleiroMock.getPeca(new Posicao(5, 0))).thenReturn(null);
        when(tabuleiroMock.getPeca(new Posicao(3, 0))).thenReturn(null);
        when(tabuleiroMock.getPeca(new Posicao(3, 1))).thenReturn(null);
        when(tabuleiroMock.getPeca(new Posicao(4, 1))).thenReturn(null);
        when(tabuleiroMock.getPeca(new Posicao(5, 6))).thenReturn(null);

        when(pecaMock1.getCor()).thenReturn(Cor.PRETO);
        when(pecaMock2.getCor()).thenReturn(Cor.BRANCO);
        when(pecaMock3.getCor()).thenReturn(Cor.PRETO);
        when(pecaMock4.getCor()).thenReturn(Cor.BRANCO);

        Torre torre = new Torre(new Posicao(5, 7), Cor.BRANCO);
        Dama dama = new Dama(new Posicao(4, 7), Cor.BRANCO);
        Peao peao = new Peao(new Posicao(4, 1), Cor.BRANCO);
        Peao peao2 = new Peao(new Posicao(3, 5), Cor.BRANCO);
        Bispo bispo = new Bispo(new Posicao(7, 6), Cor.BRANCO);
        Cavalo cavalo = new Cavalo(new Posicao(1, 6), Cor.BRANCO);

        ArrayList<Peca> vetorMock = new ArrayList<Peca>();
        ArrayList<Peca> vetorMock2 = new ArrayList<Peca>();
        vetorMock.add(torre);
        vetorMock.add(dama);
        vetorMock.add(cavalo);
        vetorMock.add(bispo);
        vetorMock.add(peao);
        vetorMock.add(rei);
        vetorMock.add(peao2);

        when(tabuleiroMock.getPecas(Cor.PRETO)).thenReturn(vetorMock2);
        when(tabuleiroMock.getPecas(Cor.BRANCO)).thenReturn(vetorMock);

        ArrayList<Posicao> movimentosPossiveis = reiP.getMovimentosPossiveis(false);

        // Verificar se a lista de movimentos possíveis está correta
        assertEquals(3, movimentosPossiveis.size());

    }
}
