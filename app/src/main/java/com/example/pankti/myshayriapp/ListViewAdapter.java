package com.example.pankti.myshayriapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by welcome on 2/14/2017.
 */

public class ListViewAdapter extends BaseAdapter {


    Context context;
    ArrayList<ListPost> quots;
    LayoutInflater layoutInflater;
    ListView listView;


    ListViewAdapter(Context context, ArrayList<ListPost> quots)
    {
        this.context=context;
        this.quots=quots;
    }

    @Override
    public int getCount() {
        return quots.size();
    }

    @Override
    public Object getItem(int position) {
        return quots.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    static class ViewHolder
    {
      TextView id;
        TextView cat_id;
        TextView quotes;


    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        final ViewHolder holder;


        if(convertView==null) {
            holder = new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(R.layout.list_row, parent, false);
            holder.id = (TextView) convertView.findViewById(R.id.TV_3);
            holder.cat_id = (TextView) convertView.findViewById(R.id.TV_4);
            holder.quotes=(TextView)convertView.findViewById(R.id.TV_5);

            convertView.setTag(holder);
        }
        else
        {
            holder=(ViewHolder)convertView.getTag();
        }
        holder.quotes.setText(quots.get(position).getQuotes());




        // convertView.setOnClickListener(new View.OnClickListener() {
        //   @Override
        // public void onClick(View v) {
        //   Intent intent = new Intent(context,MainListActivity.class);
        // intent.putExtra("pos", posts.get(position).getQuotes());
        // context.startActivity(intent);
        //}
        //});

        holder.quotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MainShowActivity.class);
                intent.putExtra("pos", quots.get(position).getQuotes());
                context.startActivity(intent);
            }
        });

        return convertView;
    }

}
