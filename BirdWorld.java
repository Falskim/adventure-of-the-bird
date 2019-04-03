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
    private Status status;
    private Spawner spawner;
    private Timer timer = new Timer();
    private final int MAX_FOOD = 3;
    private final int MAX_ENEMY = 3;
    
    public BirdWorld(){
        super(800, 600, 1);
    }
    
    public BirdWorld(Status status){
        this();
        this.status = status;
        spawner = new Spawner(this, status);
        addObject(status, getWidth()/2, getHeight()/2);
        prepare();
        firstSpawn();
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
        spawner.spawnBird();
    }
    
    public void act(){
        status.display();
    }
    
    public Status getStatus(){
        return status;
    }
}
