package com.besolutions.konsil.scenarios.scenario_checkout_credit.model;//
//  secret_code.java
//  Model Generated using http://www.jsoncafe.com/
//  Created on April 14, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class secret_code{

	@SerializedName("client_secret")
	private String clientSecret;
	@SerializedName("status")
	private int status;

	public void setClientSecret(String clientSecret){
		this.clientSecret = clientSecret;
	}
	public String getClientSecret(){
		return this.clientSecret;
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
	public secret_code(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		clientSecret = jsonObject.optString("client_secret");
		status = jsonObject.optInt("status");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("client_secret", clientSecret);
			jsonObject.put("status", status);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}