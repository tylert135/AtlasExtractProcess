package com.az;

public class FileLog {
	private String id;
	private String fileName;
	private String sourceFile;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSourceFile() {
		return sourceFile;
	}
	public void setSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}
	public FileLog(String id, String fileName, String sourceFile) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.sourceFile = sourceFile;
	}
	
}
