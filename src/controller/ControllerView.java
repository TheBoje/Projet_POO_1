package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextFlow;
import modele.Game.GameManager;

public class ControllerView
{
    GameManager gameManager = new GameManager();

    @FXML
    Pane root;

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

    @FXML
    public void handleBtnNorth()
    {
        System.out.println("nord");
    }

    @FXML
    public void handleBtnEast()
    {
        System.out.println("east");
    }

    @FXML
    public void handleBtnSouth()
    {
        System.out.println("south");
    }

    @FXML
    public void handleBtnWest()
    {
        System.out.println("west");
    }
}
