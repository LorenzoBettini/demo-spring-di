package com.examples.spring.demo;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.examples.spring.demo.todo.ITodo;

@RunWith(SpringRunner.class)
public class SpringDILearningTests {

	@Autowired
	private ITodo todo;

	@Test
	public void testTodoInjected() {
		assertNotNull(todo);
	}

}
