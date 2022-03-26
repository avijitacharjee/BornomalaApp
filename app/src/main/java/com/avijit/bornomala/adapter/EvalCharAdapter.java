package com.avijit.bornomala.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.avijit.bornomala.databinding.ItemEvalCharBinding;

import java.util.List;

public class EvalCharAdapter extends RecyclerView.Adapter<EvalCharAdapter.ViewHolder>{
    List<String> charList;
    ItemEvalCharBinding binding;
    public EvalCharAdapter(List<String> charList){
        this.charList = charList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemEvalCharBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new EvalCharAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EvalCharAdapter.ViewHolder holder, int position) {
        holder.binding.charTextView.setText(charList.get(position));
    }

    @Override
    public int getItemCount() {
        return charList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemEvalCharBinding binding;
        public ViewHolder(@NonNull ItemEvalCharBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
