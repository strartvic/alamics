package alarms.test.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("alarms.test")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
