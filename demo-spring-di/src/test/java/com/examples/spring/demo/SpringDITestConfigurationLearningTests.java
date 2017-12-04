package com.examples.spring.demo;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import com.examples.spring.demo.config.DemoConfig;
import com.examples.spring.demo.todo.ITodo;
import com.examples.spring.demo.todo.Todo;
import com.examples.spring.demo.todo.TodoClientWithConstructorInjection;

/**
 * Uses a nested {@link TestConfiguration} to define the bean injection of the
 * nested class {@link TodoPrototype}.
 */
@RunWith(SpringRunner.class)
public class SpringDITestConfigurationLearningTests {

	@Component
	private static class TodoPrototype extends Todo {

		public TodoPrototype(int id, String summary) {
			super(id, summary);
		}

	}

	/**
	 * Inherit {@link DemoConfig} in order to have automatic injection of
	 * {@link TodoClientWithConstructorInjection}, we want to inject with
	 * "prototype" scope.
	 */
	@TestConfiguration
	static class ConfigurationForTest extends DemoConfig {
//		@Bean
//		@Scope("prototype")
//		public TodoPrototype getTodoPrototype() {
//			return new TodoPrototype(1, "TodoPrototype");
//		}

		/**
		 * Overridden to return {@link TodoPrototype} when an
		 * {@link ITodo} is requested.  We must write all the annotations.
		 */
		@Bean
		@Scope("prototype")
		@Primary
		@Override
		public ITodo getTodo() {
			return new TodoPrototype(1, "TodoPrototype");
		}
	}

	@Autowired
	private TodoPrototype todo1;

	@Autowired
	private TodoPrototype todo2;

	@Autowired
	private TodoClientWithConstructorInjection client;

	@Test
	public void testTodoInjected() {
		assertNotNull(todo1);
		assertNotNull(todo2);
		assertEquals("TodoPrototype", todo1.getSummary());
		assertNotSame(todo1, todo2);
	}

	@Test
	public void testTodoInjectedInClient() {
		assertEquals("TodoPrototype", client.getTodo().getSummary());
		assertNotSame(todo1, client.getTodo());
	}

}
