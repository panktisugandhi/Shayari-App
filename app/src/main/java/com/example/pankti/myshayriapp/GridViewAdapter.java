package com.example.pankti.myshayriapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by welcome on 2/14/2017.
 */

public class GridViewAdapter extends BaseAdapter {


    Context context;
    ArrayList<GridPost> posts;
    LayoutInflater layoutInflater;
    int[] val;


    GridViewAdapter(Context context, ArrayList<GridPost> posts,int[] val)
    {
        this.context=context;
        this.posts=posts;
        this.val=val;
    }


    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder
    {
        ImageView img;
    //    TextView uid;
        TextView desc;
    }

   // private int[] tagCollection;
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView==null) {
            holder = new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(R.layout.grid_row, parent, false);
            //holder.uid = (TextView) convertView.findViewById(R.id.TV_1);
            holder.img=(ImageView) convertView.findViewById(R.id.img);
            holder.desc = (TextView) convertView.findViewById(R.id.TV_1);

            convertView.setTag(holder);



        }
        else
        {
            holder=(ViewHolder)convertView.getTag();
        }

        holder.desc.setText(posts.get(position).getDescription());
        holder.img.setImageResource(val[position]);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(context,MainActivity2.class);
                a.putExtra("pos", posts.get(position).getId());
                context.startActivity(a);
            }
        });


        return convertView;
    }


}
