package alarms.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

//fixme тесты потом посмотрим) когда сервис сделаешь)))
public class FileModelTest {

	private static File xls;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		xls = File.createTempFile("temp", ".xls");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		xls.delete();
	}

	@Test
	public void testFileModel() {
		try {
			FileModel file = new FileModel(xls.getAbsolutePath());
			assertNotNull(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFileModelSave() {
		try {
			FileModel file = new FileModel(xls.getAbsolutePath());
			file.save(new StringBuilder().append(xls.getParent()).append("\\").append("temp").toString());

			File xls2 = new File(new StringBuilder().append(xls.getParent()).append("\\").append("temp\\")
					.append(xls.getName()).toString());

			assertTrue(xls.length() == xls2.length());

			xls2.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFileModelEquals() {
		try {
			FileModel file = new FileModel(xls.getAbsolutePath());
			FileModel file2 = new FileModel(xls.getAbsolutePath());
			file2.setDate(file.getDate());
			assertEquals(file, file2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFileModelHash() {
		try {
			FileModel file = new FileModel(xls.getAbsolutePath());
			FileModel file2 = new FileModel(xls.getAbsolutePath());
			assertNotEquals(file.hashCode(), file2.hashCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
