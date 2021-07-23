package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class InventoryModel {

    private ObservableList<Item> inventory;
    private ObservableList<Item> foundInventory;

    public InventoryModel() {
        inventory = FXCollections.observableArrayList();
        foundInventory = FXCollections.observableArrayList();
    }

    public InventoryModel(ObservableList<Item> itemInventory, ObservableList<Item> searchedInventory) {
        this.inventory = itemInventory;
        this.foundInventory = searchedInventory;
    }

    public ObservableList<Item> getInventory() {
        return inventory;
    }

    public ObservableList<Item> getFoundInventory() {
        return foundInventory;
    }

    public String generateHTMLOutput() {
        return "";
    }

    public void writeToHTML(File file) {
        String output = generateHTMLOutput();
        try {
            FileWriter writer = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String generateTSVOutput() {
        String output = "Item Name\tSerial Number\tItem Price\n";
        for (int i = 0; i < inventory.size(); i++) {
            String curName = inventory.get(i).getName();
            String curSerial = inventory.get(i).getSerial();
            String curPrice = inventory.get(i).getPrice();
            output += curName + "\t" + curSerial + "\t" + curPrice + "\n";
        }
        return output;
    }

    public void writeToTSV(File file) {
        String output = generateTSVOutput();
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(output);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList loadHTML(File file) {
        return FXCollections.observableArrayList();
    }

    public ObservableList loadTSV(File file) {
        return FXCollections.observableArrayList();
    }

    public void addItemToInventory(Item item) {
        // Add passed in item to the ObservableList
        inventory.add(item);
    }

    public void removeItemFromInventory(Item selectedItem) {
        inventory.remove(selectedItem);
    }

    public void searchByName(String search) {
        for (int i = 0; i < inventory.size(); i++) {
            Item curItem = inventory.get(i);
            System.out.println(curItem.getName());
            if (curItem.getName().toLowerCase().equals(search) ||
                    curItem.getName().toLowerCase().startsWith(search)) {
                foundInventory.add(curItem);
            }
            System.out.println(foundInventory.size());
        }
    }

    public void searchBySerial(String search) {
        for (int i = 0; i < inventory.size(); i++) {
            Item curItem = inventory.get(i);
            System.out.println(curItem.getName());
            if (curItem.getSerial().toLowerCase().equals(search) ||
                    curItem.getSerial().toLowerCase().startsWith(search)) {
                foundInventory.add(curItem);
            }
            System.out.println(foundInventory.size());
        }
    }

    public void loadInventoryFromFile(File file) {
        if (inventory.size() > 0)
            inventory.clear();

        if (file.getName().endsWith("txt")) {
            try {
                Scanner in = new Scanner(file);
                // Read first line so it's not inputted as an inventory item
                in.nextLine();
                while (in.hasNextLine()) {
                    String[] properties = in.nextLine().split("\t");
                    inventory.add(new Item(properties[0], properties[1], properties[2]));
                }
                return;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (file.getName().endsWith("html")) {

        }
    }
}
