import events.GerenciadorEventos;
import events.TipoEvento;
import events.Thrower;

class EventsExample {
    static int x = 0, y = 1;

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
            System.out.println("O usuario clicou no tabuleiro!!" + x + " " + y);
        });
        clickSquareThrower.Throw();
        x = 1;
        clickSquareThrower.Throw();
    }
}
