import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bird here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bird extends Actor{
    private int speed = 5;
    
    public void act(){
        movement();
        if(isTouching(Tree.class)){
            changeWorld();
        }
    }
    private void movement(){
        if(Greenfoot.isKeyDown("A")){
            this.setLocation(getX()-speed, getY());
        }
        if(Greenfoot.isKeyDown("W")){
            this.setLocation(getX(), getY()-speed);
        }
        if(Greenfoot.isKeyDown("D")){
            this.setLocation(getX()+speed, getY());
        }
        if(Greenfoot.isKeyDown("S")){
            this.setLocation(getX(), getY()+speed);
        }
    }
    private void changeWorld(){
        try{
            if(getWorldOfType(EnergyWorld.class) != null ){
                Greenfoot.setWorld(new BirdWorld());
            }
        }catch(ClassCastException e){
            Greenfoot.setWorld(new EnergyWorld());
        }
    }
}
