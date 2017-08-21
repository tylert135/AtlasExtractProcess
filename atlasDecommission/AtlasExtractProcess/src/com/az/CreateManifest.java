package com.az;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.opencsv.CSVWriter;

public class CreateManifest {
	
	public static void run(HashMap<String, CrossRefMapper> assetKey_hMap, 
			Config prop, HashMap<String, CrossRefMapper> projectKey_hMap, 
			BufferedWriter bw, String[] args, BufferedWriter bwSummary) {
		
		CSVWriter manifestWriter = null;
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HH mm ss").format(Calendar.getInstance().getTime());

		LogOutput.outBar(bw);
		try {
			
			OutputStreamWriter fw1 = new OutputStreamWriter(new FileOutputStream(prop.getManifestFile()+"_"+timeStamp+".csv"),"UTF-8");
			manifestWriter = new CSVWriter(fw1, ',');

			manifestWriter.writeNext(ManifestHeader.HEADER);

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		
		
		
	    String crossRefIds = "";
	    int assetManifestCtr=0;
		/*
		 * 
		 * Create manifest for assets
		 * 
		 * 
		 */
	    LogOutput.out(bw, bwSummary, "\nTotal Asset Records: " + assetKey_hMap.size());
		for (String key : assetKey_hMap.keySet()) {
			CrossRefMapper assetManifestData = assetKey_hMap.get(key);
				
			Manifest assetManifest = createAssetManifest(assetManifestData, args[7]);
			
			// Asset Files
			if (assetManifestData.getFileLogData()!=null) {
				for (FileLog fl : assetManifestData.getFileLogData()) {
					assetManifest.setFileName(fl.getFileName());
					
					if (assetManifestData.getCrossRefID() != null) {
						crossRefIds = "";
						for (String xref : assetManifestData.getCrossRefID()) {
							//project list
							if (!crossRefIds.contains(xref))
								crossRefIds=crossRefIds+xref+";";;
						}
						if (assetManifestData.getCrossRefID().size()>1)
							LogOutput.out(bw, "Multiple projects found for asset: "+
									assetManifestData.getCsvData()[GetFieldValue.name(args[7], "DIGITAL_ASSET_ID")]+
									" == "+assetManifestData.getCrossRefID().toString());
					}
					if (crossRefIds.length() > 1)
						crossRefIds=crossRefIds.substring(0, crossRefIds.length()-1);


					assetManifest.setPromotionalID(crossRefIds);
					
					// Output Asset Manifest
					outputManifest(assetManifest, manifestWriter, args[6]+args[7]);
					assetManifestCtr++;
				}
			}
		}
		LogOutput.out(bw, bwSummary, "Total Asset Manifest Records Created: " + assetManifestCtr);
		
		int projectManifestCrt=0;

		/*
		 * 
		 * Create manifest for projects
		 * 
		 * 
		 */
		LogOutput.out(bw, bwSummary, "\nTotal Project Records: " + projectKey_hMap.size());
		for (String key : projectKey_hMap.keySet()) {
			CrossRefMapper projectManifestData = projectKey_hMap.get(key);
			
			// project Files
			if (projectManifestData.getFileLogData() != null) {
				for (FileLog fl : projectManifestData.getFileLogData()) {
					
					if (projectManifestData.getCrossRefID() != null) {
												crossRefIds = "";
						for (String xref : projectManifestData.getCrossRefID()) {
							//Asset list
							if (!crossRefIds.contains(xref))
								crossRefIds=crossRefIds+xref+";";
						}
						
						crossRefIds=crossRefIds.substring(0, crossRefIds.length()-1);
						
						Manifest projectManifest = createProjectManifest(projectManifestData, args[7]);
						
						// Asset IDs
						projectManifest.setFileName(fl.getFileName());
						projectManifest.setStudyCode(crossRefIds);
						
						// Output Project Manifest
						outputManifest(projectManifest, manifestWriter, args[6]+args[7]);
						projectManifestCrt++;
						if (projectManifestData.getCrossRefID().size()>1)
							LogOutput.out(bw, "Multiple assets found for project: "+projectManifestData.getCsvData()[GetFieldValue.name(args[7], "PROJECT_ID")]+
									" == "+projectManifestData.getCrossRefID().toString());
					}
				}
			}
		}
		LogOutput.out(bw, bwSummary, "Total Project Manifest Records Created: " + projectManifestCrt);

		try {
			manifestWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	    
	}
	
	//**
	public static Manifest createAssetManifest(CrossRefMapper assetData, String environment) {
		Manifest manifest = createManifestBase(assetData, environment);
		manifest.setStudyCode(assetData.getCsvData()[GetFieldValue.name(environment, "DIGITAL_ASSET_ID")]);
		manifest.setRelativePath("Asset\\"+assetData.getCsvData()[GetFieldValue.name(environment, "DIGITAL_ASSET_ID")]);
		return manifest;
	}
	
	//**
	public static Manifest createProjectManifest(CrossRefMapper projectData, String environment) {
		Manifest manifest = createManifestBase(projectData, environment);
		manifest.setPromotionalID(projectData.getCsvData()[GetFieldValue.name(environment, "PROJECT_ID")]);
		manifest.setRelativePath("Project\\"+projectData.getCsvData()[GetFieldValue.name(environment, "PROJECT_ID")]);
		return manifest;
	}

	public static Manifest createManifestBase(CrossRefMapper data, String environment) {
		
		Manifest manifest = new Manifest();
		manifest.setAuthor(data.getCsvData()[GetFieldValue.name(environment, "ACTIVITY_OWNER")]);
		manifest.setTitle(data.getCsvData()[GetFieldValue.name(environment, "DIGITAL_ASSET_TITLE")]);
		String[] dtArr = data.getCsvData()[GetFieldValue.name(environment, "ASSET_CREATED_DATE")].split(" ");
		manifest.setDateCreated(dtArr[0]);
		
		if (data.getCsvData()[GetFieldValue.name(environment, "DIGITAL_ASSET_EXPIRATION_DATE")] != null && 
				!data.getCsvData()[GetFieldValue.name(environment, "DIGITAL_ASSET_EXPIRATION_DATE")].equals("")) {
			dtArr = data.getCsvData()[GetFieldValue.name(environment, "DIGITAL_ASSET_EXPIRATION_DATE")].split(" ");
			manifest.setDateClosed(dtArr[0]);
		}
		else {
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date date = new Date();
			manifest.setDateClosed(dateFormat.format(date));
		}
		manifest.setProduct(data.getCsvData()[GetFieldValue.name(environment, "BRAND")]); 
		manifest.setSigner(data.getCsvData()[GetFieldValue.name(environment, "APPROVER_NAME")]); 
		manifest.setAudience(data.getCsvData()[GetFieldValue.name(environment, "TARGET_AUDIENCE")]); 
		manifest.setCountry(data.getCsvData()[GetFieldValue.name(environment, "MARKET")]);
		manifest.setStudyCode(data.getCsvData()[GetFieldValue.name(environment, "DIGITAL_ASSET_ID")]); 
		manifest.setAuditRecords(data.getCsvData()[GetFieldValue.name(environment, "ASSET_STATUS")]);
		manifest.setSubject(data.getCsvData()[GetFieldValue.name(environment, "ASSET_TYPE")]); 
		manifest.setTherapeuticArea(data.getCsvData()[GetFieldValue.name(environment, "THERAPEUTIC_AREA")]);
		manifest.setLanguage(data.getCsvData()[GetFieldValue.name(environment, "LANGUAGE")]);
		manifest.setSystemID(data.getCsvData()[GetFieldValue.name(environment, "APPROVAL_ID")]);
		return manifest;
	}
	
	
	public static void outputManifest(Manifest manifest, CSVWriter manifestWriter, String manifestTypeName) {
		try {
			String[] write = {
					manifestTypeName,							//0
					manifest.getTitle(),						//1
					"GRAD-0901",								//2
					"Company Restricted",						//3
					"Electronic",								//4
					manifest.getAuthor(),						//5
					manifest.getDateCreated(),					//6
					manifest.getLanguage(),						//7
					manifest.getRelativePath(),					//8
					manifest.getDateClosed(), 					//9
					manifest.getFileName(), 					//10
					"", "",										//11&12
					manifest.getProduct(), 						//13
					"", 										//14
					manifest.getSubject(),						//15
					manifest.getSigner(),  						//16
					"", "", "",									//17-19 
					manifest.getAudience(), 					//20
					"", "", 									//21-22
					"", "", 									//23&24
					"",  										//25
					manifest.getAuditRecords(), 				//26
					manifest.getAuthenticityComments(), 		//27
					"", "", "", "",		 						//28-31
					manifest.getCountry(),						//32
					"Commercial", 								//33
					"", "", "", "", "", "", "", "", "", "", 	//33-43
					"", "", "", "", "", 						//44-48
					manifest.getLegacyRecordNumber(), 			//49
					"",					 						//50
					"", "", "","",								//51-54
					manifest.getPromotionalID(),				//55
					"",											//56
					"", "",										//57-58
					manifest.getStudyCode(),					//59
					"",					 						//60
					manifest.getSystemID(),						//61
					"", 	 									//62
					manifest.getTherapeuticArea(),				//63
					""	 										//64
					};
			manifestWriter.writeNext(write);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

}
