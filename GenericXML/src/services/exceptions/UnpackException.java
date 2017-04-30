package services.exceptions;

/**
 * @author marcel
 * 
 *         Implementacao da excecao de descompactamento de xmls.
 */
public class UnpackException extends Exception {
	private static final long serialVersionUID = 1L;

	private String message;

	public UnpackException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return "UnpackException : " + message + "\n" + super.getMessage();
	}

}
