package alarms.test.model;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class FileModelTest {

    @Test
    public void testFileModel() {
        try {
            MultipartFile mFile = new MockMultipartFile("file", "orig.type", null, "bar".getBytes());
            FileModel file = new FileModel(mFile);
            assertEquals("orig", file.getName());
            assertEquals("type", file.getType());
            assertEquals(mFile.getBytes(), file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testHashCode() {
        try {
            MultipartFile mFile = new MockMultipartFile("file", "orig.type", null, "bar".getBytes());
            FileModel file1 = new FileModel(mFile);
            FileModel file2 = new FileModel(mFile);
            Map<FileModel, String> map = new HashMap<>();
            map.put(file1, "expected");
            Assert.assertEquals("expected", map.get(file2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testEquals() {
        try {
            MultipartFile mFile = new MockMultipartFile("file", "orig.type", null, "bar".getBytes());
            FileModel file1 = new FileModel(mFile);
            FileModel file2 = new FileModel(mFile);
            Assert.assertEquals(file1, file2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}