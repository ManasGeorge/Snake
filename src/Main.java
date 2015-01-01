import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {
    public void start(Stage stage){
        Group root = new Group();
        final CanvasBoard board = new CanvasBoard(600,600);
        final long[] last = {0};

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - last[0] > 5 * 1e7){
                    board.update();
                    last[0] = now;
                }
            }
        };

        board.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                board.handleInput(event.getCharacter());
            }
        });

        root.getChildren().add(board);
        stage.setScene(new Scene(root));
        stage.setTitle("Floating");
        stage.setMinHeight(600);
        stage.setMinWidth(600);
        stage.show();
        timer.start();

        board.requestFocus();
    }
}
