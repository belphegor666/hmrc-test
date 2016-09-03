package gov.hmrc;

public class UnknownItemException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UnknownItemException(String item) {
		super("Unknown item: "+item);
	}
}
