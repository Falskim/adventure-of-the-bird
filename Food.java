import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Food extends Actor{
    
    private Timer timer = new Timer();
    private Spawner spawner;
    public World world;
    public int energyValue;
    public int respawnDelay;
    
    public Food(){
    }
    
    public Food(World world, int energyValue, int respawnDelay){
        this.world = world;
        this.energyValue = energyValue;
        this.respawnDelay = respawnDelay;
        spawner = new Spawner((EnergyWorld)world);
        timer.markTimer();
    }
    
    public void behavior(){
        if(timer.getTimer() > respawnDelay){
            randomLocation();
            timer.markTimer();
        }
        checkCollision();
    }
    
    private void randomLocation(){
       while(true){
            int xPos = Greenfoot.getRandomNumber(world.getWidth());
            int yPos = Greenfoot.getRandomNumber(world.getHeight());
            if(spawner.isValidLocation(this, xPos, yPos)){
                setLocation(xPos, yPos);
                return;
            }
       }
    }
    
    private void checkCollision(){
        if(isTouching(Bird.class)){
            randomLocation();
            return;
        }
        if(isTouching(Snake.class)){
            randomLocation();
            return;
        }
    }
}
