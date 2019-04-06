import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Apple here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Apple extends Food
{
    private static final int RESPAWN_DELAY = 7000; //milisecond
    private static final int ENERGY = 5;
    
    public Apple(){
    }
    public Apple(World world){
        super(world, ENERGY, RESPAWN_DELAY);
    }
    public void act(){
        behavior();
    }
}
