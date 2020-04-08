package net.ttddyy.dsproxy.tests.java6;

import java.util.concurrent.TimeUnit;

import net.ttddyy.dsproxy.function.DSProxyBiConsumer;
import net.ttddyy.dsproxy.function.DSProxyBooleanSupplier;
import net.ttddyy.dsproxy.function.DSProxyConsumer;
import net.ttddyy.dsproxy.function.DSProxyFunction;
import net.ttddyy.dsproxy.listener.QueryExecutionContext;
import net.ttddyy.dsproxy.listener.SlowQueryListener;
import net.ttddyy.dsproxy.listener.TracingMethodListener;
import net.ttddyy.dsproxy.listener.count.QueryCount;
import net.ttddyy.dsproxy.listener.logging.QueryCountFormatter;
import net.ttddyy.dsproxy.listener.logging.QueryCountJsonFormatter;
import net.ttddyy.dsproxy.listener.logging.QueryExecutionContextFormatter;
import net.ttddyy.dsproxy.listener.logging.QueryExecutionContextJsonFormatter;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.junit.Test;

/**
 * Test compilation.
 *
 * @author Tadaya Tsuyukubo
 */
public class CompilationTest {

	@Test
	public void queryCountFormatter() {
		QueryCountFormatter.showAll()
				.addConsumer(new DSProxyBiConsumer<QueryCount, StringBuilder>() {
					public void accept(QueryCount queryCount, StringBuilder stringBuilder) {

					}
				})
				.showSuccess(new DSProxyBiConsumer<QueryCount, StringBuilder>() {
					public void accept(QueryCount queryCount, StringBuilder stringBuilder) {

					}
				})
				.showFailure(new DSProxyBiConsumer<QueryCount, StringBuilder>() {
					public void accept(QueryCount queryCount, StringBuilder stringBuilder) {

					}
				})
				.showTime(new DSProxyBiConsumer<QueryCount, StringBuilder>() {
					public void accept(QueryCount queryCount, StringBuilder stringBuilder) {

					}
				})
				.showTotal(new DSProxyBiConsumer<QueryCount, StringBuilder>() {
					public void accept(QueryCount queryCount, StringBuilder stringBuilder) {

					}
				})
		;

		QueryCountJsonFormatter.showAll()
				.addConsumer(new DSProxyBiConsumer<QueryCount, StringBuilder>() {
					public void accept(QueryCount queryCount, StringBuilder stringBuilder) {

					}
				})
				.showStatement(new DSProxyBiConsumer<QueryCount, StringBuilder>() {
					public void accept(QueryCount queryCount, StringBuilder stringBuilder) {

					}
				})
		;
	}

	@Test
	public void queryExecutionFormatter() {
		QueryExecutionContextFormatter.showAll()
				.addConsumer(new DSProxyBiConsumer<QueryExecutionContext, StringBuilder>() {
					public void accept(QueryExecutionContext queryExecutionContext, StringBuilder stringBuilder) {
					}
				})
				.onQuery(new DSProxyFunction<String, String>() {
					public String apply(String s) {
						return null;
					}
				})
				.showBatch(new DSProxyBiConsumer<QueryExecutionContext, StringBuilder>() {
					public void accept(QueryExecutionContext queryExecutionContext, StringBuilder stringBuilder) {
					}
				})
		;

		QueryExecutionContextJsonFormatter.showAll()
				.addConsumer(new DSProxyBiConsumer<QueryExecutionContext, StringBuilder>() {
					public void accept(QueryExecutionContext queryExecutionContext, StringBuilder stringBuilder) {

					}
				})
				.onQuery(new DSProxyFunction<String, String>() {
					public String apply(String s) {
						return null;
					}
				})
				.showDuration(new DSProxyBiConsumer<QueryExecutionContext, StringBuilder>() {
					public void accept(QueryExecutionContext queryExecutionContext, StringBuilder stringBuilder) {

					}
				})
		;
	}

	@Test
	public void slowQueryListener() {
		SlowQueryListener listener = new SlowQueryListener();
		listener.setOnSlowQuery(new DSProxyConsumer<QueryExecutionContext>() {
			public void accept(QueryExecutionContext queryExecutionContext) {
			}
		});
	}

	@Test
	public void tracingMethodListener() {
		TracingMethodListener listener = new TracingMethodListener();
		listener.setTracingCondition(new DSProxyBooleanSupplier() {
			public boolean getAsBoolean() {
				return false;
			}
		});
		listener.setTracingMessageConsumer(new DSProxyConsumer<String>() {
			public void accept(String s) {
			}
		});
	}

	@Test
	public void proxyDataSourceBuilder() {
		ProxyDataSourceBuilder.create()
				.onSlowQuery(10, TimeUnit.DAYS, new DSProxyConsumer<QueryExecutionContext>() {
					public void accept(QueryExecutionContext queryExecutionContext) {
					}
				})
				.traceMethodsWhen(new DSProxyBooleanSupplier() {
					public boolean getAsBoolean() {
						return false;
					}
				}, new DSProxyConsumer<String>() {
					public void accept(String s) {

					}
				})
		;
	}
}
