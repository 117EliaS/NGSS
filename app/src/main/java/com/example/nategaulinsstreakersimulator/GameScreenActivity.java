package com.example.nategaulinsstreakersimulator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class GameScreenActivity extends AppCompatActivity {

    private int milsecs = 0;
    private int secs = 0;
    private boolean running = false;
    private boolean runningPlayer = false;
    private Rect playerRect;
    private boolean gameOn = false;

    // Drawables for each entity to be displayed
    private AnimationDrawable playerFrameAnimation;
    private AnimationDrawable guardFrameAnimation1;
    private AnimationDrawable guardFrameAnimation2;
    private AnimationDrawable guardFrameAnimation3;
    private AnimationDrawable guardFrameAnimation4;
    private AnimationDrawable guardFrameAnimation5;
    private AnimationDrawable guardFrameAnimation6;
    private AnimationDrawable guardFrameAnimation7;
    private AnimationDrawable footballFrameAnimation1;
    private AnimationDrawable footballFrameAnimation2;
    private AnimationDrawable footballFrameAnimation3;
    private AnimationDrawable footballFrameAnimation4;

    private Footballer[] footballers;
    private Cop[] cops;
    private int copsSpawned = 1;
    private int copInterval = 10;
    private int footballRotation = 0;
    private int timesEnded = 0;
    private String timeFormatted;

    private int playerDirection = 3; //0 Up, 1 Down, 2 Left, 3 Right

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        // Grab buttons
        Button upArrow = findViewById(R.id.buttonUp);

        Button downArrow = findViewById(R.id.buttonDown);

        Button leftArrow = findViewById(R.id.buttonLeft);

        Button rightArrow = findViewById(R.id.buttonRight);

        // Make buttons look nice
        BitmapDrawable arrow_up = (BitmapDrawable) getDrawable(R.drawable.arrow_up);
        Bitmap arrow_up_bmp = Bitmap.createScaledBitmap(arrow_up.getBitmap(), 1920, 1920, false);
        Drawable arrow_up_done = new BitmapDrawable(getResources(), arrow_up_bmp);
        upArrow.setBackground(arrow_up_done);
        upArrow.getBackground().setAlpha(220);

        BitmapDrawable arrow_left = (BitmapDrawable) getDrawable(R.drawable.arrow_left);
        Bitmap arrow_left_bmp = Bitmap.createScaledBitmap(arrow_left.getBitmap(), 1920, 1920, false);
        Drawable arrow_left_done = new BitmapDrawable(getResources(), arrow_left_bmp);
        leftArrow.setBackground(arrow_left_done);
        leftArrow.getBackground().setAlpha(220);

        BitmapDrawable arrow_right = (BitmapDrawable) getDrawable(R.drawable.arrow_right);
        Bitmap arrow_right_bmp = Bitmap.createScaledBitmap(arrow_right.getBitmap(), 1920, 1920, false);
        Drawable arrow_right_done = new BitmapDrawable(getResources(), arrow_right_bmp);
        rightArrow.setBackground(arrow_right_done);
        rightArrow.getBackground().setAlpha(220);

        BitmapDrawable arrow_down = (BitmapDrawable) getDrawable(R.drawable.arrow_down);
        Bitmap arrow_down_bmp = Bitmap.createScaledBitmap(arrow_down.getBitmap(), 1920, 1920, false);
        Drawable arrow_down_done = new BitmapDrawable(getResources(), arrow_down_bmp);
        downArrow.setBackground(arrow_down_done);
        downArrow.getBackground().setAlpha(220);

        // Timer stuff
        runTimer();
        startTimer();

        runningPlayer = true;
        gameOn = true;

        //Beginning animations
        ImageView playerView = findViewById(R.id.playerImageView);
        playerView.setBackgroundResource(R.drawable.player_animation_right);

        playerFrameAnimation = (AnimationDrawable) playerView.getBackground();

        playerFrameAnimation.start();

        //----

        ImageView guardView1 = findViewById(R.id.guardImageView1);
        guardView1.setBackgroundResource(R.drawable.cop_animation_up);

        guardFrameAnimation1 = (AnimationDrawable) guardView1.getBackground();

        guardFrameAnimation1.start();

        //----

        ImageView guardView2 = findViewById(R.id.guardImageView2);
        guardView2.setBackgroundResource(R.drawable.cop_animation_up);

        guardFrameAnimation2 = (AnimationDrawable) guardView2.getBackground();

        guardFrameAnimation2.start();

        //----

        ImageView guardView3 = findViewById(R.id.guardImageView3);
        guardView3.setBackgroundResource(R.drawable.cop_animation_up);

        guardFrameAnimation3 = (AnimationDrawable) guardView3.getBackground();

        guardFrameAnimation3.start();

        //----
        ImageView guardView4 = findViewById(R.id.guardImageView4);
        guardView4.setBackgroundResource(R.drawable.cop_animation_up);

        guardFrameAnimation4 = (AnimationDrawable) guardView4.getBackground();

        guardFrameAnimation4.start();

        //----

        ImageView guardView5 = findViewById(R.id.guardImageView5);
        guardView5.setBackgroundResource(R.drawable.cop_animation_up);

        guardFrameAnimation5 = (AnimationDrawable) guardView5.getBackground();

        guardFrameAnimation5.start();

        //----

        ImageView guardView6 = findViewById(R.id.guardImageView6);
        guardView6.setBackgroundResource(R.drawable.cop_animation_up);

        guardFrameAnimation6 = (AnimationDrawable) guardView6.getBackground();

        guardFrameAnimation6.start();

        //----

        ImageView guardView7 = findViewById(R.id.guardImageView7);
        guardView7.setBackgroundResource(R.drawable.cop_animation_up);

        guardFrameAnimation7 = (AnimationDrawable) guardView7.getBackground();

        guardFrameAnimation7.start();

        //----


        ImageView footballView1 = findViewById(R.id.footballImageView1);
        footballView1.setBackgroundResource(R.drawable.footballer_animation_up);

        footballFrameAnimation1 = (AnimationDrawable) footballView1.getBackground();

        footballFrameAnimation1.start();

        //----

        ImageView footballView2 = findViewById(R.id.footballImageView2);
        footballView2.setBackgroundResource(R.drawable.footballer_animation_up);

        footballFrameAnimation2 = (AnimationDrawable) footballView2.getBackground();

        footballFrameAnimation2.start();

        //----


        ImageView footballView3 = findViewById(R.id.footballImageView3);
        footballView3.setBackgroundResource(R.drawable.footballer_animation_up);

        footballFrameAnimation3 = (AnimationDrawable) footballView3.getBackground();

        footballFrameAnimation3.start();

        //----


        ImageView footballView4 = findViewById(R.id.footballImageView4);
        footballView4.setBackgroundResource(R.drawable.footballer_animation_up);

        footballFrameAnimation4 = (AnimationDrawable) footballView4.getBackground();

        footballFrameAnimation4.start();

        //----

        // Initialize enemies
        footballers = new Footballer[] {new Footballer(0, 0, footballView1, 2),
                new Footballer(0, 0, footballView2, 2),
                new Footballer(0, 0, footballView3, 2),
                new Footballer(0, 0, footballView4, 2)};

        cops = new Cop[] {new Cop(0, 0, guardView1),
                new Cop(0, 0, guardView2),
                new Cop(0, 0, guardView3),
                new Cop(0, 0, guardView4),
                new Cop(0, 0, guardView5),
                new Cop(0, 0, guardView6),
                new Cop(0, 0, guardView7)};

        cops[1].getView().setVisibility(View.INVISIBLE);
        cops[2].getView().setVisibility(View.INVISIBLE);
        cops[3].getView().setVisibility(View.INVISIBLE);
        cops[4].getView().setVisibility(View.INVISIBLE);
        cops[5].getView().setVisibility(View.INVISIBLE);
        cops[6].getView().setVisibility(View.INVISIBLE);

        //Initialize player hitbox
        playerRect = new Rect();

        playerView.getHitRect(playerRect);

        //Start everything
        fixBackground();

        startRunningPlayer();
        movePlayer();

        spawnFootballPlayer();

        moveEnemies();

        checkCollision();

    }

    // Fix the background, duh
    public void fixBackground(){
        BitmapDrawable field = (BitmapDrawable) getDrawable(R.drawable.field);
        Bitmap fieldFixed = Bitmap.createScaledBitmap(field.getBitmap(),1920,1080,false);
        Drawable fieldDone = new BitmapDrawable(getResources(), fieldFixed);
        ConstraintLayout gameScreen = findViewById(R.id.gameScreen);

        gameScreen.setBackground(fieldDone);
    }

    // Spawn cops, duh
    public void spawnMoreCops(){
        // The position in the array of the cop we're spawning
        int thisCop = secs / copInterval;

        // Is it time to spawn a cop?
        if(secs % copInterval == 0 && thisCop < cops.length && copsSpawned <= thisCop) {
            // Set the spawn location of this cop
            int side = (int) (Math.random() * 2);
            int posDownSide = 60 + (int) (Math.random() * 850);

            // Make cop with patented Anti-Nate magic
            if (side == 1) {
                cops[thisCop].setPosX(0);
                cops[thisCop].setPosY(posDownSide);
                cops[thisCop].setDirection(2);
            } else {
                cops[thisCop].setPosX(1700);
                cops[thisCop].setPosY(posDownSide);
                cops[thisCop].setDirection(4);
            }

            cops[thisCop].getView().setVisibility(View.VISIBLE);
            copsSpawned++;
        }
    }

    // Move enemies, duh
    public void moveEnemies(){
        final ImageView playerView = findViewById(R.id.playerImageView);

        //Game better be on if we movin things
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                if (gameOn) {

                    for (int i = 0; i < footballers.length; i++) {
                        footballers[i].move();
                    }

                    for (int i = 0; i < cops.length; i++) {
                        cops[i].move((int) playerView.getX(), (int) playerView.getY());
                    }

                    handler.postDelayed(this, 2);
                }
            }
        });

    }

    // Spawn football player, duh
    public void spawnFootballPlayer(){

        if(gameOn) {
            final Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {

                    // Choose side to spawn on and generate number from 0 to 8
                    int side = (int)(Math.random() * 2);
                    int randInt = (int) (Math.random() * 8);

                    // If the number generated is 5 (???) spawn footballer
                    if(randInt == 5){

                        int yPos = 60 + (int)(Math.random() * 850);

                        if(side == 1) {
                            footballers[footballRotation].setPosX(0);
                            footballers[footballRotation].setDirection(2);
                        } else {
                            footballers[footballRotation].setPosX(1700);
                            footballers[footballRotation].setDirection(4);
                        }

                        footballers[footballRotation].setPosY(yPos);

                        // footballRotation controls which footballer is being used,
                        // so a currently running player isn't teleported
                        footballRotation++;
                        if(footballRotation > 3){
                            footballRotation = 0;
                        }
                    }

                    // Try again every 0.5 seconds
                    handler.postDelayed(this, 500);
                }
            });
        }


    }

    // Check collision, duh
    public void checkCollision(){

        final ImageView playerView = findViewById(R.id.playerImageView);

        if(gameOn) {
            final Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {

                    // Reset player's hitbox
                    playerView.getHitRect(playerRect);

                    playerRect.set(playerRect.left+10, playerRect.top+10, playerRect.right-10, playerRect.bottom-10);

                    // Check if collided with an active enemy
                    for(int i=0; i<footballers.length; i++){
                        if(footballers[i].checkIntersect(playerRect) &&
                                footballers[i].getView().getVisibility() == View.VISIBLE){
                            endGame();
                        }
                    }

                    for(int i=0; i<cops.length; i++){
                        if(cops[i].checkIntersect(playerRect) &&
                                cops[i].getView().getVisibility() == View.VISIBLE){
                            endGame();
                        }
                    }

                    // If player is hitting wall, wall says no
                    if(playerView.getY() < 60){

                        stopRunningPlayer();
                        playerView.setY(61);
                    }

                    if(playerView.getY() > 900){

                        stopRunningPlayer();
                        playerView.setY(898);
                    }

                    if(playerView.getX() < 50){

                        stopRunningPlayer();
                        playerView.setX(51);
                    }

                    if(playerView.getX() > 1750){

                        stopRunningPlayer();
                        playerView.setX(1749);
                    }

                    // Turn cops around if they wander off
                    for(int i=0; i<cops.length; i++){
                        if(cops[i].getPosY() < 55){
                            cops[i].setDirection(1);
                        }
                        if(cops[i].getPosY() > 905){
                            cops[i].setDirection(3);
                        }
                        if(cops[i].getPosX() < 45){
                            cops[i].setDirection(2);
                        }
                        if(cops[i].getPosX() > 1755){
                            cops[i].setDirection(4);
                        }
                    }

                    spawnMoreCops();

                    handler.postDelayed(this, 2);
                }
            });
        }
    }

    // End the game, duh
    public void endGame(){

        if(timesEnded == 0) {

            gameOn = false;
            stopRunningPlayer();
            stopTimer();
            footballFrameAnimation1.stop();
            footballFrameAnimation2.stop();
            footballFrameAnimation3.stop();
            footballFrameAnimation4.stop();
            guardFrameAnimation1.stop();
            guardFrameAnimation2.stop();
            guardFrameAnimation3.stop();
            guardFrameAnimation4.stop();
            guardFrameAnimation5.stop();
            guardFrameAnimation6.stop();
            guardFrameAnimation7.stop();
            playerFrameAnimation.stop();

            this.finish();

            endScreen();

            timesEnded++;

        }



    }

    // Go to the end screen
    public void endScreen(){

        Intent intent = new Intent(this, endgame_activity.class);


        intent.putExtra(endgame_activity.TIME, timeFormatted);

        startActivity(intent);
        this.finish();
    }

    public void changePlayerDirUp(View v){

        if(gameOn) {

            playerChangeAnimUp(v);
            stopRunningPlayer();
            setPlayerDirection(0);
            startRunningPlayer();

        }


    }
    public void changePlayerDirDown(View v){

        if(gameOn) {

            playerChangeAnimDown(v);
            stopRunningPlayer();
            setPlayerDirection(1);
            startRunningPlayer();

        }



    }
    public void changePlayerDirLeft(View v){

        if (gameOn) {


            playerChangeAnimLeft(v);
            stopRunningPlayer();
            setPlayerDirection(2);
            startRunningPlayer();

        }



    }
    public void changePlayerDirRight(View v){

        if(gameOn) {

            playerChangeAnimRight(v);
            stopRunningPlayer();
            setPlayerDirection(3);
            startRunningPlayer();

        }



    }

    public void startRunningPlayer(){

        runningPlayer = true;
    }

    public void stopRunningPlayer(){

        runningPlayer = false;

    }

    public void movePlayer(){

        final ImageView player = findViewById(R.id.playerImageView);

        if(runningPlayer && gameOn) {
            final Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (gameOn) {

                        int posx = (int) player.getX();
                        int posy = (int) player.getY();

                        if (playerDirection == 0) {
                            player.setY(posy - 5);
                        } else if (playerDirection == 1) {
                            player.setY(posy + 5);
                        } else if (playerDirection == 2) {
                            player.setX(posx - 5);
                        } else {
                            player.setX(posx + 5);
                        }

                        handler.postDelayed(this, 2);
                    }
                }
            });
        }
    }

    //Changes animations for the player
    public void playerChangeAnimUp(View v){

        ImageView img = findViewById(R.id.playerImageView);
        img.setBackgroundResource(R.drawable.player_animation_up);

        playerFrameAnimation = (AnimationDrawable) img.getBackground();

        playerFrameAnimation.start();

    }

    public void playerChangeAnimDown(View v){

        ImageView img = findViewById(R.id.playerImageView);
        img.setBackgroundResource(R.drawable.player_animation_down);

        playerFrameAnimation = (AnimationDrawable) img.getBackground();

        playerFrameAnimation.start();

    }

    public void playerChangeAnimLeft(View v){

        ImageView img = findViewById(R.id.playerImageView);
        img.setBackgroundResource(R.drawable.player_animation_left);

        playerFrameAnimation = (AnimationDrawable) img.getBackground();

        playerFrameAnimation.start();

    }

    public void playerChangeAnimRight(View v){

        ImageView img = findViewById(R.id.playerImageView);
        img.setBackgroundResource(R.drawable.player_animation_right);

        playerFrameAnimation = (AnimationDrawable) img.getBackground();

        playerFrameAnimation.start();

    }

    // Start stopwatch when the game starts
    public void startTimer(){

        running = true;

    }


    // Stop stopwatch when the game ends
    public void stopTimer(){

        running = false;

    }


    // Reset stopwatch when the reset button is clicked
    public void resetTimer(View v){

        running = false;
        milsecs = 0;

    }

    private void runTimer(){

        final TextView timeView = findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable(){
            @Override
            public void run() {


                int milsec = milsecs % 10;

                secs = (milsecs / 10) % 60;

                int minutes  = (milsecs / 600);


                String time = String.format(Locale.getDefault(), "%02d:%02d:%01d", minutes, secs, milsec);
                timeFormatted = String.format(Locale.getDefault(), "%02d:%02d:%01d", minutes, secs, milsec+1);


                timeView.setText(time);

                if(running){
                    milsecs ++;
                }
                handler.postDelayed(this ,100);
            }


        });
    }

    public void setPlayerDirection(int playerDirection) {
        this.playerDirection = playerDirection;
    }


}
