package com.besolutions.konsil.scenarios.scenario_my_consultations.model;//
//  root_consultation.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on February 19, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class root_consultation{

	@SerializedName("data")
	private Datum[] data;
	@SerializedName("status")
	private String status;

	public void setData(Datum[] data){
		this.data = data;
	}
	public Datum[] getData(){
		return this.data;
	}
	public void setStatus(String status){
		this.status = status;
	}
	public String getStatus(){
		return this.status;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public root_consultation(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		status = jsonObject.optString("status");
		JSONArray dataJsonArray = jsonObject.optJSONArray("data");
		if(dataJsonArray != null){
			ArrayList<Datum> dataArrayList = new ArrayList<>();
			for (int i = 0; i < dataJsonArray.length(); i++) {
				JSONObject dataObject = dataJsonArray.optJSONObject(i);
				dataArrayList.add(new Datum(dataObject));
			}
			data = (Datum[]) dataArrayList.toArray();
		}
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("status", status);
			if(data != null && data.length > 0){
				JSONArray dataJsonArray = new JSONArray();
				for(Datum dataElement : data){
					dataJsonArray.put(dataElement.toJsonObject());
				}
				jsonObject.put("data", dataJsonArray);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}