import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BirdWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BirdWorld extends World
{

    /**
     * Constructor for objects of class BirdWorld.
     * 
     */
    public BirdWorld()
    {    
        super(800, 600, 1);
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Bird bird = new Bird();
        addObject(bird,181,221);
        Tree tree = new Tree();
        addObject(tree,606,416);
    }
}
