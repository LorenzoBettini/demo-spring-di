package com.examples.spring.demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.examples.spring.demo.todo" })
public class DemoConfig {

}
