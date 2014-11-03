package stringsearch;

import java.util.LinkedList;

/**
 * Naive String Search
 * <link>https://github.com/pmkhoa/string-pattern-matching/blob/master/src/algorithm/NaiveSearch.java</link>
 * 
 * Der einfachste Algorithmus besteht darin, ein so genanntes Suchfenster von der Länge der Suchmaske über den 
 * Text zu schieben. In jeder Position der Suchmaske werden die Symbole der Maske mit denen des darunterliegenden 
 * Textes verglichen. Wenn ein nicht übereinstimmendes Symbol gefunden wird, wird das Fenster um eine Position 
 * verschoben, und erneut ein Vergleich angestellt; wenn alle Symbole im Fenster übereinstimmen, ist die Suchmaske 
 * gefunden worden. Der Algorithmus endet, wenn der ganze Text vom Fenster abgesucht worden ist.
 * 
 * Referring to implementation of naïve matcher, we see that the for-loop in line 3 is executed at most 
 * n - m +1 times, and the while-loop in line 5 is executed at most m times. Therefore, the running time of 
 * the algorithm is O((n - m +1)m), which is clearly O(nm). Hence, in the worst case, when the length of the 
 * pattern, m are roughly equal, this algorithm runs in the quadratic time.
 * 
 * @author pmkhoa
 *
 */
public class NaiveSearch implements StringSearchAlgorithm {
	int comparisons = 0;
	public NaiveSearch() {}
	
	public LinkedList<Integer> searchForPattern(String pattern, String context) {
		LinkedList<Integer> foundPatternList = new LinkedList<Integer>();
		int patternLength = pattern.length(); int contextLength = context.length();
		for(int contextIndex = 0; contextIndex <= contextLength - patternLength; contextIndex++) {
			int match = 0;
			for(int patternIndex = 0; patternIndex < patternLength; patternIndex++) {
				if(pattern.charAt(patternIndex) == context.charAt(patternIndex+contextIndex)) {comparisons++; match++;}
				else {comparisons++; break;}	
			}
			if(match == patternLength) foundPatternList.add(contextIndex);
			comparisons++;	
		}
		return foundPatternList;
	}
	
	public int getComparisons() {
		return comparisons;
	}
	
	/*
	public void printFoundPattern(LinkedList<Integer> foundPatternList) {
		int listSize = foundPatternList.size();
		System.out.println("Number of comparisons (Naive Search): "+comparisons);
		System.out.print("Found pattern in text at: ");
		for(int index = 0; index < listSize; index++) 
			System.out.print(foundPatternList.get(index)+" ");
		System.out.println();
	}
	public void foundPatternInContext(String pattern, String context) {
		LinkedList<Integer> foundPatternList = this.searchForPattern(pattern, context);
		if(foundPatternList.isEmpty()) System.out.println("Number of comparisons (Naive Search): "+comparisons + ". No pattern found in text.");
		else this.printFoundPattern(foundPatternList);
	}	*/
}
