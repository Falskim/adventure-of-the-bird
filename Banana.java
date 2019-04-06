import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Banana here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Banana extends Food
{
    private static final int RESPAWN_DELAY = 10000; //milisecond
    private static final int ENERGY = 1;
    
    public Banana(){
    }
    public Banana(World world){
        super(world, ENERGY, RESPAWN_DELAY);
    }
    public void act(){
        behavior();
    }
}
