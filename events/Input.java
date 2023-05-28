package events;

import java.awt.Canvas;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Optional;

import config.Config;
import maquinaDeRegras.MaquinaDeRegras;
import maquinaDeRegras.Movimento;
import menu.Promocao;
import pecas.Peca;
import pecas.TipoPeca;
import utils.ArmazemInt;
import utils.Cor;
import utils.Posicao;

public class Input extends MouseAdapter{

    MaquinaDeRegras maquinaDeRegras;
    public Peca selecionada;
    public Canvas canvas;
    public boolean executando=false;
    private Promocao promocaoDialog;
    private ArmazemInt promocaoGetter;

    public Input(MaquinaDeRegras maquinaDeRegras, Canvas canvas){
        super();
        this.maquinaDeRegras = maquinaDeRegras;
        this.canvas = canvas;
        this.promocaoGetter = ArmazemInt.getInstance(); 
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       
        if(!this.executando && (this.maquinaDeRegras.getTurno() == Cor.BRANCO)){
            // Seleciona posição clicada pelo mouse
            int col = (e.getX() / Config.LARGURA_TABULEIRO) - 1;
            int linha = (e.getY() / Config.ALTURA_TABULEIRO) - 1;
            Posicao p = new Posicao(col, linha);
            Peca posicaoPeca = this.maquinaDeRegras.getTabuleiro().getPeca(p);
            // Se houver uma peça onde o mouse clicou, seleciona a peça
            if(posicaoPeca != null  && posicaoPeca.getCor()==Cor.BRANCO &&(!posicaoPeca.getCapturado())){
                //System.out.println("vc selecinou uma peça valida");
                this.selecionada = posicaoPeca;
            }
        }

        // if(maquinaDeRegras.getTurno() == Cor.PRETO ){
        //     // Seleciona posição clicada pelo mouse
        //     int col = (e.getX() / Config.LARGURA_TABULEIRO) - 1;
        //     int linha = (e.getY() / Config.ALTURA_TABULEIRO) - 1;
        //     Posicao p = new Posicao(col, linha);
        //     Peca posicaoPeca = maquinaDeRegras.getTabuleiro().getPeca(p);
        //     // Se houver uma peça onde o mouse clicou, seleciona a peça
        //     if(posicaoPeca != null && posicaoPeca.getCor()==Cor.PRETO && (!posicaoPeca.getCapturado())){
        //         //System.out.println("vc selecinou uma peça valida");
        //         this.selecionada = posicaoPeca;
        //     }
        // }
    }

    @Override
    public void mouseReleased(MouseEvent evt) {
        
        int col = (evt.getX() / Config.LARGURA_TABULEIRO) - 1;
        int linha = (evt.getY() / Config.ALTURA_TABULEIRO) - 1;

        // Se houver uma peça selecionada, define a posição da peça como a posiçao onde o jogador soltou o clique do mouse
        if(this.selecionada != null){
            this.executando=true;
            Posicao selecionadaTabuleiro=this.selecionada.getPosicaoTabuleiro();
            Optional<Posicao> pecaOptinonal = this.selecionada.getMovimentosPossiveis(false).stream().filter(p -> (p.x==col && p.y==linha)||((selecionadaTabuleiro.x==p.xp && selecionadaTabuleiro.y==p.yp )&&(p.x2==col && p.y2==linha))).findFirst();
            if(pecaOptinonal.isPresent()){
                if(this.selecionada.tipoPromocao == TipoPeca.PEAO && this.selecionada.qtdMovimento == 5) {
                    this.promocaoDialog.setVisible(true);
                    this.selecionada.promocao = this.promocaoGetter.getValue();
                }
                Posicao peca = pecaOptinonal.get();
                if(!peca.duplo){
                    Movimento movimento = new Movimento(this.selecionada, selecionadaTabuleiro,new Posicao(peca.x,peca.y), 0);
                    this.maquinaDeRegras.executaMovimento(movimento,false);
                }else{
                    Peca selecionada2 = this.maquinaDeRegras.getTabuleiro().getPeca(new Posicao(peca.xp, peca.yp));
                    Movimento movimento = new Movimento(this.selecionada, selecionadaTabuleiro,new Posicao(peca.x,peca.y),selecionada2,selecionada2.getPosicaoTabuleiro(),new Posicao(peca.x2, peca.y2) ,0);
                    this.maquinaDeRegras.executaMovimento(movimento,false);
                }
                this.maquinaDeRegras.setTurno(Cor.PRETO);
                boolean[] temp=this.maquinaDeRegras.chegouFimDeJogo();
                this.maquinaDeRegras.checkmate=temp[0]||temp[1];
            }
            this.executando=false;
        }

        // Desseleciona a peça
        this.selecionada = null;
        canvas.repaint();
    }

    public void setPromocaoDialog(Promocao p){
        this.promocaoDialog = p;
    }
    
}
