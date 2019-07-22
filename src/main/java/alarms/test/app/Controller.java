package alarms.test.app;

import java.util.LinkedList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import alarms.test.model.FileModel;
import alarms.test.model.FileModelDTO;
import alarms.test.service.Facade;
import alarms.test.service.IFacade;

@RestController
public class Controller {

	private IFacade facade = new Facade();

	{
		try {
			facade.add(new FileModel("D:\\01. Mamma Mia.mp3"));
			facade.add(new FileModel("D:\\01. Mamma Mia.mp3"));
			facade.add(new FileModel("D:\\test.xls"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/file-service")
	public LinkedList<FileModelDTO> getFiles() {
		return facade.getFiles();
	}

	@RequestMapping("/file-service/file-names")
	public LinkedList<String> getFilesNames() {
		return facade.getFileNames();
	}

	@RequestMapping("/file-service/delete-file")
	public void deleteFile(@RequestParam(value = "fileId") int fileId) {
		facade.delete(fileId);
	}

	@RequestMapping("/file-service/add-file")
	public void addFile(@RequestParam(value = "filePath") String filePath) {
		try {
			FileModel file = new FileModel(filePath);
			facade.add(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/file-service/download")
	public HttpServletResponse download(@RequestParam(value = "fileId") int fileId, HttpServletResponse response) {
		return facade.getLink(fileId, response);
	}

}
