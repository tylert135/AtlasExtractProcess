package com.az;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class MoveFiles {

	/**
	 * @param args
	 * @param prop
	 * @param key_hMap
	 * @param bw
	 * @param checkFilesNotFound
	 * @param bwSummary
	 * @return
	 */
	public static HashMap<String, CrossRefMapper> run(String[] args, Config prop, HashMap<String, CrossRefMapper> key_hMap, 
			BufferedWriter bw, boolean checkFilesNotFound, BufferedWriter bwSummary) {
		/*
		 * args[0] - type: asset or project
		 * args[1] - LOG encoder: 1.e. UTF-8 or UTF-16
		 * 
		 */

		LogOutput.outBar(bw);
						
		String slash1="\\";
		String slash="\\\\";
        ArrayList<String[]> errList = new ArrayList<String[]>();

		/*
         *  Load log file data.
         *  
         *  Result:
         *  	Files are physically moved
         *  
         */
		// Files
		String fileTypeDir = null;
		String fileTypeArchive = "";
		if (!isAsset(args[0]) && !isProject(args[0]))
			return key_hMap;
		
		String logFilename=null;
	    if (isAsset(args[0])) {
	    	fileTypeDir = "Asset";
	    	if (isArchive(args[0])) {
	    		fileTypeArchive = " Archive";
	    		logFilename = prop.getAssetArchiveExtractLog();
	    	} else {
	    		logFilename = prop.getAssetExtractLog();
	    	}
	    }
	    else if (isProject(args[0])) {
	    	fileTypeDir = "Project";
	    	if (isArchive(args[0])) {
	    		fileTypeArchive = " Archive";
	    		logFilename = prop.getProjectArchiveExtractLog();
	    	} else {
	    		logFilename = prop.getProjectExtractLog();
	    	}
	    }
	    LogOutput.out(bw, "Moving " + fileTypeDir + fileTypeArchive + "s\n");

		File file = new File(logFilename);
		String[] tokens = null;
		String[] fileTokens = null;
	    try {

	        Scanner logFile = new Scanner(file,args[1]);

	        int totFiles=0, totIds=0, errs=0;
			while (logFile.hasNextLine()) {
				String s = logFile.nextLine();
				if (s.startsWith("Getting files for Id:")) {
					String delims = "[ ]+"; // parse asset ID
					tokens = s.split(delims);
					totIds++;
				} else if (s.contains("Error occurred") ) {
					String errMsg = null;
					if (s.contains("Error occurredMessage:") ) {
						
						errMsg =  "Error: " + s;
					} else {
						errMsg =  "Error: " + s + logFile.nextLine();
					}
						LogOutput.out(bw, errMsg);
						String[] ss={tokens[4],errMsg};
						errList.add(ss);
						errs++;
				} else if (s.startsWith("Copy location:")) {
					
				    File dir = new File(prop.getCopyToDir() + fileTypeDir +"s" + slash1 + tokens[4]);
				    fileTokens = s.substring(15).split(slash);
				    String assetFile =fileTokens[fileTokens.length - 1];
				    
				    String moveFromDir=null;
				    if (isAsset(args[0])) {
				    	if (isArchive(args[0]))
				    		moveFromDir = prop.getCopyFromDirAssetArchive();
				    	else
				    		moveFromDir = prop.getCopyFromDirAsset();
				    }
				    else if (isProject(args[0])) {
				    	if (isArchive(args[0]))
				    		moveFromDir = prop.getCopyFromDirProjectArchive();
				    	else
				    		moveFromDir = prop.getCopyFromDirProject();
				    }

				    String moveFromStr = moveFromDir + assetFile;
				    String targetStr = prop.getCopyToDir() + fileTypeDir +"s" + slash1 + tokens[4] + slash1 + assetFile;

				    /***************************************************************************************************
				     * 
				     * 						Reverse Move: ONLY USED FOR TESTING
				     * 
				     ***************************************************************************************************/				    
				    //String targetStr = moveFromDir + assetFile;
				    //String moveFromStr = prop.getCopyToDir() + fileTypeDir +"s" + slash1 + tokens[4] + slash1 + assetFile;
				    /***************************************************************************************************/
				    
			        Path movefrom = FileSystems.getDefault().getPath(moveFromStr);
			        Path target = FileSystems.getDefault().getPath(targetStr);

			        dir.mkdir();
			        moveFile(movefrom, target, assetFile, bw, "M");
			        LogOutput.out(bw, "Move file: " + moveFromStr);
			        LogOutput.out(bw, "       to: " + targetStr);
			        
					totFiles++;
					key_hMap = addFileToList(key_hMap, tokens[4], s.substring(15), assetFile);
				}
			}
			
			LogOutput.out(bw, bwSummary, "\nTotal " + fileTypeDir + fileTypeArchive + " Files Found: "+totFiles);
			LogOutput.out(bw, bwSummary, "Total " + fileTypeDir + fileTypeArchive +" File Ids Processed: " + totIds);
			LogOutput.out(bw, bwSummary, "Total " + fileTypeDir + fileTypeArchive +" File Errors: "+errs);
			for (String[] ss : errList) {
				LogOutput.out(bw, bwSummary, "\t"+fileTypeDir+": "+ss[0]+" - "+ss[1]);
			}
			
			if (checkFilesNotFound) {
				int idsWithNoFilesFoundCtr = 0;
				int idsWithFilesFoundCtr = 0;
				
				LogOutput.out(bw,  bwSummary, fileTypeDir  + fileTypeArchive +"'s With No File(s): ");
				if (isAsset(args[0]) || isProject(args[0])) {
					for (String key : key_hMap.keySet()) {
						if (key_hMap.get(key).getFileLogData() == null) {
							idsWithNoFilesFoundCtr++;
							String noFileFound = "NoFileFound.txt";
							LogOutput.out(bw,  bwSummary,"\tNo " + fileTypeDir  + fileTypeArchive +" File(s) found for: " + key);
							if ("944516".equals(key))
								System.out.println("test");
							
							new File(prop.getCopyToDir() + fileTypeDir +"s" + slash1 + key).mkdir();
							moveFile(FileSystems.getDefault().getPath(prop.getJavaFolder() + noFileFound), 
									FileSystems.getDefault().getPath(prop.getCopyToDir() + fileTypeDir +"s" + slash1 + key + slash1 + noFileFound),
									noFileFound, bw, "C");
							key_hMap = addFileToList(key_hMap, key, prop.getJavaFolder() + fileTypeDir +"s" + slash1 + key + slash1 + noFileFound, 
									noFileFound);
						}
						else
							idsWithFilesFoundCtr++;
					}
					LogOutput.out(bw, bwSummary, "***\nTotal ID " + fileTypeDir  + fileTypeArchive +" With Files Found: "+ idsWithFilesFoundCtr);
					LogOutput.out(bw, bwSummary, "Total ID " + fileTypeDir  + fileTypeArchive +" With No Files Found: "+ idsWithNoFilesFoundCtr);
				}
			}

	        logFile.close();
	    } 
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
		}		
	    return key_hMap;
	}
	
	private static HashMap<String, CrossRefMapper> addFileToList (HashMap<String, CrossRefMapper> key_hMap,
			String key, String fileSource, String assetFile) {
		CrossRefMapper data = key_hMap.get(key);
		if (data == null) {
			return key_hMap;
		}

		if (data.getFileLogData()==null) {
			data.setFileLogData(new ArrayList<FileLog>());
		}
	    for(FileLog fl : data.getFileLogData()){
	        if(fl.getFileName() != null && StringUtils.containsIgnoreCase(fl.getFileName(), assetFile))
	        	return key_hMap;
	    }
		data.getFileLogData().add(new FileLog(key, assetFile, fileSource));
		
		return key_hMap;
	}
	
	private static void moveFile(Path movefrom, Path target, String assetFile, BufferedWriter bw, String moveType) {
        try {
        	if ("C".equals(moveType))
        		Files.copy(movefrom, target, StandardCopyOption.REPLACE_EXISTING);
        	else if ("M".equals(moveType))
        		Files.move(movefrom, target, StandardCopyOption.REPLACE_EXISTING);
        	else
        		return;
        	
	    } catch (NoSuchFileException e) {
	    	LogOutput.out(bw, "File previously moved: " + movefrom);
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
	
	private static boolean isAsset(String type) {
		if (type != null && type.startsWith("asset"))
			return true;
		else
			return false;
	}
	
	private static boolean isProject(String type) {
		if (type != null && type.startsWith("project"))
			return true;
		else
			return false;
	}
	
	private static boolean isArchive(String type) {
		if (type != null && type.endsWith("Archive"))
			return true;
		else
			return false;
	}
}
