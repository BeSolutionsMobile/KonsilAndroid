package com.besolutions.konsil.scenarios.scenario_my_consultations.model;//
//  Datum.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on February 19, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Datum{

	@SerializedName("id")
	private int id;
	@SerializedName("image")
	private String image;
	@SerializedName("name")
	private String name;
	@SerializedName("price")
	private Object price;
	@SerializedName("status")
	private String status;
    @SerializedName("doctor_id")
    private int doctor_id;
	@SerializedName("type")
	private String type;

	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setImage(String image){
		this.image = image;
	}
	public String getImage(){
		return this.image;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setPrice(Object price){
		this.price = price;
	}
	public Object getPrice(){
		return this.price;
	}
	public void setStatus(String status){
		this.status = status;
	}
	public String getStatus(){
		return this.status;
	}
	public void setType(String type){
		this.type = type;
	}
    public void setDocId(int doctor_id){
        this.doctor_id = doctor_id;
    }
    public int getDocId(){
        return this.doctor_id;
    }
	public String getType(){
		return this.type;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Datum(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		image = jsonObject.optString("image");
		name = jsonObject.optString("name");
		status = jsonObject.optString("status");
		type = jsonObject.optString("type");
		id = jsonObject.optInt("id");
		doctor_id = jsonObject.optInt("doctor_id");
		price = jsonObject.optString("price");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("id", id);
			jsonObject.put("image", image);
			jsonObject.put("name", name);
			jsonObject.put("price", price);
            jsonObject.put("doctor_id", doctor_id);
            jsonObject.put("status", status);
			jsonObject.put("type", type);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}