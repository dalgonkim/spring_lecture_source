package mvjsp.chap13.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "id", "guestName","password","message"})
public class Message {

	private int message_id;
	private String guest_name;
	private String password;
	private String message;

	


	public int getMessage_id() {
		return message_id;
	}




	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}




	public String getGuest_name() {
		return guest_name;
	}




	public void setGuest_name(String guest_name) {
		this.guest_name = guest_name;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public String getMessage() {
		return message;
	}




	public void setMessage(String message) {
		this.message = message;
	}




	public boolean hasPassword() {
		return password != null && !password.isEmpty();
	}

}
