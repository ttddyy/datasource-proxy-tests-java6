package net.ttddyy.dsproxy.tests.java6;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Test suite to run all tests.
 *
 * @author Tadaya Tsuyukubo
 */
@RunWith(Suite.class)
@SuiteClasses({
		CompilationTest.class,
		JdKVersionTest.class,
		ListenerTest.class
})
public class AllTests {
}
