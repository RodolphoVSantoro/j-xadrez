package events;

public class Listener {

    private TipoEvento tipoEvento;
    private Runnable runnable;

    public Listener(TipoEvento tipoEvento, Runnable runnable) {
        this.tipoEvento = tipoEvento;
        this.runnable = runnable;
    }

    public void Catch() {
        new Thread(() -> {
            runnable.run();
        }).start();
    }

    public TipoEvento getTipoEvento() {
        return this.tipoEvento;
    }

    public void setEventId(TipoEvento TipoEvento) {
        this.tipoEvento = TipoEvento;
    }
}
