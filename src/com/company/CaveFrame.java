package com.company;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

import static com.company.Input.*;

/**
 * JFrame frame for cave
 * Also is main class... :)
 */
public class CaveFrame extends JFrame implements KeyListener{
    static List<Enemy> enemyArrayList = new ArrayList<>(); //stores list of active enemies
    static int x, y, dx, dy;
    static CaveFrame frame = new CaveFrame();
    Player player;
    public static void main(String [] args){
        frame.setVisible(true);
        enemyArrayList.add(frame.addEnemy());
        enemyArrayList.add(frame.addEnemy());
        enemyArrayList.add(frame.addEnemy());
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
        if((kCode == 38||kCode==87||kCode==104) && Cave[y-1][x]!=0){//north
            dx=0;
            dy=-1;
        }
        else if ((kCode==40||kCode==83||kCode==98) && Cave[y+1][x]!=0){//south
            dx=0;
            dy=1;
        }
        else if((kCode==37||kCode==65||kCode==100) && Cave[y][x-1]!=0){//west
            dx=-1;
            dy=0;
        }
        else if((kCode==39||kCode==68||kCode==102) && Cave[y][x+1]!=0){//east
            dx=1;
            dy=0;
        }
        else if (kCode==103 && Cave[y-1][x-1]!=0 && Cave[y][x-1]!=0 && Cave[y-1][x]!=0){//northwest
            dx=-1;
            dy=-1;
        }
        else if (kCode==105 && Cave[y-1][x+1]!=0 && Cave[y][x+1]!=0 && Cave[y-1][x]!=0){//northeast
            dx=1;
            dy=-1;
        }
        else if (kCode==97 && Cave[y+1][x-1]!=0 && Cave[y][x-1]!=0 && Cave[y+1][x]!=0){//southwest
            dx=-1;
            dy=1;
        }
        else if (kCode==99 && Cave[y+1][x+1]!=0 && Cave[y][x+1]!=0 && Cave[y+1][x]!=0){//southeast
            dx=1;
            dy=1;
        }
        else {
            dx = 0;
            dy = 0;
        }

        if (Cave[y+dy][x+dx]<3) {
            x+=dx;
            y+=dy;
            player.move(dx, dy);
            Input.updateCave(x,y,dx,dy);
        }
        else {
            //damage enemy at x,y
            //need to add a list of enemies and positions in CaveFrame for organization, attacking and damaging and move
            player.damageEnemy(x+dx,y+dy);
        }
        for (int i = 0; i < enemyArrayList.size(); i++){
            Enemy enemy = enemyArrayList.get(i);
            enemy.move(player);
        }
    }

    public Enemy addEnemy(){
        Enemy.Races[] races = Enemy.Races.values();
        Random random = new Random();
        int i = random.nextInt(races.length);
        Enemy.Races race = races[i];
        int x; int y;
        int maxX = Input.w, maxY = Input.h;
        while (true){
            y=random.nextInt(maxY);
            x=random.nextInt(maxX);
            if (Input.Cave[y][x] == 1){
                break;
            }
        }
        if (race == Enemy.Races.OGRE){
            return new Ogre(x,y);
        }
        else if (race == Enemy.Races.GOBLIN){
            return new Goblin(x,y);
        }
        else if (race == Enemy.Races.KOBOLD){
            return new Kobold(x,y);
        }
        else{ //default shade - because if it's an error, its pretty shady...
            return new Shade(x, y);
        }
    }
}