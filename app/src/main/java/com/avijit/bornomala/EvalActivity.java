package com.avijit.bornomala;

import static android.view.DragEvent.ACTION_DROP;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.DragAndDropPermissions;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.avijit.bornomala.adapter.EvalCharAdapter;
import com.avijit.bornomala.databinding.ActivityEvalBinding;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class EvalActivity extends AppCompatActivity {
    ActivityEvalBinding binding ;
    List<String> charList = new ArrayList<>();
    EvalCharAdapter adapter ;
    private ConstraintLayout.LayoutParams layoutParams;
    private String msg = "lakjdsf";
    private static String SERVER_URL = "http://borno-api.avijitacharjee.com/";
    //private static String SERVER_URL = "http://192.168.0.2/";
    private static final String IMAGEVIEW_TAG = "icon bitmap";
    private JSONArray questions = null;
    int index = 0;
    private String word;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEvalBinding.inflate(getLayoutInflater(),null,false);
        setContentView(binding.getRoot());
//        addChars();
//        adapter = new EvalCharAdapter(charList);
//        binding.recyclerView.setAdapter(adapter);
        binding.mainImage.setTag(IMAGEVIEW_TAG);
        //binding.mainImage.setImageDrawable(loadImageFromWebOperations("http://192.168.0.4/storage/images/2022-04-21-10-30-37JKf98.bmp"));
        //binding.mainImage.setImageDrawable(getResources().getDrawable(R.drawable.boat));
        //Picasso.get().load(SERVER_URL+"storage/images/2022-04-21-10-30-37JKf98.bmp").into(binding.mainImage);
        loadQuestions();
        binding.mainImage.setOnLongClickListener( v -> {
            ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
            ClipData dragData = new ClipData(
                    (CharSequence) v.getTag(),
                    new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN },
                    item);
            View.DragShadowBuilder myShadow = new MyDragShadowBuilder(binding.mainImage);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                v.startDragAndDrop(dragData,
                        myShadow,  // The drag shadow builder
                        null,      // No need to use local data
                        0          // Flags (not currently used, set to 0)
                );
            }
            // Indicate that the long-click was handled.
            return true;
        });
        /*binding.mainImage.setOnDragListener((v,event)->{
            switch (event.getAction()){
                case DragEvent.ACTION_DRAG_STARTED:
                    layoutParams = (ConstraintLayout.LayoutParams)v.getLayoutParams();
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
                    //((ImageView)v).setColorFilter(Color.GREEN);
                    //((ImageView)v).setImageResource(R.drawable.boat);
                    // Invalidates the view to force a redraw in the new tint.
                    v.invalidate();

                    // Returns true; the value is ignored.
                    return true;

                case ACTION_DROP:
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
                    ClipData data = ClipData.newPlainText("", "Hello");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(binding.mainImage);

                binding.mainImage.startDrag(data, shadowBuilder, binding.mainImage, 0);
                //binding.mainImage.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }});*/
        binding.targetImage.setOnDragListener( (v, e) -> {
            switch(e.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // Determines if this View can accept the dragged data.
                    if (e.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                        // As an example of what your application might do, applies a blue color tint
                        // to the View to indicate that it can accept data.
                        //((ImageView)v).setColorFilter(Color.BLUE);

                        // Invalidate the view to force a redraw in the new tint.
                        //v.invalidate();

                        // Returns true to indicate that the View can accept the dragged data.
                        return true;

                    }

                    // Returns false to indicate that, during the current drag and drop operation,
                    // this View will not receive events again until ACTION_DRAG_ENDED is sent.
                    return false;

                case DragEvent.ACTION_DRAG_ENTERED:

                    // Applies a green tint to the View.
                    //((ImageView)v).setColorFilter(Color.GREEN);

                    // Invalidates the view to force a redraw in the new tint.
                    //v.invalidate();

                    // Returns true; the value is ignored.
                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:

                    // Ignore the event.
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:

                    // Resets the color tint to blue.
                    //((ImageView)v).setColorFilter(Color.BLUE);

                    // Invalidates the view to force a redraw in the new tint.
                    //v.invalidate();

                    // Returns true; the value is ignored.
                    return true;

                case DragEvent.ACTION_DROP:

                    // Gets the item containing the dragged data.
                    ClipData.Item item = e.getClipData().getItemAt(0);

                    // Gets the text data from the item.
                    CharSequence dragData = item.getText();

                    // Displays a message containing the dragged data.
                    //Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_LONG).show();
                    Log.d("avijit", "onCreate: "+dragData);
                    // Turns off any color tints.
                    //((ImageView)v).clearColorFilter();
                    //((ImageView)v).setImageResource(R.drawable.boat);
                    TextView textView = ((TextView)v);
                    textView.append(dragData);
                    if(textView.getText().toString().equals(this.word)){
                        Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
                    }
                    // Invalidates the view to force a redraw.
                    //v.invalidate();

                    // Returns true. DragEvent.getResult() will return true.
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:

                    // Turns off any color tinting.
                    //((ImageView)v).clearColorFilter();

                    // Invalidates the view to force a redraw.
                    //v.invalidate();

                    // Does a getResult(), and displays what happened.
                    if (e.getResult()) {
                        //Toast.makeText(this, "The drop was handled.", Toast.LENGTH_LONG).show();
                    } else {
                        //Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_LONG).show();
                    }

                    // Returns true; the value is ignored.
                    return true;

                // An unknown action type was received.
                default:
                    Log.e("DragDrop Example","Unknown action type received by View.OnDragListener.");
                    break;
            }

            return false;

        });
        binding.clearTextView.setOnClickListener(l->{
            binding.targetImage.setText("");
            Toast.makeText(this, "Cleared", Toast.LENGTH_SHORT).show();
        });
        //handleQuestion();
        binding.nextTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index++;
                if(index<questions.length()){
                    handleQuestion();
                }
                else {
                    Toast.makeText(EvalActivity.this, "Completed All Questions", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void handleQuestion(){
        try {
            JSONObject questionObject = questions.getJSONObject(index);
            String imgUrl = SERVER_URL + "storage/"+questionObject.getString("image");
            Picasso.get().load(imgUrl).into(binding.mainImage);
            String word = questionObject.getString("word");
            this.word = word;
            String[] parts = questionObject.getString("parts").split(",");
            charList.clear();
            charList.addAll(Arrays.asList(parts));
            adapter = new EvalCharAdapter(charList);
            binding.recyclerView.setAdapter(adapter);
        }catch (JSONException e){

        }
    }
    private void addChars() {
        charList.add("কা");
        charList.add("নৌ");
        charList.add("নে");
    }
    private static class MyDragShadowBuilder extends View.DragShadowBuilder {

        // The drag shadow image, defined as a drawable object.
        private static Drawable shadow;

        // Constructor
        public MyDragShadowBuilder(View v) {

            // Stores the View parameter.
            super(v);

            // Creates a draggable image that fills the Canvas provided by the system.
            shadow = new ColorDrawable(Color.LTGRAY);
        }

        // Defines a callback that sends the drag shadow dimensions and touch point
        // back to the system.
        @Override
        public void onProvideShadowMetrics (Point size, Point touch) {

            // Defines local variables
            int width, height;

            // Set the width of the shadow to half the width of the original View.
            width = getView().getWidth() / 2;

            // Set the height of the shadow to half the height of the original View.
            height = getView().getHeight() / 2;

            // The drag shadow is a ColorDrawable. This sets its dimensions to be the
            // same as the Canvas that the system provides. As a result, the drag shadow
            // fills the Canvas.
            shadow.setBounds(0, 0, width, height);

            // Set the size parameter's width and height values. These get back to the
            // system through the size parameter.
            size.set(width, height);

            // Set the touch point's position to be in the middle of the drag shadow.
            touch.set(width / 2, height / 2);
        }

        // Defines a callback that draws the drag shadow in a Canvas that the system
        // constructs from the dimensions passed to onProvideShadowMetrics().
        @Override
        public void onDrawShadow(Canvas canvas) {
            // Draw the ColorDrawable on the Canvas passed in from the system.
            shadow.draw(canvas);
        }

    }
    public static Drawable loadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }
    public void loadQuestions (){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = SERVER_URL+"api/questions";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(EvalActivity.this, response, Toast.LENGTH_SHORT).show();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            questions = jsonArray;

                            handleQuestion();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EvalActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}