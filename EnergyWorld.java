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
    Timer timer = new Timer();
    
    public EnergyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 600, 1);
        prepare();
        
    }

    public void act(){
        //spawnApple();
        //spawnBanana();
        //spawnPapaya();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
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
        Snake snake = new Snake();
        addObject(snake,433,425);
        Snake snake2 = new Snake();
        addObject(snake2,342,278);
        Cat cat = new Cat();
        addObject(cat,511,204);
        Cat cat2 = new Cat();
        addObject(cat2,261,215);
        Wall wall5 = new Wall();
        addObject(wall5,494,352);
        Wall wall6 = new Wall();
        addObject(wall6,408,126);
        Wall wall7 = new Wall();
        addObject(wall7,111,273);
        Wall wall8 = new Wall();
        addObject(wall8,285,485);
        Bird bird = new Bird();
        addObject(bird,224,97);
        Tree tree = new Tree();
        addObject(tree,513,548);
    }

    public void spawnApple(){
        randomSpawn(new Apple());
    }
    public void spawnBanana(){
        randomSpawn(new Banana());
    }
    public void spawnPapaya(){
        randomSpawn(new Papaya());
    }
    private void randomSpawn(Actor act){
       while(true){
            int xPos = Greenfoot.getRandomNumber(getWidth());
            int yPos = Greenfoot.getRandomNumber(getHeight());
            if(isValidLocation(xPos, yPos)){
                addObject(act, xPos, yPos);
                return;
            }
       }
    }
    private boolean isValidLocation(int x, int y){
        int spawnRange = 10; //Jarak toleransi antar object 
        //Pengecekan object apa saja yang berada pada posisi x dan y
        List<Actor> actors = getObjectsAt(x, y, null);
        actors.addAll(getObjectsAt(x+spawnRange, y+spawnRange, null));
        actors.addAll(getObjectsAt(x-spawnRange, y-spawnRange, null));
        actors.addAll(getObjectsAt(x+spawnRange, y-spawnRange, null));
        actors.addAll(getObjectsAt(x-spawnRange, y+spawnRange, null));
        if(actors.isEmpty()){
            return true;
        }
        return false;
    }
}
