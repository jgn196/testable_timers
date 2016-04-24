package name.jgn196.testable_timers.examples;

import name.jgn196.testable_timers.Clock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * This example shows how to create and use a Clock object to trigger code to run at one second intervals.
 *
 * In this example the Clock is allowed to run for 10 ticks before being stopped.
 */
public class RealClockExample {
    public static void main(final String[] args) throws InterruptedException {
        final Clock clock = Clock.withTickPeriod(1, TimeUnit.SECONDS);
        final CountDownLatch latch = new CountDownLatch(10);

        clock.register(() -> {
            System.out.println("Tick!");
            latch.countDown();
        });
        clock.start();
        
        latch.await();
        clock.stop();
    }
}
