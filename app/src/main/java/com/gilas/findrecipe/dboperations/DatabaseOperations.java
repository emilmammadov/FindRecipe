package com.gilas.findrecipe.dboperations;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gilas.findrecipe.data.Recipe;
import com.gilas.findrecipe.data.Tag;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseOperations {

    private static final String TAG = "TAG";
    private static final String ip = "192.168.0.11";
    private Context context;

    public DatabaseOperations(Context context) {
        this.context = context;
    }


    public void login(final String username, final String password) {

        String url = "http://" + ip + "/login.php";

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().contains("basarili")) {
                    Toast.makeText(context, "basarili", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "basarisiz" + response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
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


    public void register(final String username, final String password) {

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


    public List<Tag> getAllTags() {
        String url = "http://" + ip + "/get_all_tags.php";
        final List<Tag> tagArrayList = new ArrayList<>();

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
                        tagArrayList.add(new Tag(id, name));


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

        return tagArrayList;
    }


    public void getRecipes(List<Integer> selectedTags, final RecipeWithTagCallback callback) {

        final List<Recipe> justRecipeList = new ArrayList<>();
        final List<Recipe> maybeRecipeList = new ArrayList<>();

        String url = "http://" + ip + "/get_recipe_with_tag.php";
        final Object[] selectedTagsArray = selectedTags.toArray();

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e(TAG, "onResponse: " + response);
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray array = jsonObject.getJSONArray("recipes");

                    for (int i = 0; i < array.length(); i++) {
                        Log.e(TAG, "onResponse: " + i);

                        if (array.getJSONObject(i).has("recipe")) {
                            jsonObject = array.getJSONObject(i).getJSONObject("recipe");
                            justRecipeList.add(new Recipe(
                                    jsonObject.getInt("id"),
                                    jsonObject.getString("title"),
                                    jsonObject.getString("ingredient_list"),
                                    jsonObject.getString("body"),
                                    jsonObject.getInt("person_count"),
                                    jsonObject.getInt("prep_time_sec"),
                                    jsonObject.getInt("cook_time_sec")));
                        } else {
                            jsonObject = array.getJSONObject(i).getJSONObject("deneme");
                            maybeRecipeList.add(new Recipe(
                                    jsonObject.getInt("id"),
                                    jsonObject.getString("title"),
                                    jsonObject.getString("ingredient_list"),
                                    jsonObject.getString("body"),
                                    jsonObject.getInt("person_count"),
                                    jsonObject.getInt("prep_time_sec"),
                                    jsonObject.getInt("cook_time_sec")));
                        }

                    }

                    callback.onSuccess(justRecipeList, maybeRecipeList);

                } catch (JSONException e) {
                    Log.e(TAG, "onERR: " + e);
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
                Map<String, String> params = new HashMap<>();
                params.put("recipes", Arrays.toString(selectedTagsArray));

                return params;
            }
        };


        requestQueue.add(stringRequest);
    }


    public void getAllRecipeTitles(final VolleyCallback callbackTitle) {

        String url = "http://" + ip + "/get_all_recipe_titles.php";
        final List<Recipe> titlesArrayList = new ArrayList<>();

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    JSONArray array = response.getJSONArray("titles");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj = (JSONObject) array.getJSONObject(i).get("title");

                        int id = Integer.parseInt(obj.get("id").toString());
                        String title = obj.get("title").toString();

                        titlesArrayList.add(new Recipe(id, title, null, null, 0, 0, 0));
                    }

                    callbackTitle.onSuccess(titlesArrayList);
                } catch (JSONException e) {
                    Log.e(TAG, "onError: " + e);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: " + error);
            }
        });

        requestQueue.add(jsonObjectRequest);
    }


    public void getRecipe(final int id, final RecipeCallback callback) {
        String url = "http://" + ip + "/get_recipe.php";


        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    Log.e(TAG, "onResponse: " + obj.get("title").toString());
                    Recipe recipe = new Recipe(
                            Integer.parseInt(obj.get("id").toString()),
                            obj.get("title").toString(),
                            obj.get("ingredient_list").toString(),
                            obj.get("body").toString(),
                            Integer.parseInt(obj.get("person_count").toString()),
                            Integer.parseInt(obj.get("prep_time_sec").toString()),
                            Integer.parseInt(obj.get("cook_time_sec").toString())
                    );

                    callback.onSuccess(recipe);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onError: " + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", id + "");

                return params;
            }
        };

        requestQueue.add(stringRequest);
    }


    public interface VolleyCallback {
        void onSuccess(List<Recipe> result);
    }

    public interface RecipeWithTagCallback {
        void onSuccess(List<Recipe> justRecipes, List<Recipe> maybeRecipes);
    }

    public interface RecipeCallback {
        void onSuccess(Recipe result);
    }


}
