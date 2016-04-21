package name.jgn196.testable_timers;

class TestClock extends Clock {

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
