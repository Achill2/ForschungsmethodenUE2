package main;

import java.util.List;

import stringsearch.StringSearchAlgorithm;

public class Testcase {

private StringSearchAlgorithm alg;
private String searchString;
private String sourceString;
private int numberOfExecutions;
private boolean showResult;


/**
 * Constructor of Class testcase
 * @param alg - the algorithm to use
 * @param searchString - the string to search for
 * @param sourceString - the string to search in
 * @param numberOfExecutions
 * @param showResults - show results or not
 * @return
 */
public Testcase (StringSearchAlgorithm alg, String searchString, String sourceString, int numberOfExecutions, boolean showResult) {
	this.alg = alg;
	this.numberOfExecutions = numberOfExecutions;
	this.showResult = showResult;
	this.searchString = searchString;
	this.sourceString = sourceString;
	
	alg.setSource(sourceString);
	
}

	
public void execute() {
		
	long startTime = 0;
	long endTime = 0;
	List<Integer> res = null;
	
	System.out.println("Searching for string " + searchString);
	
	for (int i = 1; i <= numberOfExecutions; i++) {
	
		startTime = System.nanoTime(); // get current time
		
		res = null;
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
	
	System.out.println("\nThe found string is: " + this.sourceString.substring(res.get(0), res.get(0) + this.searchString.length() -1));
	}
}
