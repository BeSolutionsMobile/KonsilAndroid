package com.besolutions.konsil.scenarios.scenario_sign_up.model;//
//  signup_model.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on February 5, 2020

import org.json.*;
import java.util.*;


public class signup_model{

	private String token;
	private signup_modelUserInfo userInfo;

	public void setToken(String token){
		this.token = token;
	}
	public String getToken(){
		return this.token;
	}
	public void setUserInfo(signup_modelUserInfo userInfo){
		this.userInfo = userInfo;
	}
	public signup_modelUserInfo getUserInfo(){
		return this.userInfo;
	}


	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public signup_model(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		token = jsonObject.optString("token");
		userInfo = new signup_modelUserInfo(jsonObject.optJSONObject("userInfo"));
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("token", token);
			jsonObject.put("userInfo", userInfo.toJsonObject());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}