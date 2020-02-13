package com.besolutions.konsil.scenarios.scenario_doctor_info.model;//
//  Doctor.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on February 11, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Doctor{

	@SerializedName("bio")
	private Object bio;
	@SerializedName("consultation_price")
	private Object consultationPrice;
	@SerializedName("degree")
	private String degree;
	@SerializedName("id")
	private int id;
	@SerializedName("image_url")
	private String imageUrl;
	@SerializedName("job_title")
	private Object jobTitle;
	@SerializedName("name")
	private String name;
	@SerializedName("rate")
	private int rate;
	@SerializedName("specialist")
	private String specialist;
	@SerializedName("total_consultation")
	private int totalConsultation;
	@SerializedName("total_conversation")
	private int totalConversation;

	public void setBio(Object bio){
		this.bio = bio;
	}
	public Object getBio(){
		return this.bio;
	}
	public void setConsultationPrice(Object consultationPrice){
		this.consultationPrice = consultationPrice;
	}
	public Object getConsultationPrice(){
		return this.consultationPrice;
	}
	public void setDegree(String degree){
		this.degree = degree;
	}
	public String getDegree(){
		return this.degree;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}
	public String getImageUrl(){
		return this.imageUrl;
	}
	public void setJobTitle(Object jobTitle){
		this.jobTitle = jobTitle;
	}
	public Object getJobTitle(){
		return this.jobTitle;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setRate(int rate){
		this.rate = rate;
	}
	public int getRate(){
		return this.rate;
	}
	public void setSpecialist(String specialist){
		this.specialist = specialist;
	}
	public String getSpecialist(){
		return this.specialist;
	}
	public void setTotalConsultation(int totalConsultation){
		this.totalConsultation = totalConsultation;
	}
	public int getTotalConsultation(){
		return this.totalConsultation;
	}
	public void setTotalConversation(int totalConversation){
		this.totalConversation = totalConversation;
	}
	public int getTotalConversation(){
		return this.totalConversation;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Doctor(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		degree = (String) jsonObject.opt("degree");
		imageUrl = (String) jsonObject.opt("image_url");
		name =(String) jsonObject.opt("name");
		rate = jsonObject.optInt("rate");
		specialist = (String) jsonObject.opt("specialist");
		bio = jsonObject.opt("bio");
		consultationPrice = jsonObject.opt("consultation_price");
		id = jsonObject.optInt("id");
		jobTitle = jsonObject.opt("job_title");
		totalConsultation = jsonObject.optInt("total_consultation");
		totalConversation = jsonObject.optInt("total_conversation");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("bio", bio);
			jsonObject.put("consultation_price", consultationPrice);
			jsonObject.put("degree", degree);
			jsonObject.put("id", id);
			jsonObject.put("image_url", imageUrl);
			jsonObject.put("job_title", jobTitle);
			jsonObject.put("name", name);
			jsonObject.put("rate", rate);
			jsonObject.put("specialist", specialist);
			jsonObject.put("total_consultation", totalConsultation);
			jsonObject.put("total_conversation", totalConversation);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}