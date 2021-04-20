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
import javafx.stage.Popup;
import modele.Crossings.Crossing;
import modele.Game.GameManager;
import modele.Game.InputError;
import modele.Items.Item;
import modele.Personnages.Personnage;
import modele.Tiles.Direction;
import modele.Tiles.UnknownDirection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerView
{
    enum listType{INV, ITEMS, PERSO, CROSS}

    int itemSelectedToUse = -1;
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
        informations.getChildren().add(new Text("> " + message + "\n"));
    }

    private void updateContextList(List<String> items)
    {
        ObservableList<String> observableItems = FXCollections.observableArrayList(items);
        contextList.setItems(observableItems);
    }

    public void updateContextListItems()
    {
        List<Item> items = gameManager.getItemsOnTile();
        updateContextList(items.stream().map(Item::getName).collect(Collectors.toList()));
        typeInList = listType.ITEMS;
    }

    public void updateContextListInventory()
    {
        List<Item> inventory = gameManager.getInventory();
        updateContextList(inventory.stream().map(Item::getName).collect(Collectors.toList()));
        typeInList = listType.INV;
    }

    public void updateContextListPersonnages()
    {
        List<Personnage> personnages = gameManager.getPersonnagesOnTile();
        updateContextList(personnages.stream().map(Personnage::getName).collect(Collectors.toList()));
        typeInList = listType.PERSO;
    }

    public void updateContextListPassways()
    {
        Crossing[] crossings = new Crossing[4];
        try
        {
            crossings = gameManager.getDirection();
        }
        catch (UnknownDirection unknownDirection)
        {
            unknownDirection.printStackTrace();
        }

        List<String> listCrossings = new ArrayList<>();

        // Pour chaque passages on regarde s'il existe et son état
        for(int i = 0; i < crossings.length; i++)
        {
                try
                {
                    if(crossings[i] != null)
                    {
                        listCrossings.add((Direction.intToDirection(i) + " " + (crossings[i].isOpen() ? "open" : "close")));
                    }
                    else
                    {
                        listCrossings.add("NO PASARAN");
                    }
                }
                catch (Exception e)
                {
                    System.err.println(e.getMessage());
                }
        }

        updateContextList(listCrossings);
        typeInList = listType.CROSS;
    }

    @FXML
    public void useContextList()
    {
        if(itemSelectedToUse < 0)
        {
            switch(typeInList)
            {
                case INV -> {
                    itemSelectedToUse = contextList.getSelectionModel().getSelectedIndex();
                    updateContextListPersonnages();
                }
                case ITEMS -> {
                    try
                    {
                        gameManager.take(contextList.getSelectionModel().getSelectedIndex());
                        updateContextListItems();
                    }
                    catch (InputError e)
                    {
                        System.err.println(e.getMessage());
                    }
                }
                case PERSO -> {
                    try
                    {
                        updateText(gameManager.talk(contextList.getSelectionModel().getSelectedIndex()));
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
                        updateContextListPassways();
                    }
                    catch (Exception e)
                    {
                        System.err.println(e.getMessage());
                    }
                }
            }
        }
        else
        {
            if(typeInList == listType.PERSO)
            {
                try
                {
                    int targetIndex = contextList.getSelectionModel().getSelectedIndex();
                    Personnage target = gameManager.getPersonnagesOnTile().get(targetIndex);
                    Item usedItem = gameManager.getInventory().get(itemSelectedToUse);

                    gameManager.use(itemSelectedToUse, targetIndex);
                    updateText("Used " + usedItem.getName() + " on " + target.getName());
                    updateContextListInventory();
                }
                catch (Exception e)
                {
                    System.err.println(e.getMessage());
                }
            }

            itemSelectedToUse = -1;
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
        updateContextListPassways();
    }

    @FXML
    public void handleBtnListItems()
    {
        updateContextListItems();
    }

    @FXML
    public void handleBtnListInventory()
    {
        updateContextListInventory();
    }

    @FXML
    public void handleBtnListPersonnages()
    {
        updateContextListPersonnages();
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
