import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bird here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bird extends Actor{
    //General Attribute
    private int speed = 3;
    private Status status;
    private World world;
    private boolean isEnergyWorld;
    
    //Spawn Related
    private int xSpawnPosition;
    private int ySpawnPosition;
    private boolean hasSpawnPosition = false;
    
    //Fly related
    private int flyCounter = 0;
    private int flyThreshold = 8;
    
    //Animation related
    private GreenfootImage[][] sprites;
    private Timer animation = new Timer();
    private int animationDelay = 100;
    private int spriteCounter;
    private int facing = 0; //0 = kanan, 1 = kiri
    
    public Bird(){
    }
    public Bird(World world, Status status, boolean isEnergyWorld){
        this.world = world;
        this.status = status;
        this.isEnergyWorld = isEnergyWorld;
        if(isEnergyWorld){
            flyThreshold *= 2;
        }
        prepareImage();
    }
    
    public void act(){
        if(world == null) return;
        if(!hasSpawnPosition) setSpawnPosition();
        if(status.isLose()){
            setImage(new GreenfootImage("gameover.png"));
            setLocation(world.getWidth()/2, world.getHeight()/2);
            return;
        }
        if(status.isWin()){
            setImage(new GreenfootImage("youwin.png"));
            setLocation(world.getWidth()/2, world.getHeight()/2 + 30);
            return;
        }
        movement();
        animate();
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
            facing = 1;
            flyCounter++;
        }
        if(Greenfoot.isKeyDown("W")){
            this.setLocation(getX(), getY()-speed);
            flyCounter++;
        }
        if(Greenfoot.isKeyDown("D")){
            this.setLocation(getX()+speed, getY());
            facing = 0;
            flyCounter++;
        }
        if(Greenfoot.isKeyDown("S")){
            this.setLocation(getX(), getY()+speed);
            flyCounter++;
        }
        
        if(flyCounter >= flyThreshold){
            status.decreaseEnergy();
            flyCounter = 0;
        }
    }

    private void prepareImage(){
        String facing;
        sprites = new GreenfootImage[2][8];
        for(int i = 0; i < 2 ; i++){
            switch(i){
                case 0:
                    facing = "right";
                    break;
                default:
                    facing = "left";
                    break;
            }
            for(int j = 0 ; j < 5 ; j++){
                sprites[i][j] = new GreenfootImage("/bird/bird" + facing + (j+1) + ".png");
            }
            for(int j = 5 ; j < 8 ; j++){
                sprites[i][j] = new GreenfootImage("/bird/bird" + facing + (9-j) + ".png");
            }
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
        Predator predator = (Predator)getOneIntersectingObject(Predator.class);
        if(food != null){
            status.increaseEnergy(food.energyValue);
            status.increaseScore(food.energyValue);
            if(food.isWorm()) status.increaseWormEaten();
        }
        if(predator != null){
            predator.respawn();
            respawn();
            status.decreaseLives(2);
        }
        if(isTouching(Wall.class)){
            respawn();
            status.decreaseLives(1);
        }
        if(isTouching(Tree.class)){
            changeWorld();
        }
    }
    
    private void respawn(){
        setLocation(xSpawnPosition, ySpawnPosition);
    }
}
