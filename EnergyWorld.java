import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class EnergyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnergyWorld extends World
{
    private Status status;
    private Timer timer = new Timer();
    private final int MAX_FOOD = 3;
    private final int MAX_ENEMY = 3;
    private Spawner spawner;
    
    public EnergyWorld(){    
        super(600, 600, 1);
    }
    
    public EnergyWorld(Status status){
        this();
        this.status = status;
        spawner = new Spawner((World)this);
        addObject(status, getWidth()/2, getHeight()/2);
        prepare();
        firstSpawn();
    }
    
    public void act(){
        status.display();
    }

    private void prepare()
    {
        Wall wall = new Wall();
        addObject(wall,33,379);
        wall.setLocation(129,154);
        wall.setLocation(92,78);
        Wall wall2 = new Wall();
        addObject(wall2,86,118);
        Wall wall3 = new Wall();
        addObject(wall3,26,431);
        Wall wall4 = new Wall();
        addObject(wall4,76,430);
        Wall wall5 = new Wall();
        addObject(wall5,494,352);
        Wall wall6 = new Wall();
        addObject(wall6,408,126);
        Wall wall7 = new Wall();
        addObject(wall7,111,273);
        Wall wall8 = new Wall();
        addObject(wall8,285,485);
        Tree tree = new Tree();
        addObject(tree,513,548);
    }
    
    private void firstSpawn(){
        //Spawning food
        for(int i = 0 ; i < MAX_FOOD ; i++){
            spawner.spawnApple();
            spawner.spawnBanana();
            spawner.spawnPapaya();
        }
        //Spawning enemy
        for(int i = 0 ; i < MAX_ENEMY ; i++){
            spawner.spawnSnake();
        }
        spawner.spawnBird(status, true);
    }
    
    public Status getStatus(){
        return status;
    }
}
