package com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_files.model;//
//  Consultation.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on February 23, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Consultation{

	@SerializedName("files")
	private JSONArray files;
	@SerializedName("id")
	private int id;

	public void setFiles(JSONArray files){
		this.files = files;
	}
	public JSONArray getFiles(){
		return this.files;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Consultation(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		id = jsonObject.optInt("id");
		files = jsonObject.optJSONArray("files");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("files", files);
			jsonObject.put("id", id);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}