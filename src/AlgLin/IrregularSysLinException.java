package AlgLin;

public class IrregularSysLinException extends Exception{
	private String message; 

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public IrregularSysLinException() {
		super("Exception : Le Systeme est Irregulier");
		setMessage("Exception : Le Systeme est Irregulier");
	}
	
	@Override
	public String toString() {
		return getMessage();
	}

}
