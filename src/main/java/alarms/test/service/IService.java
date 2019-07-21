package alarms.test.service;

import java.util.LinkedHashSet;
import java.util.LinkedList;

import alarms.test.model.FileModel;

public interface IService {

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
	void delete(FileModel file);

	/**
	 * Получить файл по имени
	 * 
	 * @param fileName имя файла
	 * @return файл
	 */
	FileModel get(String fileName);

	/**
	 * Получить имена файлов
	 * 
	 * @return файлы
	 */
	LinkedList<String> getFileNames();

	/**
	 * Получить файлы
	 * 
	 * @return файлы
	 */
	LinkedHashSet<FileModel> getFiles();

}
