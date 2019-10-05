package com.example.uploadimagetoserver;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";

    EditText name;
    Button chooseBtn, uploadBtn, btnImageList;
    ImageView imgView;
    private final int IMG_REQUEST = 1;
    private Bitmap bitmap;

    String image_upload_url = "http://192.168.10.12/upload_image_with_volley/uploadimage.php";
    String img_location = "http://192.168.10.12/upload_image_with_volley/uploads";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.name);
        chooseBtn = (Button) findViewById(R.id.choosebtn);
        uploadBtn = (Button) findViewById(R.id.uploadbtn);
        imgView = (ImageView) findViewById(R.id.imageView);

        btnImageList = (Button) findViewById(R.id.btnImageList);

        chooseBtn.setOnClickListener(this);
        uploadBtn.setOnClickListener(this);
        btnImageList.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.choosebtn :
                selectImage();
                break;
            case R.id.uploadbtn :
                String img_name = name.getText().toString().trim();
                if(!img_name.equalsIgnoreCase("")){
                    uploadImage();
                    break;
                } else {
                    Toast.makeText(getApplicationContext(), "Image name is blank!!!", Toast.LENGTH_LONG).show();
                    break;
                }

            case R.id.btnImageList :
                Intent imgListIntent = new Intent(MainActivity.this, ImageListActivity.class);
                startActivity(imgListIntent);
        }
    }

    private void selectImage()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null){

            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                imgView.setImageBitmap(bitmap);
                imgView.setVisibility(View.VISIBLE);
                name.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void uploadImage()
    {
        Log.i(TAG, "uploadImage: " + image_upload_url);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, image_upload_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String Response = jsonObject.getString("response");
                            Toast.makeText(MainActivity.this, Response, Toast.LENGTH_LONG).show();

                            imgView.setImageResource(0);
                            imgView.setVisibility(View.GONE);
                            name.setText("");
                            name.setVisibility(View.GONE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Intent imgListIntent = new Intent(MainActivity.this, ImageListActivity.class);
                        startActivity(imgListIntent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", name.getText().toString().trim());
                params.put("image", imageToString(bitmap));
                params.put("img_url", img_location + "/" + name.getText().toString().trim()+".jpg");
                return params;
            }
        };

        MySingleton.getmySingleton(MainActivity.this).addToRequestque(stringRequest);

    }

    private String imageToString(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
    }

}
