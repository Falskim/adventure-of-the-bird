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
    private GreenfootSound bgm;
    private Title title = new Title();
    private int speed = 1;
    private int MOVE_THRESHOLD = 5;
    private int moveCounter = 0;

    //Dark Soul Mode
    private boolean DARK_SOUL_MODE = true;
    private Timer darksoulButtonTimer = new Timer();
    private DarksoulButton button;
    private boolean hasButtonDisplayed = false;

    private int opaque = 0;
    public StartMenu(){    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        addObject(new StartButton(), getWidth()/2, getHeight()/2 + 50);
        addObject(title, getWidth()/2, getHeight()/2 - 75);
        if(DARK_SOUL_MODE){
            bgm = new GreenfootSound("Kagome Kagome.mp3");
            darksoulButtonTimer.markTimer();
        }else{
            bgm = new GreenfootSound("The Fantastic Tales from Tono.mp3");
        }
    }

    public void act(){
        if(!isMusicPlaying){
            bgm.playLoop();
            isMusicPlaying = true;
        }
        moveCounter++;
        if(moveCounter >= MOVE_THRESHOLD){
            title.setLocation(title.getX(), title.getY() + speed);
            moveCounter = 0;
        }
        if(title.getY() >= 150) speed *= -1;
        if(title.getY() <= 75) speed *= -1;
        if(darksoulButtonTimer.getTimer() >= 11000){
            if(!hasButtonDisplayed){
                button = new DarksoulButton();
                addObject(button,  getWidth()/2, getHeight()/2 + 145);
                button.getImage().setTransparency(0);
                hasButtonDisplayed = true;
            }
            if(opaque < 255) opaque++;
            button.getImage().setTransparency(opaque);
        }
    }

    public void stopMusic(){
        bgm.stop();
    }
}
