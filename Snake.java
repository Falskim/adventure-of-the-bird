import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Snake here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Snake extends Predator{
    private static final int SPEED = 3;
    private static final int TURN_DURATION = 3000;
    private static final int TOTAL_SPRITE = 1;
   
    public Snake(){
    }

    public Snake(World world){
        super(world, TURN_DURATION);
        prepareImage();
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
                    facing = "right";
                    break;
                case 2:
                    facing = "left";
                    break;
                default:
                facing = "left";
            }
            //for(int j = 0 ; j < 2 ; j++){
                sprites[i][0] = new GreenfootImage("snake" + facing + ".png");
            //}
        }
    }
}
