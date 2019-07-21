package alarms.test.service;

import java.util.LinkedHashSet;
import java.util.LinkedList;

import alarms.test.model.FileModel;

public class Service implements IService {

	private LinkedHashSet<FileModel> files = new LinkedHashSet<FileModel>();

	@Override
	public void add(FileModel file) {
		files.add(file);
	}

	@Override
	public void delete(FileModel file) {
		files.remove(file);
	}

	@Override
	public FileModel get(String fileName) {
		for (FileModel file : files) {
			if (fileName.equals(file.getFileName())) {
				return file;
			}
		}
		return null;
	}

	@Override
	public LinkedList<String> getFileNames() {
		LinkedList<String> fileNames = new LinkedList<String>();
		for (FileModel file : files) {
			fileNames.add(file.getFileName());
		}
		return fileNames;
	}

}
