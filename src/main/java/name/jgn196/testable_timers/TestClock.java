package name.jgn196.testable_timers;

class TestClock extends ClockBase {

    @Override
    public void start() {
        // Ignored
    }

    @Override
    public void stop() {
        // Ignored
    }

    public void triggerTimer() {
        reportTimeElapsed();
    }
}
