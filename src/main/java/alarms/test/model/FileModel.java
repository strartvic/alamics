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
	private Instant date = Instant.now();

	/**
	 * Байтовое представление
	 */
	private byte[] bytes;

	public FileModel(MultipartFile file) throws IOException {
		bytes = file.getBytes();

		name = file.getOriginalFilename();
		int indexPoint = name.lastIndexOf('.');
		if (indexPoint != -1) {
			type = name.substring(indexPoint + 1);
			name = name.substring(0, indexPoint);
		}
		guid = hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		FileModel fileModel = (FileModel) o;

		if (name != null ? !name.equals(fileModel.name) : fileModel.name != null) return false;
		if (type != null ? !type.equals(fileModel.type) : fileModel.type != null) return false;
		return Arrays.equals(bytes, fileModel.bytes);
	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (type != null ? type.hashCode() : 0);
		result = 31 * result + Arrays.hashCode(bytes);
		return result;
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
