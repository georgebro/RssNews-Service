package beans;

public class Error {

	private String message;

	public Error(String message) {
		if(message==null) {
			message="No ERROR Information...";
		}
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
