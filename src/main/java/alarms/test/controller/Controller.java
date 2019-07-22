package alarms.test.controller;

import java.util.LinkedList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import alarms.test.model.FileModel;
import alarms.test.model.FileModelDTO;
import alarms.test.service.Facade;
import alarms.test.service.IFacade;
import io.swagger.annotations.Api;

@RestController
@Api(value = "fileService", description = "Операции для работы с файлами")
//fixme  тут вместо @Api лучше использовать @RequestMapping()
public class Controller { //fixme я бы переименовал в FileStorageController

	//fixme этот класс должен лежать в WEB директории, к примеру alarms.test.web.controller

	private IFacade facade = new Facade(); //fixme это не понятно что за класс))))) для чего он?

	{
		try {
			//fixme  как я понимаю это статичный набор файлов, а фалы должны прилетать в хранилище через web морду или апи
			facade.add(new FileModel("D:\\01. Mamma Mia.mp3"));
			facade.add(new FileModel("D:\\01. Mamma Mia.mp3"));
			facade.add(new FileModel("D:\\test.xls"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/file-service")
	//fixme обязательно надо указать какого типа запрос (POST, GET, PUT и т.д.)
	//fixme этот урл я бы сделал GET через такую аннотацию @GetMapping("/file-service")
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
	//fixme тут не понятно что мы передаем? судя по коду это путь к файлу, а надо сюда передать MultipartFile
	// и на диске его сохранять не надо, файлы должны быть в памяти в виде byte[]
	public void addFile(@RequestParam(value = "filePath") String filePath) {
		try {
			FileModel file = new FileModel(filePath);
			facade.add(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/file-service/download")
	//fixme при скачивании сервис должен отдать файл, а не линк
	// под линком подразумевалось некое апи, напрмер <host>/file-service/download, которое должно отдавать поток с файлом
	public HttpServletResponse download(@RequestParam(value = "fileId") int fileId, HttpServletResponse response) {
		return facade.getLink(fileId, response);
	}

}
