package com.company;

import java.awt.*;

/**
 * Ogre Class
 * Ogres are strong and tough.
 * Will try to beat you up.
 * They don't do poetry
 * So they don't rhyme
 *
 * Will try to make them dumber at some point
 */
public class Ogre extends Enemy {
    public Ogre(){
        this.setHp(7);
        this.setAtkDmg(3);
        this.setColor(Color.red);
    }
}
