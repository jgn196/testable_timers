package name.jgn196.testable_timers;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class RealClock extends Clock {

    private final long tickPeriod;
    private final TimeUnit periodTimeUnit;

    private final ScheduledExecutorService timerService = Executors.newSingleThreadScheduledExecutor();

    public RealClock(final long tickPeriod, final TimeUnit periodTimeUnit) {
        if(tickPeriod <= 0) throw new IllegalArgumentException("Illegal tick period: " + tickPeriod);
        if(periodTimeUnit == null) throw new IllegalArgumentException("Period time unit is null");

        this.tickPeriod = tickPeriod;
        this.periodTimeUnit = periodTimeUnit;
    }

    @Override
    public void start() {
        timerService.scheduleAtFixedRate(this::tick, tickPeriod, tickPeriod, periodTimeUnit);
    }

    @Override
    public void stop() {
        timerService.shutdown();
    }
}
