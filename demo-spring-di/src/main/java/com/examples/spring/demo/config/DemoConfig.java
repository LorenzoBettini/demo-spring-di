package com.examples.spring.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.examples.spring.demo.todo.ITodo;
import com.examples.spring.demo.todo.Todo;

@Configuration
@ComponentScan(basePackages = { "com.examples.spring.demo.todo" })
public class DemoConfig {

	@Bean
	@Primary
	public ITodo getTodo() {
		return new Todo(1, "Default");
	}

}
