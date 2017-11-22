package com.company;

import javax.swing.*;
import java.awt.*;

import static com.company.Input.h;
import static com.company.Input.w;
/**
 * Creates things to be drawn, updates from Input
 */
public class CaveDraw extends JComponent {
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        for(int y = 0; y < Automata.getWorld().length; y++) {
            for(int x = 0; x < Automata.getWorld()[0].length; x++) {
                Tile tile = new Tile(x, y);
                tile.draw(g2);
            }
        }
    }
}

