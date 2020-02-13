package com.besolutions.konsil.scenarios.scenario_doctor_list.model;//
//  root.java
//  Model Generated using http://www.jsoncafe.com/
//  Created on February 11, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class root{

    @SerializedName("degrees")
    private Degree[] degrees;
    @SerializedName("doctors")
    private Doctor[] doctors;
    @SerializedName("status")
    private int status;

    public void setDegrees(Degree[] degrees){
        this.degrees = degrees;
    }
    public Degree[] getDegrees(){
        return this.degrees;
    }
    public void setDoctors(Doctor[] doctors){
        this.doctors = doctors;
    }
    public Doctor[] getDoctors(){
        return this.doctors;
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
    public root(JSONObject jsonObject){
        if(jsonObject == null){
            return;
        }
        status = jsonObject.optInt("status");
        JSONArray degreesJsonArray = jsonObject.optJSONArray("degrees");
        if(degreesJsonArray != null){
            ArrayList<Degree> degreesArrayList = new ArrayList<>();
            for (int i = 0; i < degreesJsonArray.length(); i++) {
                JSONObject degreesObject = degreesJsonArray.optJSONObject(i);
                degreesArrayList.add(new Degree(degreesObject));
            }
            degrees = (Degree[]) degreesArrayList.toArray();
        }
        JSONArray doctorsJsonArray = jsonObject.optJSONArray("doctors");
        if(doctorsJsonArray != null){
            ArrayList<Doctor> doctorsArrayList = new ArrayList<>();
            for (int i = 0; i < doctorsJsonArray.length(); i++) {
                JSONObject doctorsObject = doctorsJsonArray.optJSONObject(i);
                doctorsArrayList.add(new Doctor(doctorsObject));
            }
            doctors = (Doctor[]) doctorsArrayList.toArray();
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
            if(degrees != null && degrees.length > 0){
                JSONArray degreesJsonArray = new JSONArray();
                for(Degree degreesElement : degrees){
                    degreesJsonArray.put(degreesElement.toJsonObject());
                }
                jsonObject.put("degrees", degreesJsonArray);
            }
            if(doctors != null && doctors.length > 0){
                JSONArray doctorsJsonArray = new JSONArray();
                for(Doctor doctorsElement : doctors){
                    doctorsJsonArray.put(doctorsElement.toJsonObject());
                }
                jsonObject.put("doctors", doctorsJsonArray);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }

}