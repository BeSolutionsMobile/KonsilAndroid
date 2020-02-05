package com.besolutions.konsil.NetworkLayer;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.besolutions.konsil.local_data.saved_data;

import org.json.JSONException;
import org.json.JSONObject;

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


    void makeSimpleRequest(String url) {

        networkInterface.OnStart();

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        ResponseModel model = new ResponseModel(0, response);

                        networkInterface.OnResponse(model);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        networkInterface.OnError(error);
                    }
                });
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
            /*  @Override
              protected Map<String, String> getParams(){
                  HashMap<String, String> hashMap = new HashMap<>();
                  if(params != null && values!=null)
                  {
                      for(int i = 0; i<params.size();i++)
                      {
                          Log.e("params",params.get(i)+","+values.get(i));
                          hashMap.put(params.get(i),values.get(i));
                      }
                  }
                  return hashMap;
              }
  */
            @Override
            public Map<String, String> getHeaders() {
                final HashMap<String, String> header = new HashMap<>();
                header.put("Accept", "application/json");
                header.put("Content-Type", "application/json");
                header.put("Authorization", "Bearer " + "jifjiefi");
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
