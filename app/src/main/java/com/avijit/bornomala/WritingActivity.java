package com.avijit.bornomala;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.avijit.bornomala.databinding.ActivityWritingBinding;

public class WritingActivity extends AppCompatActivity {
    ActivityWritingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWritingBinding.inflate(getLayoutInflater(),null,false);
        setContentView(binding.getRoot());
    }
}