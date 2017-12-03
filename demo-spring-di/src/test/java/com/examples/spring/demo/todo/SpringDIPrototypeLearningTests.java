package com.examples.spring.demo.todo;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.examples.spring.demo.config.DemoConfig;
import com.examples.spring.demo.todo.Todo;

/**
 * Verifies protoype scope; this test class must be in the
 * "todo" package to make {@link DemoConfig} able to inject
 * {@link TodoPrototype}.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes=DemoConfig.class)
public class SpringDIPrototypeLearningTests {

	@Component
	@Scope("prototype")
	private static class TodoPrototype extends Todo {
		
	}

	@Autowired
	private TodoPrototype todo1;

	@Autowired
	private TodoPrototype todo2;

	@Test
	public void testTodoInjected() {
		assertNotNull(todo1);
		assertNotNull(todo2);
		assertNotSame(todo1, todo2);
	}

}
