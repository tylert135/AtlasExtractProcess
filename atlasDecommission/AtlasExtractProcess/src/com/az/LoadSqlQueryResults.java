package com.az;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.opencsv.CSVReader;

public class LoadSqlQueryResults {
	
	private static int ASSET_ID = EMEAsqlFields.DIGITAL_ASSET_ID;
	private static int PROJECT_ID = EMEAsqlFields.PROJECT_ID;
	private static int ASSET_ARCHIVE = EMEAsqlFields.ASSETARCHIVEFLAG;
	private static int PROJECT_ARCHIVE = EMEAsqlFields.ATTACHMENTARCHIVEFLAG;
	
	public static ArrayList<HashMap<String, CrossRefMapper>> run (Config prop, String encoder, char delimiter, BufferedWriter bw, 
			String instance, BufferedWriter bwSummary) {
		
		HashMap<String, CrossRefMapper> assetKey_hMap = new HashMap<String, CrossRefMapper>();
		HashMap<String, CrossRefMapper> projectKey_hMap = new HashMap<String, CrossRefMapper>();
		

		String[] csvData = null;
		int queryResultsCtr=0;
		try {
			CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream(prop.getQueryResultCSV()), encoder), delimiter);
			csvData = csvReader.readNext();
			while ((csvData = csvReader.readNext()) != null) {
				queryResultsCtr++;

				/*
				 * Assets
				 */
				if (!"NULL".equals(csvData[ASSET_ID])) {
					CrossRefMapper assetData = assetKey_hMap.get(csvData[ASSET_ID]); // See if asset already exists
					if (assetData== null) {
						// add asset to asset Map
						assetData = new CrossRefMapper(csvData);
						assetData.setCrossRefID(new ArrayList <String>());
						assetData.getCrossRefID().add(csvData[PROJECT_ID]);
						assetData.setArchived("Yes".equals(csvData[ASSET_ARCHIVE]));
						assetData.setCrossReferences(assetData.getCrossReferences()+csvData[PROJECT_ID]+";");
						assetKey_hMap.put(csvData[ASSET_ID], assetData);
					} else {
						if (!assetData.getCrossRefID().contains(csvData[PROJECT_ID])) {
							assetData.getCrossRefID().add(csvData[PROJECT_ID]);
							assetData.setCrossReferences(assetData.getCrossReferences()+csvData[PROJECT_ID]+";");
						}
					}
				}
				
				/*
				 * Projects
				 */
				if (!"NULL".equals(csvData[PROJECT_ID])) {
					CrossRefMapper projectData = projectKey_hMap.get(csvData[PROJECT_ID]); // See if project already exists
					if (projectData== null) {
						// add project to project Map
						projectData = new CrossRefMapper(csvData);
						projectData.setCrossRefID(new ArrayList <String>());
						projectData.getCrossRefID().add(csvData[ASSET_ID]);
						projectData.setArchived("Yes".equals(csvData[PROJECT_ARCHIVE]));
						projectData.setCrossReferences(projectData.getCrossReferences()+csvData[ASSET_ID]+";");
						projectKey_hMap.put(csvData[PROJECT_ID], projectData);
					} else {
						if (!projectData.getCrossRefID().contains(csvData[ASSET_ID])) {
							projectData.getCrossRefID().add(csvData[ASSET_ID]);
							projectData.setCrossReferences(projectData.getCrossReferences()+csvData[ASSET_ID]+";");
						}
					}
				}

			}
			
			/*
			for (String key : assetKey_hMap.keySet()) {
				CrossRefMapper mapData = assetKey_hMap.get(key);
				String refs = (mapData.getCrossReferences().length()==0?"":
					mapData.getCrossReferences().substring(0, mapData.getCrossReferences().length()-1));
				LogOutput.out(bw, "Asset: " + mapData.getCsvData()[ASSET_ID] + " Projects: [" + refs + "]");
			}

			for (String key : projectKey_hMap.keySet()) {
				CrossRefMapper mapData = projectKey_hMap.get(key);
				String refs = (mapData.getCrossReferences().length()==0?"":
					mapData.getCrossReferences().substring(0, mapData.getCrossReferences().length()-1));
				LogOutput.out(bw, "Project: " + mapData.getCsvData()[PROJECT_ID] + " Assets: [" + refs + "]");
			}
			*/
			
			LogOutput.out(bw, bwSummary, "Total CSV Records: "+queryResultsCtr);
			LogOutput.out(bw, bwSummary, "Total Asset Records: " + assetKey_hMap.size());
			LogOutput.out(bw, bwSummary, "Total Project Records: " + projectKey_hMap.size());
			csvReader.close();
		} catch (IOException e) {
			System.err.println(e+"\n"+csvData);
		} catch (Exception e) {
			LogOutput.out(bw, e+"\n"+ Arrays.toString(csvData));
			System.err.println(e+"\n"+csvData);
		}
		
		ArrayList<HashMap<String, CrossRefMapper>> hMapArray = new ArrayList<HashMap<String, CrossRefMapper>>();
		hMapArray.add(assetKey_hMap);
		hMapArray.add(projectKey_hMap);
		
		return hMapArray;
	}

}
