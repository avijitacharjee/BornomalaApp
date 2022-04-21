package com.avijit.bornomala;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.avijit.bornomala.adapter.VowelAdapter;
import com.avijit.bornomala.databinding.ActivityVowelBinding;

import java.util.ArrayList;
import java.util.List;

public class VowelActivity extends AppCompatActivity {
    ActivityVowelBinding binding;
    List<String> letters = new ArrayList<>();
    VowelAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVowelBinding.inflate(getLayoutInflater(), null, false);
        setContentView(binding.getRoot());
        addVowels();
        adapter = new VowelAdapter(letters);
        binding.recyclerView.setAdapter(adapter);
    }

    private void addVowels() {
        letters.add("অ");
        letters.add("আ");
        letters.add("ই");
        letters.add("ঈ");
        letters.add("উ");
        letters.add("ঊ");
        letters.add("ঋ");
        letters.add("এ");
        letters.add("ঐ");
        letters.add("ও");
        letters.add("ঔ");
    }
}