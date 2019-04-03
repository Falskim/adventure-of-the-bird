import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Predator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Predator extends Actor
{
    private Timer timer;
    private int turnDuration = 1000; //Toleransi timer, dalam miliseconds
    public Predator(){
        timer = new Timer();
        timer.markTimer();
    }
    public void behaviour(int speed){
        move(speed);
        if(isCollide() || timer.getTimer() > turnDuration){
            changeDirection();
            timer.markTimer();
        }
    }
    private boolean isCollide(){
        return isAtEdge() || isTouching(Wall.class);
    }
    private void changeDirection(){
        int currentRotation = getRotation();
        int newRotation = 90 * Greenfoot.getRandomNumber(4);
        int relocationRange = 15;
        while(newRotation == currentRotation){
            newRotation = 90 * Greenfoot.getRandomNumber(4);
        }
        switch(currentRotation){
            case 0:
                setLocation(getX()-relocationRange, getY());
                break;
            case 90:
                setLocation(getX(), getY()-relocationRange);
                break;
            case 180:
                setLocation(getX()+relocationRange, getY());
                break;
            case 270:
                setLocation(getX(), getY()+relocationRange);
                break;
        }
        setRotation(newRotation);
    }
}
