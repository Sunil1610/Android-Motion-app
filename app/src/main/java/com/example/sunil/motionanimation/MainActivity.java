package com.example.sunil.motionanimation;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

public class MainActivity extends AppCompatActivity {


    private static final int width = 64;
    private static final int height = 70;
    private static final int num = 4;
    private static final int numx = 4;
    private static final int numy = 1;
    private static final int gen_delay =80;
    private static final int maxX = 800;
    private static final int minX = 0-40;
    private static final int maxY = 800;
    private static final int minY = 0;
    private static final int frames = 20;
    private int direc = 0;
    private int rand = 0;
    private static final int scale = 5;
    private int rect = 0;
    private boolean st_down = false;
    private boolean st_up = false;
    private float x = 0;
    private float y = 0;
    private int check = 0;
    private int xx = 0;
    private Canvas canvas;
    private boolean tr = true;
    private SurfaceHolder holder;
    private ImageView img;
    private Bitmap[] bm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView) findViewById(R.id.img);
        img.setX(minX);img.setY(0);
        Bitmap bmp = getMoveForwardBM(MainActivity.this,"img_02.jpg");
        load_animation(bmp);

        final Button left = (Button) findViewById(R.id.button);
//        left.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(direc!=1) check=0;
//                else if(check<3) check++;
//                else check=0;
//                direc = 1;
//                Bitmap bitmap = getMoveForwardBM(MainActivity.this, "img_01.jpg");
//                if (img.getX() < -40) img.setX(840);
//                img.setX((int) img.getX() - 20);
//                load_animation(bitmap);
//
//            }
//        });
        final Handler repeat1 = new Handler();
        final Runnable run1 = new Runnable() {
            @Override
            public void run() {
                if(direc!=1) check=0;
                else if(check<3) check++;
                else check=0;
                direc = 1;
                Bitmap bitmap = getMoveForwardBM(MainActivity.this, "img_01.jpg");
                if (img.getX() < minX) img.setX(maxX);
                img.setX((int) img.getX() - frames);
                load_animation(bitmap);
                if(left.isPressed()) repeat1.postDelayed(this,gen_delay);
                if (rect==3) {
                    if (img.getX() > minX) repeat1.postDelayed(this, gen_delay);
                    else {rect++;}
                }
            }
        };
        left.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) repeat1.postDelayed(run1,gen_delay);
                else xx=1;
                return false;
            }
        });

        final Button front = (Button) findViewById(R.id.button2);
//        front.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(direc!=2) check=0;
//                else if(check<3) check++;
//                else check=0;direc = 2;
//                Bitmap bitmap = getMoveForwardBM(MainActivity.this, "img_02.jpg");
//                if (img.getY() > 1000) img.setY(0);
//                img.setY((int) img.getY() + 20);
//                load_animation(bitmap);
//
//            }
//        });
        final Handler repeat2 = new Handler();
        final Runnable run2 = new Runnable() {
            @Override
            public void run() {
                if(direc!=2) check=0;
                else if(check<3) check++;
                else check=0;direc = 2;
                Bitmap bitmap = getMoveForwardBM(MainActivity.this, "img_02.jpg");
                if (img.getY() > maxY) img.setY(minY);
                img.setY((int) img.getY() + frames);
                load_animation(bitmap);
                if(front.isPressed()) repeat2.postDelayed(this,gen_delay);
                if (rect==2) {
                    if (img.getY() < maxY) repeat2.postDelayed(this, gen_delay);
                    else {rect++;}
                }
            }
        };
        front.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) repeat2.postDelayed(run2,gen_delay);
                else xx=1;
                return false;
            }
        });

        final Button back = (Button) findViewById(R.id.button3);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(direc!=3) check=0;
//                else if(check<3) check++;
//                else check=0;
//                direc = 3;
//                Bitmap bitmap = getMoveForwardBM(MainActivity.this, "img_03.jpg");
//                if (img.getY() < 0) img.setY(1000);
//                img.setY((int) img.getY() - 20);
//                load_animation(bitmap);
//
//            }
//        });
        final Handler repeat3 = new Handler();
        final Runnable run3 = new Runnable() {
            @Override
            public void run() {
                if(direc!=3) check=0;
                else if(check<3) check++;
                else check=0;
                direc = 3;
                Bitmap bitmap = getMoveForwardBM(MainActivity.this, "img_03.jpg");
                if (img.getY() < minY) img.setY(maxY);
                img.setY((int) img.getY() - frames);
                load_animation(bitmap);
                if(back.isPressed()) repeat3.postDelayed(this,gen_delay);
                if(rect==4)
                {
                    if(img.getY()>=minY) repeat3.postDelayed(this,gen_delay);
                    else {rect=0;}
                }
            }
        };
        back.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) repeat3.postDelayed(run3,gen_delay);
                else xx=1;
                return false;
            }
        });

        final Button right = (Button) findViewById(R.id.button4);
//        right.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(direc!=4) check=0;
//                else if(check<3) check++;
//                else check=0;
//                direc = 4;
//                Bitmap bitmap = getMoveForwardBM(MainActivity.this, "img_04.jpg");
//                if (img.getX() > 840) img.setX(0 - 40);
//                img.setX((int) img.getX() + 20);
//                load_animation(bitmap);
//
//            }
//        });
        final Handler repeat4 = new Handler();
        final Runnable run4 = new Runnable() {
            @Override
            public void run() {
                if(direc!=4) check=0;
                else if(check<3) check++;
                else check=0;
                direc = 4;
                Bitmap bitmap = getMoveForwardBM(MainActivity.this, "img_04.jpg");
                if (img.getX() > maxX) img.setX(minX);
                img.setX((int) img.getX() + frames);
                load_animation(bitmap);
                if(right.isPressed()) repeat4.postDelayed(this,gen_delay);
                if(rect==1)
                {
                    if(img.getX()<maxX) repeat4.postDelayed(this,gen_delay);
                    else {rect=2;}
                }
            }
        };
        right.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) repeat4.postDelayed(run4,gen_delay);
                else xx=1;
                return false;
            }
        });

        Button new1 = (Button) findViewById(R.id.button5);
        new1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setX(minX);img.setY(minY);direc=0;check=0;
                rect = 1;
                repeat4.postDelayed(run4,gen_delay);
//                System.out.println("asha");System.out.println(rect);
                repeat2.postDelayed(run2,55*gen_delay);
//                System.out.println("asha");System.out.println(rect);
                repeat1.postDelayed(run1,105*gen_delay);
//                System.out.println("asha");System.out.println(rect);
                repeat3.postDelayed(run3,160*gen_delay);
//                System.out.println("asha");System.out.println(rect);
            }
        });

    }

    private Bitmap getMoveForwardBM(MainActivity mainActivity, String s) {
        AssetManager am = mainActivity.getAssets();
        InputStream is = null;
        Bitmap bm2 = null;

        try{
            is = am.open(s);
            bm2 = BitmapFactory.decodeStream(is);
        }catch (IOException io){
            return null;
        }
        return bm2;
    }

    private void load_animation(Bitmap bitmap) {
        if (bitmap != null) {
            bm = new Bitmap[num];

            int currentFrame = 0;

            for (int i = 0; i < numy; i++) {
                for (int j = 0; j < numx; j++) {
                    bm[currentFrame] = Bitmap.createBitmap(bitmap, width * j, height * i, width, height);
                    bm[currentFrame] = Bitmap.createScaledBitmap(
                            bm[currentFrame], width * scale, height * scale, true);
                    if (++currentFrame >= num) {
                        break;
                    }
                }
            }
            final AnimationDrawable animation = new AnimationDrawable();
            animation.setOneShot(true);
                animation.addFrame(new BitmapDrawable(getResources(), bm[check]), 100);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    img.setBackground(animation);
                }
                animation.start();
        }
    }
}