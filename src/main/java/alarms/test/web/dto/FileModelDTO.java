package alarms.test.web.dto;

import java.time.Instant;

import alarms.test.model.FileModel;

public class FileModelDTO {

	private int guid;

	private String name;

	private String type;

	private Instant date;

	public FileModelDTO(FileModel fileModel) {
		this.guid = fileModel.getGuid();
		this.name = fileModel.getFileName();
		this.type = fileModel.getFileType();
		this.date = fileModel.getDate();
	}

	public int getGuid() {
		return guid;
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

}
