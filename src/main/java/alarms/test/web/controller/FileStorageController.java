package alarms.test.web.controller;

import java.io.IOException;
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

import alarms.test.mapper.FileModelMapper;
import alarms.test.model.FileModel;
import alarms.test.service.FileStorageService;
import alarms.test.web.dto.FileModelDTO;

@RestController
@RequestMapping("/file-service")
public class FileStorageController {

	@Autowired
	private FileStorageService service;

	@GetMapping("/")
	public LinkedList<FileModelDTO> getFiles() {
		return FileModelMapper.INSTANCE.fileModelToFileModelDTO(service.getFiles());
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
	public void download(@RequestParam(value = "fileId") int fileId, HttpServletResponse response) {
		FileModel file = service.get(fileId);
		String fullName = new StringBuilder().append(file.getName()).append(".").append(file.getType()).toString();

		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + fullName);
		try (ServletOutputStream out = response.getOutputStream();) {
			out.write(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
