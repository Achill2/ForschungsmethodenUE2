package stringsearch;

import java.util.List;

public abstract class StringSearchAlgorithm {
	
	
	protected String source;
	
	public void setSource(String source) {
		this.source = source;
		
	}
	
	public abstract List<Integer> searchFor(String pattern);
	

}
