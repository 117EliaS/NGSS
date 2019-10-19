package com.example.nategaulinsstreakersimulator;

import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.widget.ImageView;

public class Footballer extends Enemy{

    private final int SPEED = 5;
    private int posX;
    private int posY;
    // Uses int values to represent the direction the entity is facing
    // None = 0, Down = 1, Right = 2, Up = 3, Left = 4
    private int direction;
    ImageView imageView;
    Rect hitbox;
    AnimationDrawable animation;

    public Footballer(int posX, int posY, ImageView imageView, int direction){
        super();

        if(direction > 0 && direction <= 4){
            this.direction = direction;
        } else {
            this.direction = 1;
        }

        this.posX = posX;
        this.posY = posY;

        this.imageView = imageView;
        imageView.setX(posX);
        imageView.setY(posY);
        this.hitbox = new Rect();
        imageView.getHitRect(this.hitbox);

        imageView.setBackgroundResource(R.drawable.footballer_animation_down);
        this.animation = (AnimationDrawable) imageView.getBackground();
        this.animation.start();

    }

    public int getSPEED() {
        return SPEED;
    }

    public int getDirection(){
        return direction;
    }

    public boolean checkIntersect(Rect playerHitbox){
        return Rect.intersects(playerHitbox, hitbox);
    }

    public ImageView getView(){
        return imageView;
    }

    public void move(){
        // Move based on direction.
        if(direction == 1){
            posY += SPEED;
            imageView.setBackgroundResource(R.drawable.footballer_animation_down);
        } else if(direction == 2){
            posX += SPEED;
            imageView.setBackgroundResource(R.drawable.footballer_animation_right);
        } else if(direction == 3){
            posY -= SPEED;
            imageView.setBackgroundResource(R.drawable.footballer_animation_up);
        } else {
            posX -= SPEED;
            imageView.setBackgroundResource(R.drawable.footballer_animation_left);
        }

        animation = (AnimationDrawable) imageView.getBackground();
        animation.start();

        imageView.setX(posX);
        imageView.setY(posY);
    }
}
