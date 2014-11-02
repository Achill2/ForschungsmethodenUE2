package main;

import java.math.BigDecimal;
import java.util.List;

public class Helper {
	
	// factor for calulcating seconds from nano-seconds
	public static final double NANOSEC_TO_SEC_FACTOR = 1000000000.0;  
	
	public static final String SHOW_RESULT = "-r";
	
	
	
	/**
	 * override Constructor
	 */
	private Helper() {
		
	}
	
	/**
	 * helper method to round double values precisely 
	 * @param unrounded
	 * @param precision - the number of decimal points
	 * @return
	 */
	public static double round(double unrounded, int precision) {
		BigDecimal bd = new BigDecimal(unrounded);
		BigDecimal rounded = bd.setScale(precision, BigDecimal.ROUND_HALF_UP);
		
		return rounded.doubleValue();
	}
	
	/**
	 * helper method to generate a formatted string out of list-values
	 * @param list
	 * @return
	 */
	public static String intListToString(List<Integer> list) {
		
		if (list == null) {
		 	return null;
		}
		
		String out = "";

		for (Integer i : list) {
			if(out.length() > 0) {
				out += ", ";
			}
			out += i;
		}
		return out;
	}
	
	public static final String YOURKIT_CPU_SAMPLING_SETTINGS =
			"walltime=java.io.RandomAccessFile : readBytes(byte[], int, int) " + 
			"(\n walltime=java.io.RandomAccessFile : read() " + 
			"(\n walltime=java.io.RandomAccessFile : write(int) " + 
			"(\n walltime=java.io.RandomAccessFile : writeBytes(byte[], int, int) " + 
			"(\n walltime=java.net.SocketInputStream : socketRead0(java.io.FileDescriptor, byte[], int, int, int) " + 
			"(\n walltime=java.net.SocketOutputStream : socketWrite0(java.io.FileDescriptor, byte[], int, int) " + 
			
			"(\n sampling_period_ms=10";
}
