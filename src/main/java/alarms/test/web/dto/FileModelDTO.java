package alarms.test.web.dto;

import java.time.Instant;

public class FileModelDTO {

	private int guid;

	private String name;

	private String type;

	private Instant date;

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

	public void setGuid(int guid) {
		this.guid = guid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

}
