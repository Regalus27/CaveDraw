package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Tile class
 */
public class Tile extends JComponent {
    private int xIndex = 0, yIndex = 0;
    private int full = 1; //wall
    public Tile(int x, int y){
        /*
        true is black, false is lightGray. occupied is blue
        xRange should be 0 - (w-1), same y

        */
        if(x > -1 && x < Automata.getWidth()){
            xIndex = x;
        }
        else{
            xIndex = 0;
        }
        if(y > -1 && y < Automata.getHeight()){
            yIndex = y;
        }
        else{
            yIndex = 0;
        }
        int boolHold = Automata.getNode(yIndex,xIndex).getState();
        if(boolHold == 1){
            full = 1; //wall
        }
        else if (boolHold == 0){
            full = 0; //open
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

        Rectangle tile = new Rectangle(this.xIndex*10, this.yIndex*10, 10, 10);
        g2.fill(tile);

    }
}
