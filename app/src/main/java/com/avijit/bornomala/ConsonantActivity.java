package com.avijit.bornomala;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;

import com.avijit.bornomala.adapter.ConsonantAdapter;
import com.avijit.bornomala.databinding.ActivityConsonantBinding;

import java.util.ArrayList;
import java.util.List;

public class ConsonantActivity extends AppCompatActivity {
    ActivityConsonantBinding binding;
    List<String> letters = new ArrayList<>();
    ConsonantAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConsonantBinding.inflate(getLayoutInflater(),null,false);
        setContentView(binding.getRoot());
        addConsonants();
        adapter = new ConsonantAdapter(letters);
        binding.recyclerView.setAdapter(adapter);
    }
    public int calculateNoOfColumns(float columnWidthDp) { // For example columnWidthdp=180
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (screenWidthDp / columnWidthDp + 0.5); // +0.5 for correct rounding to int.
        return noOfColumns;
    }
    public void addConsonants(){
        letters.add("ক");
        letters.add("খ");
        letters.add("গ");
        letters.add("ঘ");
        letters.add("ঙ");
        letters.add("চ");
        letters.add("ছ");
        letters.add("জ");
        letters.add("ঝ");
        letters.add("ঞ");
        letters.add("ট");
        letters.add("ঠ");
        letters.add("ড");
        letters.add("ঢ");
        letters.add("ণ");
        letters.add("ত");
        letters.add("থ");
        letters.add("দ");
        letters.add("ধ");
        letters.add("ন");
        letters.add("প");
        letters.add("ফ");
        letters.add("ব");
        letters.add("ভ");
        letters.add("ম");
        letters.add("য");
        letters.add("র");
        letters.add("ল");
        letters.add("শ");
        letters.add("ষ");
        letters.add("স");
        letters.add("হ");
        letters.add("ড়");
        letters.add("ঢ়");
        letters.add("য়");
        letters.add("ৎ");
        letters.add("ং");
        letters.add(":");
        letters.add("‍ঁ");
    }
}