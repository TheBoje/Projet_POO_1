package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Game extends Pane
{
    public Game()
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(Game.class.getResource("./Game.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        }
        catch (IOException ioe)
        {
            System.err.println("Game constructor error");
            ioe.printStackTrace();
        }
    }
}
