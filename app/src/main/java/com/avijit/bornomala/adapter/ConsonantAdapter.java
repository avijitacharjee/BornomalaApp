package com.avijit.bornomala.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.avijit.bornomala.R;
import com.avijit.bornomala.databinding.ItemWriteBinding;

import java.io.File;
import java.util.List;

/**
 * Created by Avijit Acharjee on 1/14/2021 at 7:22 AM.
 * Email: avijitach@gmail.com.
 */
public class ConsonantAdapter extends RecyclerView.Adapter<ConsonantAdapter.ViewHolder>{
    private static final String TAG = "ConsonantAdapter";
    List<String> letterList;
    public static String PACKAGE_NAME;
    public ConsonantAdapter(List<String> letterList){
        this.letterList = letterList;
    }
    ItemWriteBinding binding;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemWriteBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        PACKAGE_NAME = parent.getContext().getPackageName();
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.textView.setText(letterList.get(position));
        holder.binding.textView.setBackgroundColor(Color.WHITE);
        holder.binding.getRoot().setOnClickListener(v -> {
            ObjectAnimator.ofFloat(v,View.SCALE_X,0f,1f).start();
            ObjectAnimator.ofFloat(v,View.SCALE_Y,0f,1f).start();
            playAudio(holder.binding.getRoot().getContext(),"sndc"+(position+1));
        });
    }

    @Override
    public int getItemCount() {return letterList.size();}

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemWriteBinding binding;
        public ViewHolder(@NonNull ItemWriteBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
    public void playAudio(Context context,String fileName){
        //set up MediaPlayer
        MediaPlayer mediaPlayer = MediaPlayer.create(context, context.getResources().getIdentifier("raw/"+fileName, null, context.getPackageName()));
        mediaPlayer.start();
    }
}
