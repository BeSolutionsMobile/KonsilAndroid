package com.besolutions.konsil.scenarios.scenario_Consulation_request.model;//
//  consultation_reserve.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on February 19, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class consultation_reserve{

	@SerializedName("id")
	private int id;
	@SerializedName("message")
	private String message;
	@SerializedName("status")
	private int status;

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
	public void setStatus(int status){
		this.status = status;
	}
	public int getStatus(){
		return this.status;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public consultation_reserve(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		message = jsonObject.optString("message");
		id = jsonObject.optInt("id");
		status = jsonObject.optInt("status");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("id", id);
			jsonObject.put("message", message);
			jsonObject.put("status", status);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}