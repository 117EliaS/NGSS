package com.example.nategaulinsstreakersimulator;

import android.graphics.Rect;
import android.widget.ImageView;

public abstract class Enemy {
    private final int SPEED = 5;
    private int posX;
    private int posY;
    // Uses int values to represent the direction the entity is facing
    // None = 0, Up = 1, Right = 2, Down = 3, Left = 4
    private int direction;
    private ImageView imageView;
    private Rect hitbox;

    public Enemy(int posX, int posY, ImageView imageView){
        this.posX = posX;
        this.posY = posY;
        this.imageView = imageView;
        this.hitbox = new Rect();

        imageView.getHitRect(this.hitbox);
    }

    public abstract int getSPEED();
    public abstract int getDirection();
}
