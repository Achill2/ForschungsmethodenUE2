package ss_algorithm_impl;

import java.util.ArrayList;
import java.util.List;

import stringsearch.StringSearchAlgorithm;

/**
 * naive string-search algorithm
 * source: <link>http://en.wikipedia.org/wiki/Rabin%E2%80%93Karp_string_search_algorithm</link>
 * 
 * @author Fabian
 *
 */
public class NaiveAlgorithm extends StringSearchAlgorithm {



	@Override
	public List<Integer> searchFor(String pattern) {
		if (source == null) {
			// no source -> no search possible
			return null;
		}
		
		List<Integer> positions = new ArrayList<Integer>();
		
		// calculate outside of loop to make measuring loop-time-only possible 
		int sLength = source.length();
		int pLength = pattern.length();
		int endOuterLoop = sLength - pLength + 1;
		int endInnerLoop = pLength - 1;
		
		outerLoop:
		for (int i = 0; i < endOuterLoop; i++) {
			
			for (int j = 0; j < endInnerLoop; j++) {
				char inSource = source.charAt(i + j);
				char inPattern = pattern.charAt(j);
				if (inSource != inPattern) {
					continue outerLoop; // jump to next iteration of outer loop
				}
			}
			
			// pattern Matched completely
			positions.add(i); // add index to list
		}
		return positions;
	}
	
	

}
