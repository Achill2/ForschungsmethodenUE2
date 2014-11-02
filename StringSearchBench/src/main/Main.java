package main;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.math.BigDecimal;
import java.util.List;

import com.yourkit.api.Controller;

import pg_algorithm_impl.PrimeAlgo01;
import pg_algorithm_impl.PrimeAlgo02;
import primegenerator.PrimeGeneratorAlgorithm;

public class Main {
	
	public static int numberOfPreExecutions = 2;
	public static int numberOfExecutions = 1;
	public static boolean showResult = false;

	public static void main(String[] args) {
		
		/*
		 * input parameters:
		 * 1.) algorithm type: which stringSearch/PrimeGenerator
		 * 2.) boundary: boundary for the generation (optional)
		 * 3.) show result (optional)
		 */
		if (args.length < 1) {
			System.err.println("Missing Argument(s)! [algorithm-type]");
			return;
		}
		
		PrimeGeneratorAlgorithm algorithm = AlgorithmTypeFactory.getInstance().getPrimeGeneratorAlgorithm(args[0]);
		
		if (algorithm == null) {
			System.err.println("No Algorithm found, please check the parameters");
			return;
		}
		
		

		
		if (args.length >= 2) {
			if (args[1].equals(Helper.SHOW_RESULT)) {
				showResult = true;
			} else {
				int nb = numberOfExecutions;
				try {
					nb = Integer.parseInt(args[1]);
				} catch (NumberFormatException e) {
					System.err
							.println("Wrong parameter value! Number-of-executions must be an integer value!"
									+ "\n"
									+ "Default ("
									+ numberOfExecutions
									+ ") value is used");
				}
				numberOfExecutions = nb;	
			}
		}
		
		if (args.length >= 3) {
			if (args[2].equals(Helper.SHOW_RESULT)) {
				showResult = true;
			}
		}
		
		// hold application to connect to YourKit
//		System.out.println("Press Enter to continue");  
//		try{
//			System.in.read();
//		} catch(Exception e){
//			System.err.println(e.getMessage());
//		}
		
		int end = numberOfPreExecutions + numberOfExecutions;
		for (int i = 0; i < end; i++) {
//			execute(algorithm, i > 2 && i < end - 2);
			execute(algorithm);
		}
		

	}

	private static void executePrimeGeneratorAlgorithm(String[] args) {
		
		PrimeGeneratorAlgorithm algo = new PrimeAlgo02(); // TODO dynamic selection etc
		
		// now hardcoded -> change
		int[] boundaries = {1000, 10000, 100000, 1000000, 10000000};
		
		for (int i = 0; i < boundaries.length; i++) {
			long startTime = System.nanoTime(); // get current time
			
			List<Integer> res = algo.generatePrimeUpTo(boundaries[i]);

			long endTime = System.nanoTime(); // get end time

			
			System.out.println("number of result for '" + boundaries[i] + "': " + res.size() 
					+ ", last prime: " + res.get(res.size() -1)); 
			
			
			long duration = (endTime - startTime);
			double durationInSecRounded = Helper.round(duration/Helper.NANOSEC_TO_SEC_FACTOR, 4);
			
			System.out.println(""
					+ "in " + duration + "nano-sec = " + durationInSecRounded + "sec"
					);
		}
		
	}
	
	private static void execute(PrimeGeneratorAlgorithm algorithm, boolean sample) {
		Controller controller = null;
		
		long startTime = 0;
		long endTime = 0;
		List<Integer> res = null;
		
		try {
			controller = new Controller();
//			controller.clearAllocationData();
//			controller.clearCPUData();
			controller.stopCPUProfiling();
			
			
			try {
				if (sample) controller.startCPUSampling(Helper.YOURKIT_CPU_SAMPLING_SETTINGS);
				
				
				startTime = System.nanoTime(); // get current time
				
				res = algorithm.generatePrimeUpTo();
				
				endTime = System.nanoTime(); // get end time
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (sample) controller.captureSnapshot(Controller.SNAPSHOT_WITH_HEAP);
				controller.stopCPUProfiling();
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (showResult) {
			System.out.println("number of result: " + res.size() 
					+ ", last prime: " + res.get(res.size() -1));
			
			long duration = (endTime - startTime);
			double durationInSecRounded = Helper.round(duration/Helper.NANOSEC_TO_SEC_FACTOR, 4);
			
			System.out.println(""
					+ "in " + duration + "nano-sec = " + durationInSecRounded + "sec"
					);
		}
	}

	private static void execute(PrimeGeneratorAlgorithm algorithm) {
		
		long startTime = 0;
		long endTime = 0;
		List<Integer> res = null;
		
		
		startTime = System.nanoTime(); // get current time
		
		res = algorithm.generatePrimeUpTo();
		
		endTime = System.nanoTime(); // get end time

		
		if (showResult) {
			System.out.println("number of result: " + res.size() 
					+ ", last prime: " + res.get(res.size() -1));
			
			long duration = (endTime - startTime);
			double durationInSecRounded = Helper.round(duration/Helper.NANOSEC_TO_SEC_FACTOR, 4);
			
			System.out.println(""
					+ "in " + duration + "nano-sec = " + durationInSecRounded + "sec"
					);
		}
	}

	
}
