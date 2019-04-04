import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class BirdWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BirdWorld extends World
{
    private Timer timer = new Timer();
    private final int MAX_FOOD = 3;
    private final int MAX_ENEMY = 3;
    private Status status;
    private Spawner spawner;
    private final GreenfootSound bgm = 
                    new GreenfootSound("The Doll Maker of Bucharest.mp3");
    
    public BirdWorld(){
        super(800, 600, 1);
        if(status == null) status = new Status();
    }
    
    public BirdWorld(Status status){
        this();
        this.status = status;
        spawner = new Spawner((World)this);
        addObject(status, getWidth()/2, getHeight()/2);
        prepare();
        firstSpawn();
        bgm.playLoop();
    }
    
    private void prepare(){
        Tree tree = new Tree();
        addObject(tree,606,416);
    }
    
    private void firstSpawn(){
        //Spawning enemy
        for(int i = 0 ; i < MAX_ENEMY ; i++){
            spawner.spawnCat();
        }
        spawner.spawnBird(status, false);
    }
    
    public void act(){
        status.display();
    }
    
    public void stopMusic(){
        bgm.stop();
    }
}
