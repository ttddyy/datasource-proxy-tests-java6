package net.ttddyy.dsproxy.tests.java6;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author Tadaya Tsuyukubo
 */
public class JdKVersionTest {

	@Test
	public void jdkVersionCheck() {
		String version = System.getProperty("java.specification.version");
		assertEquals("1.6", version);
	}

}
