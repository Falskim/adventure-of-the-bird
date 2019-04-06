import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Status here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Status extends Actor{
    //General Attribute
    private final int yPos = 20;
    private boolean hasFirstDisplayed = false;
    private World world;
    
    //Score Related
    private int score;
    private int scoreLivesBonus;
    private final int SCORE_FOR_LIVES = 50;
    
    //Lives Related
    private int lives;
    private Lives live = new Lives();
    
    //Energy Related
    private int energy;
    private Energy energyBar = new Energy();
    
    //Worm Related
    private int wormEaten;
    private final int WORM_REQUIRED = 9;
    
    //Darksoul Mode
    public boolean isDarksoulMode = false;
    
    public Status(){
        score = 0;
        lives = 6;
        energy = 100;
        wormEaten = 0;
        getImage().setTransparency(100);
    }
    public Status(boolean isDarksoulMode){
        this();
        this.isDarksoulMode = isDarksoulMode;
    }
    
    
    public void setWorld(World world){
        this.world = world;
    }
    
    public void firstDisplay(){
        world.showText("Score : " + score, 50, yPos);
        world.showText("Energy : ", (world.getWidth()/2) - 75, yPos);
        world.showText(energy + "%", (world.getWidth()/2) + 30, yPos - 2);
        world.addObject(energyBar, (world.getWidth()/2) + 30, yPos);
        world.showText("Lives : " , world.getWidth() - 130, yPos);
        world.addObject(live, world.getWidth() - 50, yPos);
        world.showText("Worm Eaten : " + wormEaten, world.getWidth()/2, 
            world.getHeight() - yPos);
        live.updateLives(lives);
        energyBar.updateEnergy(energy);
        if(isDarksoulMode){
            world.showText("DARK SOUL MODE", 100, world.getHeight() - yPos);
        }
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
        world.showText("Score : " + score, 50, yPos);
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
        world.showText(energy + "%", (world.getWidth()/2) + 30, yPos - 2);
    }

    public int getEnergy(){
        return energy;
    }

    /*
     * Worm
     */
    public void increaseWormEaten(){
        wormEaten++;
        world.showText("Worm Eaten : " + wormEaten, world.getWidth()/2, 
            world.getHeight() - yPos);
    }
}
