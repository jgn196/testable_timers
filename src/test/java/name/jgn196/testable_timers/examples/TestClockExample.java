package name.jgn196.testable_timers.examples;

import name.jgn196.testable_timers.Clock;
import name.jgn196.testable_timers.TestClock;

/**
 * This example shows how to substitute TestClock objects for Clock objects in your code so that you can control test
 * timing.
 */
public class TestClockExample {
    public static void main(final String[] args) {
        final TestClock clock = new TestClock();
        final ClassUnderTest testObject = new ClassUnderTest(clock);

        System.out.println("Test object has done stuff: " + testObject.isStuffDone());

        clock.tick();
        System.out.println("Tick!");

        System.out.println("Test object has done stuff: " + testObject.isStuffDone());
    }
}

/**
 * This class will record that it has done "stuff" when it receives a clock tick.
 */
class ClassUnderTest {
    private boolean stuffDone = false;

    public ClassUnderTest(final Clock clock) {
        clock.register(this::doStuff);
        clock.start();
    }

    private void doStuff() {
        stuffDone = true;
    }

    public boolean isStuffDone() {
        return stuffDone;
    }
}
