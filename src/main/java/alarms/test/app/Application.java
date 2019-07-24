package alarms.test.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("alarms.test") //fixme не нужна аннотация, т.к. @SpringBootApplication сам найдет все что ему нужно, если будет лежать там где нужно
public class Application {

	//fixme этот файл должен быть в корне приложения, т.е. в alarms.test
	//fixme используй файл конфигурации, посмотри что такое application.yml или application.properties
	//fixme убери директорию webapp она в сервисах api не нужна вобще, тем более у тебя она не используется

	//fixme много моментов на которые я не обратил внимаение, т.к. есть что тут править)))

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}


