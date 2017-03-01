package AlgLin;

public class IrregularSysLinException extends Exception {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public IrregularSysLinException(String string) {
		setMessage(message);
	}

	@Override
	public String toString() {
		return getMessage();
	}

}
