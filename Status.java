import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Status here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Status extends Actor
{
    private final int yPos = 20;
    private int score;
    private int scoreLivesBonus;
    private int lives;
    private int energy;
    private int wormEaten;
    private final int WORM_REQUIRED = 9;
    private final int SCORE_FOR_LIVES = 50;
    private boolean hasFirstDisplayed = false;
    private Lives live = new Lives();
    private Energy energyBar = new Energy();
    
    public Status(){
        score = 0;
        lives = 6;
        energy = 100;
        wormEaten = 0;
        getImage().setTransparency(100);
    }

    public void display(){
        getWorld().showText("Score : " + score, 50, yPos);
        getWorld().showText("Energy : ", (getWorld().getWidth()/2) - 75, yPos);
        getWorld().showText(energy + "%", (getWorld().getWidth()/2) + 30, yPos - 2);
        getWorld().showText("Lives : " , getWorld().getWidth() - 130, yPos);
        getWorld().showText("Worm Eaten : " + wormEaten, getWorld().getWidth()/2, 
            getWorld().getHeight() - yPos);
        if(!hasFirstDisplayed){
            firstDisplay();
            hasFirstDisplayed = true;
        }
    }

    public void firstDisplay(){
        getWorld().addObject(live, getWorld().getWidth() - 50, yPos);
        getWorld().addObject(energyBar, (getWorld().getWidth()/2) + 30, yPos);
        live.updateLives(lives);
        energyBar.updateEnergy(energy);
    }

    public boolean isLose(){
        return lives <= 0 || energy <= 0;
    }
    
    public boolean isWin(){
        return wormEaten >= WORM_REQUIRED;
    }
    /*
     * Score
     */
    public void increaseScore(int score){
        this.score += score;
        this.scoreLivesBonus += score;
        if(scoreLivesBonus/SCORE_FOR_LIVES > 0){
            scoreLivesBonus -= SCORE_FOR_LIVES;
            increaseLives(2);
        }
    }
    
    /*
     * Lives
     */
    public void decreaseLives(int damage){
        lives -= damage;
        live.updateLives(lives);
    }
    private void increaseLives(int ammount){
        lives += ammount;
        if(lives > 6){
            lives = 6;
            live.updateLives(lives);
        }
    }
    
    /*
     * Energy
     */
    public void increaseEnergy(int value){
        energy += value;
        if(energy > 100) energy = 100;
        energyBar.updateEnergy(energy);
    }

    public void decreaseEnergy(){
        energy--;
        energyBar.updateEnergy(energy);
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
