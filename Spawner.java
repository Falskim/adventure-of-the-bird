import greenfoot.*;
import java.util.List;

public class Spawner{
    private EnergyWorld energyWorld;
    private BirdWorld birdWorld;
    private World world;
    private boolean isEnergyWorld;
    private Status status;

    public Spawner(){
    }
    public Spawner(EnergyWorld world){
        energyWorld = world;
        this.world = (World)world;
        isEnergyWorld = true;
    }
    public Spawner(BirdWorld world){
        birdWorld = world;
        this.world = (World)world;
        isEnergyWorld = false;
    }
    public Spawner(EnergyWorld world, Status status){
        energyWorld = world;
        this.world = (World)world;
        isEnergyWorld = true;
        this.status = status;
    }

    public Spawner(BirdWorld world, Status status){
        birdWorld = world;
        this.world = (World)world;
        isEnergyWorld = false;
        this.status = status;
    }

    public void spawnBird(){
        if(isEnergyWorld) 
            randomSpawn(new Bird(energyWorld, status));
        else
            randomSpawn(new Bird(birdWorld, status));
    }

    public void spawnSnake(){
        if(isEnergyWorld) randomSpawn(new Snake(energyWorld));
    }

    public void spawnApple(){
        if(isEnergyWorld) randomSpawn(new Apple(energyWorld));
    }

    public void spawnBanana(){
        if(isEnergyWorld) randomSpawn(new Banana(energyWorld));
    }

    public void spawnPapaya(){
        if(isEnergyWorld) randomSpawn(new Papaya(energyWorld));
    }
    
    public void spawnCat(){
        if(!isEnergyWorld) randomSpawn(new Cat(birdWorld));
    }
    
    private void randomSpawn(Actor act){
        int xPos;
        int yPos;
        while(true){
            xPos = Greenfoot.getRandomNumber(world.getWidth());
            yPos = Greenfoot.getRandomNumber(world.getHeight());
            if(isValidLocation(act, xPos, yPos)){
                world.addObject(act, xPos, yPos);
                return;
            }
        }
    }

    public boolean isValidLocation(Actor act, int x, int y){
        int spawnRange = 20; //Jarak toleransi antar object 
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
