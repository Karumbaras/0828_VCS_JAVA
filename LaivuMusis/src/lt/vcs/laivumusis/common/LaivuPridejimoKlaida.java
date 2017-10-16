package lt.vcs.laivumusis.common;

public class LaivuPridejimoKlaida extends Exception {

	private static final long serialVersionUID = -4808646544225363704L;
	
	public LaivuPridejimoKlaida(){
		super();
	}
	
	public LaivuPridejimoKlaida(String message, Throwable cause){
		super(message, cause);
	}
	
	public LaivuPridejimoKlaida(Throwable cause){
		super(cause);
	}
	
	public LaivuPridejimoKlaida(String message){
		super(message);
	}

}
