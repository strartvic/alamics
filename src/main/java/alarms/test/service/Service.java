package alarms.test.service;

import java.util.LinkedHashSet;
import java.util.LinkedList;

import alarms.test.model.FileModel;

//fixme переименовать в FileStorageServiceImpl
//fixme и это сервис @Service, почитай в чем прикол сервисов, и что такое бины в спринге
public class Service implements IService {

	private LinkedHashSet<FileModel> files = new LinkedHashSet<FileModel>();

	@Override
	public void add(FileModel file) {
		files.add(file);
	}

	@Override
	public void delete(int id) {
		FileModel file = get(id);
		if (file == null) {
			return;
		}
		files.remove(file);
	}

	@Override
	public FileModel get(int id) {
		for (FileModel file : files) {
			if (file.hashCode() == id) {
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

	@Override
	public LinkedHashSet<FileModel> getFiles() {
		return files;
	}

	@Override
	public LinkedList<FileModel> getFiles(String name) {
		LinkedList<FileModel> files = new LinkedList<FileModel>();
		for (FileModel file : this.files) {
			if (file.getFileName() == name) {
				files.add(file);
			}
		}
		return files;
	}

	@Override
	public LinkedList<FileModel> getFiles(int beginSize, int endSize) {
		LinkedList<FileModel> files = new LinkedList<FileModel>();
		for (FileModel file : this.files) {
			if (file.getSize() >= beginSize && file.getSize() <= endSize) {
				files.add(file);
			}
		}
		return files;
	}
}
