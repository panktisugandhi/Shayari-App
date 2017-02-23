package com.example.pankti.myshayriapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int[] val = new int[]{R.drawable.moti,R.drawable.valti,
                          R.drawable.love,R.drawable.fship,
                          R.drawable.life,R.drawable.images,
                          R.drawable.positive};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        new MyFirstClass().execute("http://rapidans.esy.es/test/getallcat.php");

    }

    class MyFirstClass extends AsyncTask<String,Void,String> {

        ProgressDialog dialog;
        ArrayList<GridPost> postArrayList=new ArrayList<>();
        GridViewAdapter adapterG;
        Context context;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog=new ProgressDialog(MainActivity.this);
            dialog.setMessage("Loading...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection;
            try {
                URL url = new URL(params[0]);
                try {
                    connection = (HttpURLConnection)url.openConnection();
                    connection.connect();

                    InputStream stream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuffer buffer = new StringBuffer();
                    String line = "";

                    while ((line =reader.readLine())!= null){
                        buffer.append(line);
                    }

                    String bufferString = buffer.toString();
                    return  bufferString;


                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(dialog.isShowing()) {
                dialog.dismiss();
            }
            postArrayList = new ArrayList<>();

            try {

                JSONObject jsonObject1=new JSONObject(s);


                JSONArray jsonArray = jsonObject1.getJSONArray("data");
                for (int i = 0; i <jsonArray.length() ; i++)
                {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);


                    GridPost p = new GridPost();
                    p.setId(jsonObject.getInt("id"));

                    p.setDescription(jsonObject.getString("name"));

                    postArrayList.add(p);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            adapterG = new GridViewAdapter(MainActivity.this,postArrayList,val);
           GridView gridView = (GridView)findViewById(R.id.gridview1);

            gridView.setAdapter(adapterG);

        }
    }

}
