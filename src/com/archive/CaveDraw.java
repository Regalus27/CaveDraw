package com.company;

import javax.swing.*;
import java.awt.*;
/**
 * Creates things to be drawn, updates from Input
 */
public class CaveDraw extends JComponent {
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        for(int y = 0; y <= Automata.getHeight(); y++) {
            for(int x = 0; x <= Automata.getWidth(); x++) {
                Tile tile = new Tile(x, y);
                tile.draw(g2);
            }
        }
    }
}

