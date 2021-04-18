package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import modele.Crossings.Crossing;
import modele.Game.GameManager;
import modele.Items.Item;
import modele.Personnages.Personnage;
import modele.Tiles.Direction;

import java.util.List;

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

    @FXML
    TextField playerName;

    public void initGame()
    {
        updateText(gameManager.initGame());
    }

    public void updateText(String message)
    {
        informations.getChildren().add(new Text(message));
    }

    public void updateContextListItems(List<Item> items)
    {
        ObservableList<Item> observableItems = FXCollections.observableArrayList(items); // Vérifier si la méthode appelle le to_string
        contextList.setItems(observableItems);
    }

    public void updateContextListPersonnages(List<Personnage> personnages)
    {
        ObservableList<Personnage> observablePersonnages = FXCollections.observableArrayList(personnages);
        contextList.setItems(observablePersonnages);
    }

    public void updateContextListPassways(Crossing[] crossings)
    {
        ObservableList<String> observableCrossings = FXCollections.observableArrayList();

        for(int i = 0; i < crossings.length; i++)
        {
            if(crossings[i] != null)
            {
                try
                {
                    observableCrossings.add((Direction.intToDirection(i) + " " + (crossings[i].isOpen() ? "open" : "close")));
                }
                catch (Exception e)
                {
                    System.err.println(e.getMessage());
                }
            }
        }

        contextList.setItems(observableCrossings);
    }

    @FXML
    public void startGame()
    {
        if(playerName.getText().isEmpty())
        {
            System.err.println("player name's empty");
        }
        else
        {
            System.out.println("game launched");
            gameManager.initGame();


        }
    }



    @FXML
    public void handleBtnListCrossings()
    {
        try
        {
            updateContextListPassways(gameManager.getDirection());
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    public void handleBtnListItems()
    {

    }

    @FXML
    public void handleBtnListPersonnages()
    {

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
            gameManager.go(Direction.E);
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    public void handleBtnSouth()
    {
        updateContextListItems(gameManager.getItemsOnTile());
        try
        {
            gameManager.go(Direction.S);
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
            gameManager.go(Direction.W);
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
}
