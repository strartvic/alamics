package alarms.test.service;

import java.util.LinkedList;

import alarms.test.model.FileModel;

public interface IService {

	void add(FileModel file);

	void delete(FileModel file);

	FileModel get(String fileName);

	LinkedList<String> getFileNames();

}
