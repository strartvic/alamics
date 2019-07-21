package alarms.test.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;

public class FileModel {

	private String fileName;

	private String fileType;

	private Instant date;

	private byte[] bytes;

	public FileModel(String filePath) throws IOException {
		File file = new File(filePath);
		bytes = new byte[(int) (file.length())];
		try (BufferedInputStream bufInStr = new BufferedInputStream(new FileInputStream(filePath));) {
			bufInStr.read(bytes);
			date = Instant.now();
			fileName = file.getName().substring(0, file.getName().lastIndexOf('.'));
			fileType = file.getName().substring(file.getName().lastIndexOf('.') + 1);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException();
		}
	}

	public void save(String dirPath) {
		if (dirPath == null || dirPath.isEmpty()) {
			return;
		}
		File dir = new File(dirPath);
		dir.mkdirs();
		if (!dir.isDirectory()) {
			return;
		}
		File newFile = new File(new StringBuilder().append(dirPath).append("\\").append(fileName).append(".")
				.append(fileType).toString());
		try {
			newFile.createNewFile();
			try (BufferedOutputStream bufInStr = new BufferedOutputStream(new FileOutputStream(newFile));) {
				bufInStr.write(bytes);
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(bytes);
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((fileType == null) ? 0 : fileType.hashCode());
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
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (fileType == null) {
			if (other.fileType != null)
				return false;
		} else if (!fileType.equals(other.fileType))
			return false;
		return true;
	}

	public String getFileName() {
		return fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public Instant getDate() {
		return date;
	}

	public int getSize() {
		return bytes.length;
	}

}
