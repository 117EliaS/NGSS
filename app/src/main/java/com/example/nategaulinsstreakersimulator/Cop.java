package com.example.nategaulinsstreakersimulator;

import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

public class Cop extends Enemy{

    private final int SPEED = 4;
    private final int SENSITIVITY = 10;
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

        this.direction = 0;
        this.posX = posX;
        this.posY = posY;

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

    public int getSENSITIVITY(){
        return SENSITIVITY;
    }

    public int getPosX(){
        return posX;
    }

    public int getPosY(){
        return posY;
    }

    public boolean checkIntersect(Rect playerHitbox){
        return Rect.intersects(playerHitbox, hitbox);
    }

    public ImageView getView(){
        return imageView;
    }

    public void move(int playerX, int playerY){
        // Get distance from player on both axis
        int distanceX = Math.abs(playerX - posX);
        int distanceY = Math.abs(playerY - posY);

        // If direction is already defined, check to see if change is necessary.
        if(direction != 0) {
            // Direction was already defined, run AI magic
            // Test if successfully reached player on either axis
            if ((direction == 1 || direction == 3) && (Math.abs(distanceY) <= SENSITIVITY)) {
                // Reached Y value, chase on X axis
                if (playerX > posX) {
                    direction = 2;
                } else {
                    direction = 4;
                }
            } else if ((direction == 2 || direction == 4) && (Math.abs(distanceX) <= SENSITIVITY)) {
                // Reached X value, chase on Y axis
                if (playerY > posY) {
                    direction = 1;
                } else {
                    direction = 3;
                }
            }
        } else {
            // Direction not defined, start AI magic
            // Which distance is greater?
            if(distanceX >= distanceY){
                // X is greater or they are equal, move horizontally
                if(playerX > posX){
                    direction = 2;
                } else {
                    direction = 4;
                }
            } else {
                // Y is greater, move vertically
                if(playerY > posY){
                    direction = 1;
                } else {
                    direction = 3;
                }
            }
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
