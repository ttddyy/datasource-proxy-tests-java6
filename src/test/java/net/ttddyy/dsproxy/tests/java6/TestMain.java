package net.ttddyy.dsproxy.tests.java6;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static java.lang.String.format;

/**
 * Main class to run test suite.
 *
 * ref: https://stackoverflow.com/questions/36047637/how-can-i-include-test-classes-into-maven-jar-and-execute-them
 *
 * @author Tadaya Tsuyukubo
 */
public class TestMain {

	public static void main(String[] args) {
		String jdkShortVersion = System.getProperty("java.specification.version");
		String jdkFullVersion = System.getProperty("java.version");
		System.out.println(format("** Running Tests JDK=%s(%s)**", jdkShortVersion, jdkFullVersion));

		JUnitCore engine = new JUnitCore();
		engine.addListener(new TextListener(System.out)); // required to print reports
		Result result = engine.run(AllTests.class);

		if (!result.wasSuccessful()) {
			System.out.println("** FAILED TESTS **");
			for (Failure failure : result.getFailures()) {
				System.out.println("- " + failure.toString());
			}
			System.exit(1);
		}

	}

}
