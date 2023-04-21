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

}
