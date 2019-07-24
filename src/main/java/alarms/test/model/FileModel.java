package alarms.test.model;

import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

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
	private String fileType = "";

	/**
	 * Дата создания
	 */
	private Instant date;

	/**
	 * Байтовое представление
	 */
	private byte[] bytes;

	public FileModel(MultipartFile file) throws IOException {
		bytes = file.getBytes();
		date = Instant.now();

		fileName = file.getOriginalFilename();
		int indexPoint = fileName.lastIndexOf('.');
		if (indexPoint != -1) {
			fileType = fileName.substring(indexPoint + 1);
			fileName = fileName.substring(0, indexPoint);
		}
		guid = hashCode();
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
