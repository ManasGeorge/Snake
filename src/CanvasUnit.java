import javafx.scene.canvas.GraphicsContext;

public class CanvasUnit extends Unit{
    public CanvasUnit(double x, double y, CanvasUnit next) {
        super(x, y, next);
    }

    public CanvasUnit(CanvasUnit tail) {
        super(tail);
    }

    public void draw(GraphicsContext gc){
        gc.fillRect(x,y,SIZE,SIZE);
    }

    public CanvasUnit getNext(){
        return (CanvasUnit) this.next;
    }
}
