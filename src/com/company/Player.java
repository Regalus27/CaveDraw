package com.company;

import javax.swing.*;

/**
 * Player class, regulates health, attack power, location, spells?, items?
 */
public class Player {
    int killCount = 0;
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
        Input.Cave[y][x].setState(2);
    }
    public void damageEnemy(Enemy enemy){
        enemy.setHp(enemy.getHp()-getStrength());
        if (enemy.getHp()<=0) {
            enemy.die();
            ++killCount;
        }
    }
    public void damageEnemy(int x, int y){
        for (int i = 0; i < CaveFrame.enemyArrayList.size(); i++){
            Enemy enemy = CaveFrame.enemyArrayList.get(i);
            if (enemy.getMx() == x && enemy.getMy() == y){
                damageEnemy(enemy);
                break;
            }
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
        System.out.println("You died... but you killed " + killCount + " enemies.");
        JOptionPane.showMessageDialog(CaveFrame.frame, "You died... but you killed " + killCount + " enemies.");
        CaveFrame.frame.dispose();
    }
}
