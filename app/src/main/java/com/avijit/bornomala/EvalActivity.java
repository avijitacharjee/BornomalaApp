package com.avijit.bornomala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.avijit.bornomala.adapter.EvalCharAdapter;
import com.avijit.bornomala.databinding.ActivityEvalBinding;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EvalActivity extends AppCompatActivity {
    ActivityEvalBinding binding ;
    List<String> charList = new ArrayList<>();
    EvalCharAdapter adapter ;
    private android.widget.RelativeLayout.LayoutParams layoutParams;
    private String msg = "lakjdsf";

    private static final String IMAGEVIEW_TAG = "icon bitmap";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEvalBinding.inflate(getLayoutInflater(),null,false);
        setContentView(binding.getRoot());
        addChars();
        adapter = new EvalCharAdapter(charList);
        binding.recyclerView.setAdapter(adapter);

        binding.mainImage.setOnLongClickListener(view -> {
            ClipData.Item item = new ClipData.Item((CharSequence) view.getTag());
            String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
            ClipData dragData = new ClipData(view.getTag().toString(),mimeTypes,item);
            View.DragShadowBuilder myShadow = new View.DragShadowBuilder(binding.mainImage);
            view.startDrag(dragData,myShadow,null,0);
            return true;
        });
        binding.mainImage.setOnDragListener((v,event)->{
            switch (event.getAction()){
                case DragEvent.ACTION_DRAG_STARTED:
                    layoutParams = (RelativeLayout.LayoutParams)v.getLayoutParams();
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
                    Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENDED");

                    // Do nothing
                    break;

                case DragEvent.ACTION_DROP:
                    Log.d(msg, "ACTION_DROP event");

                    // Do nothing
                    break;
                default: break;
            }
            return true;
        });
        binding.mainImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(binding.mainImage);

                    binding.mainImage.startDrag(data, shadowBuilder, binding.mainImage, 0);
                    binding.mainImage.setVisibility(View.INVISIBLE);
                    return true;
                } else {
                    return false;
                }
            }
        });
    }
    private void addChars() {
        charList.add("অ");
        charList.add("আ");
        charList.add("ই");
        charList.add("ঈ");
        charList.add("উ");
        charList.add("ঊ");
        charList.add("ঋ");
        charList.add("এ");
        charList.add("ঐ");
        charList.add("ও");
        charList.add("ঔ");
    }
}