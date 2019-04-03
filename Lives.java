import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Lives here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lives extends Actor
{
    private GreenfootImage fullHeart;
    private GreenfootImage halfHeart;
    private int x;
    private int y;
    private int lives;
    
    public Lives(){
        fullHeart = new GreenfootImage("lives_full.png");
        halfHeart = new GreenfootImage("lives_half.png");
        x = 400;
        y = 50;
        lives = 6;
    }
    
    public void hitWall(){
        lives =- 1;
    }
    
    public void hitCat(){
        lives =- 2;
    }
    
    public int getLives(){
        return lives;
    }
    
    public void displayLives(){
        int totalFullHeart = lives/2;
        boolean hasHalfHeart = (lives%2 == 1);
        int startDrawingPosition = x;
        
        for(int i = 0 ; i < totalFullHeart ; i++){
            
        }
        for(int i = 0 ; i < totalFullHeart ; i++){
        
        }
    }
}
