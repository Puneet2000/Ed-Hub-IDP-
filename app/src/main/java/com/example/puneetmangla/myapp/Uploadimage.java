package com.example.puneetmangla.myapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Uploadimage extends AppCompatActivity {
ImageView profile;
Button upload;
private final int IMG_REQUEST=1;
Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadimage);
        profile=(ImageView)findViewById(R.id.imageView4);
        upload=(Button)findViewById(R.id.button9);
    }

    public void SelectImage(View view) {
  Selectfromgallery();
    }

    public void Uploadpicture(View view) {
        bitmap=((BitmapDrawable)profile.getDrawable()).getBitmap();
        new UploadtoGallery(getIntent().getExtras().getString("username"),imageToString(bitmap)).execute();
    }
    private void Selectfromgallery()
    {

        Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,IMG_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IMG_REQUEST && resultCode==RESULT_OK && data!=null)
        {
            Uri path=data.getData();
            profile.setImageURI(path);

        }
    }
    private class UploadtoGallery extends AsyncTask<String,Void,String>

    {
String user="";
String image="";
UploadtoGallery(String user,String image)
{
    this.image=image;
    this.user=user;

}
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL("http://192.168.137.1/uploadpic.php?username="+user+"&image="+image);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();


                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));


                byte[] buffer = new byte[10000000]; //adjust size depending on how much data you are expecting
                int readBytes = inputStream.read(buffer);

                String result = new String(buffer, 0, readBytes);


                inputStream.close();

                httpURLConnection.disconnect();


                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    private String imageToString(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,byteArrayOutputStream);
        byte[] imgBytes =byteArrayOutputStream.toByteArray();
        return     Base64.encodeToString(imgBytes,Base64.DEFAULT);
    }
}
