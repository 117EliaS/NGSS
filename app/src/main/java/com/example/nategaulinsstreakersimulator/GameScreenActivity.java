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
import java.util.Random;

public class GameScreenActivity extends AppCompatActivity {

    private int milsecs = 0;
    private int secs = 0;
    private boolean running = false;
    private boolean runningPlayer = false;
    private Rect playerRect;
    private Rect guardRect;
    private Rect footballRect;
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

        //----

        ImageView guard1 = findViewById(R.id.guardImageView1);
        guard1.setBackgroundResource(R.drawable.cop_animation_up);

        guardFrameAnimation1 = (AnimationDrawable) guard1.getBackground();

        guardFrameAnimation1.start();

        //----

        ImageView guard2 = findViewById(R.id.guardImageView2);
        guard2.setBackgroundResource(R.drawable.cop_animation_up);

        guardFrameAnimation2 = (AnimationDrawable) guard2.getBackground();

        guardFrameAnimation2.start();

        //----

        ImageView guard3 = findViewById(R.id.guardImageView3);
        guard3.setBackgroundResource(R.drawable.cop_animation_up);

        guardFrameAnimation3 = (AnimationDrawable) guard3.getBackground();

        guardFrameAnimation3.start();

        //----
        ImageView guard4 = findViewById(R.id.guardImageView4);
        guard4.setBackgroundResource(R.drawable.cop_animation_up);

        guardFrameAnimation4 = (AnimationDrawable) guard4.getBackground();

        guardFrameAnimation4.start();

        //----

        ImageView guard5 = findViewById(R.id.guardImageView5);
        guard5.setBackgroundResource(R.drawable.cop_animation_up);

        guardFrameAnimation5 = (AnimationDrawable) guard5.getBackground();

        guardFrameAnimation5.start();

        //----

        ImageView guard6 = findViewById(R.id.guardImageView6);
        guard6.setBackgroundResource(R.drawable.cop_animation_up);

        guardFrameAnimation6 = (AnimationDrawable) guard6.getBackground();

        guardFrameAnimation6.start();

        //----

        ImageView guard7 = findViewById(R.id.guardImageView7);
        guard7.setBackgroundResource(R.drawable.cop_animation_up);

        guardFrameAnimation7 = (AnimationDrawable) guard7.getBackground();

        guardFrameAnimation7.start();

        //----


        ImageView football1 = findViewById(R.id.footballImageView1);
        football1.setBackgroundResource(R.drawable.footballer_animation_up);

        footballFrameAnimation1 = (AnimationDrawable) football1.getBackground();

        footballFrameAnimation1.start();

        //----

        ImageView football2 = findViewById(R.id.footballImageView2);
        football2.setBackgroundResource(R.drawable.footballer_animation_up);

        footballFrameAnimation2 = (AnimationDrawable) football2.getBackground();

        footballFrameAnimation2.start();

        //----


        ImageView football3 = findViewById(R.id.footballImageView3);
        football3.setBackgroundResource(R.drawable.footballer_animation_up);

        footballFrameAnimation3 = (AnimationDrawable) football3.getBackground();

        footballFrameAnimation3.start();

        //----


        ImageView football4 = findViewById(R.id.footballImageView4);
        football4.setBackgroundResource(R.drawable.footballer_animation_up);

        footballFrameAnimation4 = (AnimationDrawable) football4.getBackground();

        footballFrameAnimation4.start();

        //----



        //hitBoxes for the imageViews
        ImageView playerView = findViewById(R.id.playerImageView);

        ImageView guardView1 = findViewById(R.id.guardImageView1);
        ImageView guardView2 = findViewById(R.id.guardImageView2);
        ImageView guardView3 = findViewById(R.id.guardImageView3);
        ImageView guardView4 = findViewById(R.id.guardImageView4);
        ImageView guardView5 = findViewById(R.id.guardImageView5);
        ImageView guardView6 = findViewById(R.id.guardImageView6);
        ImageView guardView7 = findViewById(R.id.guardImageView7);

        ImageView footballView1 = findViewById(R.id.footballImageView1);
        ImageView footballView2 = findViewById(R.id.footballImageView2);
        ImageView footballView3 = findViewById(R.id.footballImageView3);
        ImageView footballView4 = findViewById(R.id.footballImageView4);

        playerRect = new Rect();

        footballers = new Footballer[] {new Footballer(0, 0, footballView1, 2),
                new Footballer(0, 0, footballView2, 2),
                new Footballer(0, 0, footballView3, 2),
                new Footballer(0, 0, footballView4, 2)};

        cops = new Cop[] {new Cop(0, 0, guardView1, .9),
                new Cop(0, 0, guardView2, .9),
                new Cop(0, 0, guardView3, .9),
                new Cop(0, 0, guardView4, .9),
                new Cop(0, 0, guardView5, .9),
                new Cop(0, 0, guardView6, .9),
                new Cop(0, 0, guardView7, .9)};

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

        if(secs > 4 && copsSpawned == 1){

            cops[1].getView().setVisibility(View.VISIBLE);
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

            copsSpawned++;
        }

        if(secs > 8 && copsSpawned == 2){

            cops[2].getView().setVisibility(View.VISIBLE);
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

            copsSpawned++;
        }

        if(secs > 12 && copsSpawned == 3){

            cops[3].getView().setVisibility(View.VISIBLE);
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

            copsSpawned++;
        }

        if(secs > 16 && copsSpawned == 4){

            cops[4].getView().setVisibility(View.VISIBLE);


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

            copsSpawned++;
        }

        if(secs > 20 && copsSpawned == 5){

            cops[5].getView().setVisibility(View.VISIBLE);
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

            copsSpawned++;
        }

        if(secs > 24 && copsSpawned == 6){

            cops[6].getView().setVisibility(View.VISIBLE);
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

            copsSpawned++;
        }


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

    public void spawnFootballPlayer(){



        if(gameOn = true) {
            final Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {



                    double randChance = Math.random();
                    int randInt = (int) (randChance * 8);

                    if(randInt == 5){

                        int yPos = 60 + (int)(Math.random() * 850);

                        footballers[footballRotation].setPosX(0);
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



                    spawnMoreCops();

                    handler.postDelayed(this, 2);
                }
            });
        }
    }

    public void endGame(){

        stopRunningPlayer();
        stopTimer();
        gameOn = false;
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

    //Changes the animations for the guard character
    public void guardChangeAnimUp(ImageView v){

        ImageView img = v;
        img.setBackgroundResource(R.drawable.cop_animation_up);

        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();

        frameAnimation.start();

    }

    public void guardChangeAnimDown(ImageView v){

        ImageView img = v;
        img.setBackgroundResource(R.drawable.cop_animation_down);

        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();

        frameAnimation.start();

    }

    public void guardChangeAnimLeft(ImageView v){

        ImageView img = v;
        img.setBackgroundResource(R.drawable.cop_animation_left);

        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();

        frameAnimation.start();

    }

    public void guardChangeAnimRight(ImageView v){

        ImageView img = v;
        img.setBackgroundResource(R.drawable.cop_animation_right);

        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();

        frameAnimation.start();

    }

    //Changes the animations for the football character
    public void footballChangeAnimUp(ImageView v){

        ImageView img = v;
        img.setBackgroundResource(R.drawable.footballer_animation_up);

        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();

        frameAnimation.start();

    }

    public void footballChangeAnimDown(ImageView v){

        ImageView img = v;
        img.setBackgroundResource(R.drawable.footballer_animation_down);

        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();

        frameAnimation.start();

    }

    public void footballChangeAnimLeft(ImageView v){

        ImageView img = v;
        img.setBackgroundResource(R.drawable.footballer_animation_left);

        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();

        frameAnimation.start();

    }

    public void footballChangeAnimRight(ImageView v){

        ImageView img = v;
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

                secs = (milsecs / 10) % 60;

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
