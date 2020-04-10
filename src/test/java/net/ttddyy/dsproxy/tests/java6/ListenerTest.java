package net.ttddyy.dsproxy.tests.java6;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.sql.DataSource;

import net.ttddyy.dsproxy.function.DSProxyBiConsumer;
import net.ttddyy.dsproxy.function.DSProxyConsumer;
import net.ttddyy.dsproxy.listener.MethodExecutionContext;
import net.ttddyy.dsproxy.listener.QueryExecutionContext;
import net.ttddyy.dsproxy.listener.lifecycle.JdbcLifecycleEventListenerAdapter;
import net.ttddyy.dsproxy.listener.logging.QueryExecutionContextFormatter;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.h2.jdbcx.JdbcConnectionPool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Test for listeners.
 *
 * @author Tadaya Tsuyukubo
 */
public class ListenerTest {

	private JdbcConnectionPool ds;

	@Before
	public void setUp() throws Exception {
		this.ds = JdbcConnectionPool.create("jdbc:h2:~/test", "sa", "");
		TestDbUtils.populateInitialData(this.ds);
	}

	@After
	public void tearDown() throws Exception {
		this.ds.dispose();
	}

	@Test
	public void proxyDataSourceListener() throws Exception {
		final AtomicBoolean beforeQuery = new AtomicBoolean();
		final AtomicBoolean afterQuery = new AtomicBoolean();
		final AtomicBoolean beforeMethod = new AtomicBoolean();
		final AtomicBoolean afterMethod = new AtomicBoolean();

		DataSource proxyDs = ProxyDataSourceBuilder.create(ds)
				.beforeQuery(new DSProxyConsumer<QueryExecutionContext>() {
					public void accept(QueryExecutionContext queryExecutionContext) {
						beforeQuery.set(true);
					}
				})
				.afterQuery(new DSProxyConsumer<QueryExecutionContext>() {
					public void accept(QueryExecutionContext queryExecutionContext) {
						afterQuery.set(true);
					}
				})
				.beforeMethod(new DSProxyConsumer<MethodExecutionContext>() {
					public void accept(MethodExecutionContext methodExecutionContext) {
						beforeMethod.set(true);
					}
				})
				.afterMethod(new DSProxyConsumer<MethodExecutionContext>() {
					public void accept(MethodExecutionContext methodExecutionContext) {
						afterMethod.set(true);
					}
				})
				.build();

		// perform single query
		TestDbUtils.countTable(proxyDs, "emp");

		assertTrue(beforeQuery.get());
		assertTrue(afterQuery.get());
		assertTrue(beforeMethod.get());
		assertTrue(afterMethod.get());
	}

	@Test
	public void jdbcLifecycleListener() throws Exception {
		final AtomicBoolean beforeQuery = new AtomicBoolean();
		final AtomicBoolean afterMethod = new AtomicBoolean();
		final AtomicBoolean afterExecuteQueryOnStatement = new AtomicBoolean();

		JdbcLifecycleEventListenerAdapter listener = new JdbcLifecycleEventListenerAdapter() {
			@Override
			public void afterMethod(MethodExecutionContext executionContext) {
				afterMethod.set(true);
			}

			@Override
			public void beforeQuery(QueryExecutionContext executionContext) {
				beforeQuery.set(true);
			}

			@Override
			public void afterExecuteQueryOnStatement(MethodExecutionContext executionContext) {
				afterExecuteQueryOnStatement.set(true);
			}
		};

		DataSource proxyDs = ProxyDataSourceBuilder.create(ds).listener(listener).build();

		// perform single query
		TestDbUtils.countTable(proxyDs, "emp");

		assertTrue(beforeQuery.get());
		assertTrue(afterMethod.get());
		assertTrue(afterExecuteQueryOnStatement.get());
	}

	@Test
	public void queryExecutionFormatter() {
		QueryExecutionContextFormatter formatter = new QueryExecutionContextFormatter();

		formatter.addConsumer(new DSProxyBiConsumer<QueryExecutionContext, StringBuilder>() {
			public void accept(QueryExecutionContext queryExecutionContext, StringBuilder sb) {
				sb.append("on-consumer");
			}
		});

		QueryExecutionContext context = new QueryExecutionContext();
		String s = formatter.format(context);

		assertEquals("on-consumer", s);
	}

}
