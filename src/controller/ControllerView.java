package controller;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
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
import modele.Personnages.Player;
import modele.Tiles.Direction;
import modele.Tiles.UnknownDirection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerView
{
    public ControllerView() throws Exception {
    }

    enum listType{INV, ITEMS, PERSO, CROSS}

    int itemSelectedToUse = -1;

    private final DoubleProperty hp = new SimpleDoubleProperty();
    private final DoubleProperty hunger = new SimpleDoubleProperty();
    private final DoubleProperty heat = new SimpleDoubleProperty();
    listType typeInList = null;
    GameManager gameManager = new GameManager();
    private final Player player = gameManager.getWorld().getPlayer();

    public final double getHp() {return hp.get();}
    public final void setHp(int value){hp.set(value);}
    public DoubleProperty hpProperty() {return hp;}

    public final double getHunger() {return hunger.get();}
    public final void setHunger(int value){hunger.set(value);}
    public DoubleProperty hungerProperty() {return hunger;}

    public final double getHeat() {return heat.get();}
    public final void setHeat(int value){heat.set(value);}
    public DoubleProperty heatProperty() {return heat;}

    @FXML
    AnchorPane root;

    @FXML
    ProgressBar bodyheatProgressBar, hungerProgressBar, hpProgressBar;

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

    /**
     * Lance le texte de début de jeu
     */
    public void initGame() throws Exception {
        updateText(gameManager.initGame());
    }

    /**
     * Ajoute un message au panneau d'informations (il sera sous la forme : "> message\n")
     * @param message message à passer au TextFlow
     */
    public void updateText(String message)
    {
        informations.getChildren().add(new Text("> " + message + "\n"));
    }

    /**
     * Met à jour la ListView en lui ajoutant les items de la liste
     * @param items Items à rentrer dans la liste sous forme de liste de chaine de caractères
     */
    private void updateContextList(List<String> items)
    {
        ObservableList<String> observableItems = FXCollections.observableArrayList(items);
        contextList.setItems(observableItems);
    }

    /**
     * Remplace la liste précédente par celle des items sur la tile dans la ListView
     */
    public void updateContextListItems()
    {
        List<Item> items = gameManager.getItemsOnTile();
        updateContextList(items.stream().map(Item::getName).collect(Collectors.toList()));
        typeInList = listType.ITEMS;
    }

    /**
     * Remplace la liste précédente par celle des items de l'inventaire dans la ListView
     */
    public void updateContextListInventory()
    {
        List<Item> inventory = gameManager.getInventory();
        updateContextList(inventory.stream().map(Item::getName).collect(Collectors.toList()));
        typeInList = listType.INV;
    }

    /**
     * Remplace la liste précédente par celle des personnages sur la tile dans la ListView
     * TODO voir pour le map ici aussi
     */
    public void updateContextListPersonnages()
    {
        List<Personnage> personnages = gameManager.getPersonnagesOnTile();
        updateContextList(personnages.stream().map(Personnage::getName).collect(Collectors.toList()));
        typeInList = listType.PERSO;
    }

    /**
     * Remplace la liste précédente par celle des passages sur la tile dans la ListView
     */
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

    /**
     * Appelée lors d'un clique sur la ListView, elle a un comportement différent en fonction du type de donnée
     * qui est affiché.
     */
    @FXML
    public void useContextList() throws Exception {
        // Cette condition permet de gérer le cas où on utilise un item. Si on a cliqué une première fois sur un
        // objet de notre inventaire, la liste des personnages s'affichera pour choisir la cible de l'utilisation.
        if(itemSelectedToUse < 0)
        {
            switch(typeInList)
            {
                // Cas de l'utilisation d'un objet
                case INV -> {
                    itemSelectedToUse = contextList.getSelectionModel().getSelectedIndex();
                    updateContextListPersonnages();
                }

                // Cas de prise d'un objet étant sur la tile
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

                // Cas de conversation avec un PNJ
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

                // Cas d'ouverture de passage clos
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
            // Ce cas intervient quand on a un item que l'on veut utiliser (son indice étant l'attribut itemSelectedToUse)
            // Si le deuxième clique est donc sur un personnage, on utilise l'item selectionné sur lui
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
                    updatePlayer();
                }
                catch (Exception e)
                {
                    System.err.println(e.getMessage());
                }
            }

            itemSelectedToUse = -1;
        }
    }
    @FXML
    public void updatePlayer() throws Exception {
        hp.set(player.getHp()/10.0);
        hunger.set(player.getHunger()/10.0);
        heat.set(player.getBodyHeat()/10.0);
    }
    /**
     * Lance le jeu si le joueur à rentré un nom
     */
    @FXML
    public void startGame() throws Exception {
        if(playerName.getText().isEmpty())
        {
            System.err.println("player name's empty");
        }
        else
        {
            System.out.println("game launched");
            updateText(gameManager.initGame());
            updatePlayer();
            hpProgressBar.progressProperty().bind(hp);
            hungerProgressBar.progressProperty().bind(hunger);
            bodyheatProgressBar.progressProperty().bind(heat);
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
            updatePlayer();
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        updateContextListPassways();
    }

    @FXML
    public void handleBtnEast()
    {
        try
        {
            gameManager.go(Direction.E);
            updatePlayer();
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        updateContextListPassways();
    }

    @FXML
    public void handleBtnSouth()
    {
        try
        {
            gameManager.go(Direction.S);
            updatePlayer();
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        updateContextListPassways();
    }

    @FXML
    public void handleBtnWest()
    {
        try
        {
            gameManager.go(Direction.W);
            updatePlayer();
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        updateContextListPassways();
    }
}
