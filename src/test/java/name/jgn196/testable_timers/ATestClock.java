package name.jgn196.testable_timers;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

public class ATestClock {

    @Test
    public void sendsTicksToRegisteredListeners() {
        final TestClock clock = new TestClock();
        final AtomicInteger ticksHeard = new AtomicInteger(0);
        final Clock.Listener listener = ticksHeard::incrementAndGet;

        clock.register(listener);
        clock.tick();

        assertThat(ticksHeard.get()).as("Ticks heard").isEqualTo(1);

        clock.unregister(listener);
        clock.tick();

        assertThat(ticksHeard.get()).as("Ticks heard").isEqualTo(1);
    }

    @Test
    public void ignoresRegisteringNull() {
        final TestClock clock = new TestClock();

        clock.register(null);
        clock.tick();
    }

    @Test
    public void ignoresDuplicateRegistrations() {
        final TestClock clock = new TestClock();
        final AtomicInteger ticksHeard = new AtomicInteger(0);
        final Clock.Listener listener = ticksHeard::incrementAndGet;

        clock.register(listener);
        clock.register(listener);
        clock.tick();

        assertThat(ticksHeard.get()).as("Ticks heard").isEqualTo(1);
    }

    @Test
    public void isRobustToListenerExceptions() {
        final TestClock clock = new TestClock();
        final Clock.Listener failingListener = () -> {
            throw new RuntimeException();
        };
        final AtomicBoolean secondListenerCalled = new AtomicBoolean(false);
        final Clock.Listener secondListener = () -> secondListenerCalled.set(true);

        clock.register(secondListener);
        clock.register(failingListener);

        clock.tick();

        assertThat(secondListenerCalled.get()).isTrue();
    }
}
