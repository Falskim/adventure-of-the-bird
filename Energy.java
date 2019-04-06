import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Energy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Energy extends Actor{
    private GreenfootImage energy1 = new GreenfootImage("energy1.png");
    private GreenfootImage energy2 = new GreenfootImage("energy2.png");
    private GreenfootImage energy3 = new GreenfootImage("energy3.png");
    private GreenfootImage energy4 = new GreenfootImage("energy4.png");
    private GreenfootImage energy;
    private GreenfootImage container;
    public void act() 
    {
        // Add your action code here.
    }
    public void updateEnergy(int energyAmount){
        int x = 23;
        int y = 5;
        
        if(energyAmount >= 75)
            energy = energy1;
        else if(energyAmount < 75 && energyAmount >= 50)
            energy = energy2;
        else if(energyAmount < 50 && energyAmount >= 25)
            energy = energy3;
        else
            energy = energy4;
        
        container = new GreenfootImage("energycontainer.png");
        for(int i = 0 ; i < energyAmount/10 ; i++){
            container.drawImage(energy, x , y);
            x += 8;
        }
        setImage(container);
    }    
}