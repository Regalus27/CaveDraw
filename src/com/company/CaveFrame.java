package com.company;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static com.company.Input.*;

/**
 * JFrame frame for cave
 * Also is main class... :)
 */
public class CaveFrame extends JFrame implements KeyListener{
    public static int x, y, dx, dy;
    static CaveFrame frame = new CaveFrame();
    Player player;
    public static void main(String [] args){
        frame.setVisible(true);
    }
    CaveFrame(){
        setSize(w*10, h*10);
        setTitle("Cave");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Input.makeCave();
        x=fX;
        y=fY;
        //draw in cave
        CaveDraw cave = new CaveDraw();
        add(cave);
        addKeyListener(this);
        player = new Player();
    }
    public void keyPressed(KeyEvent event) {
        move(event);
        Input.updateCave(x,y,dx,dy);
        CaveDraw exploreUpdate = new CaveDraw();
        add(exploreUpdate);
        frame.repaint();
    }
    public void keyReleased(KeyEvent event){

    }
    public void keyTyped(KeyEvent event){

    }
    public void move(KeyEvent e){

        int kCode = e.getKeyCode();
        if((kCode == 38||kCode==87||kCode==104) && Cave[y-1][x]==1){//north
            dx=0;
            dy=-1;
        }
        else if ((kCode==40||kCode==83||kCode==98) && Cave[y+1][x]==1){//south
            dx=0;
            dy=1;
        }
        else if((kCode==37||kCode==65||kCode==100) && Cave[y][x-1]==1){//west
            dx=-1;
            dy=0;
        }
        else if((kCode==39||kCode==68||kCode==102) && Cave[y][x+1]==1){//east
            dx=1;
            dy=0;
        }
        else if (kCode==103 && Cave[y-1][x-1]==1 && Cave[y][x-1]==1 && Cave[y-1][x]==1){//northwest
            dx=-1;
            dy=-1;
        }
        else if (kCode==105 && Cave[y-1][x+1]==1 && Cave[y][x+1]==1 && Cave[y-1][x]==1){//northeast
            dx=1;
            dy=-1;
        }
        else if (kCode==97 && Cave[y+1][x-1]==1 && Cave[y][x-1]==1 && Cave[y+1][x]==1){//southwest
            dx=-1;
            dy=1;
        }
        else if (kCode==99 && Cave[y+1][x+1]==1 && Cave[y][x+1]==1 && Cave[y+1][x]==1){//southeast
            dx=1;
            dy=1;
        }
        else {
            dx = 0;
            dy = 0;
        }

        x+=dx;
        y+=dy;
        player.move(dx,dy);
    }

    public void addEnemy(){

    }

}