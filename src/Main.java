import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modele.Game.GameManager;
import view.Game;

public class Main extends Application
{
    private final double WIDTH = 600.0, HEIGHT = 600.0;

    @Override
    public void start(Stage stage) throws Exception
    {
        Game root = new Game();

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);

        stage.setTitle("le nom du jeu");
        stage.show();
        System.out.println("bite");
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
