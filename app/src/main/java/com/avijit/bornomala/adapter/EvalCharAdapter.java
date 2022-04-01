package com.avijit.bornomala.adapter;

import static android.view.DragEvent.ACTION_DROP;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Build;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.avijit.bornomala.EvalActivity;
import com.avijit.bornomala.R;
import com.avijit.bornomala.databinding.ItemEvalCharBinding;
import com.avijit.bornomala.utils.MyDragShadowBuilder;

import java.util.List;

public class EvalCharAdapter extends RecyclerView.Adapter<EvalCharAdapter.ViewHolder>{
    List<String> charList;
    ItemEvalCharBinding binding;
    private LinearLayout.LayoutParams layoutParams;
    private String msg = "lakjdsf";
    public EvalCharAdapter(List<String> charList){
        this.charList = charList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemEvalCharBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EvalCharAdapter.ViewHolder holder, int position) {
        //holder.binding.charTextView.setText(charList.get(position));
        holder.binding.charTextView.setOnDragListener((v,event)->{
            switch (event.getAction()){
                case DragEvent.ACTION_DRAG_STARTED:
                    layoutParams = (LinearLayout.LayoutParams)v.getLayoutParams();
                    Log.d(msg, "Action is DragEvent.ACTION_DRAG_STARTED");

                    // Do nothing
                    break;

                case DragEvent.ACTION_DRAG_ENTERED:
                    Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENTERED");
                    int x_cord = (int) event.getX();
                    int y_cord = (int) event.getY();
                    break;

                case DragEvent.ACTION_DRAG_EXITED :
                    Log.d(msg, "Action is DragEvent.ACTION_DRAG_EXITED");
                    x_cord = (int) event.getX();
                    y_cord = (int) event.getY();
                    layoutParams.leftMargin = x_cord;
                    layoutParams.topMargin = y_cord;
                    v.setLayoutParams(layoutParams);
                    break;

                case DragEvent.ACTION_DRAG_LOCATION  :
                    Log.d(msg, "Action is DragEvent.ACTION_DRAG_LOCATION");
                    x_cord = (int) event.getX();
                    y_cord = (int) event.getY();
                    break;

                case DragEvent.ACTION_DRAG_ENDED   :
                    //((ImageView)v).setColorFilter(Color.GREEN);
                    //((ImageView)v).setImageResource(R.drawable.boat);
                    // Invalidates the view to force a redraw in the new tint.
                    //v.invalidate();

                    // Returns true; the value is ignored.
                    return true;

                case ACTION_DROP:
                    Log.d(msg, "ACTION_DROP event");

                    // Do nothing
                    break;
                default: break;
            }
            return true;
        });

        holder.binding.charTextView.setOnLongClickListener( v -> {
            ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
            ClipData dragData = new ClipData(
                    (CharSequence) v.getTag(),
                    new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN },
                    item);
            View.DragShadowBuilder myShadow = new MyDragShadowBuilder(holder.binding.charTextView);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                v.startDragAndDrop(dragData,
                        myShadow,  // The drag shadow builder
                        null,      // No need to use local data
                        0          // Flags (not currently used, set to 0)
                );
            }
            // Indicate that the long-click was handled.
            return true;
        });
        holder.binding.charTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData data = ClipData.newPlainText("", charList.get(position));
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(holder.binding.charTextView);

                    holder.binding.charTextView.startDrag(data, shadowBuilder, holder.binding.charTextView, 0);
                    //binding.mainImage.setVisibility(View.INVISIBLE);
                    return true;
                } else {
                    return false;
                }
            }
        });
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
