package com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_message.model;//
//  root_msg.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on February 17, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class root_msg{

	@SerializedName("messages")
	private Message[] messages;
	@SerializedName("status")
	private int status;

	public void setMessages(Message[] messages){
		this.messages = messages;
	}
	public Message[] getMessages(){
		return this.messages;
	}
	public void setStatus(int status){
		this.status = status;
	}
	public int getStatus(){
		return this.status;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public root_msg(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		status = jsonObject.optInt("status");
		JSONArray messagesJsonArray = jsonObject.optJSONArray("messages");
		if(messagesJsonArray != null){
			ArrayList<Message> messagesArrayList = new ArrayList<>();
			for (int i = 0; i < messagesJsonArray.length(); i++) {
				JSONObject messagesObject = messagesJsonArray.optJSONObject(i);
				messagesArrayList.add(new Message(messagesObject));
			}
			messages = (Message[]) messagesArrayList.toArray();
		}
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("status", status);
			if(messages != null && messages.length > 0){
				JSONArray messagesJsonArray = new JSONArray();
				for(Message messagesElement : messages){
					messagesJsonArray.put(messagesElement.toJsonObject());
				}
				jsonObject.put("messages", messagesJsonArray);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}