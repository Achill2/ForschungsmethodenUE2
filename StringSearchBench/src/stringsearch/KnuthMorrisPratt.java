package stringsearch;

import java.util.LinkedList;

public class KnuthMorrisPratt implements StringSearchAlgorithm {
	private int comparisons;
	
	public KnuthMorrisPratt() {comparisons = 0;}
	public int[] computePrefix(String pattern) {
		int patternLength = pattern.length(); int longestPrefix = 0;
		int[] prefixFunction = new int[patternLength];
		prefixFunction[0] = 0;
		for(int patternIndex = 1; patternIndex < patternLength; patternIndex++) {
			while(longestPrefix > 0 && !this.compareTwoChars(pattern.charAt(longestPrefix) , pattern.charAt(patternIndex))) 
				longestPrefix = prefixFunction[longestPrefix-1];
			if(this.compareTwoChars(pattern.charAt(longestPrefix),pattern.charAt(patternIndex)))
				longestPrefix = longestPrefix+1;
			prefixFunction[patternIndex] = longestPrefix;
			comparisons++;
		}
		return prefixFunction;
	}
	
	public LinkedList<Integer> searchForPattern(String pattern, String context){
		LinkedList<Integer> foundPatternList = new LinkedList<Integer>();
		int patternLength = pattern.length(); int contextLength = context.length();
		int[] prefixFunction = this.computePrefix(pattern);
		int longestPrefix = 0;
		for(int contextIndex = 0; contextIndex < contextLength; contextIndex=contextIndex+1) {
			while(longestPrefix > 0 && !this.compareTwoChars(pattern.charAt(longestPrefix), context.charAt(contextIndex)))
				longestPrefix = prefixFunction[longestPrefix-1];
			if(this.compareTwoChars(pattern.charAt(longestPrefix), context.charAt(contextIndex)))
				longestPrefix = longestPrefix + 1;
			if (longestPrefix == patternLength) { foundPatternList.add(contextIndex - patternLength+1); longestPrefix = prefixFunction[longestPrefix-1];}
			comparisons++;
		}
		return foundPatternList;
	}
	
	public boolean compareTwoChars(char aChar, char anotherChar) {
		return aChar == anotherChar;
	}
	
	public int getComparisons() {
		return comparisons;
	}
	
	/*
	public void printFoundPattern(LinkedList<Integer> foundPatternList) {
		int listSize = foundPatternList.size();
		System.out.println("Number of comparisons (KMP): "+comparisons);
		System.out.print("Found pattern in text at: ");
		for(int index = 0; index < listSize; index++) 
			System.out.print(foundPatternList.get(index)+" ");
		System.out.println();
	}
	
	public void foundPatternInContext(String pattern, String context) {
		LinkedList<Integer> foundPatternList = this.searchForPattern(pattern, context);
		if(foundPatternList.isEmpty()) System.out.println("Number of comparisons (KMP): "+comparisons + ". No pattern found in text.");
		else this.printFoundPattern(foundPatternList);
	}
 */
}
