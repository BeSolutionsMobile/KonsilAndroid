package com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_files.model;//
//  root_download_files.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on February 23, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class root_download_files{

	@SerializedName("consultation")
	private Consultation consultation;
	@SerializedName("status")
	private int status;

	public void setConsultation(Consultation consultation){
		this.consultation = consultation;
	}
	public Consultation getConsultation(){
		return this.consultation;
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
	public root_download_files(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		status = jsonObject.optInt("status");
		consultation = new Consultation(jsonObject.optJSONObject("consultation"));
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("status", status);
			jsonObject.put("consultation", consultation.toJsonObject());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}