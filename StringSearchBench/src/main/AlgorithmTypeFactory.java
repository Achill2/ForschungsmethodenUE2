package main;

import pg_algorithm_impl.PrimeAlgo01;
import pg_algorithm_impl.PrimeAlgo02;
import primegenerator.PrimeGeneratorAlgorithm;

import stringsearch.*;

public class AlgorithmTypeFactory {
	
	// flags for prime-generator-algorithm types
	public static final String PG_A = "a"; // first algorithm
	public static final String PG_B = "b"; // second algorithm

	// flags for ss_algorithm types                            
	public static final String ss_alg_naive = "ss_naive";   
	public static final String ss_alg_rabin = "ss_rabin";   
	public static final String ss_alg_boyer = "ss_boyer";  
	public static final String ss_alg_kmp_dfa = "ss_kmp_dfa";  
	public static final String ss_alg_kmp = "ss_kmp";  
	
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
	public StringSearchAlgorithm getStringSearchAlgorithm(String s) {
	
		StringSearchAlgorithm alg = null;
	
		if (s.equals(ss_alg_naive)) {
		    alg = new NaiveSearch();
		} else if (s.equals(ss_alg_rabin)) {
			alg = new RabinKarp();
		} else if (s.equals(ss_alg_boyer)) {
			alg = new BoyerMoore();
		} else if (s.equals(ss_alg_kmp)) {
			alg = new KnuthMorrisPratt();
		} else if (s.equals(ss_alg_kmp_dfa)) {
			alg = new KnuthMorrisPratt_DFA();
		}

		return alg;
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
