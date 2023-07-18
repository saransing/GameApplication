package GameApp.client.views.ShoppingCartView;

import GameApp.client.core.ViewHandler;
import GameApp.client.core.ViewModelFactory;
import GameApp.client.views.ViewController;
import GameApp.shared.model.Game;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ShoppingCartViewController implements ViewController {

    private ShoppingCartViewModel shoppingCartViewModel;
    private ViewHandler vh;

    @FXML
    private TableView<Game> shoppingCartTable;
    @FXML
    TableColumn<Game, String> title, description, price;

    /**
     * Initialization.
     *
     * @param vh  view handler
     * @param vmf view model factory
     */
    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.vh = vh;
        this.shoppingCartViewModel = vmf.getShoppingCartViewModel();

        setTable();
        shoppingCartTable.itemsProperty().bind(shoppingCartViewModel.observableListProperty());
    }


    public void setTable() {

        title.setCellValueFactory(new PropertyValueFactory<>("GameTitle"));
        description.setCellValueFactory(new PropertyValueFactory<>("GameDescription"));
        price.setCellValueFactory(new PropertyValueFactory<>("GamePrice"));

    }

    public void removeGame(MouseEvent mouseEvent) {
        shoppingCartTable.itemsProperty().unbind();
        shoppingCartViewModel.removeGame(shoppingCartTable.getSelectionModel().getSelectedItem());
        shoppingCartTable.itemsProperty().bind(shoppingCartViewModel.observableListProperty());
    }

    public void openPayment(MouseEvent event) {
        vh.openPaymentView();
    }

    public void openShoppingCart(MouseEvent mouseEvent) {
        vh.openShopCartView();
    }

    public void openMyAccountView(MouseEvent mouseEvent) {
        vh.openMyAccountView();
    }

    public void openStoreView(MouseEvent mouseEvent) {
        vh.openMainShopView();
    }

    public void openLibraryView(MouseEvent mouseEvent) {
        vh.openMyLibraryView();
    }

    public void openLogInView(MouseEvent mouseEvent) {
        vh.openLoginView();
    }
}