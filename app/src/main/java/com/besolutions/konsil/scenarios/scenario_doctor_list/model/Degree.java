package com.besolutions.konsil.scenarios.scenario_doctor_list.model;//
//  Degree.java
//  Model Generated using http://www.jsoncafe.com/
//  Created on February 11, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Degree{

    @SerializedName("degree")
    private String degree;
    @SerializedName("id")
    private String id;

    public void setDegree(String degree){
        this.degree = degree;
    }
    public String getDegree(){
        return this.degree;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }

    /**
     * Instantiate the instance using the passed jsonObject to set the properties values
     */
    public Degree(JSONObject jsonObject){
        if(jsonObject == null){
            return;
        }
        degree = String.valueOf(jsonObject.opt("degree"));
        id = jsonObject.optString("id");
    }

    /**
     * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
     */
    public JSONObject toJsonObject()
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("degree", degree);
            jsonObject.put("id", id);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }

}