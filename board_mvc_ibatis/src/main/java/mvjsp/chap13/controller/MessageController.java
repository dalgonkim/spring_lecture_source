package mvjsp.chap13.controller;

import java.sql.SQLException;
import java.util.List;

import mvjsp.chap13.exception.InvalidMessagePasswordException;
import mvjsp.chap13.exception.MessageNotFoundException;
import mvjsp.chap13.exception.ServiceException;
import mvjsp.chap13.model.Message;
import mvjsp.chap13.model.MessageListJson;
import mvjsp.chap13.model.MessageListView;
import mvjsp.chap13.model.MessageListXml;
import mvjsp.chap13.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/message")
public class MessageController {
	
	@Autowired
	MessageService messageSvc;
	
	public void setMessageService(MessageService messageSvc){
		this.messageSvc=messageSvc;
	}
	
	@RequestMapping(value="messageList")
	public String  messageList(@RequestParam(value="page",defaultValue="1")
								int pageNumber, Model model){
		
		String url="list";
		
		MessageListView viewData=null;
		
		try {
			viewData = messageSvc.getMessageList(pageNumber);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("viewData",viewData);
		model.addAttribute("pageNumber",pageNumber);
		
		return url;
	}
	
	@RequestMapping(value="/writeMessage",method=RequestMethod.GET)
	public String writeMessageForm(){
		String url="writeMessageForm";
		return url;
	}
	
	@RequestMapping(value="/writeMessage",method=RequestMethod.POST)
	public String writeMessage(Message message){
		String url = "redirect:messageList";
		try {
			messageSvc.write(message);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return url;		
	}
	
	@RequestMapping(value="/updateMessage",method=RequestMethod.GET)
	public String updateMessageForm(@RequestParam(value="message_id") int id,
									 Model model){
		String url="updateMessageForm";
		try {
			Message message=messageSvc.getMessage(id);
			model.addAttribute(message);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return url;
	}
	
	@RequestMapping(value="/updateMessage",method=RequestMethod.POST)
	public String updateMessage(Message message){
		String url="redirect:messageList";
		try {
			messageSvc.updateMessage(message);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}
	
	@RequestMapping(value="deleteMessage",method=RequestMethod.GET)
	public String deleteMessageConfirm(@RequestParam(value="message_id")int id,
									   Model model){
		String url="confirmDeletion";
		
		model.addAttribute("message_id",id);
		
		return url;
	}
	
	@RequestMapping(value="deleteMessage",method=RequestMethod.POST)
	public String deleteMessage(@RequestParam(value="message_id")int id,
								@RequestParam(value="password")String pwd,
								Model model){
		
		String url="deleteMessage";
		
		boolean invalidPassowrd=false;
		try {
			messageSvc.deleteMessage(id, pwd);
		} catch (ServiceException e) {		
			e.printStackTrace();
		} catch (InvalidMessagePasswordException e) {
			invalidPassowrd=true;
		} catch (MessageNotFoundException e) {
			e.printStackTrace();
		}
		model.addAttribute("invalidPassowrd",invalidPassowrd);
		
		return url;
	}
	
	@RequestMapping("/messageListExcel")
	public String memberListExcel(@RequestParam(defaultValue="1") int pageNumber,
								  Model model){
		String url="messageListExcelView";
		
		try {
			MessageListView messageListView=messageSvc.getMessageList(pageNumber);
			List<Message> messageList=messageListView.getMessageList();
			model.addAttribute("messageList",messageList);			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return url;
	}
	
	@RequestMapping("/messageListPdf")
	public String memberListPdf(@RequestParam(defaultValue="1") int pageNumber,
								  Model model){
		String url="messageListPdfView";
		
		try {
			MessageListView messageListView=messageSvc.getMessageList(pageNumber);
			List<Message> messageList=messageListView.getMessageList();
			model.addAttribute("messageList",messageList);			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return url;
	}
	
	@RequestMapping("/messageList.xml")
	@ResponseBody
	public MessageListXml memberListXml(@RequestParam(defaultValue="1") int pageNumber){
		
		MessageListXml messXml=null;
		try {
			MessageListView messageListView=messageSvc.getMessageList(pageNumber);
			List<Message> messageList=messageListView.getMessageList();
			messXml=new MessageListXml(messageList);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return messXml;
	}
	
	@RequestMapping("/messageList.json")
	@ResponseBody
	public MessageListJson memberListJson(@RequestParam(defaultValue="1") int pageNumber){
		
		MessageListJson messJson=null;
		try {
			MessageListView messageListView=messageSvc.getMessageList(pageNumber);
			List<Message> messageList=messageListView.getMessageList();
			messJson=new MessageListJson(messageList);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return messJson;
	}
}
