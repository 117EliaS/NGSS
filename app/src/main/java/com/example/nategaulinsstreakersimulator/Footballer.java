package com.example.nategaulinsstreakersimulator;

public class Footballer {

    private final int SPEED = 10;
    private final int SENSITIVITY = 5;
    private int posX;
    private int posY;
    // Uses int values to represent the direction the entity is facing
    // None = 0, Up = 1, Right = 2, Down = 3, Left = 4
    private int direction;

    public Footballer(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
        this.direction = 0;
    }

    public int getSpeed() {
        return SPEED;
    }

    public int getDirection(){
        return direction;
    }

    public int getSENSITIVITY(){
        return SENSITIVITY;
    }

    public boolean move(int playerX, int playerY){
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
        } else if(direction == 2){
            posX += SPEED;
        } else if(direction == 3){
            posY -= SPEED;
        } else if(direction == 4){
            posX -= SPEED;
        } else {
            // Direction should be between 1-4. Return error.
            return false;
        }

        // Moved successfully. End function.
        return true;
    }
}
