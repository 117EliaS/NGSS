package com.example.nategaulinsstreakersimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private int whichScreen = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(whichScreen == 0) {
            ImageView titlescreeeeeeen = (ImageView) findViewById(R.id.menuBackground);
            BitmapDrawable titleScreen_drawable = (BitmapDrawable) getDrawable(R.drawable.titlescreeeeeen);
            Bitmap titleScreen_bmp = Bitmap.createScaledBitmap(titleScreen_drawable.getBitmap(), 1920, 1920, false);
            Drawable titleScreen_done = new BitmapDrawable(getResources(), titleScreen_bmp);
            titlescreeeeeeen.setBackground(titleScreen_done);
        }

        else{
            ImageView gameoverscreen = (ImageView) findViewById(R.id.menuBackground);
            BitmapDrawable gameoverscreen_drawable = (BitmapDrawable) getDrawable(R.drawable.gameoverscreen);
            Bitmap gameoverscreen_bmp = Bitmap.createScaledBitmap(gameoverscreen_drawable.getBitmap(), 1920, 1920, false);
            Drawable gameoverscreen_done = new BitmapDrawable(getResources(), gameoverscreen_bmp);
            gameoverscreen.setBackground(gameoverscreen_done);

            Button button = (Button) findViewById(R.id.button);

            button.setBackgroundResource(R.drawable.restartbutton);

        }

    }

    public void startGame(View v){

        Intent intent = new Intent(this, GameScreenActivity.class);
        startActivity(intent);
    }
}
