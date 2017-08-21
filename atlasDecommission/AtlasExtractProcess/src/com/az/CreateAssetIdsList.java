package com.az;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class CreateAssetIdsList {

	public static void run(String[] args, Config prop, BufferedWriter bw, HashMap<String, CrossRefMapper> assetKey_hMap, BufferedWriter bwSummary) {
		
		int ASSET_ID = GetFieldValue.name(args[2], "DIGITAL_ASSET_ID");
		int ASSET_ARCHIVE = GetFieldValue.name(args[2], "ASSETARCHIVEFLAG");
		
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
			
			fwTXT = new FileWriter(prop.getAssetIDtext());
			bwTXT = new BufferedWriter(fwTXT);
			
			fwTXTarchive = new FileWriter(prop.getArchiveAssetIDtext());
			bwTXTarchive = new BufferedWriter(fwTXTarchive);

		} catch (IOException	ex) {
			ex.printStackTrace();
		}
		
        /*
         *  Create assetID.txt
         *  
         *  Result:
         *  	ArrayList<String[]> assetCSVList
         *  
         */
		int as=0;
		int nonArchiveAs=0;
		int archiveAs=0;

		// Get assets
		String comma = "";
		String commaArchive = "";
		try {
			LogOutput.out(bw, "***************************************\nAsset ID Processing... \n");
			for (String key : assetKey_hMap.keySet()) {
				as++;
				CrossRefMapper assetData = assetKey_hMap.get(key);
				String refs = (assetData.getCrossReferences().length()==0?"None Found":
					assetData.getCrossReferences().substring(0, assetData.getCrossReferences().length()-1));
				if (assetData.getCsvData()[ASSET_ARCHIVE].equals("Yes")) {
					archiveAs++;
					//Asset Data
					bwTXTarchive.write(commaArchive + assetData.getCsvData()[ASSET_ID]);
					commaArchive=",";
				} else {
					nonArchiveAs++;
					//Asset Data
					bwTXT.write(comma + assetData.getCsvData()[ASSET_ID]);
					comma=",";
				}
				LogOutput.out(bw, "Asset ID: " + assetData.getCsvData()[ASSET_ID] + "\tRelated Projects: [" +
						refs + "]");
			}
			LogOutput.out(bw, bwSummary, "Total Asset IDs: "+as);
			LogOutput.out(bw, bwSummary, "Total Non-Archived Asset IDs: "+nonArchiveAs);
			LogOutput.out(bw, bwSummary, "Total Archived Asset IDs: "+archiveAs);

			bwTXT.close();
			bwTXTarchive.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}

	}	

}
