package mvjsp.chap13.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



public class MessageListJson {
	
	private List<Message> messageList;	
	
	public MessageListJson(){}
	public MessageListJson(List<Message> messageList){
		this.messageList=messageList;
	}
	
	public List<Message> getmessageList(){
		return this.messageList;
	}
}






