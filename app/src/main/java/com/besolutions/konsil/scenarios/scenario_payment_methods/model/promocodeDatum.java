package com.besolutions.konsil.scenarios.scenario_payment_methods.model;//
//  promocodeDatum.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on August 11, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class promocodeDatum{

	@SerializedName("code")
	private String code;
	@SerializedName("discount")
	private String discount;
	@SerializedName("id")
	private int id;

	public void setCode(String code){
		this.code = code;
	}
	public String getCode(){
		return this.code;
	}
	public void setDiscount(String discount){
		this.discount = discount;
	}
	public String getDiscount(){
		return this.discount;
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
	public promocodeDatum(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		code = jsonObject.optString("code");
		discount = jsonObject.optString("discount");
		id = jsonObject.optInt("id");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("code", code);
			jsonObject.put("discount", discount);
			jsonObject.put("id", id);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}