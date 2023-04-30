package events;

import java.util.ArrayList;

public abstract class GerenciadorEventos {
    static private ArrayList<Listener> listeners = new ArrayList<Listener>();

    public static Thrower createThrower(TipoEvento tipoEvento) {
        return new Thrower(tipoEvento, listeners);
    }

    public static Thrower createThrower(TipoEvento tipoEvento, Runnable callback) {
        return new Thrower(tipoEvento, listeners, callback);
    }

    public static Listener createListener(TipoEvento tipoEvento, Runnable callback) {
        Listener listener = new Listener(tipoEvento, callback);
        listeners.add(listener);
        return listener;
    }
}
