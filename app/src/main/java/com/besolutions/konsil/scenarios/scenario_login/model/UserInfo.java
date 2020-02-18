package com.besolutions.konsil.scenarios.scenario_login.model;//
//  UserInfo.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on February 18, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class UserInfo{

	@SerializedName("code")
	private String code;
	@SerializedName("email")
	private String email;
	@SerializedName("id")
	private int id;
	@SerializedName("image_url")
	private Object imageUrl;
	@SerializedName("lang")
	private String lang;
	@SerializedName("mobile_token")
	private String mobileToken;
	@SerializedName("name")
	private String name;
	@SerializedName("phone")
	private String phone;
	@SerializedName("user_type_id")
	private String userTypeId;

	public void setCode(String code){
		this.code = code;
	}
	public String getCode(){
		return this.code;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public String getEmail(){
		return this.email;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setImageUrl(Object imageUrl){
		this.imageUrl = imageUrl;
	}
	public Object getImageUrl(){
		return this.imageUrl;
	}
	public void setLang(String lang){
		this.lang = lang;
	}
	public String getLang(){
		return this.lang;
	}
	public void setMobileToken(String mobileToken){
		this.mobileToken = mobileToken;
	}
	public String getMobileToken(){
		return this.mobileToken;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}
	public String getPhone(){
		return this.phone;
	}
	public void setUserTypeId(String userTypeId){
		this.userTypeId = userTypeId;
	}
	public String getUserTypeId(){
		return this.userTypeId;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public UserInfo(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		code = jsonObject.optString("code");
		email = jsonObject.optString("email");
		lang = jsonObject.optString("lang");
		mobileToken = jsonObject.optString("mobile_token");
		name = jsonObject.optString("name");
		phone = jsonObject.optString("phone");
		userTypeId = jsonObject.optString("user_type_id");
		id = jsonObject.optInt("id");
		imageUrl = jsonObject.optString("image_url");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("code", code);
			jsonObject.put("email", email);
			jsonObject.put("id", id);
			jsonObject.put("image_url", imageUrl);
			jsonObject.put("lang", lang);
			jsonObject.put("mobile_token", mobileToken);
			jsonObject.put("name", name);
			jsonObject.put("phone", phone);
			jsonObject.put("user_type_id", userTypeId);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}