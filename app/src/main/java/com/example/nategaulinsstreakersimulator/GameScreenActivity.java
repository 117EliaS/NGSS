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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class GameScreenActivity extends AppCompatActivity {

    private int milsecs = 0;
    private boolean running = false;
    private boolean runningPlayer = false;
    private Rect playerRect;
    private Rect guardRect;
    private Rect footballRect;
    private boolean gameOn = false;
    private AnimationDrawable playerFrameAnimation;
    private AnimationDrawable guardFrameAnimation;
    private AnimationDrawable footballFrameAnimation;
    private Footballer[] footballers;
    private Cop[] cops;

    private int playerDirection = 0; //0 Up, 1 Down, 2 Left, 3 Right

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        Button upArrow = (Button) findViewById(R.id.buttonUp);

        Button downArrow = (Button) findViewById(R.id.buttonDown);

        Button leftArrow = (Button) findViewById(R.id.buttonLeft);

        Button rightArrow = (Button) findViewById(R.id.buttonRight);

        upArrow.getBackground().setAlpha(200);
        downArrow.getBackground().setAlpha(200);
        leftArrow.getBackground().setAlpha(200);
        rightArrow.getBackground().setAlpha(200);

        // Timer shit
        runTimer();
        startTimer();

        runningPlayer = true;
        gameOn = true;

        //Beginning animations
        ImageView player = findViewById(R.id.playerImageView);
        player.setBackgroundResource(R.drawable.player_animation_up);

        playerFrameAnimation = (AnimationDrawable) player.getBackground();

        playerFrameAnimation.start();

        ImageView guard = findViewById(R.id.guardImageView);
        guard.setBackgroundResource(R.drawable.cop_animation_up);

       guardFrameAnimation = (AnimationDrawable) guard.getBackground();

        guardFrameAnimation.start();

        ImageView football = findViewById(R.id.footballImageView);
        football.setBackgroundResource(R.drawable.footballer_animation_up);

        footballFrameAnimation = (AnimationDrawable) football.getBackground();

        footballFrameAnimation.start();


        //hitBoxes for the imageViews
        ImageView playerView = findViewById(R.id.playerImageView);

        ImageView guardView = findViewById(R.id.guardImageView);

        ImageView footballView = findViewById(R.id.footballImageView);

        playerRect = new Rect();

        footballers = new Footballer[] {new Footballer(0, 0, footballView, 2)};
        cops = new Cop[] {new Cop(0, 0, guardView)};

        playerView.getHitRect(playerRect);

        fixBackground();

        startRunningPlayer();
        movePlayer();

        moveEnemies();

        checkCollision();

    }
    //Busted, dont touch
    public void fixBackground(){

        BitmapDrawable field = (BitmapDrawable) getDrawable(R.drawable.field);
        Bitmap fieldFixed = Bitmap.createScaledBitmap(field.getBitmap(),1920,1080,true);
        Drawable fieldDone = new BitmapDrawable(getResources(), fieldFixed);
        ConstraintLayout gameScreen = findViewById(R.id.gameScreen);

        gameScreen.setBackground(fieldDone);

    }

    public void moveEnemies(){
        final ImageView playerView = findViewById(R.id.playerImageView);

        if(gameOn = true) {
            final Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {

                    for(int i=0; i < footballers.length; i++){
                        footballers[i].move();
                    }

                    for(int i=0; i < cops.length; i++){
                        cops[i].move((int) playerView.getX(), (int) playerView.getY());
                    }

                    handler.postDelayed(this, 2);
                }
            });
        }
    }

    public void checkCollision(){

        final ImageView playerView = findViewById(R.id.playerImageView);

        if(gameOn = true) {
            final Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {

                    playerView.getHitRect(playerRect);

                    for(int i=0; i<footballers.length; i++){
                        if(footballers[i].checkIntersect(playerRect)){
                            endGame();
                        }
                    }

                    for(int i=0; i<cops.length; i++){
                        if(cops[i].checkIntersect(playerRect)){
                            endGame();
                        }
                    }

                    if(playerView.getY() < 60 || playerView.getY() > 900 || playerView.getX() < 10 || playerView.getX() > 1700){

                        endGame();
                    }

                    handler.postDelayed(this, 2);
                }
            });
        }
    }

    public void endGame(){

        stopRunningPlayer();
        stopTimer();
        gameOn = false;
        footballFrameAnimation.stop();
        guardFrameAnimation.stop();
        playerFrameAnimation.stop();

    }


    public void changePlayerDirUp(View v){


            playerChangeAnimUp(v);
            stopRunningPlayer();
            setPlayerDirection(0);
            startRunningPlayer();




    }
    public void changePlayerDirDown(View v){


            playerChangeAnimDown(v);
            stopRunningPlayer();
            setPlayerDirection(1);
            startRunningPlayer();





    }
    public void changePlayerDirLeft(View v){


            playerChangeAnimLeft(v);
            stopRunningPlayer();
            setPlayerDirection(2);
            startRunningPlayer();





    }
    public void changePlayerDirRight(View v){


            playerChangeAnimRight(v);
            stopRunningPlayer();
            setPlayerDirection(3);
            startRunningPlayer();





    }

    public void startRunningPlayer(){

        runningPlayer = true;
    }

    public void stopRunningPlayer(){

        runningPlayer = false;

    }

    public void movePlayer(){

        final ImageView player = (ImageView) findViewById(R.id.playerImageView);



        int posx = (int) player.getX();
        int posy = (int) player.getY();

        if(runningPlayer = true && gameOn == true) {
            final Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {

                    if (runningPlayer == true) {


                    int posx = (int) player.getX();
                    int posy = (int) player.getY();

                    if (playerDirection == 0) {

                        player.setY(posy - 5);

                    }
                    if (playerDirection == 1) {

                        player.setY(posy + 5);

                    }
                    if (playerDirection == 2) {

                        player.setX(posx - 5);

                    }
                    if (playerDirection == 3) {

                        player.setX(posx + 5);

                    }


                    handler.postDelayed(this, 2);
                }
            }
            });
        }
    }

    //Change animations (DEVELOPER)
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

    public void setPlayerDirection(int playerDirection) {
        this.playerDirection = playerDirection;
    }


}
