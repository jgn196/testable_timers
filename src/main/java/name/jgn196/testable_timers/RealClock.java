package name.jgn196.testable_timers;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class RealClock implements Clock {

    private final long period;
    private final TimeUnit periodTimeUnit;
    private final Set<Listener> listeners = Collections.synchronizedSet(new HashSet<>());
    private final ScheduledExecutorService timerService = Executors.newSingleThreadScheduledExecutor();

    public RealClock(final long period, final TimeUnit timeUnit) {
        this.period = period;
        this.periodTimeUnit = timeUnit;
    }

    @Override
    public void register(final Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void start() {
        timerService.scheduleAtFixedRate(this::reportTimeElapsed, period, period, periodTimeUnit);
    }

    private void reportTimeElapsed() {
        listeners.forEach(listener -> listener.timeElapsed());
    }

    @Override
    public void stop() {
        timerService.shutdown();
    }
}
