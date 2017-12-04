package com.company;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class AutomataViewer extends JFrame{
    static AutomataViewer frame = new AutomataViewer();
    static int cavernID = 0;


    public static void main(String [] args){
        frame.setVisible(true);
    }

    AutomataViewer(){
        Automata.makeCave();
        setSize(Automata.getWidth()*10, Automata.getHeight()*10);
        setTitle("Cave");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //draw in cave
        CaveDraw cave = new CaveDraw();
        add(cave);
        Miner miner = new Miner();
        miner.connectCaverns();
        //printCaverns();
    }
    static void printCaverns () {
        //for testing
        for (Node[] nodes: Automata.getWorld()){
            for (Node n : nodes){
                /*if (n.getCavern() == -1){
                    System.out.print(". ");
                } else {
                    System.out.print(n.getCavern() + " ");
                }*/
                System.out.print(n.getSpecPrint() + " ");
            }
            System.out.println();
        }
    }
    static void resetCavernID(){
        cavernID = 0;
    }
}
