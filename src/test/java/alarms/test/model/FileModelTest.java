package alarms.test.model;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class FileModelTest {

	@Test
	public void testFileModel() {
		try {
			FileModel file = new FileModel("D:\\01. Mamma Mia.mp3");
			file.save("D:\\test");
			assertNotNull(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
