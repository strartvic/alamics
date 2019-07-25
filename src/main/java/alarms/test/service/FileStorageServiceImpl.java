package alarms.test.service;

import java.util.LinkedHashSet;
import java.util.LinkedList;

import org.springframework.stereotype.Service;

import alarms.test.model.FileModel;

@Service
public class FileStorageServiceImpl implements FileStorageService {

	private LinkedHashSet<FileModel> files = new LinkedHashSet<FileModel>();

	@Override
	public boolean add(FileModel file) {
		return files.add(file);
	}

	@Override
	public boolean delete(int id) {
		FileModel file = get(id);
		if (file == null) {
			return false;
		}
		return files.remove(file);
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
		LinkedList<String> fileNames = new LinkedList<>();
		for (FileModel file : files) {
			fileNames.add(file.getName());
		}
		return fileNames;
	}

	@Override
	public LinkedHashSet<FileModel> getFiles() {
		return files;
	}

	@Override
	public LinkedList<FileModel> getFiles(String name) {
		LinkedList<FileModel> files = new LinkedList<>();
		for (FileModel file : this.files) {
			if (file.getName().equals(name)) {
				files.add(file);
			}
		}
		return files;
	}

	@Override
	public LinkedList<FileModel> getFiles(int beginSize, int endSize) {
		LinkedList<FileModel> files = new LinkedList<>();
		for (FileModel file : this.files) {
			if (file.getSize() >= beginSize && file.getSize() <= endSize) {
				files.add(file);
			}
		}
		return files;
	}
}
