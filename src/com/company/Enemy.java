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

    public boolean canSeePlayer(Enemy enemy, Player player){
        int Ey = enemy.getMy();
        int Ex = enemy.getMx();
        int Px = player.getX();
        int Py = player.getY();

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
            double slope = getSlope(enemy, player);
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

    public void move(Enemy enemy, Player player){
        if (canSeePlayer(enemy, player)){
            if(this.getMx()-Player.x < 0){
                //player is to the east
                if (Input.Cave[(int)(this.getMy()+getSlope(enemy, player))][this.getMx()+1] ==1){ //if (cave[y+slope][x+1] is empty)
                    this.setLocation(this.getMx()+1, ((int)(this.getMy()+getSlope(enemy, player)))); //move enemy
                }
            }
            else if (this.getXDif(enemy, player) > 0){ //will need to set >1, then if 1 attack player, but not yet
                //player is to the west
                if (Input.Cave[(int)(this.getMy()+getSlope(enemy, player))][this.getMx()-1] ==1){ //if (cave[y+slope][x-1] is empty)
                    this.setLocation(this.getMx()-1, ((int)(this.getMy()+getSlope(enemy, player)))); //move enemy
                }
            }
        }
        else{
            // if at same x
            if (this.getYDif(this, player) > 0){
                
            }
        }
    }

    private double getSlope(Enemy enemy, Player player){
        if (getXDif(enemy, player) != 0)
            return  (getYDif(enemy, player))/(getXDif(enemy,player));
        else
            return 0;
    }

    private int getYDif(Enemy enemy, Player player){
        return player.getY()-enemy.getMy();
    }
    private int getXDif(Enemy enemy, Player player){
        return player.getX()-enemy.getMx();
    }
    public void die(){
        Input.Cave[this.getMy()][this.getMx()] = 1;
    }
    public void damagePlayer(Player player){
        player.setHealth(player.getHealth()-this.getAtkDmg());
    }
}
