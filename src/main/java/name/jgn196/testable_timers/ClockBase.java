package name.jgn196.testable_timers;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

abstract class ClockBase implements Clock {

    private final Set<Clock.Listener> listeners = Collections.synchronizedSet(new HashSet<>());

    @Override
    public void register(final Clock.Listener listener) {
        listeners.add(listener);
    }

    protected void reportTimeElapsed() {
        listeners.forEach(Listener::timeElapsed);
    }
}
