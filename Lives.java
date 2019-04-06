import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Lives here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lives extends Actor
{
    private GreenfootImage full = new GreenfootImage("/lives/heartfull.png");
    private GreenfootImage half = new GreenfootImage("/lives/hearthalf.png");
    private GreenfootImage container;
    
    public Lives(){
    }
    public void updateLives(int lives){
        int x = 13;
        int y = 3;
        container = new GreenfootImage("/lives/livescontainer.png");
        for(int i = 0 ; i < lives/2 ; i++){
            container.drawImage(full, x , y);
            x += 25;
        }
        if(lives%2 == 1){
            container.drawImage(half, x, y);
        }
        setImage(container);
    }
}
