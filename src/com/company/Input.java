package com.company;

import java.util.Random;

/**
 * Gets info for CaveDraw, sends it in way that it can be read
 */
 class Input {
    static int h = 90, w = 90, fX = 0, fY = 0;
    static int[][] Test = new int[h][w];

    //here if put something in
     static void makeCave(String [] args){
        makeCave();
    }

     static void makeCave() {
        Random rand = new Random();


        int curX = rand.nextInt(w);
        int curY = rand.nextInt(h);
        Test[curY][curX] = 1;
        int maxX = w-2;
        int maxY = h-2;
        int tries = h*w*2;
        while(tries>0) {
            int dir = rand.nextInt(4) + 1;
            if (dir == 1 && curY >= 1) {
                curY--;
                Test[curY][curX] = 1;
                tries--;
            } else if (dir == 2 && curX <= maxX) {
                curX++;
                Test[curY][curX] = 1;
                tries--;
            } else if (dir == 3 && curY <= maxY) {
                curY++;
                Test[curY][curX] = 1;
                tries--;
            } else if (dir == 4 && curX >= 1) {
                curX--;
                Test[curY][curX] = 1;
                tries--;
            }
        }
        fX = curX;
        fY = curY;
        for(int y = 0; y < h; y++){
            for (int x = 0; x < w; x++){
                System.out.print(Test[y][x]);
            }
            System.out.println();
        }
    }
}
