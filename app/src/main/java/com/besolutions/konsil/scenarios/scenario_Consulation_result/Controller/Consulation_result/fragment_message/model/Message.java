package com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_message.model;//
//  Message.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on February 17, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Message{

	@SerializedName("created_at")
	private String createdAt;
	@SerializedName("id")
	private int id;
	@SerializedName("message")
	private String message;
	@SerializedName("name")
	private String name;
	@SerializedName("user_image")
	private String userImage;

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}
	public String getCreatedAt(){
		return this.createdAt;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setMessage(String message){
		this.message = message;
	}
	public String getMessage(){
		return this.message;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setUserImage(String userImage){
		this.userImage = userImage;
	}
	public String getUserImage(){
		return this.userImage;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Message(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		createdAt = jsonObject.optString("created_at");
		message = jsonObject.optString("message");
		name = jsonObject.optString("name");
		userImage = jsonObject.optString("user_image");
		id = jsonObject.optInt("id");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("created_at", createdAt);
			jsonObject.put("id", id);
			jsonObject.put("message", message);
			jsonObject.put("name", name);
			jsonObject.put("user_image", userImage);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}