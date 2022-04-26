package com.avijit.bornomala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.avijit.bornomala.databinding.ActivityGamesBinding;

public class GamesActivity extends AppCompatActivity {
    ActivityGamesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGamesBinding.inflate(getLayoutInflater(),null,false);
        setContentView(binding.getRoot());
        binding.flyButton.setOnClickListener(listener->{
            startActivity(new Intent(this,FlyActivity.class));
        });
    }
}