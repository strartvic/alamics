package alarms.test.service;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import alarms.test.model.FileModel;
import alarms.test.model.FileModelDTO;

//fixme я бы переименовал его в FileStorageFacade и поместил в директорию alarms.test.facade
//fixme фасад по идее тоже сервис (@Service)
//fixme и если это имплементация интерфейса или абстрактного класса, то лучше их называть ...Impl (FileStorageFacadeImpl)
public class Facade implements IFacade {

	private IService service = new Service();

	@Override
	public void add(FileModel file) {
		service.add(file);
	}

	@Override
	public void delete(int id) {
		service.delete(id);
	}

	@Override
	public FileModelDTO get(int id) {
		FileModel file = service.get(id);
		if (file == null) {
			return null;
		}
		return new FileModelDTO(file);
	}

	@Override
	public LinkedList<String> getFileNames() {
		return service.getFileNames();
	}

	@Override
	public LinkedList<FileModelDTO> getFiles() {
		LinkedHashSet<FileModel> files = service.getFiles();
		if (files == null || files.isEmpty()) {
			return null;
		}
		return getFilesDTO(files);
	}

	@Override
	public LinkedList<FileModelDTO> getFiles(String name) {
		LinkedList<FileModel> files = service.getFiles(name);
		if (files == null || files.isEmpty()) {
			return null;
		}
		return getFilesDTO(files);
	}

	@Override
	public LinkedList<FileModelDTO> getFiles(int beginSize, int endSize) {
		LinkedList<FileModel> files = service.getFiles(beginSize, endSize);
		if (files == null || files.isEmpty()) {
			return null;
		}
		return getFilesDTO(files);
	}

	/**
	 * Преобразоваеть файлы в фасад
	 * 
	 * @param files файлы
	 * @return фасад
	 */
	private LinkedList<FileModelDTO> getFilesDTO(LinkedHashSet<FileModel> files) {
		LinkedList<FileModelDTO> filesDTO = new LinkedList<FileModelDTO>();
		for (FileModel file : files) {
			filesDTO.add(new FileModelDTO(file));
		}
		return filesDTO;
	}

	/**
	 * Преобразоваеть файлы в фасад
	 * 
	 * @param files файлы
	 * @return фасад
	 */
	private LinkedList<FileModelDTO> getFilesDTO(LinkedList<FileModel> files) {
		LinkedList<FileModelDTO> filesDTO = new LinkedList<FileModelDTO>();
		for (FileModel file : files) {
			filesDTO.add(new FileModelDTO(file));
		}
		return filesDTO;
	}

	@Override
	public HttpServletResponse getLink(int fileId, HttpServletResponse response) {
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

		return response;
	}

}
