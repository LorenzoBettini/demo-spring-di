package com.examples.spring.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.examples.spring.demo.config.DemoConfig;
import com.examples.spring.demo.todo.ITodo;
import com.examples.spring.demo.todo.TodoClientWithConstructorInjection;
import com.examples.spring.demo.todo.TodoClientWithFieldInjection;
import com.examples.spring.demo.todo.TodoClientWithImplicitConstructorInjection;

@SpringBootApplication
public class DemoSpringDiApplication {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		ITodo todo = context.getBean(ITodo.class);
		System.out.println(todo);
		System.out.println
			(context.getBean
				(TodoClientWithFieldInjection.class).showTodo());
		System.out.println
			(context.getBean
				(TodoClientWithConstructorInjection.class).showTodo());
		System.out.println
			(context.getBean
				(TodoClientWithImplicitConstructorInjection.class).showTodo());
		context.close();
	}
}
