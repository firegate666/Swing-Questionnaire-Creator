package de.sdavids.beans.viewbeans;

public class ErrorView {
	private Throwable fError;
 
	public ErrorView() {
	        super();
	}
	
	public ErrorView(Throwable error) {
		setError(error);
	}
	 
	public String getMessage() {
		return (null == fError) ? "" : fError.getMessage();
	}
	 
	public void setError(Throwable error) {
		fError = error;
	}
	
	public Throwable getError() {
		return fError;
	}	
}



