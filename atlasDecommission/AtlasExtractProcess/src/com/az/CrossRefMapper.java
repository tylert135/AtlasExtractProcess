package com.az;

import java.util.ArrayList;

public class CrossRefMapper {
	private String[] csvData = null;
	private boolean archived = false;
	private String crossReferences = "";
	private ArrayList <String> crossRefID = null;
	ArrayList<FileLog> fileLogData = null;
	
	
	public String getCrossReferences() {
		return crossReferences;
	}
	public void setCrossReferences(String crossReferences) {
		this.crossReferences = crossReferences;
	}
	public boolean getArchived() {
		return archived;
	}
	public void setArchived(boolean archived) {
		this.archived = archived;
	}
	public String[] getCsvData() {
		return csvData;
	}
	public void setCsvData(String[] csvData) {
		this.csvData = csvData;
	}
	
	public ArrayList<String> getCrossRefID() {
		return crossRefID;
	}
	public void setCrossRefID(ArrayList<String> crossRefID) {
		this.crossRefID = crossRefID;
	}
	
	public ArrayList<FileLog> getFileLogData() {
		return fileLogData;
	}
	public void setFileLogData(ArrayList<FileLog> fileLogData) {
		this.fileLogData = fileLogData;
	}
	public CrossRefMapper(String[] csvData) {
		super();
		this.csvData = csvData;
	}
	
	
}
