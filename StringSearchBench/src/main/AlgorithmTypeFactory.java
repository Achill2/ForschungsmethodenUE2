package main;

import pg_algorithm_impl.PrimeAlgo01;
import pg_algorithm_impl.PrimeAlgo02;
import primegenerator.PrimeGeneratorAlgorithm;

public class AlgorithmTypeFactory {
	
	// flags for prime-generator-algorithm types
	public static final String PG_A = "a"; // first algorithm
	public static final String PG_B = "b"; // second algorithm
	
	private static AlgorithmTypeFactory instance = null;
	
	/**
	 * override public constructor
	 */
	private AlgorithmTypeFactory() {
		
	}
	
	/**
	 * Singleton pattern - getInstance method
	 * @return
	 */
	public static AlgorithmTypeFactory getInstance() {
		
		if (instance == null) {
			instance = new AlgorithmTypeFactory();
		}
		
		return instance;
	}
	
	/**
	 * takes the input parameter s and returns the mapped algorithm
	 * If no proper mapping can be found, null is returned
	 * @param s - input Parameter mapped to an algorithm
	 * @return
	 */
	public PrimeGeneratorAlgorithm getPrimeGeneratorAlgorithm(String s) {
		PrimeGeneratorAlgorithm algo = null;
		
		if (s.equals(PG_A)) {
			algo = new PrimeAlgo01();
		} else if (s.equals(PG_B)) {
			algo = new PrimeAlgo02();
		} // to be continued
		
		return algo;
	}

}
