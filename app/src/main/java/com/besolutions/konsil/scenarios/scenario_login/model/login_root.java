package com.besolutions.konsil.scenarios.scenario_login.model;//
//  login_root.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on February 18, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class login_root{

	@SerializedName("token")
	private String token;
	@SerializedName("userInfo")
	private UserInfo userInfo;

	public void setToken(String token){
		this.token = token;
	}
	public String getToken(){
		return this.token;
	}
	public void setUserInfo(UserInfo userInfo){
		this.userInfo = userInfo;
	}
	public UserInfo getUserInfo(){
		return this.userInfo;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public login_root(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		token = jsonObject.optString("token");
		userInfo = new UserInfo(jsonObject.optJSONObject("userInfo"));
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