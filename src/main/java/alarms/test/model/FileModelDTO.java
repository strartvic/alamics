package alarms.test.model;

import java.time.Instant;

public class FileModelDTO {

	//fixme все файлы ДТО должны лежать в WEB директории, к примеру в директории alarms.test.web.dto

	private int guid;

	private String name;

	private String type;

	private Instant date;

	//fixme попробуй аннотации @Mappings (Mapstruct) это сторонняя библиотека, которая упрощает все работы с DTO моделями
	//fixme правда надо почитать как его запускать, там в помнике, в разделе build надо рописать правила

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
