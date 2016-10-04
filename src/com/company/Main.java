package com.company;

import javax.swing.*;

import static com.company.Input.h;
import static com.company.Input.w;

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
    }
}
