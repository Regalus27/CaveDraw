package com.company;

import javax.swing.*;

/**
 * Player class, regulates health, attack power, location, spells?, items?
 */
public class Player {
    public static int health, strength, x, y;
    public Player(){
        health = 10;
        strength = 3;
        x=CaveFrame.x;
        y=CaveFrame.y;
    }
    public void damagePlayer(int dmg){
        health-=dmg;
        if (health<=0)
            killPlayer();
    }
    public void healPlayer(int heal){
        health+=heal;
        if (health<=0)
            killPlayer();
    }
    public void move(int dx, int dy){
        x+=dx;
        y+=dy;
        if (health <= 0){
            killPlayer();
        }
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getStrength(){
        return strength;
    }
    private void killPlayer(){
        System.out.println("You died...");
        JOptionPane.showMessageDialog(CaveFrame.frame, "You died...");
        CaveFrame.frame.dispose();
    }
}
