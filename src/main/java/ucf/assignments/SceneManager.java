package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Marc-Anthony Cross
 */

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    private Map<String, Scene> scenes = new HashMap();

    public void load() {
        InventoryModel inventoryModel = new InventoryModel();

        SearchManagerController searchManagerController = new SearchManagerController(inventoryModel,
                this);
        EditInventoryManagerController editInventoryManagerController = new
                EditInventoryManagerController(inventoryModel, this);
        InventoryManagerController inventoryManagerController = new InventoryManagerController(inventoryModel,
                this, editInventoryManagerController);

        Parent root;

        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("InventoryManager.fxml"));
        mainLoader.setController(inventoryManagerController);

        try {
            root = mainLoader.load();
            scenes.put("InventoryManager", new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }

        FXMLLoader searchLoader = new FXMLLoader(getClass().getResource("SearchManager.fxml"));
        searchLoader.setController(searchManagerController);

        try {
            root = searchLoader.load();
            scenes.put("SearchManager", new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }

        FXMLLoader editLoader = new FXMLLoader(getClass().getResource("EditInventoryManager.fxml"));
        editLoader.setController(editInventoryManagerController);

        try {
            root = editLoader.load();
            scenes.put("EditInventoryManager", new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Scene getScene(String name) {
        return scenes.get(name);
    }
}
