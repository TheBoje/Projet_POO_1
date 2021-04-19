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
import modele.Game.InputError;
import modele.Items.Item;
import modele.Personnages.Personnage;
import modele.Tiles.Direction;

import java.util.List;

public class ControllerView
{
    enum listType{INV, ITEMS, PERSO, CROSS}

    listType typeInList = null;
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
        ObservableList<String> observableItems = FXCollections.observableArrayList();

        // Pour chaque passages on regarde s'il existe et son état
        for(Item item : items)
        {
            observableItems.add(item.getName());
        }

        contextList.setItems(observableItems);
    }

    public void updateContextListPersonnages(List<Personnage> personnages)
    {
        ObservableList<String> observablePersonnages = FXCollections.observableArrayList();

        // Pour chaque passages on regarde s'il existe et son état
        for(Personnage personnage : personnages)
        {
            observablePersonnages.add(personnage.getName());
        }

        contextList.setItems(observablePersonnages);
    }

    public void updateContextListPassways(Crossing[] crossings)
    {
        ObservableList<String> observableCrossings = FXCollections.observableArrayList();

        // Pour chaque passages on regarde s'il existe et son état
        for(int i = 0; i < crossings.length; i++)
        {

                try
                {
                    if(crossings[i] != null)
                    {
                        observableCrossings.add((Direction.intToDirection(i) + " " + (crossings[i].isOpen() ? "open" : "close")));
                    }
                    else
                    {
                        observableCrossings.add("NO PASARAN");
                    }
                }
                catch (Exception e)
                {
                    System.err.println(e.getMessage());
                }
        }

        contextList.setItems(observableCrossings);
    }

    @FXML
    public void useContextList()
    {
        switch(typeInList)
        {
            case INV -> {
            }
            case ITEMS -> {
                try
                {
                    gameManager.take(contextList.getSelectionModel().getSelectedIndex());
                    updateContextListItems(gameManager.getItemsOnTile());
                }
                catch (InputError e)
                {
                    System.err.println(e.getMessage());
                }
            }
            case PERSO -> {
                try
                {
                    updateText("> " + gameManager.talk(contextList.getSelectionModel().getSelectedIndex()) + "\n");
                }
                catch (Exception e)
                {
                    System.err.println(e.getMessage());
                }
            }
            case CROSS -> {
                try
                {
                    gameManager.open(Direction.intToDirection(contextList.getSelectionModel().getSelectedIndex()));
                    updateContextListPassways(gameManager.getDirection());
                }
                catch (Exception e)
                {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    /**
     * Lance le jeu si le joueur à rentré un nom
     */
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
            updateText(gameManager.initGame());
        }
    }



    @FXML
    public void handleBtnListCrossings()
    {
        typeInList = listType.CROSS;
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
        typeInList = listType.ITEMS;
        updateContextListItems(gameManager.getItemsOnTile());
    }

    @FXML
    public void handleBtnListInventory()
    {
        typeInList = listType.INV;
        updateContextListItems(gameManager.getInventory());
    }

    @FXML
    public void handleBtnListPersonnages()
    {
        typeInList = listType.PERSO;
        updateContextListPersonnages(gameManager.getPersonnagesOnTile());
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
