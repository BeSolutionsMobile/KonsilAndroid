package com.besolutions.konsil.scenarios.scenario_online_conversation.Controller.info.model;//
//  root_conversation_details.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on February 20, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class root_conversation_details{

	@SerializedName("data")
	private Datum data;
	@SerializedName("stats")
	private int stats;

	public void setData(Datum data){
		this.data = data;
	}
	public Datum getData(){
		return this.data;
	}
	public void setStats(int stats){
		this.stats = stats;
	}
	public int getStats(){
		return this.stats;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public root_conversation_details(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		stats = jsonObject.optInt("stats");
		data = new Datum(jsonObject.optJSONObject("data"));
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("stats", stats);
			jsonObject.put("data", data.toJsonObject());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}