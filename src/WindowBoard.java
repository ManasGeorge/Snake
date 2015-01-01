import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Random;

public class WindowBoard {
    private Stage stage;
    private WindowUnit head;
    private WindowUnit tail;
    private WindowUnit food;
    private boolean finished = false;
    private Direction direction;

    public WindowBoard(Stage stage){
        head = new WindowUnit(
                (int)((getWidth() - WindowUnit.SIZE) / 2 / WindowUnit.SIZE) * WindowUnit.SIZE,
                (int)((getHeight() - WindowUnit.SIZE) / 2 / WindowUnit.SIZE) * WindowUnit.SIZE,
                null);

        tail = head;
        setFood();
        direction = Direction.RIGHT;

        stage.setScene(new Scene(new VBox()));
        stage.setTitle("Floating");
        stage.setX(0);
        stage.setY(0);
        stage.setWidth(5);
        stage.setHeight(5);
        stage.getScene().setOnKeyTyped(e -> handleInput(e.getCharacter()));
        stage.requestFocus();
        stage.show();
        this.stage = stage;
    }

    public void update() {
        if (!finished) {
            move();
            if (outOfBounds() || intersectsBody()) {
                finished = true;
                gameOver();
            }
            if (head.equals(food)) {
                WindowUnit temp = new WindowUnit(tail);
                move();
                temp.setNext(tail);
                tail = temp;
                food.close();
                setFood();
            } else {
                drawBoard();
            }
            stage.requestFocus();
        }
    }

    public void handleInput(String in){
        Direction old = direction;
        switch(in){
            case "h": direction = Direction.LEFT; break;
            case "j": direction = Direction.DOWN; break;
            case "k": direction = Direction.UP; break;
            case "l": direction = Direction.RIGHT; break;
        }

        if(Math.abs(old.ordinal() - direction.ordinal()) == 1){
            direction = old;
        }
    }

    private void setFood(){
        Random r = new Random();
        food = new WindowUnit(
                r.nextInt((int)((getWidth() - WindowUnit.SIZE) / (WindowUnit.SIZE))) * WindowUnit.SIZE,
                r.nextInt((int)((getHeight() - WindowUnit.SIZE) / (WindowUnit.SIZE))) * WindowUnit.SIZE,
                null
        );
    }

    private void drawBoard(){
        for(WindowUnit i = tail; i != null; i = i.getNext()){
            i.close();
            i.open();
        }

        food.open();
    }

    private void move(){
        for(WindowUnit i = tail; i != head; i = i.getNext()){
            i.move();
        }

        head.move(direction);
    }

    private boolean outOfBounds(){
        return (head.getX() < 0 || head.getX() + WindowUnit.SIZE >= getWidth()
                || head.getY() < 0 || head.getY() + WindowUnit.SIZE >= getHeight());
    }

    private boolean intersectsBody(){
        for(WindowUnit i = tail; i != head; i = i.getNext())
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

    private double getWidth(){
        return Screen.getPrimary().getBounds().getWidth();
    }

    private double getHeight(){
        return Screen.getPrimary().getBounds().getHeight();
    }
}

