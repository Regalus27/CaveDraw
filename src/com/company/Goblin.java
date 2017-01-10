package com.company;

import java.awt.*;

/**
 * Goblin class
 */
public class Goblin extends Enemy{
    public Goblin(int x, int y){
        this.setHp(4);
        this.setAtkDmg(2);
        this.setId(4);
        this.setColor(Color.green);
        this.setLocation(x, y);
    }
}
