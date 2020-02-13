package com.besolutions.konsil.scenarios.scenario_doctor_info.model;//
//  root_details.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on February 11, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class root_details{

	@SerializedName("doctor")
	private Doctor doctor;
	@SerializedName("status")
	private int status;

	public void setDoctor(Doctor doctor){
		this.doctor = doctor;
	}
	public Doctor getDoctor(){
		return this.doctor;
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
	public root_details(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		status = jsonObject.optInt("status");
		doctor = new Doctor(jsonObject.optJSONObject("doctor"));
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("status", status);
			jsonObject.put("doctor", doctor.toJsonObject());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}