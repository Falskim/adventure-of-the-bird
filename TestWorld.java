import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TestWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestWorld extends World{
    Status stat = new Status();
    
    public TestWorld(){    
        super(600, 600, 1);
        addObject(stat, getWidth()/2, getHeight()/2);
    }
    public void act(){
        stat.display();
    }
}
