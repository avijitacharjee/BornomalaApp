package com.avijit.bornomala;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.avijit.bornomala.adapter.EvalCharAdapter;
import com.avijit.bornomala.databinding.ActivityEvalBinding;

import java.util.LinkedList;
import java.util.List;

public class EvalActivity extends AppCompatActivity {
    ActivityEvalBinding binding ;
    List<String> charList = new LinkedList<>();
    EvalCharAdapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEvalBinding.inflate(getLayoutInflater(),null,false);
        setContentView(binding.getRoot());
        addChars();
        adapter = new EvalCharAdapter(charList);
        binding.recyclerView.setAdapter(adapter);
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