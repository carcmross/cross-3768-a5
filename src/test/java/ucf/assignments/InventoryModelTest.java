package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Marc-Anthony Cross
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;

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
        inventoryModel.getInventory().addAll(new Item("Item 1", "0000000000", "$5.00"),
                new Item("Item 2", "1111111111", "$10.00"));
        String actual = inventoryModel.generateHTMLOutput();

        String expected = "<!doctype html>\n<html>\n<head>\n\t<title>Inventory Checker</title>\n</head>\n";
        expected += "<body>\n<table>\n\t<tr>\n\t\t<th>Value</th>\n\t\t<th>Serial Number</th>\n\t\t<th>Name</th>" +
                "\n\t</tr>\n";
        expected += "\t<tr>\n\t\t<td>$5.00</td>\n\t\t<td>0000000000</td>\n\t\t<td>Item 1</td>\n\t</tr>\n";
        expected += "\t<tr>\n\t\t<td>$10.00</td>\n\t\t<td>1111111111</td>\n\t\t<td>Item 2</td>\n\t</tr>\n";
        expected += "</table>\n</body>\n</html>";
        assertEquals(expected, actual);
    }

    @Test
    void writeToHTML_writes_given_output_to_html_file() {
        InventoryModel inventoryModel = new InventoryModel();
        inventoryModel.getInventory().addAll(new Item("Item 1", "0000000000", "$5.00"),
                new Item("Item 2", "1111111111", "$10.00"));

        String expected = "<!doctype html>\n<html>\n<head>\n\t<title>Inventory Checker</title>\n</head>\n";
        expected += "<body>\n<table>\n\t<tr>\n\t\t<th>Value</th>\n\t\t<th>Serial Number</th>\n\t\t<th>Name</th>" +
                "\n\t</tr>\n";
        expected += "\t<tr>\n\t\t<td>$5.00</td>\n\t\t<td>0000000000</td>\n\t\t<td>Item 1</td>\n\t</tr>\n";
        expected += "\t<tr>\n\t\t<td>$10.00</td>\n\t\t<td>1111111111</td>\n\t\t<td>Item 2</td>\n\t</tr>\n";
        expected += "</table>\n</body>\n</html>";

        File writeFile = new File("src/test/resources/HTMLWriteFile.html");
        inventoryModel.writeToHTML(writeFile);

        String actual = "";
        try {
            Scanner in = new Scanner(writeFile);
            while (in.hasNextLine()) {
                actual += in.nextLine();
                if (in.hasNextLine())
                    actual += "\n";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assertEquals(expected, actual);
    }

    @Test
    void generateTSVOutput_returns_tsv_output_in_proper_format() {
        InventoryModel inventoryModel = new InventoryModel();
        inventoryModel.getInventory().addAll(new Item("Item 1", "0000000000", "$5.00"),
                new Item("Item 2", "1111111111", "$10.00"));
        String actual = inventoryModel.generateTSVOutput();

        String expected = "Value\tSerial Number\tName\n";
        expected += "Item 1\t0000000000\t$5.00\nItem 2\t1111111111\t$10.00\n";

        assertEquals(expected, actual);
    }

    @Test
    void writeToTSV_writes_given_output_to_tsv_file() {
        InventoryModel inventoryModel = new InventoryModel();
        inventoryModel.getInventory().addAll(new Item("Item 1", "0000000000", "$5.00"),
                new Item("Item 2", "1111111111", "$10.00"));

        String expected = "Value\tSerial Number\tName\n";
        expected += "Item 1\t0000000000\t$5.00\nItem 2\t1111111111\t$10.00";

        File writeFile = new File("src/test/resources/TSVWriteFile.txt");
        inventoryModel.writeToTSV(writeFile);

        String actual = "";
        try {
            Scanner in = new Scanner(writeFile);
            while (in.hasNextLine()) {
                actual += in.nextLine();
                if (in.hasNextLine())
                    actual += "\n";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assertEquals(expected, actual);
    }

    @Test
    void loadHTML_returns_ObservableList_after_reading_html_file() {
        InventoryModel inventoryModel = new InventoryModel();
        inventoryModel.loadHTML(new File("src/test/resources/HTMLLoadFile.html"));
        ObservableList<Item> actual = inventoryModel.getInventory();
        ObservableList<Item> expected = FXCollections.observableArrayList();
        expected.add(new Item("Footlong", "0123456789", "$5.00"));
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void loadTSV_returns_ObservableList_after_reading_tsv_file() {
        InventoryModel inventoryModel = new InventoryModel();
        inventoryModel.loadTSV(new File("src/test/resources/TSVLoadFile.txt"));
        ObservableList<Item> actual = inventoryModel.getInventory();
        ObservableList<Item> expected = FXCollections.observableArrayList();
        expected.add(new Item("Playstation 5 Digital", "1234567890", "$399.99"));
        assertEquals(expected.toString(), actual.toString());
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