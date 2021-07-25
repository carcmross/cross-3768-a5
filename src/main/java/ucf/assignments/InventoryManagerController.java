package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Marc-Anthony Cross
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class InventoryManagerController implements Initializable {

    private InventoryModel inventoryModel;
    private SceneManager sceneManager;
    private EditInventoryManagerController editInventoryManagerController;

    @FXML
    private TextField newItemName;

    @FXML
    private TextField newSerialNum;

    @FXML
    private TextField newItemPrice;

    @FXML
    private TextField searchBar;

    @FXML
    private TableView<Item> inventoryTable;

    @FXML
    private TableColumn<Item, String> nameColumn;

    @FXML
    private TableColumn<Item, String> serialColumn;

    @FXML
    private TableColumn<Item, String> priceColumn;

    public InventoryManagerController(InventoryModel inventoryModel, SceneManager sceneManager,
                                      EditInventoryManagerController editInventoryManagerController) {
        this.inventoryModel = inventoryModel;
        this.sceneManager = sceneManager;
        this.editInventoryManagerController = editInventoryManagerController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        serialColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("serial"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("price"));
        inventoryTable.setItems(inventoryModel.getInventory());
    }

    @FXML
    public void saveButtonClicked(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Inventory");
        FileChooser.ExtensionFilter html = new FileChooser.ExtensionFilter("HTML File (*.html)",
                "*.html");
        FileChooser.ExtensionFilter tsv = new FileChooser.ExtensionFilter("TSV File (*.txt)",
                "*.txt");
        fileChooser.getExtensionFilters().addAll(html, tsv);
        File file = fileChooser.showSaveDialog(new Stage());

        // Write to file in format depending on extension
        if (file != null) {
            if (file.getName().endsWith("txt")) {
                inventoryModel.writeToTSV(file);
            } else if (file.getName().endsWith("html")) {
                inventoryModel.writeToHTML(file);
            }
        }
    }

    @FXML
    public void loadButtonClicked(ActionEvent actionEvent) {
        // Call fileChooser to allow user to pick file they'd like to load
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Inventory");
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null)
            inventoryModel.loadInventoryFromFile(file);
    }

    @FXML
    public void searchBySerialClicked(ActionEvent actionEvent) {
        // Clear found inventory list if there are items in it already
        if (inventoryModel.getFoundInventory().size() != 0)
            inventoryModel.getFoundInventory().clear();

        // Check if searchBar is empty before assigning string to search variable
        String search = "";
        if (searchBar.getText() != "")
            search = searchBar.getText();
        else {
            // Generate alert prompting user to type in search bar before clicking search button
            emptySearchWarning();
            return;
        }

        // Call searchBySerial to add items to foundInventory list if they match the search
        inventoryModel.searchBySerial(search);

        if (inventoryModel.getFoundInventory().size() == 0)
            noItemsFound();
        else {
            // Create new window
            Stage stage = new Stage();
            stage.setTitle("Inventory Lookup");
            stage.setScene(sceneManager.getScene("SearchManager"));
            // Display window
            stage.showAndWait();
        }

        // Clear search string after items have been added to the foundinventory list
        search = "";
    }

    @FXML
    public void searchByNameClicked(ActionEvent actionEvent) {
        // Clear found inventory list if there are items in it already
        if (inventoryModel.getFoundInventory().size() != 0)
            inventoryModel.getFoundInventory().clear();

        // Set search string so that it can be passed to searchByName function
        String search = "";
        if (searchBar.getText() != "") {
            search = searchBar.getText();
        }
        else {
            emptySearchWarning();
            return;
        }

        // Call searchBySerial to add items to foundInventory list if they match the search
        inventoryModel.searchByName(search);

        if (inventoryModel.getFoundInventory().size() == 0)
            noItemsFound();
        else {
            // Create new window
            Stage stage = new Stage();
            stage.setTitle("Inventory Lookup");
            stage.setScene(sceneManager.getScene("SearchManager"));
            // Display window
            stage.showAndWait();
        }

        // Clear search string after window is closed
        search = "";
    }

    private void emptySearchWarning() {
        // Generate alert if there is nothing typed in search bar
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNING");
        alert.setHeaderText("Search Bar is Empty");
        alert.setContentText("Please make sure that you've typed in the search bar before attempting to" +
                " search for items.");
        alert.show();
    }

    private void noItemsFound() {
        // Create alert if no matches were found for the search
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("No Matching Items Found");
            alert.setContentText("No items matching your search criteria could be found. Please try again.");
            alert.showAndWait();
    }

    @FXML
    public void addItemButtonClicked(ActionEvent actionEvent) {
        if (!addItemWarnings())
            return;

        // Add new item to inventory ObservableList
        Item newItem = new Item(newItemName.getText(), newSerialNum.getText().toUpperCase(), newItemPrice.getText());
        inventoryModel.addItemToInventory(newItem);

        // Clear text fields
        newItemName.clear();
        newSerialNum.clear();
        newItemPrice.clear();
    }

    private boolean addItemWarnings() {
        if (newItemName.getText().isEmpty() || newSerialNum.getText().isEmpty() || newItemPrice.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("Blank Text Fields");
            alert.setContentText("Please make sure that no text fields are left empty.");
            alert.show();
            return false;
        }

        // Create alert if name in the newItemName TextField is greater than 256 characters or less than 2
        if (newItemName.getText().length() > 256 || newItemName.getText().length() == 1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("Incorrect Name Length");
            alert.setContentText("Please make sure that the inputted item name is between 2 and 256 characters.");
            alert.show();
            return false;
        }

        // Create alert if serial number is in wrong format
        if (!newSerialNum.getText().matches("[a-zA-Z0-9]{10}")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("Serial Number Format Incorrect");
            alert.setContentText("Please make sure that the inputted serial number is in the format XXXXXXXXXX," +
                    " where X can be either a number or letter.");
            alert.show();
            return false;
        }

        if (!newItemPrice.getText().matches("[$]{1}[0-9]+\\.[0-9]{2}")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("Item Price Format Incorrect");
            alert.setContentText("Please make sure your price begins with a dollar sign and contains two" +
                    " digits after the decimal(i.e. $1.99).");
            alert.show();
            return false;
        }

        for (int i = 0; i < inventoryModel.getInventory().size(); i++) {
            Item curItem = inventoryModel.getInventory().get(i);
            // Create alert about existing serial if item with entered serial already exists
            if (newSerialNum.getText().toLowerCase().equals(curItem.getSerial().toLowerCase())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("WARNING");
                alert.setHeaderText("Serial Number Already Exists");
                alert.setContentText("Please make sure the new serial number you've entered isn't identical to " +
                        "that of another item already existing in the inventory.");
                alert.showAndWait();
                return false;
            }
        }

        return true;
    }

    @FXML
    public void editItemButtonClicked(ActionEvent actionEvent) {
        if (inventoryTable.getSelectionModel().getSelectedItem() == null) {
            // Generate alert if nothing has been selected before attempting to edit, then return early
            noItemSelectedWarning();
            return;
        }

        inventoryModel.setCurSelected(inventoryTable.getSelectionModel().getSelectedItem());
        editInventoryManagerController.getEditedItemName().setText(inventoryModel.getCurSelected().getName());
        editInventoryManagerController.getEditedSerialNum().setText(inventoryModel.getCurSelected().getSerial());
        editInventoryManagerController.getEditedItemPrice().setText(inventoryModel.getCurSelected().getPrice());

        // Create new window to allow for edits to be made
        Stage stage = new Stage();
        stage.setTitle("Edit Inventory Item");
        stage.setScene(sceneManager.getScene("EditInventoryManager"));
        stage.showAndWait();

        // Edit item in ObservableList
        inventoryModel.editItem(inventoryModel.getCurSelected());

        // After stage has been closed, set curSelected and new property strings back to null
        inventoryModel.setCurSelected(null);
        inventoryModel.setNewPrice("");
        inventoryModel.setNewSerial("");
        inventoryModel.setNewPrice("");

        // Refresh table to reflect changes
        inventoryTable.refresh();
    }

    private void noItemSelectedWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNING");
        alert.setHeaderText("No Item Selected");
        alert.setContentText("Please select an item before attempting to edit/remove one.");
        alert.showAndWait();
    }

    @FXML
    public void removeItemButtonClicked(ActionEvent actionEvent) {
        if (inventoryModel.getInventory().size() == 0) {
            // Create alert saying there is nothing that can be removed then return
            noItemSelectedWarning();
            return;
        }

        if (inventoryTable.getSelectionModel().getSelectedItem() == null) {
            // Create alert saying that an item should be selected before attempting to remove then return
            noItemSelectedWarning();
            return;
        }
        else {
            inventoryModel.setCurSelected(inventoryTable.getSelectionModel().getSelectedItem());
            inventoryModel.removeItemFromInventory(inventoryModel.getCurSelected());
        }

        // Set curSelected back to null
        inventoryModel.setCurSelected(null);
    }
}
