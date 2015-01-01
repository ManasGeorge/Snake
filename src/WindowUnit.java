import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class WindowUnit extends Unit{
    private Stage window = new Stage();
    public WindowUnit(double x, double y, CanvasUnit next) {
        super(x, y, next);
        window.setWidth(SIZE / 1.5);
        window.setHeight(SIZE);
        window.setX(x);
        window.setY(y);
        window.setTitle("Floating");
    }

    public WindowUnit(WindowUnit tail) {
        super(tail);
        window.setWidth(SIZE / 1.5);
        window.setHeight(SIZE);
        window.setX(x);
        window.setY(y);
        window.setTitle("Floating");
    }

    public WindowUnit getNext(){
        return (WindowUnit) this.next;
    }

    public void open(){
        window.setX(x);
        window.setY(y);
        window.show();
    }

    public void close(){
        window.close();
    }
}
