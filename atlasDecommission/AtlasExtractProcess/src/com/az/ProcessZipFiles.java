package com.az;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class ProcessZipFiles
{
  private static int zipCtr = 0;
  private static int fileCtr = 0;
  
  public static void run(Config prop, String encoder, char delimiter, BufferedWriter bw, String instance, BufferedWriter bwSummary)
  {
    String[] csvData = null;
    int manifestCSVctr = 0;
    
    CSVWriter manifestWriter = null;
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HH mm ss").format(Calendar.getInstance().getTime());
    
    LogOutput.outBar(bw);
    try
    {
      OutputStreamWriter fw1 = new OutputStreamWriter(new FileOutputStream(prop.getManifestZipOutput() + "_" + timeStamp + ".csv"), "UTF-8");
      manifestWriter = new CSVWriter(fw1, ',');
      
      manifestWriter.writeNext(ManifestHeader.HEADER);
    }
    catch (IOException ex)
    {
      ex.printStackTrace();
    }
    try
    {
      CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream(prop.getManifestZipProcessCSV()), encoder), delimiter);
      csvData = csvReader.readNext();
      while ((csvData = csvReader.readNext()) != null)
      {
        manifestCSVctr++;
        String outputFolderBase = "";
        String idType = "";
        String id = "";
        if (isArchive(csvData[Manifest.FILENAME]))
        {
          if (csvData[Manifest.RELATIVEPATH].startsWith("Asset"))
          {
            id = csvData[Manifest.RELATIVEPATH].substring(5);
            idType = "Assets";
            outputFolderBase = prop.getZipOutFolder() + "\\Assets\\" + id;
            File dir = new File(outputFolderBase);
            dir.mkdir();
          }
          else if (csvData[Manifest.RELATIVEPATH].startsWith("Project"))
          {
            id = csvData[Manifest.RELATIVEPATH].substring(7);
            idType = "Projects";
            outputFolderBase = prop.getZipOutFolder() + "\\Projects\\" + id;
            File dir = new File(outputFolderBase);
            dir.mkdir();
          }
          String pFileName = prop.getZipSourceFolder() + idType + "\\" + id + "\\" + csvData[Manifest.FILENAME];
          File pFile = new File(pFileName);
          
          processFileRecurvise(pFile, prop, encoder, bw, 
            bwSummary, manifestWriter, csvData, outputFolderBase + "\\" + csvData[Manifest.FILENAME].substring(0, csvData[Manifest.FILENAME].length() - 4));
        }
      }
      LogOutput.out(bw, bwSummary, "Total Manifest CSV Records: " + manifestCSVctr);
      LogOutput.out(bw, bwSummary, "\nTotal Archive Files Found: " + zipCtr);
      LogOutput.out(bw, bwSummary, "Total Files Unpacked: " + fileCtr);
      csvReader.close();
      manifestWriter.close();
    }
    catch (IOException e)
    {
      System.err.println(e + "\n" + csvData);
    }
    catch (Exception e)
    {
      LogOutput.out(bw, e + "\n" + Arrays.toString(csvData));
      System.err.println(e + "\n" + csvData);
    }
  }
  
  private static Manifest createManifest(String[] csvData)
  {
    Manifest manifest = new Manifest(csvData);
    return manifest;
  }
  
  private static void getFilesRecursive(File pFile, CSVWriter manifestWriter, String[] csvData, Config prop, String encoder, BufferedWriter bw, BufferedWriter bwSummary)
  {
    String relativePath = "";
    File[] arrayOfFile;
    int j = (arrayOfFile = pFile.listFiles()).length;
    for (int i = 0; i < j; i++)
    {
      File files = arrayOfFile[i];
      if (files.isDirectory())
      {
        getFilesRecursive(files, manifestWriter, csvData, prop, encoder, bw, 
          bwSummary);
      }
      else if (files.isFile())
      {
        File zFile = files.getAbsoluteFile();
        if (isArchive(zFile.getName()))
        {
          processFileRecurvise(zFile, prop, encoder, bw, 
            bwSummary, manifestWriter, csvData, zFile.getParent() + "\\" + zFile.getName().substring(0, zFile.getName().length() - 4));
        }
        else
        {
          Manifest manifest = createManifest(csvData);
          manifest.setFileName(files.getName());
          relativePath = files.getParent().substring(prop.getZipOutFolder().length() + 1);
          manifest.setRelativePath(relativePath);
          manifestWriter.writeNext(manifest.createManifestRecord());
          fileCtr += 1;
          LogOutput.out(bw, "Extracted File: " + files.getName());
        }
      }
    }
  }
  
  private static boolean isArchive(String fileName)
  {
    if ((fileName.toUpperCase().endsWith("ZIP")) || 
      (fileName.toUpperCase().endsWith("RAR"))) {
      return true;
    }
    return false;
  }
  
  private static void processFileRecurvise(File pFile, Config prop, String encoder, BufferedWriter bw, BufferedWriter bwSummary, CSVWriter manifestWriter, String[] csvData, String outputFolderBase)
  {
    String cmd = "";
    
    zipCtr += 1;
    File dir = new File(outputFolderBase);
    dir.mkdir();
    cmd = prop.getZipExeFolder() + " x \"" + pFile.getAbsoluteFile() + "\"" + 
      " -aou -xr!.DS_Store -xr!Thumb.db -xr!__MACOSX -o\"" + outputFolderBase + "\"";
    System.out.println(cmd);
    try
    {
      Process process = Runtime.getRuntime().exec(cmd);
      File faFiles = new File(outputFolderBase);
      
      BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream(), encoder));
      String s;
      while ((s = stdError.readLine()) != null)
      {
        System.out.println("* Standard error: ");
        System.out.println(s);
      }
      getFilesRecursive(faFiles, manifestWriter, csvData, prop, encoder, bw, 
        bwSummary);
      if (pFile.getAbsolutePath().startsWith(prop.getZipOutFolder()))
      {
        dir = new File(pFile.getAbsolutePath());
        dir.delete();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace(System.err);
      LogOutput.out(bw, bwSummary, e.getMessage());
    }
  }
}
