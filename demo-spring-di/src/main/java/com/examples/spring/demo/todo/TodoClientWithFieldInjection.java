package com.examples.spring.demo.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TodoClientWithFieldInjection {

	@Autowired
	private ITodo todo;

	public String showTodo() {
		return getClass().getSimpleName() + ": " + todo.toString();
	}
}
