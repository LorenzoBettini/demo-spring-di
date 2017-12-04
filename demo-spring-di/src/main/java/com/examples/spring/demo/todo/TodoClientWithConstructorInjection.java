package com.examples.spring.demo.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TodoClientWithConstructorInjection {

	private ITodo todo;

	@Autowired
	public TodoClientWithConstructorInjection(ITodo todo) {
		this.todo = todo;
	}

	public String showTodo() {
		return getClass().getSimpleName() + ": " + todo.toString();
	}

	public ITodo getTodo() {
		return todo;
	}

}
