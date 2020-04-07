package com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_files.model;//
//  Consultation.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on April 7, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Consultation{

	@SerializedName("id")
	private int id;
	@SerializedName("name")
	private String name;
	@SerializedName("url")
	private String url;

	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setUrl(String url){
		this.url = url;
	}
	public String getUrl(){
		return this.url;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Consultation(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		name = jsonObject.optString("name");
		url = jsonObject.optString("url");
		id = jsonObject.optInt("id");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("id", id);
			jsonObject.put("name", name);
			jsonObject.put("url", url);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}