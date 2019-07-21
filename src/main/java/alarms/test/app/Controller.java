package alarms.test.app;

import java.util.LinkedHashSet;
import java.util.LinkedList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import alarms.test.model.FileModel;
import alarms.test.service.IService;
import alarms.test.service.Service;

@RestController
public class Controller {

	private IService service = new Service();

	{
		try {
			service.add(new FileModel("D:\\01. Mamma Mia.mp3"));
			service.add(new FileModel("D:\\01. Mamma Mia.mp3"));
			service.add(new FileModel("D:\\test.xls"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/file-service")
	public LinkedHashSet<FileModel> getFiles() {
		return service.getFiles();
	}

	@RequestMapping("/file-service/file-names")
	public LinkedList<String> getFilesNames() {
		return service.getFileNames();
	}

	@RequestMapping("/file-service/delete-file")
	public void deleteFile(@RequestParam(value = "fileId") int fileId) {
		service.delete(fileId);
	}

	@RequestMapping("/file-service/add-file")
	public void addFile(@RequestParam(value = "filePath") String filePath) {
		try {
			FileModel file = new FileModel(filePath);
			service.add(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
