import javafx.scene.canvas.GraphicsContext;

public class CanvasUnit {
    public static final double SIZE = 10;
    private double x;
    private double y;
    private CanvasUnit next;

    public CanvasUnit(double x, double y, CanvasUnit next){
        this.x = x;
        this.y = y;
        this.next = next;
    }

    public CanvasUnit(CanvasUnit unit){
        this.x = unit.x;
        this.y = unit.y;
        this.next = unit.next;
    }

    public void move(){
        x = next.x;
        y = next.y;
    }
    public void move(Direction direction){
        double delx, dely;
        switch(direction){
            case LEFT:  delx = -SIZE; dely = 0; break;
            case RIGHT: delx = SIZE; dely = 0; break;
            case UP:    delx = 0;        dely = -SIZE; break;
            case DOWN:  delx = 0;        dely = SIZE; break;
            default: delx = 0; dely = 0;
        }
        x += delx;
        y += dely;
    }

    public CanvasUnit getNext() {
        return next;
    }

    public void setNext(CanvasUnit next){
        this.next = next;
    }

    public void draw(GraphicsContext gc){
        gc.fillRect(x,y,SIZE,SIZE);
    }

    public boolean equals(CanvasUnit other){
        return x == other.x && y == other.y;
    }
}
