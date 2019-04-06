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
    private final static int MAX_FOOD = 3;
    private final static int MAX_ENEMY = 3;
    private Spawner spawner;
    private final static GreenfootSound bgm = 
                    new GreenfootSound("Lullaby of Deserted Hell.mp3");
    private boolean hasStatusDisplayed = false;
    /*
     * Ukuran dunia 600 x 600, dan ukuran Wall 50, sehingga
     * 600/50 = 12 baris dan kolom
     * 
     * 0 = kosong
     * 1 = tembok
     * 2 = pohon
     */
    int[][] layout = {
        {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0}, //1
        {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0}, //2
        {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //3
        {1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, //4
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1}, //5
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0}, //6
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, //7
        {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, //8
        {0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0}, //9
        {0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0}, //10
        {0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //11
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //12
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, //13
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1}, //14
        {0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1}, //15
        {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1}, //16
        {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, //17
        {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //18
        {1, 0, 0, 0, 2, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, //19
        {1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1}}; //20
       //1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20
    
    public EnergyWorld(){    
        super(600, 600, 1);
        createWallLayout();
        bgm.playLoop();
        if(status == null) status = new Status();
    }
    
    public EnergyWorld(Status status){
        this();
        this.status = status;
        spawner = new Spawner((World)this);
        status.getImage().scale(600, 40);
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
        final int WALL_SIZE = 30;
        int xPos;
        int yPos = WALL_SIZE/2;
        for(int i = 0; i < layout.length ; i++){
            xPos = WALL_SIZE/2;
            for(int j = 0; j < layout[0].length ; j++){
                if(layout[i][j] == 1){
                    addObject(new EnergyWall(), xPos, yPos);
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
    
    public void stopMusic(){
        bgm.stop();
    }
}
