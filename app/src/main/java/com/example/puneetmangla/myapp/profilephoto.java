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

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class profilephoto extends AppCompatActivity {
    private  static final int RESULT_LOAD_IMAGE=1;

ImageView imageView;
Button upload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilephoto);
        imageView=(ImageView)findViewById(R.id.pic);
        upload=(Button)findViewById(R.id.upload);
    }

    public void getImage(View view) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==RESULT_LOAD_IMAGE && resultCode==RESULT_OK && data!=null)
        {
            Uri setImage=data.getData();
            imageView.setImageURI(setImage);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void Upload(View view) {
        Bitmap bitmap =((BitmapDrawable)imageView.getDrawable()).getBitmap();
        new UploadImage(getIntent().getExtras().getString("username"),bitmap).execute();
    }
    private class UploadImage extends AsyncTask <String, Void, String>
    {  Bitmap image=null;
      String user="";
      UploadImage(String user,Bitmap image)
      {
          this.image=image;
          this.user=user;
      }

        @Override
        protected String doInBackground(String... strings) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            image.compress( Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
            String encodeimage= Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT);
            String login_url= null;
            login_url = "http://192.168.137.1/setpic.php?username="+user+"&image="+byteArrayOutputStream.toByteArray().toString();

            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();

                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();


                InputStream inputStream = httpURLConnection.getInputStream();
                byte[] buffer = new byte[10000]; //adjust size depending on how much data you are expecting
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

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(profilephoto.this,result,Toast.LENGTH_LONG).show();
        }
    }


}
