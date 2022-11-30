package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.files.Document;
import com.example.demo.repo.DocumentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileTests {

	@Autowired
	DocumentRepository repo;
	
	@Test
	void testSaveFile() throws IOException {
		Document doc = new Document();
		doc.setId(1);
		doc.setFilename("Test.pdf");
		
		File file = new File("C:\\Users\\kcarlso1\\Documents\\Test.pdf");
		byte fileContents[] = new byte[(int) file.length()];
		FileInputStream inputStream = new FileInputStream(file);
		inputStream.read(fileContents);
		
		doc.setFile(fileContents);
		repo.save(doc);
		inputStream.close();
	}
	
	@Test
	void testReadFile() throws IOException {
		// Get a document from the DB
		Document doc = repo.findById(1L).get();
		
		// Where to download file
		File file = new File("C:\\Users\\kcarlso1\\Documents\\"+doc.getFilename());
		
		// Save file to local system
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(doc.getFile());
		fos.close();
		
	}
	
}
