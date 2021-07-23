package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditInventoryManagerController {

    private InventoryModel inventoryModel;
    private SceneManager sceneManager;

    @FXML
    private TextField editedItemName;

    @FXML
    private TextField editedSerialNum;

    @FXML
    private TextField editedItemPrice;

    public TextField getEditedItemName() {
        return editedItemName;
    }

    public TextField getEditedSerialNum() {
        return editedSerialNum;
    }

    public TextField getEditedItemPrice() {
        return editedItemPrice;
    }

    public EditInventoryManagerController(InventoryModel inventoryModel, SceneManager sceneManager) {
        this.inventoryModel = inventoryModel;
        this.sceneManager = sceneManager;
    }

    @FXML
    public void confirmButtonClicked(ActionEvent actionEvent) {
        if (!editItemWarnings())
            return;

        // Pass the values of the textFields and pass them on to inventoryModel
        inventoryModel.setNewName(editedItemName.getText());
        inventoryModel.setNewSerial(editedSerialNum.getText());
        inventoryModel.setNewPrice(editedItemPrice.getText());

        // Get the currently displayed stage and close it without making any changes
        Stage curStage = (Stage) editedItemName.getScene().getWindow();
        curStage.close();
    }

    private void existingSerialWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNING");
        alert.setHeaderText("Serial Number Already Exists");
        alert.setContentText("Please make sure the new serial number you've entered isn't identical to " +
                "that of another item already existing in the inventory.");
        alert.showAndWait();
    }

    private boolean editItemWarnings() {
        if (editedItemName.getText().isEmpty() || editedSerialNum.getText().isEmpty() ||
                editedItemPrice.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("Blank Text Fields");
            alert.setContentText("Please make sure that no text fields are left empty.");
            alert.show();
            return false;
        }

        // Create alert if name in the newItemName TextField is greater than 256 characters or less than 2
        if (editedItemName.getText().length() > 256 || editedItemName.getText().length() == 1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("Incorrect Name Length");
            alert.setContentText("Please make sure that the inputted item name is between 2 and 256 characters.");
            alert.show();
            return false;
        }

        // Create alert if serial number is in wrong format
        if (!editedSerialNum.getText().matches("[a-zA-Z0-9]{10}")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("Serial Number Format Incorrect");
            alert.setContentText("Please make sure that the inputted serial number is in the format XXXXXXXXXX," +
                    " where X can be either a number or letter.");
            alert.show();
            return false;
        }

        if (!editedItemPrice.getText().matches("[0-9]+\\.[0-9]{2}")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("Item Price Format Incorrect");
            alert.setContentText("Please make sure your price is a decimal with two decimal places for cents" +
                    " and only contains digits(no dollar sign necessary).");
            alert.show();
            return false;
        }

        for (int i = 0; i < inventoryModel.getInventory().size(); i++) {
            Item curItem = inventoryModel.getInventory().get(i);
            // Create alert about existing serial if item with entered serial already exists
            if (curItem.getSerial().equals(editedSerialNum.getText()) &&
                    curItem.equals(inventoryModel.getCurSelected()) == false) {
                existingSerialWarning();
                return false;
            }
        }

        return true;
    }
}
