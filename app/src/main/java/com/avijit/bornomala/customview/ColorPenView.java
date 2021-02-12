package com.avijit.bornomala.customview;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.avijit.bornomala.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Avijit Acharjee on 7/10/2020 at 9:41 PM.
 * Email: avijitach@gmail.com.
 */
public class ColorPenView extends androidx.appcompat.widget.AppCompatTextView {
    private static final String TAG = "ColorPenView";
    private List<Point> points = new ArrayList<>();
    Paint paint = new Paint();
    Resources res = getResources();
    Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.ho);
    int color = Color.GREEN;

    public ColorPenView(Context context) {
        super(context);
    }

    public ColorPenView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ColorPenView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(color);
        for (Point p : points) {
            //paint.setColor(Color.rgb((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256)));
            canvas.drawCircle(p.x, p.y, 35, paint);
        }

    }

    public boolean checkImage() {
        int width = getWidth();
        int height = getHeight();
        Log.d(TAG, "check: " + width + " " + height);
        Bitmap bitmap = takeScreenShot(this);
        this.bitmap = bitmap;
        int A, R, G, B;
        int pixel;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixel = bitmap.getPixel(i, j);
                A = Color.alpha(pixel);
                R = Color.red(pixel);
                G = Color.green(pixel);
                B = Color.blue(pixel);

                //Log.d(TAG, i+" "+j+ " check: "+pixel+" A: "+A+" R: "+R+" G: "+G+" B: "+B);
                if (R > 200 && G < 100 && B < 100) {
                    return false;
                }
            }

        }
        return true;
    }

    public Bitmap takeScreenShot(View view) {
        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
        view.buildDrawingCache();

        if (view.getDrawingCache() == null) return null;
        Bitmap snapshot = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        view.destroyDrawingCache();

        return snapshot;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        points.add(new Point((int) event.getX(), (int) event.getY()));
        //check();
        invalidate();
        return true;
    }

    public void clear() {
        Log.d(TAG, "abc: ");
        //this.setBackground(getResources().getDrawable(R.drawable.hh));
        points.clear();
        invalidate();
    }

    public boolean check() {
        return checkImage();
    }

    public void setPenColor(int color) {
        this.color = color;
    }

    public void saveToDevice() {
        /*File f = new File(Environment.getExternalStorageDirectory()
                .toString() + "/" + System.currentTimeMillis() + ".png");
        try {
            f.createNewFile();
        } catch (IOException e) {
            Log.d(TAG, "saveToDevice: " + e.toString());
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        *//*AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

            }
        });*//*
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                ContextWrapper cw = new ContextWrapper(getContext());
                // path to /data/data/yourapp/app_data/imageDir
                File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
                // path to home dir
                File homeDir = new File("/mnt/sdcard");

                //int sd
                String extStore = System.getenv("EXTERNAL_STORAGE");
                File f_exts = new File(extStore);

                //
                String folderPath = Environment.getExternalStorageDirectory() + "/Bornomala";
                File folder = new File(folderPath);
                if (!folder.exists()) {
                    File wallpaperDirectory = new File(folderPath);
                    wallpaperDirectory.mkdirs();
                }
                //
                String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
                File myDir = new File(root + "/Bornomala");
                if (!myDir.exists()) {
                    myDir.mkdirs();
                }
                // Create imageDir
                File mypath = new File(myDir, System.currentTimeMillis()+".jpg");

                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(mypath);
                    // Use the compress method on the BitMap object to write image to the OutputStream
                    FileOutputStream finalFos = fos;
                    takeScreenShot(ColorPenView.this).compress(Bitmap.CompressFormat.PNG, 100, finalFos);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fos.flush();
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            ;
        });
    }
}
