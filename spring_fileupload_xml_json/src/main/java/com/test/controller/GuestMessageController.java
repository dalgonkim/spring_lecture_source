package com.test.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.dto.GuestMessage;
import com.test.dto.GuestMessageList;
import com.test.dto.GuestMessageList2;

@Controller
public class GuestMessageController {

	@RequestMapping(value = "/guest/post", method = RequestMethod.POST)
	@ResponseBody
	public GuestMessageList postXml(@RequestBody GuestMessageList messageList) {
		return messageList;
	}

	@RequestMapping(value = "/guest/post.json", method = RequestMethod.POST)
	@ResponseBody
	public GuestMessageList2 postJson(@RequestBody Map<String, Object> data) {

		List<GuestMessage> messageList = new ArrayList<GuestMessage>();

		List<Map> dataList = (List<Map>) data.get("messages");

		for (Map message : dataList) {
			int id = (Integer) message.get("id");
			String msg = (String) message.get("content");
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date creationTime = null;
			try {
				creationTime = transFormat.parse((String) message.get("creationTime"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			GuestMessage guestMessage = new GuestMessage(id, msg, creationTime);

			messageList.add(guestMessage);

			System.out.println(message.get("id"));
			System.out.println(message.get("content"));

		}
		return new GuestMessageList2(messageList);
	}

	@RequestMapping(value = "/guest/json")
	@ResponseBody
	public GuestMessageList2 listJson() {
		return getMessageList2();
	}

	@RequestMapping(value = "/guest/xml")
	@ResponseBody
	public GuestMessageList listXml() {
		return getMessageList();
	}

	private GuestMessageList2 getMessageList2() {
		List<GuestMessage> messages = Arrays.asList(new GuestMessage(1, "메시지",
				new Date()), new GuestMessage(2, "메시지2", new Date()));

		return new GuestMessageList2(messages);
	}

	private GuestMessageList getMessageList() {
		List<GuestMessage> messages = Arrays.asList(new GuestMessage(1, "메시지",
				new Date()), new GuestMessage(2, "메시지2", new Date()));

		return new GuestMessageList(messages);
	}
}
