package com.example.nategaulinsstreakersimulator;

import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

public class Cop extends Enemy{

    private final int SPEED = 4;
    private final int SENSITIVITY = 10;
    private final int TIME = 50;
    private int runTimer;
    private double bias;
    private int posX;
    private int posY;
    // Uses int values to represent the direction the entity is facing
    // None = 0, Down = 1, Right = 2, Up = 3, Left = 4
    private int direction;
    private ImageView imageView;
    private Rect hitbox;
    private AnimationDrawable animation;

    public Cop(int posX, int posY, ImageView imageView){
        super();

        this.direction = 1;
        this.posX = posX;
        this.posY = posY;
        this.runTimer = 0;
        this.bias = .3;

        this.imageView = imageView;
        this.hitbox = new Rect();
        imageView.getHitRect(this.hitbox);

        imageView.setBackgroundResource(R.drawable.cop_animation_down);
        this.animation = (AnimationDrawable) imageView.getBackground();
        this.animation.start();
    }

    public int getSPEED() {
        return SPEED;
    }

    public int getDirection(){
        return direction;
    }

    public int getSensitivity(){
        return SENSITIVITY;
    }

    public int getPosX(){
        return posX;
    }

    public int getPosY(){
        return posY;
    }

    public void setPosY(int y){
        posY = y;
    }

    public void setPosX(int x){
        posX = x;
    }

    public boolean checkIntersect(Rect playerHitbox){
        imageView.getHitRect(this.hitbox);
        return Rect.intersects(playerHitbox, hitbox);
    }

    public void setDirection(int dir){

        direction = dir;
    }

    public ImageView getView(){
        return imageView;
    }

    public void move(int playerX, int playerY){
        // Get distance from player on both axis
        int distanceX = Math.abs(playerX - posX);
        int distanceY = Math.abs(playerY - posY);

        // Check if timer reached 0. if so, change direction
        if(runTimer <= 0){
            // Should he move toward player?
            if(Math.random() <= bias){
                // Yes, run super advanced AI code
                // Check status based on player position
                if (distanceY <= SENSITIVITY) {
                    // Reached Y value, chase on X axis
                    if (playerX > posX) {
                        direction = 2;
                    } else if(playerX < posX){
                        direction = 4;
                    }
                } else if (distanceX <= SENSITIVITY) {
                    // Reached X value, chase on Y axis
                    if (playerY > posY) {
                        direction = 1;
                    } else if(playerY < posY){
                        direction = 3;
                    }
                } else {
                    // Not aligned with player, which distance is greater?
                    if(distanceX >= distanceY){
                        // Farther from player on x-axis. Chase on x-axis.
                        if (playerX > posX) {
                            direction = 2;
                        } else if(playerX < posX){
                            direction = 4;
                        }
                    } else {
                        // Farther from player on y-axis. Chase on y-axis.
                        if (playerY > posY) {
                            direction = 1;
                        } else if(playerY < posY){
                            direction = 3;
                        }
                    }
                }
            } else {
                // No, run even more advanced AI
                direction = (int) (Math.random() * 4) + 1;
            }

            runTimer = TIME;
        } else {
            runTimer--;
        }


        // Direction now set, move based on direction
        if(direction == 1){
            posY += SPEED;
            imageView.setBackgroundResource(R.drawable.cop_animation_down);
        } else if(direction == 2){
            posX += SPEED;
            imageView.setBackgroundResource(R.drawable.cop_animation_right);
        } else if(direction == 3){
            posY -= SPEED;
            imageView.setBackgroundResource(R.drawable.cop_animation_up);
        } else if(direction == 4){
            posX -= SPEED;
            imageView.setBackgroundResource(R.drawable.cop_animation_left);
        }

        animation = (AnimationDrawable) imageView.getBackground();
        animation.start();

        imageView.setX(posX);
        imageView.setY(posY);
    }


}
