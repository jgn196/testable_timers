package name.jgn196.testable_timers;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class Clock {

    private final Set<Listener> listeners = Collections.synchronizedSet(new HashSet<>());

    public void register(final Clock.Listener listener) {
        listeners.add(listener);
    }

    public void unregister(final Listener listener) {
        listeners.remove(listener);
    }

    protected void reportTimeElapsed() {
        listeners.forEach(Listener::tick);
    }

    public abstract void start();

    public abstract void stop();

    public interface Listener {
        void tick();
    }
}
