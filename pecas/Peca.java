package pecas;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import config.Config;
import gui.Sprite;
import maquinaDeRegras.Historico;
import maquinaDeRegras.Tabuleiro;
import utils.Cor;
import utils.Posicao;

public abstract class Peca implements Cloneable{
    private Cor cor;
    public TipoPeca tipoPeca;
    public TipoPeca tipoPromocao;
    protected Posicao posicaoTabuleiro;
    public boolean capturada;
    protected Tabuleiro tabuleiro;
    protected Historico historico;
    protected Sprite sprite;

    public int qtdMovimento = 0;
    public int promocao=0;
    public int dir;

    public Peca(Posicao posicaoTabuleiro, Cor cor, TipoPeca tipoPeca, TipoPeca tipoPromocao) {
        this.capturada = false;
        this.tabuleiro = null;

        this.tipoPeca = tipoPeca;
        this.tipoPromocao = tipoPromocao;
        this.cor = cor;
        Image image = Config.IMAGENS_PECAS.get(this.cor).get(this.tipoPeca);

        this.sprite = new Sprite(image, posicaoTabuleiro);
        this.setPosicaoTabuleiro(posicaoTabuleiro,false);
    }

    public void setTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public void setHistorico(Historico historico) {
        this.historico = historico;
    }

    public void setPosicaoTabuleiro(Posicao posicaoTabuleiro,boolean ehIA) {
        this.posicaoTabuleiro = new Posicao(posicaoTabuleiro.x, posicaoTabuleiro.y);
        int spriteX = posicaoTabuleiro.x * Config.LARGURA_PECA;
        int spriteY = posicaoTabuleiro.y * Config.ALTURA_PECA;
        if(!ehIA)this.sprite.move(spriteX, spriteY);
    }

    public Posicao getPosicaoTabuleiro() {
        return new Posicao(this.posicaoTabuleiro.x, this.posicaoTabuleiro.y);
    }

    public boolean getCapturado() {
        return this.capturada;
    }

    public void captura(boolean ehIA) {
        this.capturada = true;
        if(!ehIA)this.sprite.move(-800, -800); // todo: melhorar quando exibir historico
    }

    public void recupera(boolean ehIA) {
        this.capturada = false;
        if(!ehIA)this.sprite.move(this.posicaoTabuleiro.x * Config.LARGURA_PECA, this.posicaoTabuleiro.y * Config.ALTURA_PECA);
    }

    /*
     * Diz se a peca em determinada posiçao é capturável
     * Sem considerar peças entre ambas
     */
    public boolean podeCapturar(Posicao posicaoNova) {
        Peca peca = this.tabuleiro.getPeca(posicaoNova.x, posicaoNova.y);
        return peca != null && peca.getCor() != this.getCor();
    }

    /*
     * Move caso a casa for um movimento possível
     */
    public boolean podeMover(Posicao posicao) {
        // TODO: Salvar e só computar uma vez por jogada se ficar pesado
        ArrayList<Posicao> posicoesValidas = this.getMovimentosPossiveis(false);
        for (Posicao posicaoNova : posicoesValidas) {
            if (posicaoNova.x == posicao.x && posicaoNova.y == posicao.y) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna todos os movimentos possiveis de uma peça
     */
    public abstract ArrayList<Posicao> getMovimentosPossiveis(boolean pulaTeste);

    /**
     * Método para verificar a validade dos movimentos possíveis de uma peça. O método analisa todas
     * as peças de ambas as cores, verificando se um movimento potencial resultaria em uma situação 
     * onde o rei da mesma cor que a peça está em cheque. Se isso ocorrer, o movimento é removido 
     * da lista de movimentos possíveis.
     *
     * @param movimentosPossiveis Uma lista de posições possíveis que a peça pode ocupar.
     * @return Uma lista atualizada de movimentos possíveis, excluindo aqueles que colocariam o rei 
     * da mesma cor que a peça em cheque.
     *
     * @throws CloneNotSupportedException Se o clone de qualquer peça falhar.
     */
    protected ArrayList<Posicao> checaValidadeMovimento(ArrayList<Posicao> movimentosPossiveis){
        
        ArrayList<Peca> pecaBranca = new ArrayList<Peca>();
        for(Peca peca : this.tabuleiro.getPecas(Cor.BRANCO)){
            try {
                if(peca.getTipoPeca()==TipoPeca.PEAO)pecaBranca.add((Peao) peca.clone());
                if(peca.getTipoPeca()==TipoPeca.CAVALO)pecaBranca.add((Cavalo) peca.clone());
                if(peca.getTipoPeca()==TipoPeca.BISPO)pecaBranca.add((Bispo) peca.clone());
                if(peca.getTipoPeca()==TipoPeca.TORRE)pecaBranca.add((Torre) peca.clone());
                if(peca.getTipoPeca()==TipoPeca.DAMA)pecaBranca.add((Dama) peca.clone());
                if(peca.getTipoPeca()==TipoPeca.REI)pecaBranca.add((Rei) peca.clone());
            } catch (CloneNotSupportedException e) {
                
                e.printStackTrace();
            };
        }
        ArrayList<Peca> pecaPreto = new ArrayList<Peca>();
        for(Peca peca : this.tabuleiro.getPecas(Cor.PRETO)){
            try {
                if(peca.getTipoPeca()==TipoPeca.PEAO)pecaPreto.add((Peao) peca.clone());
                if(peca.getTipoPeca()==TipoPeca.CAVALO)pecaPreto.add((Cavalo) peca.clone());
                if(peca.getTipoPeca()==TipoPeca.BISPO)pecaPreto.add((Bispo) peca.clone());
                if(peca.getTipoPeca()==TipoPeca.TORRE)pecaPreto.add((Torre) peca.clone());
                if(peca.getTipoPeca()==TipoPeca.DAMA)pecaPreto.add((Dama) peca.clone());
                if(peca.getTipoPeca()==TipoPeca.REI)pecaPreto.add((Rei) peca.clone());
            } catch (CloneNotSupportedException e) {
                
                e.printStackTrace();
            };
        }
        
        Tabuleiro tabu = new Tabuleiro(pecaBranca, pecaPreto);
        
        ArrayList<Posicao> tiraMovimento = new ArrayList<Posicao>();
        
        int xRei = tabu.getPecas(this.getCor()).stream().filter(p->p.getTipoPeca()==TipoPeca.REI).findFirst().get().getPosicaoTabuleiro().x;
        int yRei = tabu.getPecas(this.getCor()).stream().filter(p->p.getTipoPeca()==TipoPeca.REI).findFirst().get().getPosicaoTabuleiro().y;

        int xMeu =this.getPosicaoTabuleiro().x;
        int yMeu =this.getPosicaoTabuleiro().y;
    
        tabu.posicoesPecas[xMeu][yMeu]=null;
    
        for (Posicao movimento : movimentosPossiveis){
            Peca pecaCapturada=tabu.posicoesPecas[movimento.x][movimento.y];
            if(pecaCapturada==null && !movimento.duplo){
                tabu.posicoesPecas[movimento.x][movimento.y]=this;
                if(tabu.getPecas( this.getCor()==Cor.BRANCO?Cor.PRETO:Cor.BRANCO ).stream().anyMatch(p->p.tipoPromocao!=TipoPeca.PEAO && p.capturada==false && p.getMovimentosPossiveis(true).stream().anyMatch(m->m.x==xRei && m.y==yRei))) tiraMovimento.add(movimento);
                else if(tabu.getPecas( this.getCor()==Cor.BRANCO?Cor.PRETO:Cor.BRANCO ).stream().anyMatch(p->p.tipoPromocao==TipoPeca.PEAO && p.capturada==false && (p.getPosicaoTabuleiro().x+1==xRei|| p.getPosicaoTabuleiro().x-1==xRei)&& p.getPosicaoTabuleiro().y+p.dir==yRei)) tiraMovimento.add(movimento);     
                tabu.posicoesPecas[movimento.x][movimento.y]=null;
            }
            else if(!movimento.duplo){
                tabu.posicoesPecas[movimento.x][movimento.y]=this;
                if(tabu.getPecas( this.getCor()==Cor.BRANCO?Cor.PRETO:Cor.BRANCO ).stream().anyMatch(p-> p!=pecaCapturada && p.tipoPromocao!=TipoPeca.PEAO && p.capturada==false && p.getMovimentosPossiveis(true).stream().anyMatch(m->m.x==xRei && m.y==yRei))) tiraMovimento.add(movimento);
                else if(tabu.getPecas( this.getCor()==Cor.BRANCO?Cor.PRETO:Cor.BRANCO ).stream().anyMatch(p->p!=pecaCapturada && p.tipoPromocao==TipoPeca.PEAO && p.capturada==false && (p.getPosicaoTabuleiro().x+1==xRei|| p.getPosicaoTabuleiro().x-1==xRei)&& p.getPosicaoTabuleiro().y+p.dir==yRei)) tiraMovimento.add(movimento);
                tabu.posicoesPecas[movimento.x][movimento.y]=pecaCapturada;
            }
            if(movimento.duplo){
                Peca pecaCapturada2=tabu.posicoesPecas[movimento.x2][movimento.y2];
                if(pecaCapturada2==null){
                    tabu.posicoesPecas[movimento.x2][movimento.y2]=this;
                    if(tabu.getPecas( this.getCor()==Cor.BRANCO?Cor.PRETO:Cor.BRANCO ).stream().anyMatch(p->p.tipoPromocao!=TipoPeca.PEAO && p.capturada==false && p.getMovimentosPossiveis(true).stream().anyMatch(m->m.x==xRei && m.y==yRei))) tiraMovimento.add(movimento);
                    else if(tabu.getPecas( this.getCor()==Cor.BRANCO?Cor.PRETO:Cor.BRANCO ).stream().anyMatch(p->p.tipoPromocao==TipoPeca.PEAO && p.capturada==false && (p.getPosicaoTabuleiro().x+1==xRei|| p.getPosicaoTabuleiro().x-1==xRei)&& p.getPosicaoTabuleiro().y+p.dir==yRei)) tiraMovimento.add(movimento);     
                    tabu.posicoesPecas[movimento.x2][movimento.y2]=null;
                }
                else{
                    tabu.posicoesPecas[movimento.x2][movimento.y2]=this;
                    if(tabu.getPecas( this.getCor()==Cor.BRANCO?Cor.PRETO:Cor.BRANCO ).stream().anyMatch(p-> p!=pecaCapturada2 && p.tipoPromocao!=TipoPeca.PEAO && p.capturada==false && p.getMovimentosPossiveis(true).stream().anyMatch(m->m.x==xRei && m.y==yRei))) tiraMovimento.add(movimento);
                    else if(tabu.getPecas( this.getCor()==Cor.BRANCO?Cor.PRETO:Cor.BRANCO ).stream().anyMatch(p->p!=pecaCapturada2 && p.tipoPromocao==TipoPeca.PEAO && p.capturada==false && (p.getPosicaoTabuleiro().x+1==xRei|| p.getPosicaoTabuleiro().x-1==xRei)&& p.getPosicaoTabuleiro().y+p.dir==yRei)) tiraMovimento.add(movimento);
                    tabu.posicoesPecas[movimento.x2][movimento.y2]=pecaCapturada2;
                }
            }
        }
        for (Posicao movimento : tiraMovimento){
            movimentosPossiveis.remove(movimento);
        }

        tabu.posicoesPecas[xMeu][yMeu]=this;

        return movimentosPossiveis;
    }

    public Cor getCor() {
        return cor;
    }

    public TipoPeca getTipoPeca() {
        return tipoPeca;
    }

    public String stringify() {
        return this.tipoPeca.toString() + " " + this.cor.toString() + " " + this.posicaoTabuleiro.stringify();
    }

    public void desenha(Graphics graphics, ImageObserver observer) {
        this.sprite.desenha(graphics, observer);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
