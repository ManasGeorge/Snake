import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public void start(Stage stage){
        Group root = new Group();
        final CanvasBoard board = new CanvasBoard(600,600);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                board.update();
            }
        };

        root.getChildren().add(board);
        stage.setScene(new Scene(root));
        stage.setTitle("Floating");
        stage.setMinHeight(600);
        stage.setMaxHeight(600);
        stage.show();
        timer.start();
    }
}
