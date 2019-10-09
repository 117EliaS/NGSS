package com.example.nategaulinsstreakersimulator;

public class Footballer {

    private final int SPEED = 10;
    private int posX;
    private int posY;

    public Footballer(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
    }

    public int getSpeed() {
        return SPEED;
    }

    public void move(int playerX, int playerY){
        // Get distance from player on both axis
        int distanceX = Math.abs(playerX - posX);
        int distanceY = Math.abs(playerY - posY);



        // Which distance is greater?
        if(distanceX >= distanceY){
            // X is greater or they are equal, move horizontally
            if(playerX > posX){
                posX += SPEED;
            } else {
                posX -= SPEED;
            }
        } else {
            // Y is greater, move vertically
            if(playerY > posY){
                posY += SPEED;
            } else {
                posY -= SPEED;
            }
        }
    }
}
