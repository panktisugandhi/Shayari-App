package com.example.pankti.myshayriapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by welcome on 2/14/2017.
 */

public class MainShowActivity extends AppCompatActivity {

    TextView textView;
    String quotes;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);


        quotes=getIntent().getStringExtra("pos");
        textView=(TextView)findViewById(R.id.TV_6);
        textView.setText(quotes);

    //        btn=(Button)findViewById(R.id.btn1);
    //        btn.setOnClickListener(new View.OnClickListener() {
    //            @Override
    //            public void onClick(View v) {
    //                Intent shareIntent = new Intent(Intent.ACTION_SEND);
    //                shareIntent.setType("text/html");
    //                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,quotes);
    //                startActivity(Intent.createChooser(shareIntent, "Share Via"));
    //            }
    //        });

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();

        } else if (id == R.id.action_shr) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/html");
            shareIntent.putExtra(Intent.EXTRA_TEXT,quotes);
            startActivity(Intent.createChooser(shareIntent, "Share Via"));

        }


        return super.onOptionsItemSelected(item);
    }
}