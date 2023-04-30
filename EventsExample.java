import events.GerenciadorEventos;
import events.TipoEvento;
import events.Thrower;

class EventsExample {
    public static void main(String[] args) {
        Thrower movePieceThrower = GerenciadorEventos.createThrower(TipoEvento.MOVE_PECA);

        GerenciadorEventos.createListener(TipoEvento.MOVE_PECA, () -> {
            System.out.println("Jogo moveu peca!!");
        });
        GerenciadorEventos.createListener(TipoEvento.CLICK_TABULEIRO, () -> {
            System.out.println("Tabuleiro recebeu click!!");
        });
        GerenciadorEventos.createListener(TipoEvento.MOVE_PECA, () -> {
            System.out.println("Jogo moveu peca 2!!");
        });

        movePieceThrower.Throw();

        Thrower clickSquareThrower = GerenciadorEventos.createThrower(TipoEvento.CLICK_TABULEIRO, () -> {
            System.out.println("O usuario clicou no tabuleiro!!");
        });
        clickSquareThrower.Throw();
    }
}
