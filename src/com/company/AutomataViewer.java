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
        setSize((Automata.getWorld()[0].length)*11, (Automata.getWorld().length)*11);
        setTitle("Cave");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //draw in cave
        CaveDraw cave = new CaveDraw();
        add(cave);
        printCaverns();
    }
    static void printCaverns () {
        //for testing
        for (Node[] nodes: Automata.getWorld()){
            for (Node n : nodes){
                if (n.getCavern() == -1){
                    System.out.print(". ");
                } else {
                    System.out.print(n.getCavern() + " ");
                }
            }
            System.out.println();
        }
    }
}
