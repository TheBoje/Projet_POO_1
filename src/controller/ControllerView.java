package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import modele.Game.GameManager;
import modele.Tiles.Direction;

public class ControllerView
{
    GameManager gameManager = new GameManager();

    @FXML
    AnchorPane root;

    @FXML
    Button btnNorth, btnEast, btnSouth, btnWest;

    @FXML
    TextFlow informations;

    @FXML
    ImageView gameBoardImage;

    @FXML
    ListView contextList;

    @FXML
    GridPane actions;

    public void initGame()
    {
        updateText(gameManager.initGame());
    }

    public void updateText(String message)
    {
        informations.getChildren().add(new Text(message));
    }

    @FXML
    public void handleBtnNorth()
    {
        try
        {
            gameManager.go(Direction.N);
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    public void handleBtnEast()
    {
        try
        {
            gameManager.go(Direction.N);
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    public void handleBtnSouth()
    {
        try
        {
            gameManager.go(Direction.N);
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    public void handleBtnWest()
    {
        try
        {
            gameManager.go(Direction.N);
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
}
