import javafx.scene.canvas.Canvas;

import java.util.Random;

public class CanvasBoard extends Canvas implements Board {
    private CanvasUnit head;
    private CanvasUnit tail;
    private CanvasUnit food;
    private Direction direction;

    public CanvasBoard(double width, double height){
        super(width, height);
        head = new CanvasUnit(
                (int)(getWidth() * CanvasUnit.SIZE/2) / (int)CanvasUnit.SIZE,
                (int)(getHeight() * CanvasUnit.SIZE/2) / (int)CanvasUnit.SIZE,
                null);
        tail = head;
        setFood();
        direction = Direction.RIGHT;
    }

    @Override
    public void update(){
        drawBoard();
        move();

        if (head.equals(food)) {
            CanvasUnit temp = new CanvasUnit(tail);
            move();
            temp.setNext(tail);
            tail = temp;
            setFood();
        }
    }

    @Override
    public void handleInput(String in){
        System.out.println(in);
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
                r.nextInt((int)(getWidth() / CanvasUnit.SIZE)) * CanvasUnit.SIZE,
                r.nextInt((int)(getHeight() / CanvasUnit.SIZE)) * CanvasUnit.SIZE,
                null
        );
    }

    private void drawBoard(){
        getGraphicsContext2D().clearRect(0,0,getWidth(),getHeight());
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
