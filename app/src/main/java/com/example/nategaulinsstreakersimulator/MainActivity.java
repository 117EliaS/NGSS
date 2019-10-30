package com.example.nategaulinsstreakersimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
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

            ImageView titleScreen = (ImageView) findViewById(R.id.menuBackground);
            titleScreen.setBackgroundResource(R.drawable.title_screen_animation);

            AnimationDrawable title = (AnimationDrawable) titleScreen.getBackground();

            title.start();




        }

        else{

            ImageView titleScreen = (ImageView) findViewById(R.id.menuBackground);

            titleScreen.setBackgroundResource(R.drawable.gameoverscreen);

            Button button = (Button) findViewById(R.id.button);

            button.setBackgroundResource(R.drawable.restartbutton);

        }

    }

    public void startGame(View v){

        Intent intent = new Intent(this, GameScreenActivity.class);
        startActivity(intent);
    }
}
