package com.company;

import java.awt.*;

/**
 * Kobold enemy class
 */
public class Kobold extends Enemy {
    public Kobold(int x, int y){
        this.setHp(2);
        this.setAtkDmg(1);
        this.setId(3);
        this.setColor(Color.orange);
        this.setLocation(x, y);
    }
}
