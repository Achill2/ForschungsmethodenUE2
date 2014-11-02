package ss_algorithm_impl;

import java.util.ArrayList;
import java.util.List;

import stringsearch.StringSearchAlgorithm;
/**
 * Rabin-Karp string-search algorithm
 * source: <link>http://en.wikipedia.org/wiki/Rabin%E2%80%93Karp_string_search_algorithm</link>
 * 
 * @author Fabian
 *
 */
public class RabinKarpAlgorithm extends StringSearchAlgorithm{

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
		
		int hashedPattern = pattern.hashCode();
		int hashedSourcePart = source.substring(0, pLength).hashCode();
		
		for (int i = 0; i < endOuterLoop; i++) {
			if (hashedSourcePart == hashedPattern) {
				if (pattern.equals(source.substring(i, i + pLength))) {
					positions.add(i);
				}
			}
			
			// assure that the end of the source has not been reached yet
			int substringEnd = i + 1 + pLength;
			if (substringEnd < source.length()) {
				hashedSourcePart = source.substring(i+1, substringEnd).hashCode();
			} else {
				break; // end the loop
			}
			
		}
		
		return positions;
	}

}
