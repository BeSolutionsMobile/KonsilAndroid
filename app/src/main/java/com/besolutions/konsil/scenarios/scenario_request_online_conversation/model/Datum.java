package com.besolutions.konsil.scenarios.scenario_request_online_conversation.model;//
//  Datum.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on February 19, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Datum{

	@SerializedName("from")
	private String from;
	@SerializedName("id")
	private int id;
	@SerializedName("to")
	private String to;

	public void setFrom(String from){
		this.from = from;
	}
	public String getFrom(){
		return this.from;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setTo(String to){
		this.to = to;
	}
	public String getTo(){
		return this.to;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Datum(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		from = jsonObject.optString("from");
		to = jsonObject.optString("to");
		id = jsonObject.optInt("id");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("from", from);
			jsonObject.put("id", id);
			jsonObject.put("to", to);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}