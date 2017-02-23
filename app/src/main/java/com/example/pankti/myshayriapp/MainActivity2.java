package com.example.pankti.myshayriapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

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

/**
 * Created by welcome on 2/14/2017.
 */

public class MainActivity2 extends AppCompatActivity {

    ListView listView;
    ListPost LP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        int gotid = getIntent().getIntExtra("pos", 1);

        new MySecondClass().execute("http://rapidans.esy.es/test/getquotes.php?cat_id=" + gotid);

    }

    class MySecondClass extends AsyncTask<String,Void,String> {

        private ProgressDialog dialog;
        ArrayList<ListPost> quotesArrayList = new ArrayList<>();
        ListViewAdapter adapterL;
        Context context;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(MainActivity2.this);
            dialog.setMessage("Loading...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection;
            try {
                URL url = new URL(params[0]);//new URL("http://rapidans.esy.es/test/getquotes.php?cat_id=1");
                try {
                    connection = (HttpURLConnection) url.openConnection();
                    connection.connect();

                    InputStream stream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuffer buffer = new StringBuffer();
                    String line = "";

                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }

                    String bufferString = buffer.toString();
                    return bufferString;


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
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            quotesArrayList = new ArrayList<>();

            try {

                JSONObject jsonObject1 = new JSONObject(s);

                JSONArray jsonArray = jsonObject1.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);


                    LP = new ListPost();
                    LP.setId(jsonObject.getInt("id"));
                    LP.setCat_id(jsonObject.getInt("cat_id"));
                    LP.setQuotes(jsonObject.getString("quotes"));

                    quotesArrayList.add(LP);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            adapterL = new ListViewAdapter(MainActivity2.this,quotesArrayList);

            listView = (ListView) findViewById(R.id.listview1);

            listView.setAdapter(adapterL);

            String passedVar=null;



        }
    }


}