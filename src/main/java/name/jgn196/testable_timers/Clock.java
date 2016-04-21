package name.jgn196.testable_timers;

public interface Clock {

    void register(Listener listener);

    void start();

    void stop();

    interface Listener {
        void timeElapsed();
    }
}