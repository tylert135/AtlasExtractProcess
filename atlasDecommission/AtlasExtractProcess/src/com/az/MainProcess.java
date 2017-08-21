package com.az;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class MainProcess {

	/**
	 * @param args
	 * 		0	- Process to run: 
	 * 				CreateIdListsEMEA
	 * 				CreateIdListsAPAC
	 * 				MoveFilesCreateManifestEMEA
	 * 				MoveFilesCreateManifestAPAC
	 * 		1	- Unicode Type
	 * 				UTF-8
	 * 		2	- Dilimeter
	 * 				"	"
	 * 				,
	 * 		3	- Unicode Type
	 * 				UTF-8
	 * 		4	- Dilimeter
	 * 				"	"
	 * 				,
	 * 		5	- Unicode Type
	 * 				UTF-8
	 * 		6	- 
	 * 				AtlasApproval
	 * 		7	- Market
	 * 				EMEA
	 * 				APAC
	 * 				US
	 * i.e.
	 * 	CreateIdListsAPAC - CreateIdListsAPAC UTF-8 , UTF-16 "	" UTF-8 APAC
	 *  CreateIdListsEMEA - CreateIdListsAPAC UTF-8 , UTF-16 , UTF-8 EMEA
	 *  MoveFileCreateManifestAPAC - MoveFilesCreateManifestAPAC UTF-8 "	" UTF-16 , UTF-8 AtlasApproval APAC
	 *  MoveFileCreateManifestEMEA - MoveFilesCreateManifestEMEA UTF-8 "	" UTF-16 , UTF-8 AtlasApproval EMEA
	 */
	public static void main(String[] args) {
		String slash="\\";
		Config prop = new Config();
		BufferedWriter bw = null, bwSummary = null;	
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HH mm ss").format(Calendar.getInstance().getTime());
		/*
		 * Output batch file
		 */
		try {
			// Batch file write
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(prop.getDestinationFolder()+"Logs"+slash+args[0]+"_"+timeStamp+".log"), args[1]));
			bwSummary = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(prop.getDestinationFolder()+"Logs"+slash+args[0]+"Summary_"+timeStamp+".log"), args[1]));

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		if ("CreateIdListsEMEA".equals(args[0])) {
			ArrayList<HashMap<String, CrossRefMapper>> hMapArray = LoadSqlQueryResults.run(prop, args[1], args[2].charAt(0), bw, "EMEA", bwSummary);
			String[] params = {args[3], args[4], "EMEA"};
			CreateProjectIdsList.run(params, prop, bw, hMapArray.get(1), bwSummary);
			CreateAssetIdsList.run(params, prop, bw, hMapArray.get(0), bwSummary);
		} else if ("CreateIdListsAPAC".equals(args[0])) {
			ArrayList<HashMap<String, CrossRefMapper>> hMapArray = LoadSqlQueryResults.run(prop, args[1], args[2].charAt(0), bw, "APAC", bwSummary);
			String[] params = {args[3], args[4], "APAC"};
			CreateProjectIdsList.run(params, prop, bw, hMapArray.get(1), bwSummary);
			CreateAssetIdsList.run(params, prop, bw, hMapArray.get(0), bwSummary);
		} else if ("ProcessZipsEMEA".equals(args[0])) {
			ProcessZipFiles.run(prop, args[1], args[2].charAt(0), bw, "EMEA", bwSummary);

		} else if ("MoveFilesCreateManifestEMEA".equals(args[0])) {
			ArrayList<HashMap<String, CrossRefMapper>> hMapArray = LoadSqlQueryResults.run(prop, args[1], args[2].charAt(0), bw, "EMEA", bwSummary);
			String[] assetParams1 = {"asset", args[5]};
			hMapArray.set(0, MoveFiles.run(assetParams1, prop, hMapArray.get(0), bw, false, bwSummary));
			
			String[] assetParams2 = {"assetArchive", args[5]};
			hMapArray.set(0, MoveFiles.run(assetParams2, prop, hMapArray.get(0), bw, true, bwSummary));

			String[] projectParams1 = {"project", args[5]};
			hMapArray.set(1, MoveFiles.run(projectParams1, prop, hMapArray.get(1), bw, false, bwSummary));
			
			String[] projectParams2 = {"projectArchive", args[5]};
			hMapArray.set(1, MoveFiles.run(projectParams2, prop, hMapArray.get(1), bw, true, bwSummary));

			CreateManifest.run(hMapArray.get(0), prop, hMapArray.get(1), bw, args, bwSummary);
			
			LogOutput.out(bw, "**Process Complete**");
		} else if ("MoveFilesCreateManifestAPAC".equals(args[0])) {
			ArrayList<HashMap<String, CrossRefMapper>> hMapArray = LoadSqlQueryResults.run(prop, args[1], args[2].charAt(0), bw, "APAC", bwSummary);
			String[] assetParams1 = {"asset", args[5]};
			hMapArray.set(0, MoveFiles.run(assetParams1, prop, hMapArray.get(0), bw, false, bwSummary));
			
			String[] assetParams2 = {"assetArchive", args[5]};
			hMapArray.set(0, MoveFiles.run(assetParams2, prop, hMapArray.get(0), bw, true, bwSummary));

			String[] projectParams1 = {"project", args[5]};
			hMapArray.set(1, MoveFiles.run(projectParams1, prop, hMapArray.get(1), bw, false, bwSummary));
			
			String[] projectParams2 = {"projectArchive", args[5]};
			hMapArray.set(1, MoveFiles.run(projectParams2, prop, hMapArray.get(1), bw, true, bwSummary));

			CreateManifest.run(hMapArray.get(0), prop, hMapArray.get(1), bw, args, bwSummary);
			
			LogOutput.out(bw, "**Process Complete**");
		} else if ("CreateManifestAPAC".equals(args[0])) {
			ArrayList<HashMap<String, CrossRefMapper>> hMapArray = LoadSqlQueryResults.run(prop, args[1], args[2].charAt(0), bw, "APAC", bwSummary);
			String[] assetParams1 = {"asset", args[5]};
			hMapArray.set(0, MoveFiles.run(assetParams1, prop, hMapArray.get(0), bw, false, bwSummary));
			
			String[] assetParams2 = {"assetArchive", args[5]};
			hMapArray.set(0, MoveFiles.run(assetParams2, prop, hMapArray.get(0), bw, true, bwSummary));

			String[] projectParams1 = {"project", args[5]};
			hMapArray.set(1, MoveFiles.run(projectParams1, prop, hMapArray.get(1), bw, false, bwSummary));
			
			String[] projectParams2 = {"projectArchive", args[5]};
			hMapArray.set(1, MoveFiles.run(projectParams2, prop, hMapArray.get(1), bw, true, bwSummary));

			CreateManifest.run(hMapArray.get(0), prop, hMapArray.get(1), bw, args, bwSummary);
			
			LogOutput.out(bw, "**Process Complete**");
		} else if ("CreateManifestEMEA".equals(args[0])) {
			ArrayList<HashMap<String, CrossRefMapper>> hMapArray = LoadSqlQueryResults.run(prop, args[1], args[2].charAt(0), bw, "EMEA", bwSummary);
			String[] assetParams1 = {"asset", args[5]};
			hMapArray.set(0, GetFileLogData.run(assetParams1, prop, hMapArray.get(0), bw, false, bwSummary));
			
			String[] assetParams2 = {"assetArchive", args[5]};
			hMapArray.set(0, GetFileLogData.run(assetParams2, prop, hMapArray.get(0), bw, true, bwSummary));

			String[] projectParams1 = {"project", args[5]};
			hMapArray.set(1, GetFileLogData.run(projectParams1, prop, hMapArray.get(1), bw, false, bwSummary));
			
			String[] projectParams2 = {"projectArchive", args[5]};
			hMapArray.set(1, GetFileLogData.run(projectParams2, prop, hMapArray.get(1), bw, true, bwSummary));

			CreateManifest.run(hMapArray.get(0), prop, hMapArray.get(1), bw, args, bwSummary);
			
			LogOutput.out(bw, "**Process Complete**");
		}
		/*
		if ("MoveAssetFilesAPAC".equals(args[0])) {
			HashMap<String, CrossRefMapper> assetKey_hMap = LoadAssets.run(prop.getAssetReportCSV(), args[2], args[3].charAt(0), bw);
			String[] params = {args[1], args[6]};
			assetKey_hMap = MoveFiles.run(params, prop, assetKey_hMap, bw, false);
		} else if ("MoveProjectFilesAPAC".equals(args[0])) {
			HashMap<String, CrossRefMapper> assetKey_hMap = LoadAssets.run(prop.getAssetReportCSV(), args[2], args[3].charAt(0), bw);
			HashMap<String, CrossRefMapper> projectKey_hMap = LLoadProjects.run(assetKey_hMap, prop.getProjectQueryCSV(), args[4], args[5].charAt(0), bw);
			String[] params = {args[1], args[6]};
			MoveFiles.run(params, prop, projectKey_hMap, bw, false);
		} else if ("MoveFilesCreateManifestAPAC".equals(args[0])) {
			HashMap<String, CrossRefMapper> assetKey_hMap = LoadAssets.run(prop.getAssetReportCSV(), args[1], args[2].charAt(0), bw);
			String[] assetParams1 = {"asset", args[5]};
			assetKey_hMap = MoveFiles.run(assetParams1, prop, assetKey_hMap, bw, false);
			
			String[] assetParams2 = {"assetArchive", args[5]};
			assetKey_hMap = MoveFiles.run(assetParams2, prop, assetKey_hMap, bw, true);

			HashMap<String, CrossRefMapper> projectKey_hMap = LLoadProjects.run(assetKey_hMap, prop.getProjectQueryCSV(), args[3], args[4].charAt(0), bw);
			String[] projectParams1 = {"project", args[5]};
			projectKey_hMap = MoveFiles.run(projectParams1, prop, projectKey_hMap, bw, false);
			
			String[] projectParams2 = {"projectArchive", args[5]};
			projectKey_hMap = MoveFiles.run(projectParams2, prop, projectKey_hMap, bw, true);
			
			CreateManifest.run(assetKey_hMap, prop, projectKey_hMap, bw);
			
			LogOutput.out(bw, "**Process Complete**");
		} else if ("CreateAssetIdsListAPAC".equals(args[0])) {
			String[] params = {args[1],args[2]};
			CreateAssetIdsList.run(params, prop, bw);
		} else if ("CreateProjectIdsListAPAC".equals(args[0])) {
			String[] params = {args[3],args[4]};
			CreateProjectIdsList.run(params, prop, bw);
		}
		*/
		try {
			bw.close();
			bwSummary.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
