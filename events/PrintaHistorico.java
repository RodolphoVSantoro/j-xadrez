package events;

import maquinaDeRegras.MaquinaDeRegras;
import pecas.Peca;

public class PrintaHistorico {
    
    private String nomeCasaPeca = "";
    private MaquinaDeRegras maquinaDeRegras;
    private Peca peca;
    
    public PrintaHistorico(MaquinaDeRegras maquinaDeRegras) {
        this.maquinaDeRegras = maquinaDeRegras;
        this.peca = this.maquinaDeRegras.getHistorico().getUltimoMovimento().getPeca();
    }

    private String nomeCasa(int casaPeca){
        switch (casaPeca) {
            case 0:
                nomeCasaPeca = "A";
                break;
            case 1:
                nomeCasaPeca = "B";
                break;
            case 2:
                nomeCasaPeca = "C";
                break;
            case 3:
                nomeCasaPeca = "D";
                break;
            case 4 :
                nomeCasaPeca = "E";
                break;
            case 5: 
                nomeCasaPeca = "F";
                break;
            case 6:
                nomeCasaPeca = "G";
                break;
            case 7 :
                nomeCasaPeca = "H";
                break;
            default:
                break;
        }
        return nomeCasaPeca;
    }

    public void print(){        
        System.out.println(
            peca.getTipoPeca().toString().substring(0, 1) + " -> " +
            this.nomeCasa(peca.getPosicaoTabuleiro().x) +
            peca.getPosicaoTabuleiro().y);
    }

}