package com.example.projecttest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projecttest.Model.Parent;
import com.example.projecttest.adapter.ExpandableListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShowCategoryActivity extends AppCompatActivity {

    final String URL_GET_DATA = "https://www.test.api.liker.com/get_categories";
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_category);
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        loadHeroes();
    }
    List<String> parentlist;
    HashMap<String, List<String>>  childlist;
    List<String> subid;
    List<String> subcat;
    private void loadHeroes() {
       parentlist = new ArrayList<>();
       childlist = new HashMap<>();
       subid = new ArrayList<>();
       subcat = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_GET_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            // get JSONObject from JSON file
                            JSONObject obj = new JSONObject(response);
                            // fetch JSONArray named users
                            JSONArray userArray = obj.getJSONArray("categories");
                            // implement for loop for getting users list data
                            for (int i = 0; i < userArray.length(); i++) {
                                // create a JSONObject for fetching single user data
                                subcat = new ArrayList<>();
                                JSONObject userDetail = userArray.getJSONObject(i);
                                String category_id = userDetail.getString("category_id");
                                String category_name = userDetail.getString("category_name");
                                Parent parent = new Parent(category_id, category_name);
                                parentlist.add(category_name);
                                JSONArray jArray1 = userDetail.getJSONArray("subcatg");
                                for (int t = 0; t < jArray1.length(); t++) {
                                    JSONObject json_data1 = jArray1.getJSONObject(t);
                                    String sub_category_id = json_data1.getString("sub_category_id");
                                    String sub_category_name = json_data1.getString("sub_category_name");
                                    subcat.add(sub_category_name);
                                    childlist.put(parentlist.get(i),subcat);
                                }
                            }

                            listAdapter = new ExpandableListAdapter(ShowCategoryActivity.this, parentlist, childlist);

                            // setting list adapter
                            expListView.setAdapter(listAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("check_count", "2: " + e.getLocalizedMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("check_count", "3: " + error.getLocalizedMessage());
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}