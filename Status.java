import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Status here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Status extends Actor
{
    private int score;
    private int lives;
    private int energy;
    private int wormEaten;
    
    public Status(){
        score = 0;
        lives = 6;
        energy = 100;
        wormEaten = 0;
    }
    
    public void display(){
        getWorld().showText("Score : " + score, 50, 20);
        getWorld().showText("Energy : " + energy, getWorld().getWidth()/2, 20);
        getWorld().showText("Lives : " + lives, getWorld().getWidth() - 50, 20);
    }
    
    /*
     * Score
     */
    public void increaseScore(int score){
        this.score += score;
    }
    public int getScore(){
        return score;
    }
    
    /*
     * Lives
     */
    public void decreaseLives(int damage){
        lives -= damage;
    }
    public int getLives(){
        return lives;
    }
    
    /*
     * Energy
     */
    public void increaseEnergy(int value){
        energy += value;
        if(energy > 100) energy = 100;
    }
    public void decreaseEnergy(){
        energy--;
    }
    public int getEnergy(){
        return energy;
    }
    
    /*
     * Worm
     */
    public void increaseWormEaten(){
        wormEaten++;
    }
}
