package net.ttddyy.dsproxy.tests.java6;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeFalse;

/**
 *
 * @author Tadaya Tsuyukubo
 */
public class JdKVersionTest {

	@Test
	public void jdkVersionCheck() {

		String version = System.getProperty("java.specification.version");

		boolean isSurefire = "surefire".equalsIgnoreCase(System.getProperty("test.mode"));
		if (isSurefire) {
			// since mvn ran with jdk 1.7, skip this test. see surefire config in pom.xml.
			System.out.println();
			System.out.println("***********************************************");
			System.out.println("**  SKIPPING JDK Version Check Test (JDK=" + version + ")");
			System.out.println("***********************************************");
			System.out.println();
		}

		assumeFalse("Skip invoked by surefire", isSurefire);

		assertEquals("1.6", version);
	}

}
