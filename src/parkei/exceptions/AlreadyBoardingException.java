package parkei.exceptions;

public class AlreadyBoardingException extends CannotBoardException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlreadyBoardingException() {
		super();
	}

	public AlreadyBoardingException(String x) {
		super(x);
	}

}
