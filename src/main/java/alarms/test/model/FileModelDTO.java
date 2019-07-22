package alarms.test.model;

import java.time.Instant;

public class FileModelDTO {

	private int guid;

	private String name;

	private String type;

	private Instant date;

	public FileModelDTO(FileModel file) {
		guid = file.getGuid();
		name = file.getFileName();
		type = file.getFileType();
		date = file.getDate();
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
