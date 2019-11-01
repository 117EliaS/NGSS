package com.example.nategaulinsstreakersimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class endgame_activity extends AppCompatActivity {

    public static final String TIME = "0:00:00";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endgame_activity);

        Intent intent = getIntent();
        String scoreString = intent.getStringExtra(TIME);

        TextView score = (TextView) findViewById(R.id.score_view);

        score.setText("Time: " + scoreString);

        ImageView gameoverscreen = (ImageView) findViewById(R.id.menuBackground);
        BitmapDrawable gameoverscreen_drawable = (BitmapDrawable) getDrawable(R.drawable.gameoverscreen);
        Bitmap gameoverscreen_bmp = Bitmap.createScaledBitmap(gameoverscreen_drawable.getBitmap(), 1920, 1920, false);
        Drawable gameoverscreen_done = new BitmapDrawable(getResources(), gameoverscreen_bmp);
        gameoverscreen.setBackground(gameoverscreen_done);


    }

    public void returnToMenu(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
