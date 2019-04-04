import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Snake here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Snake extends Predator{
    private static final int SPEED = 3;
    private static final int TURN_DURATION = 1000;
    
    public Snake(World world){
        super(world, TURN_DURATION);
    }
    
    public void act(){
        behaviour(SPEED);
    }
}
