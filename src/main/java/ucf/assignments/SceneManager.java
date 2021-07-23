package ucf.assignments;

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

        InventoryManagerController inventoryManagerController = new InventoryManagerController(inventoryModel,
                this);
        SearchManagerController searchManagerController = new SearchManagerController(inventoryModel,
                this);

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
    }

    public Scene getScene(String name) {
        return scenes.get(name);
    }
}
