package maquinaDeRegras;

import java.util.ArrayList;

import pecas.Peca;
import utils.Posicao;

public class Historico {
    private ArrayList<Movimento> movimentos;
    private int ultimoMovimento;
    Historico(){
        this.movimentos = new ArrayList<Movimento>();
        this.ultimoMovimento = -1;
    }
    void adicionaMovimento(Peca peca, Posicao posicaoAnterior, Posicao posicaoPosterior){
        this.movimentos.add(new Movimento(peca,posicaoAnterior,posicaoPosterior));
        this.ultimoMovimento++;
    }
    Movimento getUltimoMovimento(){
        try {
            Movimento ultimoMovimento = this.movimentos.get(this.ultimoMovimento);
            return ultimoMovimento;
        } catch(Error error){
            System.out.println("Não existe movimento " + Integer.toString(this.ultimoMovimento));
            System.out.println(error.toString());
            return null;
        }
    }
    Movimento reverteMovimento(){
        try {
            Movimento ultimoMovimento = this.movimentos.remove(this.ultimoMovimento);
            this.ultimoMovimento--;
            return ultimoMovimento;
        } catch(Error error){
            System.out.println("Impossivel voltar na jogada");
            System.out.println(error.toString());
            return null;
        }
    }

}
