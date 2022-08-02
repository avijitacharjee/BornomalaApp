package com.avijit.bornomala.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.avijit.bornomala.databinding.ItemWriteBinding;

import java.util.List;

/**
 * Created by Avijit Acharjee on 1/10/2021 at 6:43 PM.
 * Email: avijitach@gmail.com.
 */
public class WriteAdapter extends RecyclerView.Adapter<WriteAdapter.ViewHolder>{
    private final List<String> chars ;
    private final View.OnClickListener listener;
    public WriteAdapter(List<String> chars, View.OnClickListener listener){
        this.chars = chars;
        this.listener = listener;
    }

    ItemWriteBinding binding;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemWriteBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.textView.setText(chars.get(position));
        holder.binding.getRoot().setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return chars.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemWriteBinding binding;
        public ViewHolder(@NonNull ItemWriteBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
