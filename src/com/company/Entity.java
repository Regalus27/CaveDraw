package com.company;

/**
 * Parent Class for Player, Monsters, etc.
 */
public class Entity {
    //move, takeDamage, die, heal, useItem, useAbility
    private int maxHealth, currentHealth;
    private Position position;

    public Entity(int maxHealth, Position startingPosition){
        this.maxHealth = maxHealth;
        this.position = startingPosition;
    }

    public int getMaxHealth(){
        return this.maxHealth;
    }

    public int getCurrentHealth(){
        return this.currentHealth;
    }

    public Position getCurrentPosition(){
        return this.position;
    }

    public Position moveRelative(Position newPosition){
        return this.moveRelative(newPosition.getY()-this.getCurrentPosition().getY(), newPosition.getX()-this.getCurrentPosition().getX());
    }

    public Position moveRelative(int dy, int dx){
        //try to move in a direction, stop where blocked.
        return null;
    }


    public boolean moveAbsolute(Position newPosition){
        //try to move to a position, if not a force move, check if open.
        if (Automata.getNode(newPosition.getY(), newPosition.getX()).getOpen()){
            this.getCurrentPosition().setPosition(newPosition.getY(), newPosition.getX());
            return true;
        }
        return false;
    }

    public boolean moveAbsolute(int dy, int dx){
        return this.moveAbsolute(new Position(this.getCurrentPosition().getY()+dy, this.getCurrentPosition().getX()+dx));
    }
}
