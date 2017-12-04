package com.examples.spring.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.examples.spring.demo.todo.ITodo;
import com.examples.spring.demo.todo.Todo;

@Configuration
public class DemoConfig {

	@Bean
	public ITodo getTodo() {
		return new Todo(1, "Default");
	}

}
