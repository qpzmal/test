package cn.advu.workflow.common.exception;

public class IWanviException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 564748557366623676L;

	public IWanviException(String message, Throwable cause) {
		super(message, cause);
	}

	public IWanviException(String message) {
		super(message);
	}
	
	@Override
	public String toString() {
		return getMessage();
	}
}
