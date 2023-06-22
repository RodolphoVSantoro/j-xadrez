package tests.maquinaDeRegras;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import config.Config;
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

public class MaquinaDeRegrasIntegracaoTest {
    private MaquinaDeRegras maquinaDeRegras;
    private Tabuleiro tabuleiro;
    private Rei rei;
    private Rei reiP;

    @Before
    public void setUp() {

        Config.LOAD_FAKE_IMAGES = true;
        Config.loadImages();
        // Configurar objetos mock
        this.rei = new Rei(new Posicao(4, 7), Cor.BRANCO);

        this.reiP = new Rei(new Posicao(4, 0), Cor.PRETO);
        // Criar instância da classe a ser testada
        maquinaDeRegras = new MaquinaDeRegras(Cor.BRANCO, 3);

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

        ArrayList<Peca> pecasPretas = new ArrayList<Peca>();
        ArrayList<Peca> pecasBrancas = new ArrayList<Peca>();

        pecasPretas.add(torre);
        pecasPretas.add(dama);
        pecasPretas.add(cavalo);
        pecasPretas.add(bispo);
        pecasPretas.add(peao);
        pecasPretas.add(reiP);

        pecasBrancas.add(rei);

        this.tabuleiro = new Tabuleiro(pecasBrancas, pecasPretas);
        maquinaDeRegras.setTabuleiro(tabuleiro);

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

        ArrayList<Peca> pecasPretas = new ArrayList<Peca>();
        ArrayList<Peca> pecasBrancas = new ArrayList<Peca>();

        pecasBrancas.add(torre);
        pecasBrancas.add(dama);
        pecasBrancas.add(cavalo);
        pecasBrancas.add(bispo);
        pecasBrancas.add(peao);
        pecasBrancas.add(rei);

        pecasPretas.add(reiP);

        this.tabuleiro = new Tabuleiro(pecasBrancas, pecasPretas);
        maquinaDeRegras.setTabuleiro(tabuleiro);

        boolean[] vetor = maquinaDeRegras.chegouFimDeJogo();

        assertEquals(true, vetor[1]);
        assertEquals(false, vetor[0]);
    }
}
