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
	private String name;

	/**
	 * Тип файла
	 */
	private String type = "";

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

		name = file.getOriginalFilename();
		int indexPoint = name.lastIndexOf('.');
		if (indexPoint != -1) {
			type = name.substring(indexPoint + 1);
			name = name.substring(0, indexPoint);
		}
		guid = hashCode();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(bytes);
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public Instant getDate() {
		return date;
	}

	public int getSize() {
		return bytes.length;
	}

	public void setName(String fileName) {
		this.name = fileName;
	}

	public void setType(String fileType) {
		this.type = fileType;
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
