package stringsearch;

import java.util.LinkedList;


/**
 * Knuth Morris Pratt Algorithm
 * <link>https://github.com/pmkhoa/string-pattern-matching/blob/master/src/algorithm/KnuthMorrisPratt.java</link>
 * 
 * Der Morris-Pratt-Algorithmus baut auf dem Naiven Suchalgorithmus auf. Wesentlicher Unterschied ist, dass das 
 * Vergleichsfenster nicht immer um nur eine Position weitergerückt wird, sondern eventuell um mehr als eine Position.
 * 
 * Dazu muss zu Anfang die Suchmaske analysiert werden, so dass bei jeder teilweisen Übereinstimmung, etwa der ersten k 
 * Symbole, bekannt ist, ob der Anfang der Suchmaske mit dem Ende der letzten übereinstimmenden Teilmaske übereinstimmt. 
 * Die Verschiebung der Suchmaske erfolgt nach der überlappenden Übereinstimmung; zusätzlicher Vorteil ist, dass die 
 * schon verglichenen Symbole nicht noch einmal verglichen werden müssen.
 * 
 * Da Präfix-Analyse und Suche nacheinander ausgeführt werden, ist die Laufzeit des gesamten Algorithmus in Θ(n+m). 
 * Insgesamt werden höchstens 2(n+m) Vergleiche zwischen Zeichen des Musters und des Textes durchgeführt. 
 * Damit kann der Algorithmus von Knuth, Morris und Pratt eine bessere worst-case-Laufzeit garantieren als der Algorithmus 
 * von Boyer und Moore mit Θ(n⋅m).Allerdings kann Boyer-Moore eine Suche unter bestimmten Umständen in Θ(n/m) durchführen, 
 * Knuth-Morris-Pratt benötigt immer linear viele Vergleiche.
 * 
 * @author pmkhoa
 *
 */
public class KnuthMorrisPratt implements StringSearchAlgorithm {
	private long comparisons;
	
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
	
	public long getComparisons() {
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
