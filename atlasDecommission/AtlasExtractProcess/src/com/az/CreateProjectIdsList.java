package com.az;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class CreateProjectIdsList {


	/**
	 * Creates project Id and project archive Id list text files from the resultant
	 * SQL output CSV file.
	 * 
	 * @param args[]
	 * 		0
	 * 		1
	 * 		2
	 * @param prop
	 * @param bw
	 * @param projectKey_hMap
	 * @param bwSummary
	 */
	public static void run(String[] args, Config prop, BufferedWriter bw, HashMap<String, CrossRefMapper> projectKey_hMap, BufferedWriter bwSummary) {

		int PROJECT_ID = GetFieldValue.name(args[2], "PROJECT_ID");
		int PROJECT_ARCHIVE = GetFieldValue.name(args[2], "ATTACHMENTARCHIVEFLAG");
		
		/*
		 * Creates assetId.txt and assetINclause.txt used for project query SQL
		 */
		
		// Initialize some stuff
		
		// File read/write
		FileWriter fwTXT = null;
		BufferedWriter bwTXT = null;
		
		FileWriter fwTXTarchive = null;
		BufferedWriter bwTXTarchive = null;
		
		/*
		 *Initialize files
		 */
		try {
			
			fwTXT = new FileWriter(prop.getProjectIDtext());
			bwTXT = new BufferedWriter(fwTXT);
			
			fwTXTarchive = new FileWriter(prop.getArchiveProjectIDtext());
			bwTXTarchive = new BufferedWriter(fwTXTarchive);

		} catch (IOException	ex) {
			ex.printStackTrace();
		}

		
        /*
         *  Create projectID.txt
         *  
         *  Result:
         *  	ArrayList<String[]> projectCSVList
         *  
         */
		int pj=0;
		int nonArchivePJ=0;
		int archivePJ=0;
		// Get projects
		String comma = "";
		String commaArchive = "";
		try {
			LogOutput.out(bw, "***************************************\nProject ID Processing...\n");
			for (String key : projectKey_hMap.keySet()) {
				pj++;
				CrossRefMapper projectData = projectKey_hMap.get(key);
				String refs = (projectData.getCrossReferences().length()==0?"None Found":
					projectData.getCrossReferences().substring(0, projectData.getCrossReferences().length()-1));
				if ("Yes".equals(projectData.getCsvData()[PROJECT_ARCHIVE])) {
					//project Data
					archivePJ++;
					bwTXTarchive.write(commaArchive + projectData.getCsvData()[PROJECT_ID]);
					commaArchive=",";
				} else {
					//project Data
					nonArchivePJ++;
					bwTXT.write(comma + projectData.getCsvData()[PROJECT_ID]);
					comma=",";
				}
				LogOutput.out(bw, "Project: " + projectData.getCsvData()[PROJECT_ID] + "\tRelated Assets: [" +
						refs + "]");

			}
			LogOutput.out(bw, bwSummary, "Total Project IDs: "+pj);
			LogOutput.out(bw, bwSummary, "Total Non-Archived Project IDs: "+nonArchivePJ);
			LogOutput.out(bw, bwSummary, "Total Archived Project IDs: "+archivePJ);
			
			bwTXT.close();
			bwTXTarchive.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}

	}	

}
