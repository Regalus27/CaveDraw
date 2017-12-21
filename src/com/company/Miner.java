package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Miner {
    /*
    * Connects caverns
    * */
    Random rand = new Random();
    public void makeEntrances(){

    }
    public ArrayList<Node> getConnectionNodes(ArrayList<Cavern> caverns){ //for testing only
        ArrayList<Node> connectionNodes = new ArrayList<>();
        for(Cavern cavern: caverns){
            for (Cavern targetCavern: caverns) {
                try {
                    connectionNodes.add(cavern.getConnectionNode(targetCavern));
                } catch (NullPointerException e) {
                    connectionNodes.add(cavern.getConnectionNode(cavern));
                }
            }
        }

        return null;
    }
    //make connections proportional to size of starting cavern.
    //smaller caverns get only 1 or 2, larger get more
    public int getNumConnections(Cavern cavern){
        int a = cavern.getArea();
        //not sure if going to need this, but putting it now
        return 1;
    }
    public void connectCaverns(){
        ArrayList<Cavern> caverns = new ArrayList<>();
        while (caverns.size() != 1){
            Automata.findCaverns();
            caverns = Automata.getCaverns();
            Node sNode, fNode;
            for (Cavern c : caverns) {
                sNode = c.getConnectionNode(c.getNearestCavern());
                fNode = c.getNearestCavern().getConnectionNode(c);
                makeTunnel(sNode, fNode);
            }
        }
    }
    private void makeTunnel(Node startNode, Node finishNode){
        int x = startNode.getX();
        int y = startNode.getY();
        int xf = finishNode.getX();
        int yf = finishNode.getY();
        while (x != xf){
            if (x < xf){
                ++x;
            } else {
                --x;
            }
            Automata.getNode(y,x).setState(0);
        }
        while (y != yf){
            if (y < yf){
                ++y;
            } else {
                --y;
            }
            Automata.getNode(y,x).setState(0);
        }
    }
    public void mineRooms(){
        for (Cavern c: Automata.getCaverns()){
            if (makeRoom(c.getEdges().get(rand.nextInt(c.getEdges().size())))){
                //room made
            }
        }
    }
    private boolean makeRoom(Node node){ //true if success, false if unable to


        return false;
    }
}
