import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Papaya here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Papaya extends Food
{
    private static final int RESPAWN_DELAY = 3000; //milisecond
    private static final int ENERGY = 10;
    
    public Papaya(){
    }
    public Papaya(World world){
        super(world, ENERGY, RESPAWN_DELAY);
    }
    public void act(){
        behavior();
    }   
}
