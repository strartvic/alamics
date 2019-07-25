package alarms.test.service;

import java.util.LinkedHashSet;
import java.util.LinkedList;

import alarms.test.model.FileModel;

public interface FileStorageService {

	/**
	 * Добавить файл
	 *
     * @param file файл
     * @return true/false
     */
	boolean add(FileModel file);

	/**
	 * удалить файл
	 *
     * @param id уник номер
     * @return true/false
     */
	boolean delete(int id);

	/**
	 * Получить файл по id
	 * 
	 * @param id уник
	 * @return файл
	 */
	FileModel get(int id);

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

	/**
	 * Получить файлы по имени
	 * 
	 * @param name имя файла
	 * @return файлы
	 */
	LinkedList<FileModel> getFiles(String name);

	/**
	 * Получить файлы по размеру
	 * 
	 * @param beginSize начальный размер (байты)
	 * @param endSize   конечный размер (байты)
	 * @return файлы
	 */
	LinkedList<FileModel> getFiles(int beginSize, int endSize);

}
