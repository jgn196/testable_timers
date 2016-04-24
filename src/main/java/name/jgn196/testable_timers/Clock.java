package name.jgn196.testable_timers;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class Clock {

    private final Set<Listener> listeners = Collections.synchronizedSet(new HashSet<>());

    public void register(final Clock.Listener listener) {
        if (listener == null) return;

        listeners.add(listener);
    }

    public void unregister(final Listener listener) {
        listeners.remove(listener);
    }

    protected void tick() {
        listeners.forEach(this::safeTick);
    }

    private void safeTick(final Listener listener) {
        try {
            listener.tick();
        } catch (final RuntimeException error) {
            // Do nothing
        }
    }

    public abstract void start();

    public abstract void stop();

    public interface Listener {
        void tick();
    }
}
