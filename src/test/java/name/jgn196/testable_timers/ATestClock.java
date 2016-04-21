package name.jgn196.testable_timers;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class ATestClock {
    @Test
    public void signalsRegisteredListenersWhenTriggered() {
        final TestClock clock = new TestClock();

        final AtomicInteger ticksHeard = new AtomicInteger(0);
        final Clock.Listener listener = () -> ticksHeard.getAndAdd(1);

        clock.register(listener);
        clock.triggerTimer();

        clock.unregister(listener);
        clock.triggerTimer();

        Assert.assertEquals(1, ticksHeard.get());
    }
}
