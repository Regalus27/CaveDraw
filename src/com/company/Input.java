package com.company;

import java.util.Random;

/**
 * Future possible feature - pools of water
 * Generates Cave, add water
 * First, make int[][] to store elevations in. For each open space made, get elevation of last generated space
 * and add one, subtract one, or keep it static.
 *
 * Water - start at a place, check if open
 * if open, start there, if an open space adjacent to it is lower elevation, water flows there
 * Alternatively, get lowest point in cave, fill to one elevation level higher around that point.
 */
 class Input {
    static int h = 70, w = 70, fX = 0, fY = 0;
    static int[][] Cave = new int[h][w];

     static void makeCave() {
        Random rand = new Random();


        int curX = rand.nextInt(w);
        int curY = rand.nextInt(h);
        Cave[curY][curX] = 1;
        int maxX = w-4;
        int maxY = h-4;
        int tries = h*w*2;
        while(tries>0) {
            int dir = rand.nextInt(4) + 1;
            if (dir == 1 && curY > 1) {
                curY--;
                Cave[curY][curX] = 1;
                tries--;
            } else if (dir == 2 && curX < maxX) {
                curX++;
                Cave[curY][curX] = 1;
                tries--;
            } else if (dir == 3 && curY < maxY) {
                curY++;
                Cave[curY][curX] = 1;
                tries--;
            } else if (dir == 4 && curX > 1) {
                curX--;
                Cave[curY][curX] = 1;
                tries--;
            }
        }
        fX = curX;
        fY = curY;
        for (int i = 0; i < 3; i++){
            Automata.cycle();
        }
        /*for(int y = 0; y < h; y++){
            for (int x = 0; x < w; x++){
                System.out.print(Cave[y][x]);
            }
            System.out.println();
        }*/ //was here for seeing if cave generated properly
    }
    static void updateCave(int x, int y, int dx, int dy){
        if (Cave[y-dy][x-dx]==2) {
            Cave[y - dy][x - dx] = 1;
        }
        Cave[y][x]=2;
    }
}
