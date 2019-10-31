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
    private int footballRotation = 0;
    private int timesEnded = 0;
    private String timeFormatted;

    private int playerDirection = 3; //0 Up, 1 Down, 2 Left, 3 Right

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        Button upArrow = findViewById(R.id.buttonUp);

        Button downArrow = findViewById(R.id.buttonDown);

        Button leftArrow = findViewById(R.id.buttonLeft);

        Button rightArrow = findViewById(R.id.buttonRight);

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



        //hitBoxes for the imageViews
        playerRect = new Rect();

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


        playerView.getHitRect(playerRect);

        fixBackground();

        startRunningPlayer();
        movePlayer();

        spawnFootballPlayer();

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

    public void spawnMoreCops(){

        int side = (int)(Math.random() * 2);

        int posDownSide = 60 + (int)(Math.random() * 850);

        if(secs > 10 && copsSpawned == 1){


            if(side == 1) {
                cops[1].setPosX(0);
                cops[1].setPosY(posDownSide);
                cops[1].setDirection(2);
            }

            else{

                cops[1].setPosX(1700);
                cops[1].setPosY(posDownSide);
                cops[1].setDirection(4);

            }
            cops[1].getView().setVisibility(View.VISIBLE);

            copsSpawned++;
        }

        if(secs > 20 && copsSpawned == 2){


            if(side == 1) {
                cops[2].setPosX(0);
                cops[2].setPosY(posDownSide);
                cops[2].setDirection(2);
            }

            else{

                cops[2].setPosX(1700);
                cops[2].setPosY(posDownSide);
                cops[2].setDirection(4);

            }
            cops[2].getView().setVisibility(View.VISIBLE);

            copsSpawned++;
        }

        if(secs > 30 && copsSpawned == 3){


            if(side == 1) {
                cops[3].setPosX(0);
                cops[3].setPosY(posDownSide);
                cops[3].setDirection(2);
            }

            else{

                cops[3].setPosX(1700);
                cops[3].setPosY(posDownSide);
                cops[3].setDirection(4);

            }
            cops[3].getView().setVisibility(View.VISIBLE);

            copsSpawned++;
        }

        if(secs > 40 && copsSpawned == 4){




            if(side == 1) {
                cops[4].setPosX(0);
                cops[4].setPosY(posDownSide);
                cops[4].setDirection(2);
            }

            else{

                cops[4].setPosX(1700);
                cops[4].setPosY(posDownSide);
                cops[4].setDirection(4);

            }
            cops[4].getView().setVisibility(View.VISIBLE);

            copsSpawned++;
        }

        if(secs > 50 && copsSpawned == 5){


            if(side == 1) {
                cops[5].setPosX(0);
                cops[5].setPosY(posDownSide);
                cops[5].setDirection(2);
            }

            else{

                cops[5].setPosX(1700);
                cops[5].setPosY(posDownSide);
                cops[5].setDirection(4);

            }
            cops[5].getView().setVisibility(View.VISIBLE);

            copsSpawned++;
        }

        if(secs > 60 && copsSpawned == 6){


            if(side == 1) {
                cops[6].setPosX(0);
                cops[6].setPosY(posDownSide);
                cops[6].setDirection(2);
            }

            else{

                cops[6].setPosX(1700);
                cops[6].setPosY(posDownSide);
                cops[6].setDirection(4);

            }
            cops[6].getView().setVisibility(View.VISIBLE);

            copsSpawned++;
        }


    }

    public void moveEnemies(){
        final ImageView playerView = findViewById(R.id.playerImageView);

        if(gameOn) {
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
    }

    public void spawnFootballPlayer(){



        if(gameOn) {
            final Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {



                    int side = (int)(Math.random() * 2);
                    double randChance = Math.random();
                    int randInt = (int) (randChance * 8);

                    if(randInt == 5){

                        int yPos = 60 + (int)(Math.random() * 850);

                        if(side == 1) {

                            footballers[footballRotation].setPosX(0);
                            footballers[footballRotation].setDirection(2);

                        }

                        else{

                            footballers[footballRotation].setPosX(1700);
                            footballers[footballRotation].setDirection(4);

                        }
                        footballers[footballRotation].setPosY(yPos);

                        footballRotation++;
                        if(footballRotation > 3){
                            footballRotation = 0;
                        }
                    }


                    handler.postDelayed(this, 500);
                }
            });
        }


    }

    public void checkCollision(){

        final ImageView playerView = findViewById(R.id.playerImageView);

        if(gameOn) {
            final Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {


                    playerRect.set(playerRect.left-5, playerRect.top-5, playerRect.right-5, playerRect.bottom-5);
                    playerView.getHitRect(playerRect);

                    playerRect.set(playerRect.left+10, playerRect.top+10, playerRect.right-10, playerRect.bottom-10);

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

                    if(playerView.getY() < 60 || playerView.getY() > 900 || playerView.getX() < 10 || playerView.getX() > 1700){
                        endGame();
                    }

                    for(int i=0; i<cops.length; i++){
                        if(cops[i].getPosY() < 55){
                            cops[i].setDirection(1);
                        }
                        if(cops[i].getPosY() > 905){
                            cops[i].setDirection(3);
                        }
                        if(cops[i].getPosX() < -1){
                            cops[i].setDirection(2);
                        }
                        if(cops[i].getPosX() > 1705){
                            cops[i].setDirection(4);
                        }

                        }


                    spawnMoreCops();

                    handler.postDelayed(this, 2);
                }
            });
        }
    }

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

    //Change animations (DEVELOPER)

    /*
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

     */


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
