package com.avijit.bornomala;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.avijit.bornomala.adapter.WriteAdapter;
import com.avijit.bornomala.databinding.ActivityWriteBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.jfenn.colorpickerdialog.dialogs.ColorPickerDialog;

public class WriteActivity extends AppCompatActivity {
    private static Context CONTEXT;
    ActivityWriteBinding binding;
    WriteAdapter adapter;
    List<String> chars;
    int flag = 0;
    public static final int PERMISSION_REQUEST_CODE =102;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWriteBinding.inflate(getLayoutInflater(), null, false);
        setContentView(binding.getRoot());
        CONTEXT = this;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.recyclerView.setLayoutManager(layoutManager);
        chars = new ArrayList<>();

        addLetters();
        View.OnClickListener recyclerItemListener = v -> {
            int itemPosition = binding.recyclerView.getChildLayoutPosition(v);
            //binding.customView.setText(chars.get(itemPosition));
            //binding.customView.checkImage();
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
        binding.icSave.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(
                    CONTEXT, Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_GRANTED) {
                // You can use the API that requires the permission.
                //performAction(...);
                    binding.customView.saveToDevice();
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    // In an educational UI, explain to the user why your app requires this
                    // permission for a specific feature to behave as expected. In this UI,
                    // include a "cancel" or "no thanks" button that allows the user to
                    // continue using your app without granting the permission.

                } else {
                    // You can directly ask for the permission.
                    // The registered ActivityResultCallback gets the result of this request.
                    requestPermissions(new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE , Manifest.permission.READ_EXTERNAL_STORAGE},
                            PERMISSION_REQUEST_CODE);
                }
            }
            //binding.customView.saveToDevice();
        });
        /*Timer timer = new Timer();
        binding.customView.checkImage();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                a(binding.customView.checkImage());
            }
        }, 2000, 1000);//wait 0 ms before doing the action and do it evry 1000ms (1second)
*/
    }
    private void a(long count){
        if(count<1000){
            new Handler(Looper.getMainLooper()).post(new Runnable(){
                @Override
                public void run() {
                    if(flag==0){
                        binding.customView.setBackground(getResources().getDrawable(R.drawable.a1));
                    }else if(flag==1) {
                        binding.customView.setBackground(getResources().getDrawable(R.drawable.a2));
                    }else if(flag==2){
                        binding.customView.setBackground(getResources().getDrawable(R.drawable.a4));
                    }
                    flag++;
                }
            });

        }
    }
    public void addLetters() {
        // Vowels
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

        // Consonants
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
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                } else {
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                }
                return;
        }
        // Other 'case' lines to check for other
        // permissions this app might request.
    }
}