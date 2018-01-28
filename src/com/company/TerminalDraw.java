package com.company;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

/**
 * This first tutorial, demonstrating setting up a simple {@link Terminal} and performing some basic operations on it.
 * @author Martin
 */
public class TerminalDraw {
    public static void main(String[] args) throws InterruptedException {


        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        defaultTerminalFactory.setInitialTerminalSize(new TerminalSize(Automata.getWidth()*2, Automata.getHeight()));
        Terminal terminal = null;
        try {
            Automata.makeCave();
            AutomataViewer.printCaverns();
            Miner miner = new Miner();
            miner.connectCaverns();


            terminal = defaultTerminalFactory.createTerminal();

            for (Node[] nodes: Automata.getWorld()){
                for (Node node: nodes){
                    terminal.putCharacter(node.getSpecPrint().charAt(0));
                    terminal.putCharacter(' ');
                }
                terminal.putCharacter('\n');
            }
            terminal.flush();
            terminal.readInput();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        finally {
            if(terminal != null) {
                try {
                    /*
                    Closing the terminal doesn't always do something, but if you run the Swing or AWT bundled terminal
                    emulators for example, it will close the window and allow this application to terminate. Calling it
                    on a UnixTerminal will not have any affect.
                     */
                    terminal.close();
                }
                catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
