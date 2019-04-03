import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bird here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bird extends Actor{
    private int speed = 10;
    private int xSpawnPosition;
    private int ySpawnPosition;
    private Status status;
    private boolean hasSpawnPosition = false;
    private EnergyWorld energyWorld;
    private BirdWorld birdWorld;
    private final int FLY_THRESHOLD = 5;
    private int flyCounter = 0;
    
    public Bird(EnergyWorld world, Status status){
        energyWorld = world;
        this.status = status;
    }
    public Bird(BirdWorld world, Status status){
        birdWorld = world;
        this.status = status;
    }
    
    public void act(){
        if(!hasSpawnPosition) setSpawnPosition();
        movement();
        checkCollision();
    }
    
    private void setSpawnPosition(){
        xSpawnPosition = getX();
        ySpawnPosition = getY();
        hasSpawnPosition = true;
    }
    
    private void movement(){
        if(Greenfoot.isKeyDown("A")){
            this.setLocation(getX()-speed, getY());
            flyCounter++;
        }
        if(Greenfoot.isKeyDown("W")){
            this.setLocation(getX(), getY()-speed);
            flyCounter++;
        }
        if(Greenfoot.isKeyDown("D")){
            this.setLocation(getX()+speed, getY());
            flyCounter++;
        }
        if(Greenfoot.isKeyDown("S")){
            this.setLocation(getX(), getY()+speed);
            flyCounter++;
        }
        
        if(flyCounter >= FLY_THRESHOLD){
            status.decreaseEnergy();
            flyCounter = 0;
        }
    }

    private void changeWorld(){
        if(energyWorld == null ){
            Greenfoot.setWorld(new EnergyWorld(status));
        }
        if(birdWorld == null ){
            Greenfoot.setWorld(new BirdWorld(status));
        }
    }
    
    private void checkCollision(){
        Food food = (Food)getOneIntersectingObject(Food.class);
        if(food != null){
            status.increaseEnergy(food.energyValue);
        }
        if(isTouching(Wall.class)){
            respawn();
            status.decreaseLives(1);
        }
        if(isTouching(Predator.class)){
            respawn();
            status.decreaseLives(2);
        }
        if(isTouching(Tree.class)){
            changeWorld();
        }
    }
    
    private void respawn(){
        setLocation(xSpawnPosition, ySpawnPosition);
    }
}
