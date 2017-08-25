package com.company;

import java.util.Random;

/**
 * Processes input to make it smoother
 */
public class Automata {

    private static final int TARGETL = 4, TARGETH = 9;
    private static Node[][] world, temp;

    private static Node[][] getWorld(){
        return Input.Cave;
    }

    static void cycle(){
        world = getWorld();
        temp = world;
        int neighbors = 0;
        for (int h = 0; h < world.length; h++) {
            for (int w = 0; w < world[0].length; w++) {
                neighbors = getNeighbors(h, w);
                if (neighbors < TARGETL) {
                    temp[h][w].setState(1);
                } else if (neighbors > TARGETH){
                    temp[h][w].setState(0);
                }

            }
        }
        world = temp;
    }

    private static int getNeighbors(int h, int w){
        int count = 0;
        boolean onEdge = false;
        /*
        * gets the number of 0s
        * eventually
        * */
        if (h==0){
            onEdge = true;
        }
        else if (w == 0){
            onEdge = true;
        }
        else if (h == world.length - 1){
            onEdge = true;
        }
        else if (w == world[0].length - 1){
            onEdge = true;
        }

        if (!onEdge){ //if not on edge, check for neighbors normally
            for (int y = -1; y < 2; y++){
                for (int x = -1; x < 2; x++){
                    if (world[h+y][w+x].getState()==0){
                        ++count;
                    }
                }
            }
        }
        else{ //if on edge, set to wall, return high count
            temp[h][w].setState(0);
            return TARGETH+1;
        }
        return count;
    }
}