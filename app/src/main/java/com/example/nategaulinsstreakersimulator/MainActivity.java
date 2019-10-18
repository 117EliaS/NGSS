package com.example.nategaulinsstreakersimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView titleScreen = (ImageView) findViewById(R.id.menuBackground);
        titleScreen.setBackgroundResource(R.drawable.title_screen_animation);

        AnimationDrawable title = (AnimationDrawable) titleScreen.getBackground();

        title.start();


    }

    public void startGame(View v){

        Intent intent = new Intent(this, GameScreenActivity.class);
        startActivity(intent);
    }
}
