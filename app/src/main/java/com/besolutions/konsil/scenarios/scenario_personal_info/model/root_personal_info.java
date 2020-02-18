package com.besolutions.konsil.scenarios.scenario_personal_info.model;//
//  root_personal_info.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on February 18, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class root_personal_info{

	@SerializedName("status")
	private int status;
	@SerializedName("userInfo")
	private UserInfo userInfo;

	public void setStatus(int status){
		this.status = status;
	}
	public int getStatus(){
		return this.status;
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
	public root_personal_info(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		status = jsonObject.optInt("status");
		userInfo = new UserInfo(jsonObject.optJSONObject("userInfo"));
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("status", status);
			jsonObject.put("userInfo", userInfo.toJsonObject());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}