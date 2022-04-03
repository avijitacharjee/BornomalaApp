package com.avijit.bornomala;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.avijit.bornomala.customview.ColorPenView;
import com.avijit.bornomala.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ColorPenView colorPenView;
    TextView clearTextView,checkTextView;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater(),null,false);
        setContentView(binding.getRoot());
        binding.readLayout.setOnClickListener(l->{
            if(binding.readText.getText().toString().equals("পড়ি")){
                ObjectAnimator.ofFloat(binding.readLayout,View.ALPHA,0f,1f).start();
                ObjectAnimator.ofFloat(binding.writeLayout,View.ALPHA,0f,1f).start();
                binding.readText.setText("স্বরবর্ণ");
                binding.readLogo.setVisibility(View.GONE);

                binding.writeText.setText("ব্যঞ্জনবর্ণ");
                binding.writeLogo.setVisibility(View.GONE);

                binding.evalLayout.setVisibility(View.GONE);
            }else {
                startActivity(new Intent(this,VowelActivity.class));
            }
        });
        binding.writeLayout.setOnClickListener(l->{
            if(binding.readText.getText().toString().equals("পড়ি")){
                animate();
            }else {
                startActivity(new Intent(this,ConsonantActivity.class));
            }
        });

        binding.evalLayout.setOnClickListener(l->{
            startActivity(new Intent(this,EvalActivity.class));
        });
    }
    public void animate(){
        new Handler(Looper.myLooper()).postDelayed(() -> startActivity(new Intent(MainActivity.this,WriteActivity.class)),0);
    }

    @Override
    public void onBackPressed() {
        if(binding.readText.getText().toString().equals("স্বরবর্ণ")){
            ObjectAnimator.ofFloat(binding.readLayout,View.ALPHA,0f,1f).start();
            ObjectAnimator.ofFloat(binding.writeLayout,View.ALPHA,0f,1f).start();
            binding.readText.setText("পড়ি");
            binding.readLogo.setVisibility(View.VISIBLE);

            binding.writeText.setText("লিখি");
            binding.writeLogo.setVisibility(View.VISIBLE);
        }else {
            super.onBackPressed();
        }
    }
}
