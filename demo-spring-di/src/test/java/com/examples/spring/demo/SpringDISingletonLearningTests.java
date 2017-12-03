package com.examples.spring.demo;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.examples.spring.demo.config.DemoConfig;
import com.examples.spring.demo.todo.ITodo;
import com.examples.spring.demo.todo.TodoClientWithConstructorInjection;

/**
 * Verifies that Component means singleton.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes=DemoConfig.class)
public class SpringDISingletonLearningTests {

	@Autowired
	private ITodo todo1;

	@Autowired
	private ITodo todo2;

	@Autowired
	private TodoClientWithConstructorInjection client;

	@Test
	public void testTodoInjected() {
		assertNotNull(todo1);
		assertSame(todo1, todo2);
		assertSame(todo1, client.getTodo());
	}

}
