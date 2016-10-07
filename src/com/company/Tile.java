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
    private boolean full = true;
    public Tile(int x, int y){
        /*
        true is black, false is lightGray
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
        int boolHold = Cave[yIndex][xIndex]; //multiplied by 20 for something else, so undoing it for reading file
        if(boolHold == 0){
            full = true;
        }
        else {
            full = false;
        }
    }

    public void draw(Graphics2D g2){
        if(full){
            g2.setColor(Color.black);
        }
        else{
            g2.setColor(Color.lightGray);
        }
        //Tiles
        int[] xTiles = new int[w+1];
        int[] yTiles = new int[h+1];
        for(int i = 0; i < w+1; i++){
            xTiles[i] = i*10;
            yTiles[i] = i*10;
        }
        Rectangle tile = new Rectangle(xTiles[xIndex], yTiles[yIndex], 10, 10);
        g2.fill(tile);
    }
}
