package com.examples.spring.demo.todo;

import org.springframework.stereotype.Component;

@Component
public class TodoClientWithImplicitConstructorInjection {

	private ITodo todo;

	/**
	 * Automatically used for dependency injection.
	 * 
	 * @param todo
	 */
	public TodoClientWithImplicitConstructorInjection(ITodo todo) {
		this.todo = todo;
	}

	public String showTodo() {
		return getClass().getSimpleName() + ": " + todo.toString();
	}
}
