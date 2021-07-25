package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Marc-Anthony Cross
 */

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
    private Item curSelected;
    private String newName;
    private String newSerial;
    private String newPrice;

    public Item getCurSelected() {
        return curSelected;
    }

    public void setCurSelected(Item curSelected) {
        this.curSelected = curSelected;
    }

    public String getNewName() {
        return newName;
    }

    public String getNewSerial() {
        return newSerial;
    }

    public String getNewPrice() {
        return newPrice;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public void setNewSerial(String newSerial) {
        this.newSerial = newSerial;
    }

    public void setNewPrice(String newPrice) {
        this.newPrice = newPrice;
    }

    public InventoryModel() {
        inventory = FXCollections.observableArrayList();
        foundInventory = FXCollections.observableArrayList();

        newName = "";
        newSerial = "";
        newPrice = "";
    }

    public InventoryModel(ObservableList<Item> itemInventory, ObservableList<Item> searchedInventory) {
        this.inventory = itemInventory;
        this.foundInventory = searchedInventory;

        this.newName = "";
        this.newPrice = "";
        this.newSerial = "";
    }

    public ObservableList<Item> getInventory() {
        return inventory;
    }

    public ObservableList<Item> getFoundInventory() {
        return foundInventory;
    }

    public String generateHTMLOutput() {
        String output = "<!doctype html>\n<html>\n<head>\n\t<title>Inventory Checker</title>\n</head>\n";
        output += "<body>\n<table>\n\t<tr>\n\t\t<th>Value</th>\n\t\t<th>Serial Number</th>\n\t\t<th>Name</th>" +
                "\n\t</tr>\n";

        for (int i = 0; i < inventory.size(); i++) {
            output += "\t<tr>\n";
            output += "\t\t<td>" + inventory.get(i).getPrice() + "</td>\n";
            output += "\t\t<td>" + inventory.get(i).getSerial() + "</td>\n";
            output += "\t\t<td>" + inventory.get(i).getName() + "</td>\n";
            output += "\t</tr>\n";
        }

        output += "</table>\n</body>\n</html>";

        return output;
    }

    public void writeToHTML(File file) {
        String output = generateHTMLOutput();
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(output);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String generateTSVOutput() {
        String output = "Value\tSerial Number\tName\n";
        for (int i = 0; i < inventory.size(); i++) {
            String curPrice = inventory.get(i).getPrice();
            String curSerial = inventory.get(i).getSerial();
            String curName = inventory.get(i).getName();
            output += curName + "\t" + curSerial + "\t" + curPrice + "\n";
        }
        return output;
    }

    public void writeToTSV(File file) {
        // Get output from generate function and write to file given by filechooser
        String output = generateTSVOutput();
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(output);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadHTML(File file) {
        try {
            Scanner in = new Scanner(file);
            // Read first 10 so it's not inputted as an inventory item
            for (int i = 0; i < 12; i++)
                in.nextLine();

            // Split line into individual properties with tab and assign the new item to inventory
            while (in.hasNextLine()) {
                // Read out <tr>, return if tag is </table> (marking bottom of html file)
                String tag = in.nextLine();
                if (tag.equals("</table>"))
                    return;

                // Grab input from html file and get rid of the tags so only the properties are obtained
                String readPrice = in.nextLine();
                readPrice = readPrice.replace("\t\t<td>", "");
                readPrice = readPrice.replace("</td>", "");

                String readSerial = in.nextLine();
                readSerial = readSerial.replace("\t\t<td>", "");
                readSerial = readSerial.replace("</td>", "");

                String readName = in.nextLine();
                readName = readName.replace("\t\t<td>", "");
                readName = readName.replace("</td>", "");

                inventory.add(new Item(readName, readSerial, readPrice));

                // Read out </tr>
                in.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadTSV(File file) {
        try {
            Scanner in = new Scanner(file);
            // Read first line so it's not inputted as an inventory item
            in.nextLine();
            // Split line into individual properties with tab and assign the new item to inventory
            while (in.hasNextLine()) {
                String[] properties = in.nextLine().split("\t");
                inventory.add(new Item(properties[0], properties[1], properties[2]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addItemToInventory(Item item) {
        // Add passed in item to the ObservableList
        inventory.add(item);
    }

    public void editItem(Item item) {
        // Set item's properties to the properties obtained from the TextFields in the edit window
        item.setName(newName);
        item.setSerial(newSerial.toUpperCase());
        item.setPrice(newPrice);
    }

    public void removeItemFromInventory(Item selectedItem) {
        inventory.remove(selectedItem);
    }

    public void searchByName(String search) {
        // Add item to foundInventory if it matches the search string
        for (int i = 0; i < inventory.size(); i++) {
            Item curItem = inventory.get(i);
            if (curItem.getName().toLowerCase().contains(search)) {
                foundInventory.add(curItem);
            }
        }
    }

    public void searchBySerial(String search) {
        // Add item to foundInventory if it matches the search string
        for (int i = 0; i < inventory.size(); i++) {
            Item curItem = inventory.get(i);
            if (curItem.getSerial().toLowerCase().contains(search)){
                foundInventory.add(curItem);
            }
        }
    }

    public void loadInventoryFromFile(File file) {
        if (inventory.size() > 0)
            inventory.clear();

        if (file.getName().endsWith("txt")) {
            loadTSV(file);
            return;
        } else if (file.getName().endsWith("html")) {
            loadHTML(file);
            return;
        }
    }
}
