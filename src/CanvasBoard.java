import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

public class CanvasBoard extends Canvas implements Board {
    private CanvasUnit head;
    private CanvasUnit tail;
    private CanvasUnit food;
    private boolean finished = false;
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
    public void update() {
        if (!finished) {
            drawBoard();
            move();

            if (head.equals(food)) {
                CanvasUnit temp = new CanvasUnit(tail);
                move();
                temp.setNext(tail);
                tail = temp;
                setFood();
            }

            if (outOfBounds() || intersectsBody()) {
                finished = true;
                gameOver();
            }
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

    private boolean outOfBounds(){
        return (head.getX() < 0 || head.getX() >= getWidth()
                || head.getY() < 0 || head.getY() >= getHeight());
    }

    private boolean intersectsBody(){
        for(CanvasUnit i = tail; i != head; i = i.getNext())
            if(head.equals(i)) return true;
        return false;
    }

    private void gameOver(){
        VBox vBox = new VBox(5);
        Text text = new Text("Game Over!");
        Button okay = new Button("Okay");

        okay.setOnAction(e -> Platform.exit() );

        vBox.getChildren().addAll(text,okay);
        vBox.setAlignment(Pos.CENTER);
        Stage stage = new Stage();
        stage.setScene(new Scene(vBox));
        stage.setTitle("Floating");
        stage.setMinWidth(150);
        stage.setMinHeight(50);
        stage.show();
    }
}
