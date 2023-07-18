package GameApp.client.views.AdminMainShopView;

import GameApp.client.core.ViewHandler;
import GameApp.client.core.ViewModelFactory;
import GameApp.client.views.ViewController;
import GameApp.shared.model.Game;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * A view controller class.
 *
 * @author Adrian Bugiel, Andreea Asimine, Kevin Kluka, Saran Singh
 * @version 1.0
 */
public class AdminMainShopViewController implements ViewController {

    private AdminMainShopViewModel adminMainShopViewModel;
    private ViewHandler vh;


    public TextField searchField;

    public TableView tableView;

    public TableColumn idColumn;
    public TableColumn titleColumn;
    public TableColumn priceColumn;
    public TableColumn genreColumn;
    public TableColumn descriptionColumn;

    /**
     * Initialization.
     *
     * @param vh  view handler
     * @param vmf view model factory
     */
    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.vh = vh;
        this.adminMainShopViewModel = vmf.getAdminMainShopViewModel();

        setTable();

        tableView.itemsProperty().bind(adminMainShopViewModel.observableListProperty());
    }

    /**
     * Sets the table in the view
     */
    public void setTable()
    {
        idColumn.setCellValueFactory(new PropertyValueFactory<Game, String>("GameId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Game, String>("GameTitle"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Game, Double>("GamePrice"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<Game, String>("GameGenre"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Game, String>("GameDescription"));
    }

    /**
     * Opens add game view.
     *
     * @param mouseEvent Mouse event
     */
    public void openAddGameView(MouseEvent mouseEvent) {
        vh.openAdminAddGameView();
    }

    /**
     * Opens log in view.
     *
     * @param mouseEvent Mouse event
     */
    public void openLogInView(MouseEvent mouseEvent) {
        vh.openLoginView();
    }

    /**
     * Opens game view.
     *
     * @param mouseEvent Mouse event
     */
    public void openGamesView(MouseEvent mouseEvent) {
        vh.openAdminMainShopView();
    }

    /**
     * Opens account view.
     *
     * @param mouseEvent Mouse event
     */
    public void openAccountsView(MouseEvent mouseEvent) {
        vh.openAdminUserListView();
    }

    /**
     * Opens transaction view.
     *
     * @param mouseEvent Mouse event
     */
    public void openTransactionView(MouseEvent mouseEvent) {
        vh.openAdminTransactionsHistoryView();
    }

    /**
     * Opens admin view.
     *
     * @param mouseEvent Mouse event
     */
    public void openAdminAccountView(MouseEvent mouseEvent) {
        vh.openAdminMyAccountView();
    }

    /**
     * Searches for the game.
     *
     * @param mouseEvent Mouse event
     */
    public void searchGames(MouseEvent mouseEvent) {
        if (searchField.getText().equals(""))
        {
            adminMainShopViewModel.updateObservableList();
        }
        else
        {
            adminMainShopViewModel.searchForGame(searchField.getText());
        }
        resetField();
    }

    /**
     * Resets field
     */
    private void resetField() {
        searchField.setText("");
    }
}
