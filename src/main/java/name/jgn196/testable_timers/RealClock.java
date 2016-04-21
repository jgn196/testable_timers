package name.jgn196.testable_timers;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class RealClock extends Clock {

    private final long period;
    private final TimeUnit periodTimeUnit;

    private final ScheduledExecutorService timerService = Executors.newSingleThreadScheduledExecutor();

    public RealClock(final long period, final TimeUnit timeUnit) {
        this.period = period;
        this.periodTimeUnit = timeUnit;
    }

    @Override
    public void start() {
        timerService.scheduleAtFixedRate(this::tick, period, period, periodTimeUnit);
    }

    @Override
    public void stop() {
        timerService.shutdown();
    }
}
