package com.company;

import java.util.ArrayList;
import java.util.Random;

import static com.company.AutomataViewer.resetCavernID;

/**
 * Processes input to make it smoother
 * and makes a cave now
 * boom
 */
public class Automata {

    private static final int TARGETL = 5, TARGETH = 9, SPAWNPROB = 48, CYCLE_TIMES = 10, WIDTH = 60, HEIGHT = 60;
    private static Node[][] world, temp;
    private static ArrayList<Cavern> caverns;

    static void makeCave(){
        initializeWorld(HEIGHT, WIDTH);
        cycle(CYCLE_TIMES);
        findCaverns();
    }

    public static int getWidth(){
        return WIDTH;
    }
    public static int getHeight(){
        return HEIGHT;
    }

    static ArrayList<Cavern> getCaverns(){
        return caverns;
    }

    public static void findCaverns(){
        caverns = new ArrayList<>();
        for(Node[] nodes: getWorld()){
            for (Node n: nodes) {
                n.setCavern(-1);
            }
        }
        caverns.clear();
        resetCavernID();
        for(Node[] nodes: getWorld()){
            for (Node n: nodes){
                if (n.getState()==0 && n.getCavern() == -1) {
                    caverns.add(new Cavern());
                    fillAndAddCavern(n, caverns.get(caverns.size() - 1).getCavernID());
                }
            }
        }
    }

    private static void fillAndAddCavern(Node node, int cavernID){
        //gets passed in a node, double check that value is 0, not a wall or another cavern
        //then find each neighbor with a 0, add to list until no more neighbors with zeroes for any in that list
        //make new cavern with that list
        caverns.get(cavernID).addNode(node);
        node.setCavern(cavernID);
        for (Node n: node.getOpenNeighbors()){
            if (n.getCavern() != cavernID){
                fillAndAddCavern(n, cavernID);
            }
        }
    }

    static Node[][] getWorld(){
        return world;
    }

    static void cycle(int n){
        for(int i = 0; i < n; i++) {
            cycle();
        }
    }

    private static void cycle(){ //automata run through
        world = getWorld();
        temp = world;
        int neighbors;
        for (int h = 0; h < world.length; h++) {
            for (int w = 0; w < world[0].length; w++) {
                neighbors = getOpenNeighbors(h, w);
                if (neighbors >= TARGETL && neighbors <= TARGETH) {
                    temp[h][w].setState(0); //0 means wall... yeah
                } else {
                    temp[h][w].setState(1);
                }

            }
        }
        world = temp;
    }

    private static int getOpenNeighbors(int h, int w){
        //used to get open neighbors
        int n =getWorld()[h][w].getOpenNeighbors().size();
        return n;
    }
    private static void initializeWorld(int height, int width){
        Random rand = new Random();
        Node[][] newWorld = new Node[height][width]; //all zeroes, empty
        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                if (rand.nextInt(99)+1 > SPAWNPROB){
                    newWorld[y][x] = new Node(x, y, 1);
                }
                else {
                    newWorld[y][x] = new Node(x, y, 0);
                }
            }
        }
        world = newWorld;
    }

    public static void initializeWorld(){
        //default size 40x40 at start
        initializeWorld(30, 30);
    }

    public static Node getNode(int y, int x) { return Automata.getWorld()[y][x]; }
}