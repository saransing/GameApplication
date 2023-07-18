package GameApp.client.core;

import GameApp.client.views.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * A Class that creates new Scene objects.
 *
 * @author Adrian Bugiel, Andreea Asimine, Kevin Kluka, Saran Singh
 * @version 1.0
 */
public class ViewFactory {
    private static Map<String, Scene> scenes;
    private static Stage stage;

    static {
        scenes = new HashMap<>();
    }

    /**
     * Initialization of all the scenes.
     *
     * @param theStage Stage object
     */
    public static void init(Stage theStage) {
        stage = theStage;
        createScene("LoginView");
        createScene("RegisterView");
        createScene("MainShopView");
        createScene("MyAccountView");
        createScene("GameView");
        createScene("MyLibraryView");
        createScene("AdminMainShopView");
        createScene("AdminUserListView");
        createScene("ShoppingCartView");
        createScene("PaymentView");
        createScene("AdminTransactionsHistoryView");
        createScene("AdminAddGameView");
        createScene("AdminMyAccountView");
        createScene("UserTransactionHistoryView");
    }

    /**
     * Creates a scene through scene name.
     * @param sceneName scene name
     */
    private static void createScene(String sceneName) {
        Scene scene = null;

        switch (sceneName) {
            case "LoginView":
                try {
                    //change path here
                    Parent root = loadFXML("../views/LoginView/LoginView.fxml");

                    //change title
                    scene = new Scene(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "RegisterView":
                try {
                    Parent root = loadFXML("../views/RegisterView/RegisterView.fxml");

                    scene = new Scene(root);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "MainShopView":
                try {
                    Parent root = loadFXML("../views/MainShopView/MainShopView.fxml");

                    scene = new Scene(root);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "MyAccountView":
                try {
                    Parent root = loadFXML("../views/MyAccountView/MyAccountView.fxml");

                    scene = new Scene(root);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "MyLibraryView":
                try {
                    Parent root = loadFXML("../views/MyLibraryView/MyLibraryView.fxml");

                    scene = new Scene(root);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "GameView":
                try {
                    Parent root = loadFXML("../views/GameView/GameView.fxml");

                    scene = new Scene(root);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "AdminMainShopView":
                try {
                    Parent root = loadFXML("../views/AdminMainShopView/AdminMainShopWindow.fxml");

                    scene = new Scene(root);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "AdminUserListView":
                try {
                    Parent root = loadFXML("../views/AdminUserListView/AdminUserListView.fxml");

                    scene = new Scene(root);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "ShoppingCartView":
                try {
                    Parent root = loadFXML("../views/ShoppingCartView/ShoppingCartView.fxml");

                    scene = new Scene(root);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "PaymentView":
                try {
                    Parent root = loadFXML("../views/PaymentView/PaymentView.fxml");

                    scene = new Scene(root);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "AdminTransactionsHistoryView":
                try {
                    Parent root = loadFXML("../views/AdminTransactionHistoryView/AdminTransactionHistoryView.fxml");

                    scene = new Scene(root);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "AdminAddGameView":
                try {
                    Parent root = loadFXML("../views/AdminAddGameView/AdminAddGameView.fxml");

                    scene = new Scene(root);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "AdminMyAccountView":
                try {
                    Parent root = loadFXML("../views/AdminMyAccountView/AdminMyAccountView.fxml");

                    scene = new Scene(root);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "UserTransactionHistoryView":
                try {
                    Parent root = loadFXML("../views/UserTransactionHistoryView/UserTransactionHistoryView.fxml");

                    scene = new Scene(root);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
        scenes.put(sceneName, scene);
    }

    /**
     * Loads views from url.
     *
     * @param path
     * @return Parent object
     * @throws IOException failed or interrupted I/O operations
     */
    private static Parent loadFXML(String path) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ViewFactory.class.getResource(path));
        Parent root = loader.load();

        ViewController ctrl = loader.getController();
        ctrl.init(ViewHandler.getInstance(), ViewModelFactory.getInstance());
        return root;
    }

    /**
     * Gets scene.
     *
     * @param sceneName scene's name
     * @return Scene object
     */
    public static Scene getScene(String sceneName) {
        return scenes.get(sceneName);
    }
}
