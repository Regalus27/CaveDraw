package com.company;

import java.awt.*;

import static com.company.Player.x;

/**
 * Generic enemy class, adds hp, attack damage, enemy color
 */
public class Enemy {
    public enum Enemies{
        KOBOLD (3),
        GOBLIN (4),
        SKELETON (5),
        GOBLIN_SHAMAN (6),
        OGRE (7);

        Enemies(int i) {

        }
    }
    private int hp;
    private int atkDmg;

    public int getMx() {
        return this.Mx;
    }

    public int getMy() {
        return this.My;
    }

    private int Mx;
    private int My;
    private Color color;
    public Enemy(Enemies type){
        if (type == Enemies.KOBOLD){
            this.hp = 2;
            this.atkDmg = 1;
            this.color = Color.orange;
        }
        if (type == Enemies.GOBLIN){
            this.hp = 3;
            this.atkDmg = 2;
            this.color = Color.green;
        }
        if (type == Enemies.SKELETON){
            this.hp = 4;
            this.atkDmg = 2;
            this.color = Color.yellow;
        }
        if (type == Enemies.GOBLIN_SHAMAN){
            this.hp = 4;
            this.atkDmg = 1;
            //add spells... maybe just ranged attacks?
            this.color = Color.magenta;
        }
        if (type == Enemies.OGRE){
            hp = 7;
            atkDmg = 4;
            color = Color.red;
        }
    }
    public void setLocation(int x, int y){
        Input.Cave[My][Mx] = 1;
        this.Mx = x;
        this.My = y;
        //Input.Cave[My][Mx] = this.
    }

    public boolean canSeePlayer(int Ex, int Ey, int Px, int Py){
        double slope = (Py-Ey)/(Px-Ex);
        //now increment x, round to int y with slope to wrap to tiles

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

}
