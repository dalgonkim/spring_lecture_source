package mvjsp.chap13.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "member-list")
public class MemberListXml {
	
	@XmlElement(name = "member")
	private List<Member> memberList;	
	
	public MemberListXml(){}
	public MemberListXml(List<Member> memberList){
		this.memberList=memberList;
	}
	
	public List<Member> getMemberList(){
		return this.memberList;
	}
}






