package mvjsp.chap13.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "message-list")
public class MessageListXml {
	
	@XmlElement(name = "message")
	private List<Message> messageList;	
	
	public MessageListXml(){}
	public MessageListXml(List<Message> messageList){
		this.messageList=messageList;
	}
	
	public List<Message> getmessageList(){
		return this.messageList;
	}
}






