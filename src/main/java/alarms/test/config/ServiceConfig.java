package alarms.test.config;

import org.springframework.context.annotation.Configuration;

import alarms.test.service.FileStorageService;
import alarms.test.service.FileStorageServiceImpl;

@Configuration
public class ServiceConfig {

	FileStorageService fileStorageService() {
		return new FileStorageServiceImpl();
	}
}
