package com.company;

import java.util.ArrayList;

/*
* Cavern is a subclass of world, used in generation so that I can get the edges of each cavern, area, center,
* and then connect them.
* */
public class Cavern {
    //main thing a Node[], just a list of the nodes in the cave.
    private ArrayList<Node> cavern;
    private int cavernID;
    Cavern(){
        this.cavernID = AutomataViewer.cavernID++;
        cavern = new ArrayList<>();
    }
    ArrayList<Node> getEdges(){
        //find the edges of the cavern
        //could be on the inside as well, around a pillar or something.

        ArrayList<Node> edges = new ArrayList<>();

        for (Node n: cavern){ //get the nodes that aren't in the middle basically
            if (this.onEdge(n)){
                edges.add(n);
            }
        }
        return edges;
    }
    public int getArea(){
        return this.cavern.size();
    }
    private boolean onEdge(Node n){
        return n.getOpenNeighbors().size()<9;
    }
    public void addNode(Node n){
        this.cavern.add(n);
    }
    public int getCavernID(){
        return this.cavernID;
    }
    private Node getCenterNode(){
        int x = 0, y = 0, c = 0;
        for (Node n: this.cavern){
            x+=n.getX();
            y+=n.getY();
            c++;
        }
        x/=c;
        y/=c;
        return Automata.getWorld()[y][x]; //might not be in cavern?
    }
    public Cavern getNearestCavern() {
        /*
        double distance = 99999, dTemp;
        int targetID = this.getCavernID();
        for (Cavern cavern: Automata.getCaverns()){
            if (cavern.getCavernID() != this.getCavernID()){
                dTemp = Math.sqrt(Math.pow(cavern.getCenterNode().getX()-this.getCenterNode().getX(),2)
                        + Math.pow(cavern.getCenterNode().getY()-this.getCenterNode().getY(),2));
                if (dTemp < distance){
                    distance = dTemp;
                    targetID = cavern.getCavernID();
                }
            }
        }
        return Automata.getCaverns().get(targetID);
        */
        double distance = 100, dTemp;
        int targetID = this.getCavernID();
        for (Cavern cavern: Automata.getCaverns()){
            if (cavern.getCavernID() != this.getCavernID()){
                for (Node n: this.getEdges()){
                    for (Node e: cavern.getEdges()){
                        dTemp = n.getDistance(e);
                        if (dTemp < distance){
                            distance = dTemp;
                            targetID = cavern.getCavernID();
                        }
                    }
                }
            }
        }
        return Automata.getCaverns().get(targetID);
    }
    public Node getConnectionNode(Cavern targetCavern){
        Node targetNode = targetCavern.getCenterNode(), returnNode = null;
        double d = 100, tempD;
        for (Node n:this.cavern){
            tempD = n.getDistance(targetNode);
            if (tempD < d){
                d = tempD;
                returnNode = n;
            }
        }
        return returnNode; //could be null if no other caverns
    }
}
