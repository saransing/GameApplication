package GameApp.client.views.MyLibraryView;

import GameApp.client.core.ViewHandler;
import GameApp.client.core.ViewModelFactory;
import GameApp.client.views.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * A view controller class.
 *
 * @author Adrian Bugiel, Andreea Asimine, Kevin Kluka, Saran Singh
 * @version 1.0
 */
public class MyLibraryViewController implements ViewController {

    private MyLibraryViewModel myLibraryViewModel;
    private ViewHandler vha;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane gridPane;
    @FXML
    private TextField searchField;

    /**
     * Initialization.
     *
     * @param vh  view handler
     * @param vmf view model factory
     */
    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {

        this.vha = vh;
        this.myLibraryViewModel = vmf.getMyLibraryViewModel();

        scrollPane.setFitToHeight(true);

        myLibraryViewModel.addListener("Refresh", this::needUpdate);
    }

    private void needUpdate(PropertyChangeEvent event) {
        update();
    }

    public void update() {
        try {
            myLibraryViewModel.insertGame(gridPane);
        } catch (SQLException | RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateLibrary(MouseEvent actionEvent) {
        update();
    }

    public void openShoppingCart(MouseEvent mouseEvent) {
        vha.openShopCartView();
        update();
    }

    public void openMyAccountView(MouseEvent mouseEvent) {
        vha.openMyAccountView();
        update();
    }

    public void openStoreView(MouseEvent mouseEvent) {
        vha.openMainShopView();
        update();
    }

    public void searchGame(MouseEvent mouseEvent) {
        myLibraryViewModel.searchGames(gridPane, searchField.getText());
        searchField.setText("");
    }

    public void logOut(MouseEvent mouseEvent) {
        vha.openLoginView();
        update();
    }
}
