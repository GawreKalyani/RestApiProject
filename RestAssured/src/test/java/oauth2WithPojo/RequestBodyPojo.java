package oauth2WithPojo;

public class RequestBodyPojo {
	private String action;
	private String success;
	private String message;
	private String data;
	
	public String getAction() {
		return action;
	}
	public String getSuccess() {
		return success;
	}
	public String getMessage() {
		return message;
	}
	public String getData() {
		return data;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setData(String data) {
		this.data = data;
	}
}
