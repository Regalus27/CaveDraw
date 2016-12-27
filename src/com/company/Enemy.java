package com.company;

import java.awt.*;
/**
 * Generic enemy class, adds hp, attack damage, enemy color
 */
public class Enemy {
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
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    public Enemy(){
        this.hp = 0;
        this.atkDmg = 0;
        this.id = 3; //default to dead Kobold, so nothing breaks.
    }
    public void setLocation(int x, int y){
        Input.Cave[My][Mx] = 1;
        this.Mx = x;
        this.My = y;
        Input.Cave[My][Mx] = this.id;
    }

    public boolean canSeePlayer(Enemy enemy, int Px, int Py){
        int Ey = enemy.getMy();
        int Ex = enemy.getMx();

        if (Ex == Px){
                while (Ey > Py){
                    Ey--;
                    if (Input.Cave[Ey][Ex] == 0){
                        return false;
                    }
                }
                while (Ey < Py){
                    Ey++;
                    if (Input.Cave[Ey][Ex] == 0){
                        return false;
                    }
                }
                //if Ey==Py
            return true;
        }

        else if (Ey == Py){
            while (Ex > Px){
                Ex--;
                if (Input.Cave[Ey][Ex] == 0){
                    return false;
                }
            }
            while (Ex < Px){
                Ex++;
                if (Input.Cave[Ey][Ex] == 0){
                    return false;
                }
            }
            //if Ex==Px
            return true;
        }

        else {
            double slope = getSlope(enemy, Px, Py);
            //now increment x, round to int y with slope to wrap to tiles
            while (Ex < Px){
                Ex++;
                Ey = (int)(Ey+slope);
                if (Input.Cave[Ey][Ex] == 0){
                    return false;
                }
            }
            while (Ex > Px){
                Ex--;
                Ey = (int)(Ey+slope);
                if (Input.Cave[Ey][Ex] == 0){
                    return false;
                }
            }
            return true;
        }
    }

    public void move(Enemy enemy){
        if (canSeePlayer(enemy, Player.x, Player.y)){
            
        }
    }

    private double getSlope(Enemy enemy, int x, int y){ //x and y of thing to get slope to
                                                        //won't return an error for /0, but isn't accurate
                                                        //so be smart when using this.
        if (x-enemy.getMx() != 0)
            return  (y-enemy.getMy())/(x-enemy.getMx());
        else
            return 0;
    }

}
