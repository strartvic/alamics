package alarms.test.service;

import java.util.LinkedList;

import javax.servlet.http.HttpServletResponse;

import alarms.test.model.FileModel;
import alarms.test.web.dto.FileModelDTO;

public interface IFacade {

	/**
	 * Добавить файл
	 * 
	 * @param file файл
	 */
	void add(FileModel file);

	/**
	 * удалить файл
	 * 
	 * @param file файл
	 */
	void delete(int id);

	/**
	 * Получить представление файла по id
	 * 
	 * @param id уник
	 * @return файл
	 */
	FileModelDTO get(int id);

	/**
	 * Получить имена файлов
	 * 
	 * @return файлы
	 */
	LinkedList<String> getFileNames();

	/**
	 * Получить представления файлов
	 * 
	 * @return файлы
	 */
	LinkedList<FileModelDTO> getFiles();

	/**
	 * Получить представления файлов по имени
	 * 
	 * @param name имя файла
	 * @return файлы
	 */
	LinkedList<FileModelDTO> getFiles(String name);

	/**
	 * Получить представления файлов по размеру
	 * 
	 * @param beginSize начальный размер (байты)
	 * @param endSize   конечный размер (байты)
	 * @return файлы
	 */
	LinkedList<FileModelDTO> getFiles(int beginSize, int endSize);

	/**
	 * Получить линк
	 * 
	 * @param fileId   уник
	 * @param response ответ
	 * @return ответ
	 */
	HttpServletResponse getLink(int fileId, HttpServletResponse response);
}
