package events;

import javax.swing.JTextArea;

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

    public void print(JTextArea textArea, String espaco){        
        textArea.append(espaco);
    }

    public void print(JTextArea textArea){        
        textArea.append( 
            peca.getTipoPeca().toString().substring(0, 1) + " -> " +
            this.nomeCasa(peca.getPosicaoTabuleiro().x) +
            (8 - peca.getPosicaoTabuleiro().y));
    }

}
