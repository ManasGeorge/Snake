import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

import java.util.Random;

public class CanvasBoard extends Canvas implements Board {
    private CanvasUnit head;
    private CanvasUnit tail;
    private CanvasUnit food;
    private Direction direction;

    public CanvasBoard(double width, double height){
        super(width, height);
        head = new CanvasUnit(getHeight()/2, getWidth()/2, null);
        tail = head;
        setFood();
        direction = Direction.RIGHT;

        setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                handleInput(event.getCharacter());
            }
        });
    }

    @Override
    public void update(){
        drawBoard();
        move();

        if (head.equals(food)) {
            CanvasUnit temp = new CanvasUnit(tail);
            move();
            tail = temp;
            setFood();
        }
    }

    @Override
    public void handleInput(String in){
        switch(in){
            case "h": direction = Direction.LEFT; break;
            case "j": direction = Direction.DOWN; break;
            case "k": direction = Direction.UP; break;
            case "l": direction = Direction.RIGHT; break;
        }
    }

    private void setFood(){
        Random r = new Random();
        food = new CanvasUnit(
                (int)((r.nextDouble() * getWidth()) / CanvasUnit.SIZE),
                (int)((r.nextDouble() * getHeight()) / CanvasUnit.SIZE),
                null
        );
    }

    private void drawBoard(){
        for(CanvasUnit i = tail; i != null; i = i.getNext()){
            i.draw(getGraphicsContext2D());
        }

        food.draw(getGraphicsContext2D());
    }

    private void move(){
        for(CanvasUnit i = tail; i != head; i = i.getNext()){
            i.move();
        }

        head.move(direction);
    }
}
