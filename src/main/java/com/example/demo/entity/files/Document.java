package com.example.demo.entity.files;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * Example of Entity for a DB table with a BLOB/CLOB field
 * @author kcarlso1
 *
 */
@Entity
public class Document {
	
	@Id
	private long id;
	private String filename;
	@Lob
	private byte[] file;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	
	
	
}
