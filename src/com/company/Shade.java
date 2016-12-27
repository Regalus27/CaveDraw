package com.company;

import java.awt.*;

/**
 * Shade class - color is dark gray, so it's sneaky...
 */
public class Shade extends Enemy{
    public Shade(int x, int y){
        this.setHp(1);
        this.setAtkDmg(1);
        this.setColor(Color.darkGray);
        this.setLocation(x, y);
    }
}
