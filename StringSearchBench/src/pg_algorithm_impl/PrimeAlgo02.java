package pg_algorithm_impl;

import java.util.ArrayList;
import java.util.List;

import primegenerator.PrimeGeneratorAlgorithm;

/**
 * Second Prime-Generator Algorithm according to
 * <link>http://en.wikibooks.org/wiki/Efficient_Prime_Number_Generating_Algorithms</link>
 * 
 * @author Fabian
 *
 */
public class PrimeAlgo02 extends PrimeGeneratorAlgorithm {

	@Override
	public List<Integer> generatePrimeUpTo(int boundary) {
		List<Integer> primeNumbers = new ArrayList<Integer>();
		
		// only positive boundaries
		boundary = Math.abs(boundary);
		
		int pp = 2;
		primeNumbers.add(pp);
		pp+=1;
		primeNumbers.add(pp);
		
		while (pp < boundary) {
			pp += 2;
			boolean test = true;
			for (int i : primeNumbers) {
				if (pp%i == 0) {
					test = false;
					break;
				}
			}
			if (test) {
				primeNumbers.add(pp);
			}
		}
		return primeNumbers;
	}

}
