package com.company;

/**
 * Stores the x-y position of something
 * use the entity move class to move stuff
 */
public class Position {
    int x, y;
    public Position(int y, int x){
        this.x = x;
        this.y = y;
    }

    public Position getPosition(){
        return this;
    }

    public Position setPosition(int y, int x) {
        this.move(y,x);
        return this;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void move(int dy, int dx){
        //inputs relative movement
        this.y += dy;
        this.x += dx;
    }

    public void move(Position position){
        this.x = position.getX();
        this.y = position.getY();
    }

    public double getSlope(Position otherPosition){
        //a^2 + b^2 = c^2
        //sqrt(a^2 + b^2) = c
        //sqrt((af-a0)^2 + (bf-b0)^2) = c
        return Math.sqrt(Math.pow(otherPosition.getX()-this.getX(),2) + Math.pow(otherPosition.getY()-this.getY(),2));
    }
}
