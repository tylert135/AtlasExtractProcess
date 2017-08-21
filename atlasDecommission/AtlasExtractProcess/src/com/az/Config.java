package com.az;

import java.io.File;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class Config {
	// Properties
	Configurations configs = new Configurations();
	Configuration config = null;

	String assetExtractLog=null;
	String assetArchiveExtractLog=null;
	String projectExtractLog=null;
	String projectArchiveExtractLog=null;
	
	String processLog = null;

	String queryResultCSV = null;
	String manifestZipProcessCSV = null;
	String manifestZipOutput = null;

	String assetIDtext=null;
	String archiveAssetIDtext=null;
	String projectIDtext=null;
	String archiveProjectIDtext=null;
	
	String manifestFile = null;
	
	String copyToDir = null;
	
	String destinationFolder = null;
	String processFolder = null;
	String zipExeFolder = null;
	String zipOutFolder=null;
	String zipSourceFolder=null;

	String javaFolder = null;
	
	String copyFromDirAsset = null;
	String copyFromDirAssetArchive = null;
	String copyFromDirProject = null;
	String copyFromDirProjectArchive = null;

	public Config() {
		super();
		try {
			// Properties files
		    config = configs.properties(new File("./Configuration.properties"));


			// Get property values 
			assetExtractLog=config.getString("assetExtractLog");
			assetArchiveExtractLog=config.getString("assetArchiveExtractLog");
			projectExtractLog=config.getString("projectExtractLog");
			projectArchiveExtractLog=config.getString("projectArchiveExtractLog");

			processLog = config.getString("processLog");
			queryResultCSV = config.getString("queryResultCSV");
			manifestZipProcessCSV = config.getString("manifestZipProcessCSV");
			zipExeFolder = config.getString("7zip.folder");
			zipOutFolder = config.getString("zipOutput.folder");
			zipSourceFolder = config.getString("zipSource.folder");
			manifestZipOutput = config.getString("manifestZipOutput");
			
			assetIDtext=config.getString("assetIDtext");
			archiveAssetIDtext=config.getString("archiveAssetIDtext");
			projectIDtext=config.getString("projectIDtext");
			archiveProjectIDtext=config.getString("archiveProjectIDtext");

			manifestFile = config.getString("manifestFile");
			copyToDir = config.getString("copyToDir");

			destinationFolder = config.getString("destination.folder");
			processFolder = config.getString("process.folder");
			
			javaFolder = config.getString("java.folder");

			copyFromDirAsset = config.getString("copyFromDirAsset");
			copyFromDirAssetArchive = config.getString("copyFromDirAssetArchive");
			copyFromDirProject = config.getString("copyFromDirProject");
			copyFromDirProjectArchive = config.getString("copyFromDirProjectArchive");

			
		} catch (ConfigurationException ex) {
			ex.printStackTrace();
		}
	}

	public Configurations getConfigs() {
		return configs;
	}

	public void setConfigs(Configurations configs) {
		this.configs = configs;
	}

	public Configuration getConfig() {
		return config;
	}

	public void setConfig(Configuration config) {
		this.config = config;
	}

	public String getAssetExtractLog() {
		return assetExtractLog;
	}

	public void setAssetExtractLog(String assetExtractLog) {
		this.assetExtractLog = assetExtractLog;
	}

	public String getAssetArchiveExtractLog() {
		return assetArchiveExtractLog;
	}

	public void setAssetArchiveExtractLog(String assetArchiveExtractLog) {
		this.assetArchiveExtractLog = assetArchiveExtractLog;
	}

	public String getProjectExtractLog() {
		return projectExtractLog;
	}

	public void setProjectExtractLog(String projectExtractLog) {
		this.projectExtractLog = projectExtractLog;
	}

	public String getProjectArchiveExtractLog() {
		return projectArchiveExtractLog;
	}

	public void setProjectArchiveExtractLog(String projectArchiveExtractLog) {
		this.projectArchiveExtractLog = projectArchiveExtractLog;
	}

	public String getProcessLog() {
		return processLog;
	}

	public void setProcessLog(String processLog) {
		this.processLog = processLog;
	}

	public String getQueryResultCSV() {
		return queryResultCSV;
	}

	public void setQueryResultCSV(String queryResultCSV) {
		this.queryResultCSV = queryResultCSV;
	}

	public String getAssetIDtext() {
		return assetIDtext;
	}

	public void setAssetIDtext(String assetIDtext) {
		this.assetIDtext = assetIDtext;
	}

	public String getArchiveAssetIDtext() {
		return archiveAssetIDtext;
	}

	public void setArchiveAssetIDtext(String archiveAssetIDtext) {
		this.archiveAssetIDtext = archiveAssetIDtext;
	}

	public String getProjectIDtext() {
		return projectIDtext;
	}

	public void setProjectIDtext(String projectIDtext) {
		this.projectIDtext = projectIDtext;
	}

	public String getArchiveProjectIDtext() {
		return archiveProjectIDtext;
	}

	public void setArchiveProjectIDtext(String archiveProjectIDtext) {
		this.archiveProjectIDtext = archiveProjectIDtext;
	}

	public String getManifestFile() {
		return manifestFile;
	}

	public void setManifestFile(String manifestFile) {
		this.manifestFile = manifestFile;
	}

	public String getCopyToDir() {
		return copyToDir;
	}

	public void setCopyToDir(String copyToDir) {
		this.copyToDir = copyToDir;
	}

	public String getDestinationFolder() {
		return destinationFolder;
	}

	public void setDestinationFolder(String destinationFolder) {
		this.destinationFolder = destinationFolder;
	}

	public String getProcessFolder() {
		return processFolder;
	}

	public void setProcessFolder(String processFolder) {
		this.processFolder = processFolder;
	}

	public String getJavaFolder() {
		return javaFolder;
	}

	public void setJavaFolder(String javaFolder) {
		this.javaFolder = javaFolder;
	}

	public String getCopyFromDirAsset() {
		return copyFromDirAsset;
	}

	public void setCopyFromDirAsset(String copyFromDirAsset) {
		this.copyFromDirAsset = copyFromDirAsset;
	}

	public String getCopyFromDirAssetArchive() {
		return copyFromDirAssetArchive;
	}

	public void setCopyFromDirAssetArchive(String copyFromDirAssetArchive) {
		this.copyFromDirAssetArchive = copyFromDirAssetArchive;
	}

	public String getCopyFromDirProject() {
		return copyFromDirProject;
	}

	public void setCopyFromDirProject(String copyFromDirProject) {
		this.copyFromDirProject = copyFromDirProject;
	}

	public String getCopyFromDirProjectArchive() {
		return copyFromDirProjectArchive;
	}

	public void setCopyFromDirProjectArchive(String copyFromDirProjectArchive) {
		this.copyFromDirProjectArchive = copyFromDirProjectArchive;
	}

	public String getManifestZipProcessCSV() {
		return manifestZipProcessCSV;
	}

	public void setManifestZipProcessCSV(String manifestZipProcessCSV) {
		this.manifestZipProcessCSV = manifestZipProcessCSV;
	}

	public String getZipExeFolder() {
		return zipExeFolder;
	}

	public void setZipExeFolder(String zipExeFolder) {
		this.zipExeFolder = zipExeFolder;
	}

	public String getZipOutFolder() {
		return zipOutFolder;
	}

	public void setZipOutFolder(String zipOutFolder) {
		this.zipOutFolder = zipOutFolder;
	}

	public String getZipSourceFolder() {
		return zipSourceFolder;
	}

	public void setZipSourceFolder(String zipSourceFolder) {
		this.zipSourceFolder = zipSourceFolder;
	}

	public String getManifestZipOutput() {
		return manifestZipOutput;
	}

	public void setManifestZipOutput(String manifestZipOutput) {
		this.manifestZipOutput = manifestZipOutput;
	}



}
