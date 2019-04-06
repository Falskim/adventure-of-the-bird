import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Snake here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Snake extends Predator{
    private static final int TURN_DURATION = 1000;
    private static final int TOTAL_SPRITE = 4;
    private int SPEED = 2;

    public Snake(){
    }
    
public Snake(World world){
        super(world, TURN_DURATION, new GreenfootSound("snake.wav"));
        prepareImage();
        try{
            if(((EnergyWorld)world).status.isDarksoulMode){
                SPEED++;
            }
        }catch(ClassCastException e){
            //Nothing to do here lol
        }
    }

    public void act(){
        behaviour(SPEED);
    }

    private void prepareImage(){
        String facing;
        sprites = new GreenfootImage[4][TOTAL_SPRITE];
        for(int i = 0; i < 4 ; i++){
            switch(i){
                case 0:
                    facing = "right";
                    break;
                case 1:
                    facing = "down";
                    break;
                case 2:
                    facing = "left";
                    break;
                default:
                    facing = "up";
            }
            for(int j = 0 ; j < TOTAL_SPRITE ; j++){
                sprites[i][j] = new GreenfootImage("/snake/snake" + facing + (j+1) + ".png");
            }
        }
    }
}
