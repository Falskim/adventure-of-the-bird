import greenfoot.*;
import java.util.List;

public class Spawner{
    private World world;
    private boolean isEnergyWorld;
    private Status status;

    public Spawner(){
    }
    public Spawner(World world){
        this.world = world;
    }
    public Spawner(EnergyWorld world){
        this.world = (World)world;
        isEnergyWorld = true;
    }
    public Spawner(BirdWorld world){
        this.world = (World)world;
        isEnergyWorld = false;
    }

    public void spawnBird(Status status, boolean isEnergyWorld){
        randomSpawn(new Bird(world, status, isEnergyWorld));
    }

    public void spawnSnake(){
        randomSpawn(new Snake(world));
    }

    public void spawnApple(){
        randomSpawn(new Apple(world));
    }

    public void spawnBanana(){
        randomSpawn(new Banana(world));
    }

    public void spawnPapaya(){
        randomSpawn(new Papaya(world));
    }
    
    public void spawnCat(){
        randomSpawn(new Cat(world));
    }
    
    public void spawnWorm(){
        randomSpawn(new Worm(world));
    }
    
    private void randomSpawn(Actor act){
       while(true){
            int xPos = Greenfoot.getRandomNumber(world.getWidth());
            int yPos = Greenfoot.getRandomNumber(world.getHeight());
            if(isValidLocation(xPos, yPos)){
                world.addObject(act, xPos, yPos);
                return;
            }
        }
    }

    private boolean isValidLocation(int x, int y){
        int spawnRange = 30; //Jarak toleransi antar object 
        //Pengecekan object apa saja yang berada pada posisi x dan y
        List<Actor> actors = world.getObjectsAt(x, y, null);
        actors.addAll(world.getObjectsAt(x+spawnRange, y+spawnRange, null));
        actors.addAll(world.getObjectsAt(x-spawnRange, y-spawnRange, null));
        actors.addAll(world.getObjectsAt(x+spawnRange, y-spawnRange, null));
        actors.addAll(world.getObjectsAt(x-spawnRange, y+spawnRange, null));
        actors.addAll(world.getObjectsAt(x+spawnRange, y, null));
        actors.addAll(world.getObjectsAt(x-spawnRange, y, null));
        actors.addAll(world.getObjectsAt(x, y-spawnRange, null));
        actors.addAll(world.getObjectsAt(x, y+spawnRange, null));
        return actors.isEmpty();
    }
}
