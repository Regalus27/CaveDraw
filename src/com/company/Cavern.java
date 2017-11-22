package com.company;

import java.util.ArrayList;

/*
* Cavern is a sublclass of world, used in generation so that I can get the edges of each cavern, area, center,
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
    private boolean onEdge(Node n){
        return n.getOpenNeighbors().size()<9;
    }
    public void addNode(Node n){
        this.cavern.add(n);
    }
    public int getCavernID(){
        return this.cavernID;
    }
    public Node getCenterNode(){
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

}
