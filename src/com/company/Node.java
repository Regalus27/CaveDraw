package com.company;

import java.util.ArrayList;

public class Node {
    private int x, y, state;
    public Node (int X, int Y, int State){
        this.x = X;
        this.y = Y;
        this.state = State;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public int getState(){
        return this.state;
    }
    public void setState(int newState){
        this.state = newState;
    }
    public ArrayList<Node> getOpenNeighbors(){
        return null; //Todo: finish this, first make map a Node[][] so it's usable.
    }
}
