package com.example.projectasl2;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class ExampleAdapterDottori extends RecyclerView.Adapter<ExampleAdapterDottori.ExampleViewHolder> {
    private ArrayList<ExampleItemDottori> exampleItemDottoriArrayList;
    public static int pos;
    Context context;
    int lastP=-1;

    public class ExampleViewHolder extends RecyclerView.ViewHolder {
       // public ImageView imageDoc;
        public FloatingActionButton imageDoc;
        public TextView nomeDoc;
        public String colore;

        public ExampleViewHolder(View itemView){
            super(itemView);
            imageDoc=itemView.findViewById(R.id.floatingDoc);
            nomeDoc=itemView.findViewById(R.id.nomeDoc);
            nomeDoc.setTextSize(15);

            //imageDoc.setBackgroundTintList(ColorStateList.valueOf(R.drawable.circle));
            //imageDoc.setBackgroundDrawable(R.drawable.circle);


            imageDoc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    nomeDoc.setTextSize(20);
                    nomeDoc.setTextColor(Color.WHITE);
                    MainActivity.floatInvioPass.setVisibility(View.VISIBLE);
                    MainActivity.password.setVisibility(View.VISIBLE);
                    int position=getPosition();
                    pos=position;


                        //MainActivity.invioPass.setVisibility(View.VISIBLE);

                    //Toast.makeText(view.getContext(), "sei in Holder"+position, Toast.LENGTH_LONG).show();

                }

            });
        }
    }

    public ExampleAdapterDottori(ArrayList<ExampleItemDottori> exampleItemDottoris){
        exampleItemDottoriArrayList=exampleItemDottoris;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item_dottori,parent,false);
        ExampleViewHolder evh= new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {

        if (holder.getAdapterPosition()>lastP){
            Animation animation=AnimationUtils.loadAnimation(MainActivity.context,R.anim.animation_falldown);
            holder.itemView.startAnimation(animation);
            lastP=holder.getAdapterPosition();
        }




        ExampleItemDottori currentItem=exampleItemDottoriArrayList.get(position);
        holder.imageDoc.setImageResource(currentItem.getImage());
        holder.nomeDoc.setText(currentItem.getLine1());


       // holder.imageDoc.setAnimation(AnimationUtils.loadAnimation(MainActivity.context.getApplicationContext(),R.anim.layout_item_falldown));
        //holder.imageDoc.setBackgroundColor(R.drawable.button_shape);

    }

    @Override
    public int getItemCount() {
        return exampleItemDottoriArrayList.size();
    }




}

