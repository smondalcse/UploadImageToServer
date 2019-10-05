package com.example.uploadimagetoserver;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageListActivity extends AppCompatActivity {

    List<ImageModel> imageModelList = new ArrayList<>();;

    String image_path = "http://192.168.10.12/upload_image_with_volley/retriveimage.php";

    //the recyclerview
    RecyclerView recyclerView;

    private static final String TAG = "ImageListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);


        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Log.i(TAG, "onCreate: image_path" + image_path);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, image_path,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray imgArray = new JSONArray(response);

                            for(int i = 0; i < imgArray.length(); i++)
                            {
                                JSONObject imgObj = imgArray.getJSONObject(i);
                                String id = imgObj.getString("id");
                                String name = imgObj.getString("name");
                                String imgUrl = imgObj.getString("url");

                                ImageModel imageModel = new ImageModel(id, name, imgUrl);
                                imageModelList.add(imageModel);

//                                if(i == 1)
//                                {
//                                    Glide.with(ImageListActivity.this).load(imgUrl).into(img1);
//                                    img1_title.setText(name);
//                                } else {
//                                    Glide.with(ImageListActivity.this).load(imgUrl).into(img2);
//                                    img2_title.setText(name);
//                                }
                            }

                            ImageRecyclerViewAdapter adapter = new ImageRecyclerViewAdapter(ImageListActivity.this, imageModelList);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                }
        });

        MySingleton.getmySingleton(ImageListActivity.this).addToRequestque(stringRequest);
    }
}
