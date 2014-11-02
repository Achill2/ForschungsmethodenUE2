package main;

import stringsearch.StringSearchAlgorithm;

import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class TestcaseFactory {
	
	//location of input files
	public static final String inputLocation = "./SharedProject/StringSearchBench/input/";
	
	private static TestcaseFactory instance = null;
	
	/**
	 * override public constructor
	 */
	private TestcaseFactory() {
		
	}
	
	/**
	 * Singleton pattern - getInstance method
	 * @return
	 */
	public static TestcaseFactory getInstance() {
		
		if (instance == null) {
			instance = new TestcaseFactory();
		}
		
		return instance;
	}
	
	
	/**
	 * This method extracts the content of the input files into variables and returns an instance of Testcase
	 * @param alg - the algorithm to use
	 * @param inputNumber - number of the input files to use
	 * @param numberOfExecutions
	 * @param showResults - show results or not
	 * @return
	 */
	public Testcase getTestcase(StringSearchAlgorithm alg, int inputNumber, int numberOfExecutions,  boolean showResult) {
		
		File inputStringFile = new File(inputLocation + "source_string.i" + inputNumber);
		File searchStringFile = new File(inputLocation + "search_string.i" + inputNumber);
		
		BufferedReader reader = null;
		String searchString = null;
		String sourceString = null;
		
		//Extract the input- and search-string from  files into variables
		try {	
			reader = new BufferedReader( new FileReader (inputStringFile));
			String line = null;
			StringBuilder stringBuilder = new StringBuilder();
			String ls = System.getProperty("line.separator");

			while( ( line = reader.readLine() ) != null ) {
				stringBuilder.append( line );
				stringBuilder.append( ls );					   
			}
			
			sourceString = stringBuilder.toString();
			reader.close();
			
			reader = new BufferedReader(new FileReader (searchStringFile));
			stringBuilder = new StringBuilder();

			 while( ( line = reader.readLine() ) != null ) {
				stringBuilder.append( line );            
				//stringBuilder.append( " " );              
			}

			searchString = stringBuilder.toString();		
			
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		
		} finally {
			try {
				reader.close();
			} catch (IOException e) {}
		}

		return new Testcase(alg, searchString, sourceString, numberOfExecutions, showResult);
	}

}
