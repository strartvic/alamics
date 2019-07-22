package alarms.test.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;

public class FileModel {

	/**
	 * Уникальный номер
	 */
	private int guid;

	/**
	 * Имя файла
	 */
	private String fileName;

	/**
	 * Тип файла
	 */
	private String fileType;

	/**
	 * Дата создания
	 */
	private Instant date;

	/**
	 * Байтовое представление
	 */
	private byte[] bytes;

	public FileModel(String filePath) throws IOException, Exception {
		File file = new File(filePath);
		//fixme у спринга есть конфиг, настройка допустимой размерности файла
		if ((int) file.length() > 15e+7) {
			throw new Exception("Размер файла превышает допустимое значение 15Мбайт!");
		}

		//fixme вижу модель содержит byte[], его и надо возвращать при скачивании
		bytes = new byte[(int) (file.length())];
		try (BufferedInputStream bufInStr = new BufferedInputStream(new FileInputStream(filePath));) {
			bufInStr.read(bytes);
			date = Instant.now();
			String fullName = file.getName();
			fileName = fullName.substring(0, fullName.lastIndexOf('.'));
			fileType = fullName.substring(fullName.lastIndexOf('.') + 1);
			guid = hashCode();
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException();
		}
	}

	//fixme модель файла не должна ничего сохранять, этим должен заниматься FileStorageService
	public void save(String dirPath) {
		if (dirPath == null || dirPath.isEmpty()) {
			return;
		}
		//fixme класс File применять вобще не стоит
		//fixme должжно быть MultipartFile -> byte[] без всяких сохранений в файл на диске
		File dir = new File(dirPath);
		dir.mkdirs();
		if (!dir.isDirectory()) {
			return;
		}
		File newFile = new File(new StringBuilder().append(dirPath).append("\\").append(fileName).append(".")
				.append(fileType).toString());
		try {
			newFile.createNewFile();
			try (BufferedOutputStream bufInStr = new BufferedOutputStream(new FileOutputStream(newFile));) {
				bufInStr.write(bytes);
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(bytes);
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((fileType == null) ? 0 : fileType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileModel other = (FileModel) obj;
		if (!Arrays.equals(bytes, other.bytes))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (fileType == null) {
			if (other.fileType != null)
				return false;
		} else if (!fileType.equals(other.fileType))
			return false;
		return true;
	}

	public String getFileName() {
		return fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public Instant getDate() {
		return date;
	}

	public int getSize() {
		return bytes.length;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public int getGuid() {
		return guid;
	}
}
