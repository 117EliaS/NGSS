package com.example.nategaulinsstreakersimulator;

import android.graphics.Rect;
import android.widget.ImageView;

public class Footballer {

    private final int SPEED = 30;
    private final int SENSITIVITY = 5;
    private int posX;
    private int posY;
    // Uses int values to represent the direction the entity is facing
    // None = 0, Up = 1, Right = 2, Down = 3, Left = 4
    private int direction;
    ImageView imageView;
    Rect hitbox;

    public Footballer(int posX, int posY, ImageView imageView, int direction){
        this.posX = posX;
        this.posY = posY;
        if(direction > 0 && direction <= 4){
            this.direction = direction;
        } else {
            this.direction = 1;
        }

        this.imageView = imageView;
        this.hitbox = new Rect();

        imageView.getHitRect(this.hitbox);
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

    public void move(){
        // Move based on direction.
        if(direction == 1){
            posY += SPEED;
        } else if(direction == 2){
            posX += SPEED;
        } else if(direction == 3){
            posY -= SPEED;
        } else {
            posX -= SPEED;
        }
    }
}
