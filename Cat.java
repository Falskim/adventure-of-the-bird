import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cat here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cat extends Predator{
    private static final int SPEED = 5;
    private static final int TURN_DURATION = 2000;
    
    public Cat(World world){
        super(world, TURN_DURATION);
    }
    public void act() 
    {
        behaviour(SPEED);
    }    
}
