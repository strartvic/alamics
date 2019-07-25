package alarms.test.service;

import alarms.test.model.FileModel;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashSet;
import java.util.LinkedList;

public class FileStorageServiceImplTest {

    @Test
    public void add() {
        FileStorageService service = new FileStorageServiceImpl();
        try {
            MultipartFile mFile = new MockMultipartFile("file", "orig.type", null, "bar".getBytes());
            FileModel file1 = new FileModel(mFile);
            FileModel file2 = new FileModel(mFile);
            Assert.assertTrue(service.add(file1));
            Assert.assertFalse(service.add(file2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete() {
        FileStorageService service = new FileStorageServiceImpl();
        try {
            MultipartFile mFile = new MockMultipartFile("file", "orig.type", null, "bar".getBytes());
            FileModel file1 = new FileModel(mFile);
            service.add(file1);
            Assert.assertEquals(file1, service.get(file1.getGuid()));
            Assert.assertTrue(service.delete(file1.getGuid()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void get() {
        FileStorageService service = new FileStorageServiceImpl();
        try {
            MultipartFile mFile = new MockMultipartFile("file", "orig.type", null, "bar".getBytes());
            FileModel file1 = new FileModel(mFile);
            service.add(file1);
            Assert.assertEquals(file1, service.get(file1.getGuid()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getFileNames() {
        FileStorageService service = new FileStorageServiceImpl();
        try {
            MultipartFile mFile1 = new MockMultipartFile("file", "orig1.type", null, "bar1".getBytes());
            FileModel file1 = new FileModel(mFile1);
            MultipartFile mFile2 = new MockMultipartFile("file", "orig2.type", null, "bar2".getBytes());
            FileModel file2 = new FileModel(mFile2);
            service.add(file1);
            service.add(file2);
            LinkedList<String> names = service.getFileNames();
            Assert.assertArrayEquals(new String[] {file1.getName(), file2.getName()}, names.toArray(new String[names.size()]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getFiles() {
        FileStorageService service = new FileStorageServiceImpl();
        try {
            MultipartFile mFile1 = new MockMultipartFile("file", "orig1.type", null, "bar1".getBytes());
            FileModel file1 = new FileModel(mFile1);
            MultipartFile mFile2 = new MockMultipartFile("file", "orig2.type", null, "bar2".getBytes());
            FileModel file2 = new FileModel(mFile2);
            service.add(file1);
            service.add(file2);
            LinkedHashSet<FileModel> files = service.getFiles();
            Assert.assertArrayEquals(new FileModel[] {file1, file2}, files.toArray(new FileModel[files.size()]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getFilesByName() {
        FileStorageService service = new FileStorageServiceImpl();
        try {
            MultipartFile mFile1 = new MockMultipartFile("file", "orig1.type", null, "bar1".getBytes());
            FileModel file1 = new FileModel(mFile1);
            MultipartFile mFile2 = new MockMultipartFile("file", "orig1.type", null, "bar23".getBytes());
            FileModel file2 = new FileModel(mFile2);
            service.add(file1);
            service.add(file2);
            LinkedList<FileModel> files = service.getFiles(file1.getName());
            Assert.assertArrayEquals(new FileModel[] {file1, file2}, files.toArray(new FileModel[files.size()]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getFilesBySize() {
        FileStorageService service = new FileStorageServiceImpl();
        try {
            MultipartFile mFile1 = new MockMultipartFile("file", "orig1.type", null, "bar1".getBytes());
            FileModel file1 = new FileModel(mFile1);
            MultipartFile mFile2 = new MockMultipartFile("file", "orig2.type", null, "bar23123213213213".getBytes());
            FileModel file2 = new FileModel(mFile2);
            service.add(file1);
            service.add(file2);
            LinkedList<FileModel> files = service.getFiles(0, file2.getSize());
            Assert.assertArrayEquals(new FileModel[] {file1, file2}, files.toArray(new FileModel[files.size()]));
            Assert.assertEquals(file1, service.getFiles(0, file1.getSize()).getFirst());
            Assert.assertEquals(file2, service.getFiles(file2.getSize(), file2.getSize() + 100).getFirst());
            Assert.assertTrue(service.getFiles(file2.getSize()+10, file2.getSize() + 100).isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}