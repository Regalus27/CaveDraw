package com.company;

import java.util.Random;

/**
 * Generates Cave, add water
 * First, make int[][] to store elevations in. For each open space made, get elevation of last generated space
 * and add one, subtract one, or keep it static.
 *
 * Water - start at a place, check if open
 * if open, start there, if an open space adjacent to it is lower elevation, water flows there
 * Alternatively, get lowest point in cave, fill to one elevation level higher around that point.
 */
 class Input {
    static int h = 90, w = 90, fX = 0, fY = 0;
    static int[][] Cave = new int[h][w];
    static int[][] Elevations = new int[h][w];

    //here if put something in
     static void makeCave(String [] args){
        makeCave();
    }

     static void makeCave() {
        Random rand = new Random();


        int curX = rand.nextInt(w);
        int curY = rand.nextInt(h);
        Cave[curY][curX] = 1;
        int maxX = w-2;
        int maxY = h-2;
        int tries = h*w*2;
        while(tries>0) {
            int dir = rand.nextInt(4) + 1;
            if (dir == 1 && curY >= 1) {
                curY--;
                Cave[curY][curX] = 1;
                tries--;
            } else if (dir == 2 && curX <= maxX) {
                curX++;
                Cave[curY][curX] = 1;
                tries--;
            } else if (dir == 3 && curY <= maxY) {
                curY++;
                Cave[curY][curX] = 1;
                tries--;
            } else if (dir == 4 && curX >= 1) {
                curX--;
                Cave[curY][curX] = 1;
                tries--;
            }
        }
        fX = curX;
        fY = curY;
         makeElevation();
        for(int y = 0; y < h; y++){
            for (int x = 0; x < w; x++){
                System.out.print(Cave[y][x]);
            }
            System.out.println();
        }
    }
    static void makeElevation(){
        int[][] emptyTiles = new int[h][w];
        int emptyNum = 0, curX = 0, curY = 0, north,east,south,west,self;
        Random rand = new Random();
        //stores empty tile coordinates in x y format
        int i = 0;
        for(int y = 0; y < h; y++){
            for(int x = 0; x < h; x++){
                if(Elevations[y][x] == 0){
                    Elevations[y][x] = 999;
                    //default value, highly unlikely to be reached, so if this returned, knows it's a wall
                }
                else{
                    emptyTiles[i][0] = x;
                    emptyTiles[i][1] = y;
                    emptyNum++;
                    Elevations[y][x] = -999;
                    /*
                    * default value, highly unlikely to be reached, so if this returned
                    * can know it's empty (not changed yet)
                    */
                }
            }
        }
        for(int j = 0; j < emptyNum; j++){
            curX = emptyTiles[j][0];
            curY = emptyTiles[j][1];

            //add if all surrounding tiles are empty and this one is to, make it random.

            //don't change to if, else if, else if, else - need to check each one independently
            self = Elevations[curY][curX];
            if(curX > 0){
                //check west
                west = Elevations[curY][curX-1];
            }
            else{
                west = 999; //wall
            }
            if(curX < w-1){
                east = Elevations[curY][curX+1];
            }
            else {
                east = 999;
            }
            if(curY > 0){
                north = Elevations[curY][curX];
            }
            else{
                north = 999;
            }
            if(curY < h-1){
                south = Elevations[curY][curX];
            }
            else{
                south = 999;
            }

            if((self == 999 && west == 999 && east == 999 && north == 999) || (self == -999 && west == -999 && east == -999 && north == -999)){
                Elevations[curY][curX] = rand.nextInt(10) + 10;
            }
            else{
                /*
                based on surroundings, set elevation
                terrain gen for other program idea - set random highest points and random lowest points,
                then fill in the rest from there.
                    4 5 4 4 4
                    5 H 5 4 4
                    3 4 4 4 5
                    L 2 3 5 H
                    2 3 3 4 5
                */
            }
        }
    }
}
