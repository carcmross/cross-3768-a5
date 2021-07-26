package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Marc-Anthony Cross
 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchManagerController implements Initializable {

    private InventoryModel inventoryModel;
    private SceneManager sceneManager;

    @FXML
    private TableView<Item> searchedItemView;

    @FXML
    private TableColumn<Item, String> searchedName;

    @FXML
    private TableColumn<Item, String> searchedSerial;

    @FXML
    private TableColumn<Item, String> searchedPrice;

    public SearchManagerController(InventoryModel inventoryModel, SceneManager sceneManager) {
        this.inventoryModel = inventoryModel;
        this.sceneManager = sceneManager;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        searchedName.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        searchedSerial.setCellValueFactory(new PropertyValueFactory<Item, String>("serial"));
        searchedPrice.setCellValueFactory(new PropertyValueFactory<Item, String>("price"));
        searchedItemView.setItems(inventoryModel.getFoundInventory());
        searchedName.setReorderable(false);
        searchedSerial.setReorderable(false);
        searchedPrice.setReorderable(false);
    }
}
