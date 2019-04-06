import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TestWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestWorld extends World{
    /*
     * Ukuran dunia 600 x 600, dan ukuran Wall 50, sehingga
     * 600/50 = 12 baris dan kolom
     */
    int[][] layout = {{1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //1
                      {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //2
                      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //3
                      {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //4
                      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //5
                      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //6
                      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //7
                      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //8
                      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //9
                      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //10
                      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //11
                      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}}; //12
                     //1  2  3  4  5  6  7  8  9 10 11 12
    
    public TestWorld()
    {    
        super(600, 600, 1);
        createWallLayout();
    }
    private void createWallLayout(){
        final int WALL_SIZE = 50;
        int xPos;
        int yPos = WALL_SIZE/2;
        for(int i = 0; i < layout.length ; i++){
            xPos = WALL_SIZE/2;
            for(int j = 0; j < layout[0].length ; j++){
                if(layout[i][j] == 1){
                    addObject(new Wall(), xPos, yPos);
                }
                xPos += WALL_SIZE;
            }
            yPos += WALL_SIZE;
        }
    }
}
