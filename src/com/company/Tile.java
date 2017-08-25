package com.company;

import javax.swing.*;
import java.awt.*;

import static com.company.Input.Cave;
import static com.company.Input.h;
import static com.company.Input.w;

/**
 * Tile class
 */
public class Tile extends JComponent {
    private int xIndex = 0, yIndex = 0;
    private int full = 1;
    public Tile(int x, int y){
        /*
        true is black, false is lightGray. occupied is blue
        xRange should be 0 - (w-1), same y

        */
        if(x > -1 && x < w){
            xIndex = x;
        }
        else{
            xIndex = 0;
        }
        if(y > -1 && y < h){
            yIndex = y;
        }
        else{
            yIndex = 0;
        }
        int boolHold = Cave[yIndex][xIndex].getState(); //multiplied by 20 for something else, so undoing it for reading file
        if(boolHold == 0){
            full = 1;
        }
        else if (boolHold == 1){
            full = 0;
        }
        else{
            full = boolHold;
        }
    }

    public void draw(Graphics2D g2){
        if(full==1){
            g2.setColor(Color.black);
        }
        else if(full==0){
            g2.setColor(Color.lightGray);
        }
        else if(full == 2){
            g2.setColor(Color.blue);
        }
        else if (full == 3){
            g2.setColor(Color.orange);
        }
        else if (full == 4){
            g2.setColor(Color.green);
        }
        else if (full == 5){
            g2.setColor(Color.darkGray);
        }
        else if (full == 6){
            g2.setColor(Color.red);
        }
        //Tiles
        int[] xTiles = new int[w+1];
        int[] yTiles = new int[h+1];
        for(int i = 0; i < w+1; i++){
                xTiles[i] = i * 10;
                yTiles[i] = i * 10;
        }
        Rectangle tile = new Rectangle(xTiles[xIndex], yTiles[yIndex], 10, 10);
        g2.fill(tile);
        Rectangle topLeft = new Rectangle(0,0,10,10);
        g2.setColor(Color.black);
        g2.fill(topLeft);
    }
}
