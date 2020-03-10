package com.besolutions.konsil.scenarios.scenario_doctor_list.model;//
//  Doctor.java
//  Model Generated using http://www.jsoncafe.com/
//  Created on February 11, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Doctor{

    @SerializedName("degree")
    private String degree;
    @SerializedName("id")
    private int id;
    @SerializedName("image_url")
    private String imageUrl;
    @SerializedName("name")
    private String name;
    @SerializedName("rate")
    private int rate;
    @SerializedName("lang")
    private String lang;

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
    public void setLang(String lang){
        this.lang = lang;
    }
    public String getLang(){
        return this.lang;
    }

    /**
     * Instantiate the instance using the passed jsonObject to set the properties values
     */
    public Doctor(JSONObject jsonObject){
        if(jsonObject == null){
            return;
        }
        degree = String.valueOf(jsonObject.opt("degree"));
        imageUrl = String.valueOf(jsonObject.opt("image_url"));
        name = String.valueOf(jsonObject.opt("name"));
        rate = jsonObject.optInt("rate");
        id = jsonObject.optInt("id");
        lang = String.valueOf(jsonObject.opt("lang"));
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
            jsonObject.put("image_url", imageUrl);
            jsonObject.put("name", name);
            jsonObject.put("rate", rate);
            jsonObject.put("lang", lang);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }

}