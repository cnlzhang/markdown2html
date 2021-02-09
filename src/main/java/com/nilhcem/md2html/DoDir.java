package com.nilhcem.md2html;

import java.io.File;

import com.nilhcem.md2html.console.ArgsParser;
import org.apache.commons.io.FilenameUtils;

import com.nilhcem.md2html.console.ConsoleMode;

public class DoDir {
	
	public static ConsoleMode cm = new ConsoleMode();
	
	public static void doDir(File dir) throws Exception{
		File[] files = dir.listFiles();
		for(int i=0; i<files.length; i++){
			if(files[i].isDirectory()){
				System.out.println( "doDir:" + files[i].getAbsolutePath());
				doDir(files[i]);
			}else{
				String markdownFile = files[i].getAbsolutePath(); 
				if(!markdownFile.endsWith(".md")){
					continue;
				}
				String strOutputFile = FilenameUtils.removeExtension(markdownFile) + ".html";
				File outputFile = new File(strOutputFile);
				if(outputFile.exists()){
					System.out.println( "out put file {} already exist, try next file!" + outputFile);
					continue;
				}

				String strErrFile = FilenameUtils.removeExtension(markdownFile) + ".err";
				File errFile = new File(strErrFile);
				if(errFile.exists()){
					System.out.println( "error file {} already exist, try next file!" + errFile);
					continue;
				}
				errFile.createNewFile();
				System.out.println( "errFile:" + errFile.getAbsolutePath());
				
				System.out.println( "doM2h:" + files[i].getAbsolutePath());
				try {
					// Not tested change
					ArgsParser ap = new ArgsParser();
					String[] args = {markdownFile, "-out", outputFile.getAbsolutePath()};
					ap.checkArgs(args);
					cm.process(ap);
					System.out.println( "del errFile:" + errFile.getAbsolutePath());
				} catch (Exception e) {
					System.out.println( "doM2h:" + e.getMessage());
				}
				errFile.deleteOnExit();
			}
		}
	}

}
