# Testable Timers
A Java 8 timer class and test injectable companion class.

## Example Usage
Create a clock.
``` Java
  Clock clock = Clock.withTickPeriod(1, TimeUnit.SECONDS);
```
Register to receive ticks from it.
``` Java
  clock.register(() -> System.out.println("Tick!"));
```
Start and then later stop it.
``` Java
  clock.start();
  ...
  clock.stop();
```
Replace this `Clock` with an instance of `TestClock` in your unit tests and control when a tick happens with the `TestClock.tick()` 
method.

See the more complete examples in the `src/test/name/jgn196/testable_timers/examples` directory.

## Motivation
These classes were written help make code that is driven by timers easier to test.

## Building and Testing
This is a Gradle project that makes use of Java 8 features. It's only dependencies are JUnit and AssertJ and these are only used by the
test code.

To run all the tests and build the JAR run:
```
  gradle test jar
```
(Use the gradlew command on windows.)
