package name.jgn196.testable_timers;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class ARealClock {
    public static final int PERIOD = 100;
    public static final int TIMER_FIRE_COUNT = 5;
    public static final int EXPECTED_DURATION = 100 * 5;

    @Test
    public void notifiesRegisteredListenersPeriodically() throws BrokenBarrierException, InterruptedException {
        final Clock clock = new RealClock(PERIOD, TimeUnit.MILLISECONDS);
        final CountDownLatch latch = new CountDownLatch(TIMER_FIRE_COUNT);
        final long startTicks = System.currentTimeMillis();

        clock.register(latch::countDown);
        clock.start();

        latch.await();

        clock.stop();

        final long ticksTaken = System.currentTimeMillis() - startTicks;
        Assert.assertTrue(ticksTaken >= EXPECTED_DURATION);
    }

    @Test
    public void willNotFireWhileTheLastNotificationIsStillRunning() throws InterruptedException {
        final Clock clock = new RealClock(PERIOD, TimeUnit.MILLISECONDS);
        final CountDownLatch latch = new CountDownLatch(TIMER_FIRE_COUNT);
        final AtomicBoolean processingFire = new AtomicBoolean(false);
        final AtomicBoolean fireCollision = new AtomicBoolean(false);

        clock.register(() -> {
            if(processingFire.getAndSet(true)){
                // Fail we were already processing the last fire
                fireCollision.set(true);
            }
            try {
                Thread.sleep(2 * PERIOD);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
            processingFire.set(false);
        });
        clock.start();

        latch.await();

        clock.stop();

        Assert.assertFalse(fireCollision.get());
    }
}
