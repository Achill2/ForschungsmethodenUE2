package primegenerator;

import java.util.List;

public abstract class PrimeGeneratorAlgorithm{
	
	private final int DEFAULT_BOUNDARY = 100000;
	
	public List<Integer> generatePrimeUpTo() {
		return generatePrimeUpTo(DEFAULT_BOUNDARY);
	}
	
	public abstract List<Integer> generatePrimeUpTo(int boundary);

}
