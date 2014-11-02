package main;

import stringsearch.StringSearchAlgorithm;

import java.io.File;

public class TestcaseFactory {
	
	//location of input files
	public static final String inputLocation = "../input/";
	
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
	
	public Testcase getTestcase(StringSearchAlgorithm alg, int inputNumber, int numberOfExecutions,  boolean showResult) {
			
		File inputStringFile = new File(inputLocation + "source_string.i" + inputNumber);
		File searchStringFile = new File(inputLocation + "search_string.i" + inputNumber);

		return new Testcase(alg, inputStringFile, searchStringFile, numberOfExecutions, showResult);
	}

}
