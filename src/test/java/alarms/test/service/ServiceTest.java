package alarms.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import alarms.test.model.FileModel;

public class ServiceTest {

	private static FileModel file, file2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		File temp = File.createTempFile("temp", ".xls");
		file = new FileModel(temp.getAbsolutePath());

		File temp2 = File.createTempFile("temp2", ".xls");
		file2 = new FileModel(temp.getAbsolutePath());
		temp.delete();
		temp2.delete();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}

	@Test
	public void testAdd() {
		IService service = new Service();
		service.add(file);
		service.add(file);
		assertTrue(service.getFiles().size() == 1);
		assertNotNull(service.get(file.hashCode()));
	}

	@Test
	public void testDelete() {
		IService service = new Service();
		service.add(file);
		service.delete(file.hashCode());
		assertTrue(service.getFiles().isEmpty());
	}

	@Test
	public void testGet() {
		IService service = new Service();
		service.add(file);
		assertNotNull(service.get(file.hashCode()));
	}

	@Test
	public void testGetFileNames() {
		IService service = new Service();
		service.add(file);
		assertEquals(service.getFileNames().get(0), file.getFileName());
	}

	@Test
	public void testGetFilesByName() {
		IService service = new Service();
		service.add(file);
		assertNotNull(service.getFiles(file.getFileName()));
	}

	@Test
	public void testGetFilesIntInt() {
		IService service = new Service();
		service.add(file);
		service.add(file2);
		assertFalse(service.getFiles(0, 150000).isEmpty());
	}

}
