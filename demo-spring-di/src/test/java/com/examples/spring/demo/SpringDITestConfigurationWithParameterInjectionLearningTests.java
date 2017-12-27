package com.examples.spring.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import com.examples.spring.demo.todo.ITodo;
import com.examples.spring.demo.todo.Todo;

/**
 * Uses a nested {@link TestConfiguration} to define the bean injection of the
 * nested class {@link TodoPrototype}, calling the constructor explicitly,
 * passing as argument an injected parameter in the configuration.
 */
@RunWith(SpringRunner.class)
public class SpringDITestConfigurationWithParameterInjectionLearningTests {

	@Component
	private static class TodoWrapper implements ITodo {

		private ITodo delegate;

		public TodoWrapper(ITodo delegate) {
			this.delegate = delegate;
		}

		@Override
		public long getId() {
			return delegate.getId();
		}

		@Override
		public String getSummary() {
			return delegate.getSummary();
		}

		public ITodo getDelegate() {
			return delegate;
		}

	}

	@Component
	private static class TodoPrototype extends Todo {

		public TodoPrototype(int id, String summary) {
			super(id, summary);
		}

	}

	/**
	 * The {@link ITodo} parameter needed by
	 * {@link ConfigurationForTest#getWrapper(ITodo)} is implicitly passed as
	 * argument by using {@link ConfigurationForTest#getTodo()}
	 */
	@TestConfiguration
	static class ConfigurationForTest {
		/**
		 * Return a {@link TodoPrototype} when an {@link ITodo} is requested.
		 */
		@Bean
		@Scope("prototype")
		@Primary
		public ITodo getTodo() {
			return new TodoPrototype(1, "TodoPrototype");
		}

		/**
		 * Return a {@link TodoWrapper}
		 * @param delegate passed by injection
		 * @return
		 */
		@Bean
		public TodoWrapper getWrapper(ITodo delegate) {
			return new TodoWrapper(delegate);
		}
	}

	@Autowired
	private ITodo todo1;

	@Autowired
	private ITodo todo2;

	@Autowired
	private TodoWrapper wrapper;

	@Test
	public void testTodoInjected() {
		assertNotNull(todo1);
		assertNotNull(todo2);
		assertEquals("TodoPrototype", todo1.getSummary());
		assertNotSame(todo1, todo2);
		assertEquals("TodoPrototype", wrapper.getSummary());
		assertNotSame(todo1, wrapper.getDelegate());
		assertNotSame(todo2, wrapper.getDelegate());
	}

}
