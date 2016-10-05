package com.company;

import javax.swing.*;

import static com.company.Input.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Input.makeCave();
        frame.setSize(w*10, h*10);
        frame.setTitle("Cave");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //draw in cave
        CaveDraw cave = new CaveDraw();
        frame.add(cave);

        frame.setVisible(true);

        explore();
    }
    public static void explore(){
        /*
        get last position of generator
        put player in that position

        output a different color at that position
        listen to keystroke
        check walls
        change position
        */

        //position variables
        int X = fX;
        int Y = fY;

        //holds state of cell currently in. 1 empty, 0 full.
        int state = 1;

        //add keylistener, then movement
    }
}
