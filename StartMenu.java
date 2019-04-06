import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartMenu extends World
{
    private boolean isMusicPlaying = false;
    private GreenfootSound bgm = new GreenfootSound("The Fantastic Tales from Tono.mp3");
    public StartMenu(){    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        addObject(new StartButton(), getWidth()/2, getHeight()/2);
    }
    
    public void act(){
        if(isMusicPlaying) return;
        bgm.playLoop();
        isMusicPlaying = true;
    }
    public void stopMusic(){
        bgm.stop();
    }
}
