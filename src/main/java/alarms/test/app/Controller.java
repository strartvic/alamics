package alarms.test.app;

import java.io.IOException;
import java.util.LinkedList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import alarms.test.model.FileModel;
import alarms.test.service.IService;
import alarms.test.service.Service;

@RestController
@RequestMapping("/file-service")
public class Controller {

	private IService service = new Service();

	@GetMapping
	public LinkedList<String> getFile() {
		try {
			service.add(new FileModel("D:\\01. Mamma Mia.mp3"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return service.getFileNames();
	}

}
