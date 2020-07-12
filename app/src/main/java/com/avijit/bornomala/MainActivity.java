package com.avijit.bornomala;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.avijit.bornomala.customview.ColorPenView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ColorPenView colorPenView;
    TextView clearTextView,checkTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        clearTextView.setOnClickListener(this::clearOnClick);
        checkTextView.setOnClickListener(this::checkOnClick);
    }
    private void init(){
        colorPenView= findViewById(R.id.color_pen_view);
        clearTextView = findViewById(R.id.clear_button);
        checkTextView = findViewById(R.id.check_button);
    }
    private void clearOnClick(View view){
        Log.d(TAG, "clearOnClick: ");
        colorPenView.clear();
    }
    private void checkOnClick(View view){
        Log.d(TAG, "checkOnClick: ");
        if(colorPenView.check()){
            Toast.makeText(this, "Perfect", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Try Again", Toast.LENGTH_SHORT).show();
        }
    }
}
