package com.company;

import java.util.ArrayList;

public class Node {
    public enum Direction{
        NORTH,
        EAST,
        SOUTH,
        WEST
    }
    private int x, y, state, cavern;
    private String specPrint;
    public Node (int X, int Y, int State){
        this.x = X;
        this.y = Y;
        this.state = State;
        this.cavern = -1;
        this.specPrint = "#";
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public double getDistance(Node n){
        return Math.sqrt(Math.pow(n.getX()-this.getX(),2) + Math.pow(n.getY()-this.getY(),2));
    }
    public int getCavern(){
        return this.cavern;
    }
    public void setCavern(int cavernID){
        this.cavern = cavernID;
        this.setSpecPrint((this.getCavern()<0)  ? "." : "#");
    }
    public int getState(){
        return this.state;
    }
    public String getSpecPrint(){
        return this.specPrint;
    }
    public void setSpecPrint(String str){
        this.specPrint = str;
    }
    public void setState(int newState){
        this.state = newState;
    }
    public ArrayList<Node> getNeighbors(){
        ArrayList<Node> openNodes = new ArrayList<>();
        for (int i = (this.getY() == 0) ? 0 : -1; i <= ((this.getY() == Automata.getWorld().length-1) ? 0 : 1); i++) { //because who needs if?
            for (int j = (this.getX() == 0) ? 0 : -1; j <= ((this.getX() == Automata.getWorld()[0].length-1) ? 0 : 1); j++){
                openNodes.add(Automata.getWorld()[this.getY()+i][this.getX()+j]);
            }
        }
        return openNodes;
            /*
            * Those ternary for statements mean for from ( (-1 or 0) to (0 or 1) ) depending on if it's on those edges
            * Then, if there isn't a wall there, add to list
            * */
    }
    public ArrayList<Node> getOpenNeighbors(){
        ArrayList<Node> nodeList = this.getNeighbors(), openNodes = new ArrayList<>();
        for (Node node: nodeList) {
            if (node.getState()==0) { //no wall
                openNodes.add(node);
            }
        }
        return openNodes;
    }
    public int getDistToEdge(Direction d){
        switch (d){
            case EAST:
                return Automata.getWidth()-this.getX();
            case WEST:
                return this.getX();
            case NORTH:
                return this.getY();
            case SOUTH:
                return Automata.getHeight()-this.getY();
            default:
                return 0;
        }
    }
    public boolean getOpen(){
        if (this.getState()==0){
            return true;
        } else {
            return false;
        }
    }
}
