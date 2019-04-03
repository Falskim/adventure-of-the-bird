import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cat here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cat extends Predator{
    private final int SPEED = 5;
    
    public Cat(World world){
        super(world);
    }
    public void act() 
    {
        behaviour(SPEED);
    }    
}
