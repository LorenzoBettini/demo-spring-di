package com.examples.spring.demo.todo;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import com.examples.spring.demo.config.DemoConfig;
import com.examples.spring.demo.todo.ITodo;

/**
 * Uses a nested {@link TestConfiguration} to define the bean injection of the
 * nested class {@link TodoWithAutowiredSummary} using {@link Qualifier} annotations.
 */
@RunWith(SpringRunner.class)
public class SpringDIConfigurationWithQualifierLearningTests {

	@Component
	private static class TodoWithAutowiredSummary implements ITodo {

		@Autowired
		@Qualifier("defaultId")
		private long id;

		private String summary;

		@Override
		public long getId() {
			return id;
		}

		@Override
		public String getSummary() {
			return summary;
		}

		@Autowired
		@Qualifier("defaultSummary")
		public void setSummary(String summary) {
			this.summary = summary;
		}

	}

	@TestConfiguration
	static class ConfigurationForTest extends DemoConfig {
		@Bean
		@Qualifier("defaultSummary")
		public String getDefaultSummary() {
			return "A default summary";
		}

		@Bean
		@Qualifier("defaultId")
		public long getDefaultId() {
			return 10;
		}
	}

	@Autowired
	private TodoWithAutowiredSummary todo1;

	@Autowired
	private TodoWithAutowiredSummary todo2;

	@Test
	public void testDefaultSummaryInjected() {
		assertNotNull(todo1);
		assertNotNull(todo2);
		assertEquals("A default summary", todo1.getSummary());
		assertEquals(10, todo1.getId());
		assertSame(todo1, todo2);
	}

}
