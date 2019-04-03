import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Snake here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Snake extends Predator{
    private final int SPEED = 3;
    
    public Snake(World world){
        super(world);
    }
    
    public void act(){
        behaviour(SPEED);
    }
}
