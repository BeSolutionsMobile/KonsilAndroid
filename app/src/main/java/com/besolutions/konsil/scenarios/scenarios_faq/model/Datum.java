package com.besolutions.konsil.scenarios.scenarios_faq.model;//
//  Datum.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on February 20, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Datum{

	@SerializedName("answer")
	private String answer;
	@SerializedName("id")
	private int id;
	@SerializedName("question")
	private String question;

	public void setAnswer(String answer){
		this.answer = answer;
	}
	public String getAnswer(){
		return this.answer;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setQuestion(String question){
		this.question = question;
	}
	public String getQuestion(){
		return this.question;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Datum(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		answer = jsonObject.optString("answer");
		question = jsonObject.optString("question");
		id = jsonObject.optInt("id");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("answer", answer);
			jsonObject.put("id", id);
			jsonObject.put("question", question);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}