package com.besolutions.konsil.scenarios.scenario_online_conversation.Controller.info.model;//
//  Datum.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on February 20, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Datum{

	@SerializedName("conversation_link")
	private String conversationLink;
	@SerializedName("date")
	private String date;
	@SerializedName("doctor")
	private String doctor;
	@SerializedName("id")
	private int id;
	@SerializedName("status")
	private String status;

	public void setConversationLink(String conversationLink){
		this.conversationLink = conversationLink;
	}
	public String getConversationLink(){
		return this.conversationLink;
	}
	public void setDate(String date){
		this.date = date;
	}
	public String getDate(){
		return this.date;
	}
	public void setDoctor(String doctor){
		this.doctor = doctor;
	}
	public String getDoctor(){
		return this.doctor;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setStatus(String status){
		this.status = status;
	}
	public String getStatus(){
		return this.status;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Datum(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		conversationLink = jsonObject.optString("conversation_link");
		date = jsonObject.optString("date");
		doctor = jsonObject.optString("doctor");
		status = jsonObject.optString("status");
		id = jsonObject.optInt("id");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("conversation_link", conversationLink);
			jsonObject.put("date", date);
			jsonObject.put("doctor", doctor);
			jsonObject.put("id", id);
			jsonObject.put("status", status);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}