package com.gilas.findrecipe.Database;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DatabaseOperations {

    private static final String TAG = "TAG";
    private static final String ip = "192.168.0.11";

    public void login(final Context context, final String username, final String password) {

        String url = "http://" + ip + "/login.php";

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().contains("basarili")) {
                    Toast.makeText(context, "basarili", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "basarisiz" + response, Toast.LENGTH_SHORT).show();
                    Log.e("TAGTAG", "onResponse: " + response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "hata: " + error.toString(), Toast.LENGTH_SHORT).show();
                Log.e("TAG", "onErrorResponse: " + error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);

                return params;
            }
        };

        requestQueue.add(stringRequest);
    }


    public void register(final Context context, final String username, final String password) {

        String url = "http://" + ip + "/register.php";


        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onResponse: " + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);

                return params;
            }
        };

        requestQueue.add(stringRequest);
    }


    public ArrayList<Tags> getAllTags(final Context context) {
        String url = "http://" + ip + "/get_all_tags.php";
        final ArrayList<Tags> tagsArrayList = new ArrayList<>();

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    JSONArray array = response.getJSONArray("tags");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj = (JSONObject) array.getJSONObject(i).get("tag");
                        int id = Integer.parseInt(obj.get("id").toString());
                        String name = obj.get("name").toString();
                        tagsArrayList.add(new Tags(id, name));


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest);

        return tagsArrayList;
    }


    public ArrayList<Recipes> getRecipes(final Context context, ArrayList<Integer> selectedTags) {

        final ArrayList<Recipes> recipesArrayList = new ArrayList<>();

        String url = "http://" + ip + "/get_recipe.php";
        final Object[] selectedTagsArray = selectedTags.toArray();

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("recipes");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj = (JSONObject) array.getJSONObject(i).get("recipe");
                        int id = Integer.parseInt(obj.get("id").toString());
                        String title = obj.get("title").toString();
                        String body = obj.get("body").toString();
                        recipesArrayList.add(new Recipes(id, title, body));


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: " + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Log.e(TAG, "getParams: ");
                Map<String, String> params = new HashMap<>();
                params.put("recipes", Arrays.toString(selectedTagsArray));

                return params;
            }
        };

        requestQueue.add(jsonObjectRequest);
        
        return recipesArrayList;
    }


}
