public class Timer{
    private long lastMark = System.currentTimeMillis();
    
    public void markTimer(){
        lastMark = System.currentTimeMillis();
    }
    
    public int getTimer(){
        return (int) (System.currentTimeMillis() - lastMark);
    }
}