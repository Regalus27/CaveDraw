package com.company;

import javax.swing.*;

/**
 * Player class, regulates health, attack power, location, spells?, items?
 */
public class Player {
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        Player.health = health;
    }

    public static int health, strength, x, y;
    public Player(){
        health = 10;
        strength = 3;
        x=CaveFrame.x;
        y=CaveFrame.y;
    }
    public void damageEnemy(Enemy enemy){
        enemy.setHp(enemy.getHp()-getStrength());
        if (enemy.getHp()<=0) {
            enemy.die();
        }
    }
    public void move(int dx, int dy){
        x+=dx;
        y+=dy;
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
    private void die(){
        System.out.println("You died...");
        JOptionPane.showMessageDialog(CaveFrame.frame, "You died...");
        CaveFrame.frame.dispose();
    }
}
