package com.example.projectasl2;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class ExampleAdapterAppuntamenti extends RecyclerView.Adapter<ExampleAdapterAppuntamenti.ExampleViewHolder> {
    private ArrayList<ExampleItemAppuntamenti> mExampleList;
    public static int pos;
    Context context;
    int lastP=-1;



    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView line1;
        public TextView line2;
        public TextView line3;


        public ExampleViewHolder(View itemView){
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            line1=itemView.findViewById(R.id.line1);
            line2=itemView.findViewById(R.id.line2);
            line3=itemView.findViewById(R.id.line3);

        }
    }

    public ExampleAdapterAppuntamenti(ArrayList<ExampleItemAppuntamenti> exampleList){
        mExampleList=exampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item_appuntamenti,parent,false);
        ExampleViewHolder evh= new ExampleViewHolder(v);
        return evh;
    }


    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        if (holder.getAdapterPosition()>lastP){
            Animation animation= AnimationUtils.loadAnimation(MainActivity.context,R.anim.animation_from_right);
            holder.itemView.startAnimation(animation);

            ExampleItemAppuntamenti currentItem=mExampleList.get(position);
            holder.imageView.setImageResource(currentItem.getImage());
            holder.line1.setText(currentItem.getLine1());
            holder.line2.setText(currentItem.getLine2());
            holder.line3.setText(currentItem.getLine3());


            lastP=holder.getAdapterPosition();
        }

        /*ExampleItemAppuntamenti currentItem=mExampleList.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        holder.line1.setText(currentItem.getLine1());
        holder.line2.setText(currentItem.getLine2());
        holder.line3.setText(currentItem.getLine3());*/

    }



    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


}
