package alarms.test.web.controller;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import alarms.test.model.FileModel;
import alarms.test.service.FileStorageService;
import alarms.test.web.dto.FileModelDTO;
import alarms.test.web.dto.FileModelMapper;

@RestController
@RequestMapping("/file-service")
public class FileStorageController {

	// Сейчас фасад исключил, работаю с сервисом, поэтому возвращается
	// полный набор параметров модели файла вместе с байтовым массивом
	@Autowired
	private FileStorageService service;

	// Тестовый метод для маппера (выпадает в ошибку)
	@GetMapping("/test")
	public FileModelDTO getFilesTest() {
		return FileModelMapper.INSTANCE.fileModelToFileModelDTO(service.getFiles().iterator().next());
	}

	@GetMapping("/")
	public LinkedHashSet<FileModel> getFiles() {
		return service.getFiles();
	}

	@GetMapping("/file-names")
	public LinkedList<String> getFilesNames() {
		return service.getFileNames();
	}

	@DeleteMapping("/delete-file")
	public void deleteFile(@RequestParam(value = "fileId") int fileId) {
		service.delete(fileId);
	}

	@PostMapping("/add-file")
	public void addFile(@RequestParam("file") MultipartFile file) {
		try {
			service.add(new FileModel(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/download")
	// fixme при скачивании сервис должен отдать файл, а не линк
	// под линком подразумевалось некое апи, напрмер <host>/file-service/download,
	// которое должно отдавать поток с файлом
	public void download(@RequestParam(value = "fileId") int fileId, HttpServletResponse response) {

		// Это из фасада перенес))) Так же передавать обратно или нет??
		FileModel file = service.get(fileId);
		String fullName = new StringBuilder().append(file.getFileName()).append(".").append(file.getFileType())
				.toString();

		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + fullName);
		try (ServletOutputStream out = response.getOutputStream();) {
			out.write(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
