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

    /**
     * Este método sobrescrito é chamado sempre que o mouse é clicado. Ele verifica se uma ação está sendo 
     * executada e se é a vez do jogador de cor branca (player). Se a condição for satisfeita, 
     * ele seleciona a peça na posição onde o mouse foi clicado, caso exista e ela não esteja capturada.
     *
     * @param e O evento de mouse que aciona este método. 
     * Contém informações sobre o estado do mouse durante o evento, como a posição do ponteiro do mouse.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
       
        if(!this.executando && (this.maquinaDeRegras.getTurno() == Cor.BRANCO || true)){
            // Seleciona posição clicada pelo mouse
            int col = (e.getX() / Config.LARGURA_TABULEIRO) - 1;
            int linha = (e.getY() / Config.ALTURA_TABULEIRO) - 1;
            Posicao p = new Posicao(col, linha);
            Peca posicaoPeca = this.maquinaDeRegras.getTabuleiro().getPeca(p);
            // Se houver uma peça onde o mouse clicou, seleciona a peça
            if(posicaoPeca != null &&(!posicaoPeca.getCapturado())){
                this.selecionada = posicaoPeca;
            }
        }
    }

    /**
     * Método sobrescrito 'mouseReleased' é chamado sempre que o botão do mouse é liberado.
     * Se houver uma peça selecionada, este método redefine sua posição para o local onde o usuário liberou o clique do mouse.
     * Este método também verifica se é necessário realizar a promoção de um peão, executa o movimento no tabuleiro e altera
     * o turno do jogo para o jogador de cor preta. Se o movimento leva ao fim do jogo, ele registra essa informação.
     * No final, desseleciona a peça e solicita a atualização da interface do usuário.
     *
     * @param evt O evento de mouse que aciona este método.
     */
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
                    //Promocao telaPromocao = new Promocao();
                    // telaPromocao.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    // telaPromocao.setLocationRelativeTo(null);
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
