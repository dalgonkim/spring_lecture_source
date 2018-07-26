package mvjsp.chap13.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



public class MemberListJson {
	
	
	private List<Member> memberList;	
	
	public MemberListJson(){}
	
	public MemberListJson(List<Member> memberList){
		this.memberList=memberList;
	}
	
	public List<Member> getMemberList(){
		return this.memberList;
	}
}






