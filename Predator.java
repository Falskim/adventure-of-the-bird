import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Predator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Predator extends Actor
{
    //General Attribute
    public World world;
    private int turnDuration; //Toleransi turnTimer, dalam miliseconds
    private int facing = 0; //0 = kanan, 1 = bawah, 2 = kiri, 3 = atas
    private Timer turnTimer = new Timer();
    private GreenfootSound sound;
    
    //Collide Handler Related
    private Timer colliderTimer = new Timer();
    private int collideTimerThreshold = 750; //Batas glitch stuck ditembok
    private int collideThreshold = 15; //Batas glitch stuck ditembok
    private int collideCounter;
    
    //Spawn Related
    private int xSpawnPosition;
    private int ySpawnPosition;
    private boolean hasSpawnPosition = false;
    
    //Animation related
    public GreenfootImage[][] sprites;
    private Timer animation = new Timer();
    private int animationDelay = 250;
    private int spriteCounter;
    
    public Predator(){
    }
    public Predator(World world, int turnDuration, GreenfootSound sound){
        this.world = world;
        this.turnDuration = turnDuration;
        this.sound = sound;
        turnTimer.markTimer();
        colliderTimer.markTimer();
        animation.markTimer();
    }
    
    public void behaviour(int speed){
        if(!hasSpawnPosition) setSpawnPosition();
        movement(speed);
        if(isColliding() || turnTimer.getTimer() > turnDuration){
            changeDirection();
            if(colliderTimer.getTimer() > collideTimerThreshold){
                colliderTimer.markTimer();
                collideCounter = 0;
            }
            collideCounter++;
            turnTimer.markTimer();
        }
        if(collideCounter > collideThreshold){
            respawn();
        }
        animate();
    }
    
    private void setSpawnPosition(){
        xSpawnPosition = getX();
        ySpawnPosition = getY();
        hasSpawnPosition = true;
    }
    
    public void respawn(){
        setLocation(xSpawnPosition, ySpawnPosition);
    }
    
    public void movement(int speed){
        switch(facing){
            case 0:
                setLocation(getX() + speed, getY());
                break;
            case 1:
                setLocation(getX(), getY() + speed);
                break;
            case 2:
                setLocation(getX() - speed, getY());
                break;
            case 3:
                setLocation(getX(), getY() - speed);
                break;
        }
    }
    
    private boolean isColliding(){
        return isAtEdge() || isTouching(Wall.class);
    }
    
    private void changeDirection(){
        int currentFacing = facing;
        int relocationRange = 5;
        int newRotation;
        do{
            facing = Greenfoot.getRandomNumber(4);
        }while(currentFacing == facing);
        
        //Menghindari agar tidak stuck di tembok
        switch(currentFacing){
            case 0:
                setLocation(getX()-relocationRange, getY());
                break;
            case 1:
                setLocation(getX(), getY()-relocationRange);
                break;
            case 2:
                setLocation(getX()+relocationRange, getY());
                break;
            case 3:
                setLocation(getX(), getY()+relocationRange);
        }
    }
    
    private void animate(){
        if(animation.getTimer() >= animationDelay){
            spriteCounter++;
            if(spriteCounter >= sprites[0].length){
                spriteCounter = 0;
            }
            this.setImage(sprites[facing][spriteCounter]);
            animation.markTimer();
        }
    }
    
    public void playSound(){
        sound.play();
    }
}
