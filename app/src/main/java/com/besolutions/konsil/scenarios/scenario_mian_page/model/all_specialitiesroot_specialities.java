package com.besolutions.konsil.scenarios.scenario_mian_page.model;//
//  all_specialitiesroot_specialities.java
//  Model Generated using http://www.jsoncafe.com/
//  Created on February 10, 2020

import org.json.*;
import java.util.*;


import com.google.gson.annotations.SerializedName;


public class all_specialitiesroot_specialities{

    @SerializedName("data")
    private all_specialitiesDatum[] data;
    @SerializedName("status")
    private int status;

    public void setData(all_specialitiesDatum[] data){
        this.data = data;
    }
    public all_specialitiesDatum[] getData(){
        return this.data;
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
    public all_specialitiesroot_specialities(JSONObject jsonObject){
        if(jsonObject == null){
            return;
        }
        status = jsonObject.optInt("status");
        JSONArray dataJsonArray = jsonObject.optJSONArray("data");
        if(dataJsonArray != null){
            ArrayList<all_specialitiesDatum> dataArrayList = new ArrayList<>();
            for (int i = 0; i < dataJsonArray.length(); i++) {
                JSONObject dataObject = dataJsonArray.optJSONObject(i);
                dataArrayList.add(new all_specialitiesDatum(dataObject));
            }
            data = (all_specialitiesDatum[]) dataArrayList.toArray();
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
                for(all_specialitiesDatum dataElement : data){
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