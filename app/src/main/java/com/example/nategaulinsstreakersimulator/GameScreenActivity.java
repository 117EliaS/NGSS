package com.example.nategaulinsstreakersimulator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class GameScreenActivity extends AppCompatActivity {

    private int milsecs = 0;
    private boolean running = false;

    private int playerDirection = 0; //0 Up, 1 Down, 2 Left, 3 Right

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        // Timer shit
        runTimer();
        startTimer();

        //Beginning animations
        ImageView player = findViewById(R.id.playerImageView);
        player.setBackgroundResource(R.drawable.player_animation_up);

        AnimationDrawable playerFrameAnimation = (AnimationDrawable) player.getBackground();

        playerFrameAnimation.start();

        ImageView guard = findViewById(R.id.guardImageView);
        guard.setBackgroundResource(R.drawable.cop_animation_up);

        AnimationDrawable guardFrameAnimation = (AnimationDrawable) guard.getBackground();

        guardFrameAnimation.start();

        ImageView football = findViewById(R.id.footballImageView);
        football.setBackgroundResource(R.drawable.footballer_animation_up);

        AnimationDrawable footballFrameAnimation = (AnimationDrawable) football.getBackground();

        footballFrameAnimation.start();


        //hitBoxes for the imageViews
        ImageView playerView = findViewById(R.id.playerImageView);

        ImageView guardView = findViewById(R.id.guardImageView);

        ImageView footballView = findViewById(R.id.footballImageView);

        Rect playerRect = new Rect();
        Rect guardRect = new Rect();
        Rect footballRect = new Rect();

        //Use:
        // Rect.intersects(rect1,rect2){
        //
        // insert method here
        // }



        fixBackground();
        
    }
    //Busted, dont touch
    public void fixBackground(){

        BitmapDrawable field = (BitmapDrawable) getDrawable(R.drawable.field);
        Bitmap fieldFixed = Bitmap.createScaledBitmap(field.getBitmap(),1920,1080,true);
        Drawable fieldDone = new BitmapDrawable(getResources(), fieldFixed);
        ConstraintLayout gameScreen = (ConstraintLayout) findViewById(R.id.gameScreen);

        gameScreen.setBackground(fieldDone);

    }


    //Change animations 
    public void devChangeAllUp(View v){

        playerChangeAnimUp(v);
        guardChangeAnimUp(v);
        footballChangeAnimUp(v);
    }

    public void devChangeAllDown(View v){

        playerChangeAnimDown(v);
        guardChangeAnimDown(v);
        footballChangeAnimDown(v);
    }

    public void devChangeAllLeft(View v){

        playerChangeAnimLeft(v);
        guardChangeAnimLeft(v);
        footballChangeAnimLeft(v);
    }

    public void devChangeAllRight(View v){

        playerChangeAnimRight(v);
        guardChangeAnimRight(v);
        footballChangeAnimRight(v);
    }


    //Changes animations for the player
    public void playerChangeAnimUp(View v){

        ImageView img = findViewById(R.id.playerImageView);
        img.setBackgroundResource(R.drawable.player_animation_up);

        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();

        frameAnimation.start();

    }

    public void playerChangeAnimDown(View v){

        ImageView img = findViewById(R.id.playerImageView);
        img.setBackgroundResource(R.drawable.player_animation_down);

        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();

        frameAnimation.start();

    }

    public void playerChangeAnimLeft(View v){

        ImageView img = findViewById(R.id.playerImageView);
        img.setBackgroundResource(R.drawable.player_animation_left);

        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();

        frameAnimation.start();

    }

    public void playerChangeAnimRight(View v){

        ImageView img = findViewById(R.id.playerImageView);
        img.setBackgroundResource(R.drawable.player_animation_right);

        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();

        frameAnimation.start();

    }

    //Changes the animations for the guard character
    public void guardChangeAnimUp(View v){

        ImageView img = findViewById(R.id.guardImageView);
        img.setBackgroundResource(R.drawable.cop_animation_up);

        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();

        frameAnimation.start();

    }

    public void guardChangeAnimDown(View v){

        ImageView img = findViewById(R.id.guardImageView);
        img.setBackgroundResource(R.drawable.cop_animation_down);

        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();

        frameAnimation.start();

    }

    public void guardChangeAnimLeft(View v){

        ImageView img = findViewById(R.id.guardImageView);
        img.setBackgroundResource(R.drawable.cop_animation_left);

        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();

        frameAnimation.start();

    }

    public void guardChangeAnimRight(View v){

        ImageView img = findViewById(R.id.guardImageView);
        img.setBackgroundResource(R.drawable.cop_animation_right);

        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();

        frameAnimation.start();

    }

    //Changes the animations for the football character
    public void footballChangeAnimUp(View v){

        ImageView img = findViewById(R.id.footballImageView);
        img.setBackgroundResource(R.drawable.footballer_animation_up);

        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();

        frameAnimation.start();

    }

    public void footballChangeAnimDown(View v){

        ImageView img = findViewById(R.id.footballImageView);
        img.setBackgroundResource(R.drawable.footballer_animation_down);

        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();

        frameAnimation.start();

    }

    public void footballChangeAnimLeft(View v){

        ImageView img = findViewById(R.id.footballImageView);
        img.setBackgroundResource(R.drawable.footballer_animation_left);

        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();

        frameAnimation.start();

    }

    public void footballChangeAnimRight(View v){

        ImageView img = findViewById(R.id.footballImageView);
        img.setBackgroundResource(R.drawable.footballer_animation_right);

        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();

        frameAnimation.start();

    }

    public void moevPlayer(View v){}

    // Start stopwatch when the game starts
    public void startTimer(){

        running = true;

    }


    // Stop stopwatch when the game ends
    public void stopTimer(View v){

        running = false;

    }


    // Reset stopwatch when the reset button is clicked
    public void resetTimer(View v){

        running = false;
        milsecs = 0;

    }

    private void runTimer(){

        final TextView timeView = (TextView) findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable(){
            @Override
            public void run() {


                int milsec = milsecs % 10;

                int secs = (milsecs / 10) % 60;

                int minutes  = (milsecs / 600);


                String time = String.format(Locale.getDefault(), "%02d:%02d:%01d", minutes, secs, milsec);

                timeView.setText(time);

                if(running){
                    milsecs ++;
                }
                handler.postDelayed(this ,100);
            }


        });



    }


}
