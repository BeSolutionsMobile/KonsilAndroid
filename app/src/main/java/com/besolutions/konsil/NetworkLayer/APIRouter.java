package com.besolutions.konsil.NetworkLayer;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.airbnb.lottie.L;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.besolutions.konsil.local_data.saved_data;
import com.besolutions.konsil.scenarios.scenario_my_consultations.pattern.my_consultations_adapter;
import com.besolutions.konsil.utils.firebase_storage;
import com.besolutions.konsil.utils.firebase_storage_pdf;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @desc Java APIRouter Class for Managing Different Types of Network Calls
 */

public class APIRouter {

    private Context context;
    private NetworkInterface networkInterface;


    APIRouter(Context context, NetworkInterface networkInterface) {
        this.context = context;
        this.networkInterface = networkInterface;
    }


    void performRequest(final String URL, final List<String> params, final List<String> values, final int method, final int responseCode) {
        Log.e("url", URL);
        networkInterface.OnStart();

        StringRequest stringRequest = new StringRequest(method, URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ResponseModel model = new ResponseModel(responseCode, response);
                        networkInterface.OnResponse(model);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                networkInterface.OnError(error);

            }

        }) {

            @Override
            protected Map<String, String> getParams() {
                HashMap<String, String> hashMap = new HashMap<>();
                if (params != null && values != null) {
                    for (int i = 0; i < params.size(); i++) {
                        hashMap.put(params.get(i), values.get(i));
                    }
                }

                return hashMap;
            }
        };
        stringRequest.setShouldCache(false);
        RequestHandler.getInstance(context).addToRequestQueue(stringRequest);
    }


    public void makeAdvancedRequest(String url, final int method, final List<String> params, final List<String> values, final HashMap<String, String> body) throws JSONException {
        Log.e("url", url);

        networkInterface.OnStart();

        JSONObject object = new JSONObject();
        if (params != null && values != null) {
            for (int index = 0; index < params.size(); index++) {
                object.put(params.get(index), values.get(index));
                Log.e("paramms", params.get(index) + "000" + values.get(index));

            }
        }

        JsonObjectRequest sr = new JsonObjectRequest(method, url, object,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        ResponseModel model = new ResponseModel(0, response);

                        networkInterface.OnResponse(model);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                networkInterface.OnError(error);

            }

        }) {

            @Override
            public Map<String, String> getHeaders() {
                final HashMap<String, String> header = new HashMap<>();
                header.put("Accept", "application/json");
                header.put("Content-Type", "application/json");
                header.put("Authorization", "Bearer " + new saved_data().get_token(context));
                return header;
            }


        };
        sr.setShouldCache(false);
        sr.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestHandler.getInstance(context).addToRequestQueue(sr);
    }

    //CUSTOM SEND API FILTER
    public void makeAdvancedRequest_array(String url, final int method, String special_id, List<Integer> num, String rate, final HashMap<String, String> body) throws JSONException {
        Log.e("url", url);

        networkInterface.OnStart();

        JSONObject object = new JSONObject();

        JSONArray jsonArray = new JSONArray();
        for (Integer n : num) {
            jsonArray.put(n);
        }

        object.put("speciality_id", special_id);
        object.put("degree_id", jsonArray);
        object.put("rate", rate);

        Log.e("speciality_id", "" + special_id + "degree_id0000" + jsonArray + rate);


        JsonObjectRequest sr = new JsonObjectRequest(method, url, object,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        ResponseModel model = new ResponseModel(0, response);

                        networkInterface.OnResponse(model);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                networkInterface.OnError(error);

            }

        }) {

            @Override
            public Map<String, String> getHeaders() {
                final HashMap<String, String> header = new HashMap<>();
                header.put("Accept", "application/json");
                header.put("Content-Type", "application/json");
                header.put("Authorization", "Bearer " + new saved_data().get_token(context));
                return header;
            }


        };
        sr.setShouldCache(false);
        sr.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestHandler.getInstance(context).addToRequestQueue(sr);
    }

    //CUSTOM SEND API FOR SEND CONSULTATION
    public void makeAdvancedRequest_addconsultation(String url, final int method, String title, String details, String doc_id, final HashMap<String, String> body) throws JSONException {
        Log.e("url", url);

        networkInterface.OnStart();

        JSONObject object = new JSONObject();

        //image
        JSONArray jsonArray = new JSONArray();
        //CHECK FOR ARRAY OF IMAGES IF NULL OR NOT
        if (firebase_storage.images == null) {
            jsonArray.put("noImages");
        } else {
            for (String n : firebase_storage.images) {
                jsonArray.put(n);
            }
        }

        //pdf
        JSONArray jsonArray_pdf = new JSONArray();
        //CHECK FOR ARRAY OF IMAGES IF NULL OR NOT
        if (firebase_storage_pdf.pdf == null) {
            jsonArray_pdf.put("noFiles");
        } else {
            for (String n : firebase_storage_pdf.pdf) {
                jsonArray_pdf.put(n);
            }
        }


        //SEND FILES TO FILES
        object.put("title", title);
        object.put("details", details);
        object.put("doctor_id", doc_id);
        object.put("images", jsonArray);
        object.put("files", jsonArray_pdf);


        JsonObjectRequest sr = new JsonObjectRequest(method, url, object,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        ResponseModel model = new ResponseModel(0, response);

                        networkInterface.OnResponse(model);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                networkInterface.OnError(error);

            }

        }) {

            @Override
            public Map<String, String> getHeaders() {
                final HashMap<String, String> header = new HashMap<>();
                header.put("Accept", "application/json");
                header.put("Content-Type", "application/json");
                header.put("Authorization", "Bearer " + new saved_data().get_token(context));
                return header;
            }


        };
        sr.setShouldCache(false);
        sr.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestHandler.getInstance(context).addToRequestQueue(sr);
    }

    public void makeAdvancedRequest_uplaodconsultation_files(String url, final int method) throws JSONException {
        Log.e("url", url);

        networkInterface.OnStart();

        JSONObject object = new JSONObject();

        //image
        JSONArray jsonArray = new JSONArray();
        //CHECK FOR ARRAY OF IMAGES IF NULL OR NOT
        if (firebase_storage.images == null) {
            jsonArray.put("noImages");
        } else {
            for (String n : firebase_storage.images) {
                jsonArray.put(n);
            }
        }

        //pdf
        JSONArray jsonArray_pdf = new JSONArray();
        //CHECK FOR ARRAY OF IMAGES IF NULL OR NOT
        if (firebase_storage_pdf.pdf == null) {
            jsonArray_pdf.put("noFiles");
        } else {
            for (String n : firebase_storage_pdf.pdf) {
                jsonArray_pdf.put(n);
            }
        }

        object.put("consultation_id", my_consultations_adapter.id);
        object.put("images", jsonArray);
        object.put("files", jsonArray_pdf);


        JsonObjectRequest sr = new JsonObjectRequest(method, url, object,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        ResponseModel model = new ResponseModel(0, response);

                        networkInterface.OnResponse(model);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                networkInterface.OnError(error);

            }

        }) {

            @Override
            public Map<String, String> getHeaders() {
                final HashMap<String, String> header = new HashMap<>();
                header.put("Accept", "application/json");
                header.put("Content-Type", "application/json");
                header.put("Authorization", "Bearer " + new saved_data().get_token(context));
                return header;
            }


        };
        sr.setShouldCache(false);
        sr.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestHandler.getInstance(context).addToRequestQueue(sr);
    }

}
