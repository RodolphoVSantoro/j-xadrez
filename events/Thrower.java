package events;

import java.util.ArrayList;

public class Thrower {
    private ArrayList<Listener> listeners = new ArrayList<Listener>();
    private TipoEvento eventKind;
    private Runnable preThrow;

    public Thrower(TipoEvento tipoEvento, ArrayList<Listener> listeners) {
        this.eventKind = tipoEvento;
        this.listeners = listeners;
        this.preThrow = null;
    }

    public Thrower(TipoEvento tipoEvento, ArrayList<Listener> listeners, Runnable callback) {
        this.eventKind = tipoEvento;
        this.listeners = listeners;
        this.preThrow = callback;
    }

    public void addThrowListener(Listener listener) {
        listeners.add(listener);
    }

    public void Throw() {
        if (preThrow != null) {
            preThrow.run();
        }
        new Thread(() -> {
            for (Listener listener : listeners) {
                if (listener.getTipoEvento().equals(this.eventKind)) {
                    listener.Catch();
                }
            }
        }).start();
    }
}
