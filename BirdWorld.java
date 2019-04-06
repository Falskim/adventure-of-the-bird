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
    private final int MAX_FOOD = 1;
    private final int MAX_ENEMY = 3;
    private Status status;
    private Spawner spawner;
    private final GreenfootSound bgm = 
                    new GreenfootSound("The Doll Maker of Bucharest.mp3");
    private boolean hasStatusDisplayed = false;
    /*
     * Ukuran dunia 800 x 600, dan ukuran Wall 50, sehingga
     * 800/50 = 16 kolom 
     * 600/50 = 12 baris
     * 
     * 0 = kosong
     * 1 = tembok
     * 2 = pohon
     */
    int[][] layout = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //1
        {0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0}, //2
        {0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1}, //3
        {0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0}, //4
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //5
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //6
        {1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0}, //7
        {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, //8
        {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 2, 0}, //9
        {0, 0, 1, 1, 0, 0, 0, 0, 0, 2, 0, 0, 1, 1, 0, 0}, //10
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //11
        {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1}}; //12
        //1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16
                     
    public BirdWorld(){
        super(800, 600, 1);
        createWallLayout();
        bgm.playLoop();
        if(status == null) status = new Status();
    }
    
    public BirdWorld(Status status){
        this();
        this.status = status;
        spawner = new Spawner((World)this);
        status.getImage().scale(800, 40);
        addObject(status, getWidth()/2, 20);
        firstSpawn();
    }
    
    public void act(){
        if(!hasStatusDisplayed){
            status.firstDisplay();
            hasStatusDisplayed = true;
        }
        status.display();
    }
    private void createWallLayout(){
        final int WALL_SIZE = 50;
        int xPos;
        int yPos = WALL_SIZE/2;
        for(int i = 0; i < layout.length ; i++){
            xPos = WALL_SIZE/2;
            for(int j = 0; j < layout[0].length ; j++){
                if(layout[i][j] == 1){
                    addObject(new BirdWall(), xPos, yPos);
                }
                if(layout[i][j] == 2){
                    addObject(new Tree(), xPos, yPos);
                }
                xPos += WALL_SIZE;
            }
            yPos += WALL_SIZE;
        }
    }
    
    private void firstSpawn(){
        //Spawning enemy
        for(int i = 0 ; i < MAX_ENEMY ; i++){
            spawner.spawnCat();
        }
        //Spawning food
        for(int i = 0 ; i < MAX_FOOD ; i++){
            spawner.spawnWorm();
        }
        spawner.spawnBird(status, false);
    }
    
    public void stopMusic(){
        bgm.stop();
    }
}
