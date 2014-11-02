package pg_algorithm_impl;

import java.util.ArrayList;
import java.util.List;

import primegenerator.PrimeGeneratorAlgorithm;

/**
 * First Prime-Generator Algorithm according to
 * <link>http://en.wikibooks.org/wiki/Efficient_Prime_Number_Generating_Algorithms</link>
 * 
 * @author Fabian
 *
 */
public class PrimeAlgo01 extends PrimeGeneratorAlgorithm {

	@Override
	public List<Integer> generatePrimeUpTo(int boundary) {
		List<Integer> primeNumbers = new ArrayList<Integer>();
		
		// only positive boundaries
		boundary = Math.abs(boundary);
		int pp = 2;
		primeNumbers.add(2);
		
		while (pp < boundary) {
			
			pp++;
			boolean test = true;
			
			for (int i : primeNumbers) {
				if (pp%i == 0) {
					test = false;
				}
			}
			
			// if test is positive -> pp is a prime number 
			// -> add it to the list
			if (test) {
				primeNumbers.add(pp);
			}
			
		}
		return primeNumbers;
	}

}
