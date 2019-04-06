import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Worm here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Worm extends Food
{
    private static final int RESPAWN_DELAY = 5000; //milisecond
    private static final int ENERGY = 0;
    
    public Worm(){
    }
    public Worm(World world){
        super(world, ENERGY, RESPAWN_DELAY);
        playerSound = new GreenfootSound("worm.mp3");
    }
    public void act(){
        behavior();
    }
}
