package com.besolutions.konsil.scenarios.scenario_mian_page.model;//
//  all_specialitiesDatum.java
//  Model Generated using http://www.jsoncafe.com/
//  Created on February 10, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class all_specialitiesDatum{

    @SerializedName("id")
    private int id;
    @SerializedName("image_url")
    private String imageUrl;
    @SerializedName("title")
    private String title;

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
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }

    /**
     * Instantiate the instance using the passed jsonObject to set the properties values
     */
    public all_specialitiesDatum(JSONObject jsonObject){
        if(jsonObject == null){
            return;
        }
        imageUrl = jsonObject.optString("image_url");
        title = jsonObject.optString("title");
        id = jsonObject.optInt("id");
    }



    /**
     * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
     */
    public JSONObject toJsonObject()
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", id);
            jsonObject.put("image_url", imageUrl);
            jsonObject.put("title", title);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }

}