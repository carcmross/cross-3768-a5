package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Marc-Anthony Cross
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class InventoryModelTest {

    @Test
    void getInventory_returns_inventory_ObservableList() {
        InventoryModel inventoryModel = new InventoryModel();
        inventoryModel.getInventory().addAll(new Item("Item 1", "0000000000", "$5.00"),
                new Item("Item 2", "1111111111", "$10.00"));
        ObservableList<Item> actual = inventoryModel.getInventory();

        ObservableList<Item> expected = FXCollections.observableArrayList();
        expected.addAll(new Item("Item 1", "0000000000", "$5.00"),
                new Item("Item 2", "1111111111", "$10.00"));
        assertEquals(actual.toString(), expected.toString());
    }

    @Test
    void getFoundInventory_returns_found_inventory_ObservableList() {
        InventoryModel inventoryModel = new InventoryModel();
        inventoryModel.getFoundInventory().addAll(new Item("Item 1", "0000000000", "$5.00"),
                new Item("Item 2", "1111111111", "$10.00"));
        ObservableList<Item> actual = inventoryModel.getFoundInventory();

        ObservableList<Item> expected = FXCollections.observableArrayList();
        expected.addAll(new Item("Item 1", "0000000000", "$5.00"),
                new Item("Item 2", "1111111111", "$10.00"));
        assertEquals(actual.toString(), expected.toString());
    }

    @Test
    void generateHTMLOutput_returns_html_output_in_proper_format() {
        InventoryModel inventoryModel = new InventoryModel();
    }

    @Test
    void writeToHTML_writes_given_output_to_html_file() {
        InventoryModel inventoryModel = new InventoryModel();
    }

    @Test
    void generateTSVOutput_returns_tsv_output_in_proper_format() {
        InventoryModel inventoryModel = new InventoryModel();
    }

    @Test
    void writeToTSV_writes_given_output_to_tsv_file() {
        InventoryModel inventoryModel = new InventoryModel();
    }

    @Test
    void loadHTML_returns_ObservableList_after_reading_html_file() {
        InventoryModel inventoryModel = new InventoryModel();
    }

    @Test
    void loadTSV_returns_ObservableList_after_reading_tsv_file() {
        InventoryModel inventoryModel = new InventoryModel();
    }

    @Test
    void addItemToInventory_adds_item_to_inventory_ObservableList() {
        InventoryModel inventoryModel = new InventoryModel();
        Item tempItem = new Item("Xbox 720", "6969696969", "$499.99");
        inventoryModel.addItemToInventory(tempItem);
        ObservableList<Item> actual = inventoryModel.getInventory();
        ObservableList<Item> expected = FXCollections.observableArrayList();
        expected.add(new Item("Xbox 720", "6969696969", "$499.99"));
        assertEquals(actual.toString(), expected.toString());
    }

    @Test
    void editItem_edits_the_name_of_an_inventory_item() {
        InventoryModel inventoryModel = new InventoryModel();
        inventoryModel.getInventory().addAll(new Item("Item 1", "0000000000", "$5.00"),
                new Item("Item 2", "1111111111", "$10.00"));
        inventoryModel.setNewName("Item 300");
        inventoryModel.setNewSerial("0000000000");
        inventoryModel.setNewPrice("$5.00");
        inventoryModel.editItem(inventoryModel.getInventory().get(0));
        ObservableList<Item> actual = inventoryModel.getInventory();
        ObservableList<Item> expected = FXCollections.observableArrayList();
        expected.addAll(new Item("Item 300", "0000000000", "$5.00"),
                new Item("Item 2", "1111111111", "$10.00"));
        assertEquals(actual.toString(), expected.toString());
    }

    @Test
    void editItem_edits_the_serial_of_an_inventory_item() {
        InventoryModel inventoryModel = new InventoryModel();
        inventoryModel.getInventory().addAll(new Item("Item 1", "0000000000", "$5.00"),
                new Item("Item 2", "1111111111", "$10.00"));
        inventoryModel.setNewName("Item 1");
        inventoryModel.setNewSerial("1234567890");
        inventoryModel.setNewPrice("$5.00");
        inventoryModel.editItem(inventoryModel.getInventory().get(0));
        ObservableList<Item> actual = inventoryModel.getInventory();
        ObservableList<Item> expected = FXCollections.observableArrayList();
        expected.addAll(new Item("Item 1", "1234567890", "$5.00"),
                new Item("Item 2", "1111111111", "$10.00"));
        assertEquals(actual.toString(), expected.toString());
    }

    @Test
    void editItem_edits_the_price_of_an_inventory_item() {
        InventoryModel inventoryModel = new InventoryModel();
        inventoryModel.getInventory().addAll(new Item("Item 1", "0000000000", "$5.00"),
                new Item("Item 2", "1111111111", "$10.00"));
        inventoryModel.setNewName("Item 1");
        inventoryModel.setNewSerial("0000000000");
        inventoryModel.setNewPrice("$1000.00");
        inventoryModel.editItem(inventoryModel.getInventory().get(0));
        ObservableList<Item> actual = inventoryModel.getInventory();
        ObservableList<Item> expected = FXCollections.observableArrayList();
        expected.addAll(new Item("Item 1", "0000000000", "$1000.00"),
                new Item("Item 2", "1111111111", "$10.00"));
        assertEquals(actual.toString(), expected.toString());
    }

    @Test
    void removeItemFromInventory() {
        InventoryModel inventoryModel = new InventoryModel();
        Item itemOne = new Item("Item 1", "0000000000", "$5.00");
        Item itemTwo = new Item("Item 2", "1111111111", "$10.00");
        inventoryModel.getInventory().addAll(itemOne, itemTwo);
        inventoryModel.removeItemFromInventory(itemOne);
        ObservableList<Item> actual = inventoryModel.getInventory();
        ObservableList<Item> expected = FXCollections.observableArrayList();
        expected.add(new Item("Item 2", "1111111111", "$10.00"));
        assertEquals(actual.toString(), expected.toString());
    }

    @Test
    void searchByName_adds_matching_item_to_foundInventory_ObservableList() {
        InventoryModel inventoryModel = new InventoryModel();
        inventoryModel.getInventory().addAll(new Item("Xbox One", "0000000000", "$5.00"),
                new Item("Playstation 5", "1111111111", "$10.00"));
        inventoryModel.searchByName("pl");
        ObservableList<Item> actual = inventoryModel.getFoundInventory();
        ObservableList<Item> expected = FXCollections.observableArrayList();
        expected.add(new Item("Playstation 5", "1111111111", "$10.00"));
        assertEquals(actual.toString(), expected.toString());
    }

    @Test
    void searchBySerial_adds_matching_item_to_foundInventory_ObservableList() {
        InventoryModel inventoryModel = new InventoryModel();
        inventoryModel.getInventory().addAll(new Item("Xbox One", "0000000000", "$5.00"),
                new Item("Playstation 5", "1111111111", "$10.00"));
        inventoryModel.searchBySerial("0000000000");
        ObservableList<Item> actual = inventoryModel.getFoundInventory();
        ObservableList<Item> expected = FXCollections.observableArrayList();
        expected.add(new Item("Xbox One", "0000000000", "$5.00"));
        assertEquals(actual.toString(), expected.toString());
    }

    // Sort functions are handled by the TableView in both the main and search window(requirements 8-10).
}