import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bird here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bird extends Actor{
    //General Attribute
    private int speed = 10;
    private Status status;
    private World world;
    private boolean isEnergyWorld;
    
    //Spawn Related
    private int xSpawnPosition;
    private int ySpawnPosition;
    private boolean hasSpawnPosition = false;
    
    //Fly related
    private int flyCounter = 0;
    private final int FLY_THRESHOLD = 7;
    
    public Bird(){
    }
    public Bird(World world, Status status, boolean isEnergyWorld){
        this.world = world;
        this.status = status;
        this.isEnergyWorld = isEnergyWorld;
    }
    
    public void act(){
        if(world == null) return;
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
        if(isEnergyWorld){
            ((EnergyWorld)world).stopMusic();
            Greenfoot.setWorld(new BirdWorld(status));
            return;
        }else{
            ((BirdWorld)world).stopMusic();
            Greenfoot.setWorld(new EnergyWorld(status));
            return;
        }
    }
    
    private void checkCollision(){
        Food food = (Food)getOneIntersectingObject(Food.class);
        if(food != null){
            status.increaseEnergy(food.energyValue);
            status.increaseScore(food.energyValue);
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
