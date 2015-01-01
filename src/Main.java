import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public void start(Stage stage){
//        final CanvasBoard board = new CanvasBoard(600,600,stage);
        final WindowBoard board = new WindowBoard(stage);
        final long[] last = {0};

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - last[0] > 3 * 1e8) {
                    board.update();
                    last[0] = now;
                }
            }
        };

        timer.start();
    }
}
