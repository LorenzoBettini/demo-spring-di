package com.examples.spring.demo.todo;

import org.springframework.stereotype.Component;

@Component
public class Todo implements ITodo {

	private long id;
	private String summary;

	public Todo() {
		id = -1;
	}

	public Todo(long id, String summary) {
		super();
		this.id = id;
		this.summary = summary;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public String getSummary() {
		return summary;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", summary=" + summary + "]";
	}

}
