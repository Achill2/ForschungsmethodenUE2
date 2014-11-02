package main;

import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.List;

import stringsearch.StringSearchAlgorithm;

public class Testcase {

private StringSearchAlgorithm alg;
private String searchString;
private String sourceString;
private int numberOfExecutions;
private boolean showResult;

public Testcase (StringSearchAlgorithm alg, File inputStringFile, File searchStringFile, int numberOfExecutions, boolean showResult) {
	this.alg = alg;
	this.numberOfExecutions = numberOfExecutions;
	this.showResult = showResult;

	BufferedReader reader = null;
	//Extract the input- andsearch-string from the file into the variable
	try {	
		reader = new BufferedReader( new FileReader (inputStringFile));
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");

		while( ( line = reader.readLine() ) != null ) {
			stringBuilder.append( line );
			stringBuilder.append( ls );					   
		}
		
		this.sourceString = stringBuilder.toString();
		alg.setSource(sourceString);
		reader.close();
		
		reader = new BufferedReader(new FileReader (searchStringFile));
		stringBuilder = new StringBuilder();

		 while( ( line = reader.readLine() ) != null ) {
			stringBuilder.append( line );            
			stringBuilder.append( ls );              
		}

		this.searchString = stringBuilder.toString();		
		
		reader.close();

	} catch (IOException e) {
		e.printStackTrace();
	
	} finally {
		try {
			reader.close();
		} catch (IOException e) {}
	}
}

	
public void execute() {
		
	long startTime = 0;
	long endTime = 0;
	List<Integer> res = null;
	
	for (int i = 1; i <= numberOfExecutions; i++) {
	
		startTime = System.nanoTime(); // get current time
		res = alg.searchFor(this.searchString);
		endTime = System.nanoTime(); // get end time
		
		if (this.showResult) {
			System.out.println("number of result: " + i +", "
				+ "searchString occurs at indices " + res);
		
			long duration = (endTime - startTime);
			double durationInSecRounded = Helper.round(duration/Helper.NANOSEC_TO_SEC_FACTOR, 4);
			
			System.out.println(""
					+ "in " + duration + " nano-sec = " + durationInSecRounded + " sec");
			}
		}
	
	System.out.println("\n The found string is: " + this.sourceString.substring(res.get(0), res.get(0) + this.searchString.length() -1));
	}
}
