package com.ace.exercise;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) throws IOException {
		File file = new File("E:\\temp");
		File[] files = file.listFiles();
		
		for(File f: files){
			if(f.isFile()){
				System.out.println(f);
			}
			
			
		}
		
	}
}
