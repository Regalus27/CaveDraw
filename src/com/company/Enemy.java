package com.company;

import javax.swing.*;
import java.awt.*;
/**
 * Generic this class, adds hp, attack damage, this color
 */
public class Enemy {
    public enum Races{
        KOBOLD,
        GOBLIN,
        SHADE,
        OGRE
    }
    public Races[] getRaces(){
        return Races.values();
    }
    private int hp;
    private int atkDmg;
    private int id;
    private int Mx;
    private int My;
    private Color color;

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }



    public int getAtkDmg() {
        return atkDmg;
    }

    public void setAtkDmg(int atkDmg) {
        this.atkDmg = atkDmg;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public int getMx() {
        return this.Mx;
    }

    public int getMy() {
        return this.My;
    }



    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    public Enemy(){

    }
    public void setLocation(int x, int y){
        Input.Cave[My][Mx].setState(1);
        this.Mx = x;
        this.My = y;
        Input.Cave[this.My][this.Mx].setState(this.getId());
    }

    public boolean canSeePlayer(Player player){ //make proper ray-finding, look at brogue source, see if it can go java
        int Ey = this.getMy();
        int Ex = this.getMx();
        int Px = player.getX();
        int Py = player.getY();

        if (Ex == Px){ // if x the same
                while (this.getYDif(player) < 0){//Ey > Py
                    Ey--;
                    if (Input.Cave[Ey][Ex].getState()==0){
                        return false;
                    }
                }
                while (Ey < Py){
                    Ey++;
                    if (Input.Cave[Ey][Ex].getState()==0){
                        return false;
                    }
                }
                //if Ey==Py
            return true;
        }

        else if (Ey == Py){
            while (Ex > Px){
                Ex--;
                if (Input.Cave[Ey][Ex].getState()==0){
                    return false;
                }
            }
            while (Ex < Px){
                Ex++;
                if (Input.Cave[Ey][Ex].getState()==0){
                    return false;
                }
            }
            //if Ex==Px
            return true;
        }

        else {
            //now increment x, round to int y with slope to wrap to tiles
            while (Ex != player.getX() && Ey != player.getY()) {
                Ex += this.moveX(player);
                Ey += this.moveY(player);
                if (Input.Cave[Ey][Ex].getState()==0) {
                    return false;
                }
            }
            return true;
        }
    }

    public void move(Player player){
        //make pathfinding for movement.
        //oh joy why did I do this?
        if (this.canSeePlayer(player)){
            this.moveX(player);
            this.moveY(player);
        }
    }

    private double getSlope(Player player){
        if (getXDif(player) != 0) {
            return (getYDif(player)) / (getXDif(player));
        }
        else {
            return 0;
        }
    }

    private int moveX(Player player){
        //how far on the x-axis should monster move
        //added so that enemies didn't move away when moving west
        if (Math.abs(this.getSlope(player)) > 1){ //if rise/run > 1, for example 5 up 1 to right
            return 0;
        }
        else {
            if (this.getSlope(player) > 0){
                return 1;
            }
            else if (this.getSlope(player) < 0){
                return -1;
            }
            else {
                if (this.getXDif(player) > 0){
                    return 1;
                }
                else if (this.getXDif(player) < 0){
                    return -1;
                }
                else {
                    return 0;
                }
            }
        }
    }

    private int moveY(Player player){
        if (this.getSlope(player) > 1){
            return 1;
        }
        else if (this.getSlope(player) < -1){
            return -1;
        }
        else {
            if (this.getYDif(player) > 0){
                return 1;
            }
            else if (this.getYDif(player) < 0){
                return -1;
            }
            else {
                return 0;
            }
        }
    }

    private int getYDif(Player player){
        return player.getY()-this.getMy();
    }
    private int getXDif(Player player){
        return player.getX()-this.getMx();
    }
    public void die(){
        Input.Cave[this.getMy()][this.getMx()].setState(1);
        CaveFrame.enemyArrayList.remove(this);
        if (CaveFrame.enemyArrayList.size() < 1){
            System.out.println("You win!");
            JOptionPane.showMessageDialog(CaveFrame.frame, "You win!");
            CaveFrame.frame.dispose();
        }
    }
    public void damagePlayer(Player player){
        player.setHealth(player.getHealth()-this.getAtkDmg());
    }
}
