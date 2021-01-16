package com.avijit.bornomala;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.avijit.bornomala.adapter.WriteAdapter;
import com.avijit.bornomala.databinding.ActivityWriteBinding;

import java.util.ArrayList;
import java.util.List;

import me.jfenn.colorpickerdialog.dialogs.ColorPickerDialog;

public class WriteActivity extends AppCompatActivity {
    ActivityWriteBinding binding;
    WriteAdapter adapter;
    List<String> chars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWriteBinding.inflate(getLayoutInflater(), null, false);
        setContentView(binding.getRoot());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.recyclerView.setLayoutManager(layoutManager);
        chars = new ArrayList<>();

        addLetters();
        View.OnClickListener recyclerItemListener = v -> {
            int itemPosition = binding.recyclerView.getChildLayoutPosition(v);
            binding.customView.setText(chars.get(itemPosition));
        };
        adapter = new WriteAdapter(chars,recyclerItemListener);
        binding.recyclerView.setAdapter(adapter);
        binding.icErase.setOnClickListener(v -> {
            binding.customView.clear();
        });
        binding.icColor.setOnClickListener(v -> {
            new ColorPickerDialog()
                    .withColor(getResources().getColor(R.color.colorAccent)) // the default / initial color
                    .withListener((dialog, color) -> {
                        binding.recyclerView.setBackgroundColor(color);
                        binding.customView.setPenColor(color);
                    })
                    .show(getSupportFragmentManager(), "colorPicker");
        });
        binding.icSave.setOnClickListener(v -> binding.customView.saveToDevice());


    }

    public void addLetters() {
        chars.add("অ");
        chars.add("আ");
        chars.add("ই");
        chars.add("ঈ");
        chars.add("উ");
        chars.add("ঊ");
        chars.add("ঋ");
        chars.add("এ");
        chars.add("ঐ");
        chars.add("ও");
        chars.add("ঔ");
        chars.add("ঔ");

        chars.add("ক");
        chars.add("খ");
        chars.add("গ");
        chars.add("ঘ");
        chars.add("ঙ");
        chars.add("চ");
        chars.add("ছ");
        chars.add("জ");
        chars.add("ঝ");
        chars.add("ঞ");
        chars.add("ট");
        chars.add("ঠ");
        chars.add("ড");
        chars.add("ঢ");
        chars.add("ণ");
        chars.add("ত");
        chars.add("থ");
        chars.add("দ");
        chars.add("ধ");
        chars.add("ন");
        chars.add("প");
        chars.add("ফ");
        chars.add("ব");
        chars.add("ভ");
        chars.add("ম");
        chars.add("য");
        chars.add("র");
        chars.add("ল");
        chars.add("শ");
        chars.add("ষ");
        chars.add("স");
        chars.add("হ");
        chars.add("ড়");
        chars.add("ঢ়");
        chars.add("য়");
        chars.add("ৎ");
        chars.add("ং");
        chars.add(":");
        chars.add("‍ঁ");

    }
}