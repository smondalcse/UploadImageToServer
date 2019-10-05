package com.example.uploadimagetoserver;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ImageReader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

public class DisplayImageActivity extends AppCompatActivity {
    private static final String TAG = "DisplayImageActivity";

    ImageView img;
    Button btnRetriveImage;

    String image_path = "http://192.168.10.12/upload_image_with_volley/uploads/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image);

        img = (ImageView) findViewById(R.id.img);
        btnRetriveImage = (Button) findViewById(R.id.btnRetiveImage);

        Bundle extras = getIntent().getExtras();
        String name = extras.getString("img_name");
        image_path = image_path + name + ".jpg";

        Log.i(TAG, "image_path: " + image_path);

        btnRetriveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageRequest imageRequest = new ImageRequest(image_path, new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        img.setImageBitmap(response);
                    }
                }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DisplayImageActivity.this, "Something happen wrong", Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                });
                MySingleton.getmySingleton(DisplayImageActivity.this).addToRequestque(imageRequest);
            }
        });


    }
}
