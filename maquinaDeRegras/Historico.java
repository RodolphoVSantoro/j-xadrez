package maquinaDeRegras;

import java.util.ArrayList;

import pecas.Peca;
import utils.Posicao;

public class Historico {
    private ArrayList<Movimento> movimentos;
    private int ultimoMovimento;

    Historico(ArrayList<Peca> pecasBrancas, ArrayList<Peca> pecasPretas) {
        this.movimentos = new ArrayList<Movimento>();
        pecasBrancas.forEach(p -> p.setHistorico(this));
        pecasPretas.forEach(p -> p.setHistorico(this));
        this.ultimoMovimento = -1;
    }

    public void adicionaMovimento(Movimento movimento, Peca pecaCapturada) {
        Peca peca = movimento.getPeca();
        Posicao posicaoAnterior = movimento.getPosicaoAnterior();
        Posicao posicaoPosterior = movimento.getPosicaoPosterior();
        Movimento novoMovimento;
        if(movimento.movimentoDuplo){
            novoMovimento = new Movimento(peca, posicaoAnterior, posicaoPosterior, movimento.getPeca2(), movimento.getPosicaoAnterior2(), movimento.getPosicaoPosterior2(), pecaCapturada);
        }else{
            novoMovimento = new Movimento(peca, posicaoAnterior, posicaoPosterior, pecaCapturada);

        }
        this.movimentos.add(novoMovimento);
        this.ultimoMovimento++;
    }

    public Movimento getUltimoMovimento() {
        try {
            if(this.ultimoMovimento==-1) return null;
            Movimento ultimoMovimento = this.movimentos.get(this.ultimoMovimento);
            return ultimoMovimento;
        } catch (Error error) {
            throw new Error("Não ha ultimo movimento");
        }
    }

    public Movimento reverteMovimento() {
        try {
            Movimento ultimoMovimento = this.movimentos.remove(this.ultimoMovimento);
            this.ultimoMovimento--;
            return ultimoMovimento;
        } catch (Error error) {
            throw new Error("Impossivel voltar na jogada");
        }
    }

    public String stringify() {
        String stringified = "Historico de movimentos:\n";
        for (Movimento movimento : this.movimentos) {
            stringified += movimento.stringify();
        }
        return stringified;
    }
}
