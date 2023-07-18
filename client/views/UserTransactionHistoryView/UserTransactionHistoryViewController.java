package GameApp.client.views.UserTransactionHistoryView;

import GameApp.client.core.ViewHandler;
import GameApp.client.core.ViewModelFactory;
import GameApp.client.views.ViewController;
import GameApp.shared.model.Transaction;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.Date;

/**
 * A view controller class.
 *
 * @author Adrian Bugiel, Andreea Asimine, Kevin Kluka, Saran Singh
 * @version 1.0
 */
public class UserTransactionHistoryViewController implements ViewController {

    @FXML
    private Label errorLabel;
    @FXML
    private TextField searchField;
    @FXML
    private TableView transactionTable;
    @FXML
    private TableColumn idColumn;
    @FXML
    private TableColumn emailColumn;
    @FXML
    private TableColumn totalColumn;
    @FXML
    private TableColumn dateColumn;
    @FXML
    private TableColumn detailsColumn;

    private ViewHandler viewHandler;
    private UserTransactionHistoryViewModel userTransactionHistoryViewModel;

    /**
     * Initialization.
     *
     * @param vh  view handler
     * @param vmf view model factory
     */
    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        viewHandler = vh;
        userTransactionHistoryViewModel = vmf.getUserTransactionHistoryViewModel();

        setTable();
        transactionTable.itemsProperty().bind(userTransactionHistoryViewModel.observableListProperty());
    }

    public void setTable()
    {
        idColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("TransactionId"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("UsersEmail"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("TotalTransactionPrice"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Transaction, Date>("DateOfPurchase"));
        detailsColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("TransactionDetails"));
    }


    public void backClicked(MouseEvent mouseEvent) {
        viewHandler.openMyAccountView();
    }

    public void searchTransactionId(MouseEvent mouseEvent) {
        userTransactionHistoryViewModel.searchId(searchField.getText(), errorLabel);
    }

    public void showAll(MouseEvent mouseEvent) {
        errorLabel.setText("");
        searchField.setText("");
        userTransactionHistoryViewModel.showAll();
    }

    public void logOut(MouseEvent mouseEvent) {
        viewHandler.openLoginView();
    }

    public void resetLabel(MouseEvent mouseEvent) {
        errorLabel.setText("");
    }
}
