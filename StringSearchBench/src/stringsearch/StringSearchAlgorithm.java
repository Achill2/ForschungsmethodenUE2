package stringsearch;

import java.util.LinkedList;

public interface StringSearchAlgorithm {
	
	public LinkedList<Integer> searchForPattern(String pattern, String context);
	public long getComparisons();

}