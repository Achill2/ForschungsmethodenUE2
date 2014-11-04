package main;

import java.util.LinkedList;

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
		
}

	
public void execute() {
		
	long startTime = 0;
	long endTime = 0;
	LinkedList<Integer> res = null;
	
	//Pre-executions
	alg.searchForPattern(searchString, sourceString);
	alg.searchForPattern(searchString, sourceString);
	
	for (int i = 1; i <= numberOfExecutions; i++) {
	
		startTime = System.nanoTime(); // get current time
		
		res = null;
		res = alg.searchForPattern(searchString, sourceString);
		
		endTime = System.nanoTime(); // get end time
		
		if (this.showResult) {
		
			long duration = (endTime - startTime);
			double durationInSecRounded = Helper.round(duration/Helper.NANOSEC_TO_SEC_FACTOR, 4);
			
			System.out.println(i + ": in " + duration + " nano-sec = " + durationInSecRounded + " sec \n");
		}
	}	
	
	System.out.println("\n" + "searchString occurs at indices " + res
							+ "\n" + "number of comparisons per calculation: " + (alg.getComparisons()/(this.numberOfExecutions + 2)));  //number of executions + preexecutions
}
}
