package config;

import org.junit.Before;
import org.junit.Test;

import pecas.Peca;
import utils.Cor;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

public class SetupPecasTest {
    ArrayList<Peca> pecasBrancas;
    ArrayList<Peca> pecasPretas;

    @Before
    public void setUpTest() {
        Config.LOAD_FAKE_IMAGES = true;
        Config.loadImages();
    }

    @Test
    public void setupPecas() {
        pecasBrancas = SetupPecas.setup(Cor.BRANCO);
        pecasPretas = SetupPecas.setup(Cor.PRETO);
        assertEquals(16, pecasBrancas.size());
        assertEquals(16, pecasPretas.size());
    }
}
