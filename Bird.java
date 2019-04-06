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
    private GreenfootImage lose;
    private GreenfootImage win;
    private boolean hasEnd = false;
    private GreenfootSound wallSound = new GreenfootSound("wall.mp3");
    
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

    //DarksoulMode
    private boolean isSoundPlayed = false;
    private int opaque = 0;

    public Bird(){
    }

    public Bird(World world, Status status, boolean isEnergyWorld){
        this.world = world;
        this.status = status;
        this.isEnergyWorld = isEnergyWorld;
        if(isEnergyWorld && !status.isDarksoulMode){
            flyThreshold *= 3;
        }
        prepareImage();
    }

    public void act(){
        if(!hasSpawnPosition) setSpawnPosition();
        if(hasEnd){
            if(status.isWin())
                win();
            else
                lose();
            return;
        }
        movement();
        animate();
        checkCollision();
        if(status.isWin() || status.isLose()) hasEnd = true;
    }

    private void lose(){
        setImage(lose);
        setLocation(world.getWidth()/2, world.getHeight()/2);
        if(status.isDarksoulMode){
            darksoulLose();
        }
    }

    private void win(){
        setImage(win);
        setLocation(world.getWidth()/2, world.getHeight()/2 + 30);
    }

    private void darksoulLose(){
        if(isEnergyWorld){
            ((EnergyWorld)world).stopMusic();
        }else{
            ((BirdWorld)world).stopMusic();
        }
        if(!isSoundPlayed){
            getImage().setTransparency(0);
            new GreenfootSound("Darksoul You Died.mp3").play();
            getImage().scale(world.getWidth(), getImage().getHeight());
            isSoundPlayed = true;
        }
        if(opaque < 255) opaque++;
        getImage().setTransparency(opaque);
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
        if(status.isDarksoulMode){
            lose = new GreenfootImage("youdied.png");
            speed++;
        }else{
            lose = new GreenfootImage("gameover.png");
        }
        win = new GreenfootImage("youwin.png");
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
            predator.playSound();
            respawn();
            status.decreaseLives(2);
        }
        if(isTouching(Wall.class)){
            wallSound.play();
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
