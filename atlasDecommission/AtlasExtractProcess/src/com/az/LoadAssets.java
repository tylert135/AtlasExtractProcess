package com.az;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import com.opencsv.CSVReader;

public class LoadAssets {
	
	public static HashMap<String, CrossRefMapper> run (String assetReportCSV, String encoder, char delimiter, BufferedWriter bw) {
		LogOutput.outBar(bw);
		HashMap<String, CrossRefMapper> assetKey_hMap = new HashMap<String, CrossRefMapper>();
		String[] assetCsvData = null;
		int assetCtr=0;
		try {
			CSVReader assetReader = new CSVReader(new InputStreamReader(new FileInputStream(assetReportCSV), encoder), delimiter);
			assetCsvData = assetReader.readNext();
			while ((assetCsvData = assetReader.readNext()) != null) {
				assetCtr++;
				assetKey_hMap.put(assetCsvData[0], new CrossRefMapper(assetCsvData));
			}
			System.out.println("Total Asset CSV Records: "+assetCtr);
			bw.write("Total Asset CSV Records: "+assetCtr+"\n");
			assetReader.close();
		} catch (IOException e) {
			System.err.println(e);
		}
		return assetKey_hMap;
	}

}
