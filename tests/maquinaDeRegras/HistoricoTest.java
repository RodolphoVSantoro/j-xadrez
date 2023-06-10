package tests.maquinaDeRegras;

import maquinaDeRegras.Historico;
import maquinaDeRegras.MaquinaDeRegras;
import maquinaDeRegras.Movimento;
import maquinaDeRegras.Tabuleiro;
import pecas.Peao;
import pecas.Peca;
import pecas.TipoPeca;
import utils.Cor;
import utils.Posicao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import org.junit.Test;

import config.Config;
import config.SetupPecas;

public class HistoricoTest {

    private Historico historico;

    @Test
    public void testAdicionaMovimento() {

        Config.LOAD_FAKE_IMAGES = true;
        Config.loadImages();
        ArrayList<Peca> pecasBrancas = SetupPecas.setup(Cor.BRANCO);
        ArrayList<Peca> pecasPretas = SetupPecas.setup(Cor.PRETO);
        this.historico = new Historico(pecasBrancas, pecasPretas);

        Movimento movimento = new Movimento(
                pecasBrancas.stream().filter(p -> p.tipoPeca == TipoPeca.PEAO).findFirst().get(), new Posicao(1, 4),
                new Posicao(1, 5), null);

        // Criação do histórico e das peças
        // Historico historico = new Historico(new ArrayList<>(), new ArrayList<>());
        // Peca peca = new Peca(); // Substitua pela classe real da peça, se existir
        // Peca pecaCapturada = new Peca(); // Substitua pela classe real da peça
        // capturada, se existir

        // Adiciona o movimento ao histórico
        this.historico.adicionaMovimento(movimento, null);

        // Verifica se o último movimento é igual ao movimento adicionado
        Movimento ultimoMovimento = this.historico.getUltimoMovimento();

        assertEquals(movimento.getPosicaoAnterior(), ultimoMovimento.getPosicaoAnterior());
        assertEquals(movimento.getPosicaoPosterior(), ultimoMovimento.getPosicaoPosterior());
        assertEquals(movimento.getPeca(), ultimoMovimento.getPeca());
        assertEquals(movimento.getPecaCapturada(), ultimoMovimento.getPecaCapturada());
    }

    @Test
    public void testReverteMovimento() {

        Config.LOAD_FAKE_IMAGES = true;
        Config.loadImages();
        ArrayList<Peca> pecasBrancas = SetupPecas.setup(Cor.BRANCO);
        ArrayList<Peca> pecasPretas = SetupPecas.setup(Cor.PRETO);
        Tabuleiro tabuleiro = new Tabuleiro(pecasBrancas, pecasPretas);
        MaquinaDeRegras maquina = new MaquinaDeRegras(Cor.BRANCO, 3);
        maquina.setTabuleiro(tabuleiro);

        Peca peaoTeste = pecasBrancas.stream().filter(p -> p.tipoPeca == TipoPeca.PEAO).findFirst().get();

        Movimento movimento = new Movimento(
                peaoTeste, peaoTeste.getPosicaoTabuleiro(),
                peaoTeste.getMovimentosPossiveis(false).stream().findFirst().get(), null);

        Posicao posAnterior = peaoTeste.getPosicaoTabuleiro();

        maquina.executaMovimento(movimento, true);

        Posicao posExecutada = peaoTeste.getPosicaoTabuleiro();

        maquina.desfazUltimoMovimento(true);

        Posicao posPosterior = peaoTeste.getPosicaoTabuleiro();

        assertEquals(posAnterior.x, posPosterior.x);
        assertEquals(posAnterior.y, posPosterior.y);
        assertNotEquals(posAnterior.y, posExecutada.y);

    }

}
