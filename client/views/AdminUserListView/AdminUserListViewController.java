package GameApp.client.views.AdminUserListView;

import GameApp.client.core.ViewHandler;
import GameApp.client.core.ViewModelFactory;
import GameApp.client.views.ViewController;
import GameApp.shared.model.User;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.List;

/**
 * A view controller class.
 *
 * @author Adrian Bugiel, Andreea Asimine, Kevin Kluka, Saran Singh
 * @version 1.0
 */
public class AdminUserListViewController implements ViewController {

    @FXML
    private Label errorLabel;
    private AdminUserListViewModel adminUserListViewModel;
    private ViewHandler viewHandler;
    @FXML
    private TableView<User> table;
    @FXML
    TableColumn<User, String> username, email, address, country;
    private ObservableList<User> data;
    @FXML
    TextField searchUserByEmailTextField;

    /**
     * Initialization.
     *
     * @param vh  view handler
     * @param vmf view model factory
     */
    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.viewHandler = vh;
        this.adminUserListViewModel = vmf.getAdminUserListViewModel();
        username.cellFactoryProperty().bindBidirectional((Property) adminUserListViewModel.usernameProperty());
        email.cellFactoryProperty().bindBidirectional((Property) adminUserListViewModel.emailProperty());
        address.cellFactoryProperty().bindBidirectional((Property) adminUserListViewModel.addressProperty());
        country.cellFactoryProperty().bindBidirectional((Property) adminUserListViewModel.countryProperty());
        searchUserByEmailTextField.textProperty().bindBidirectional(adminUserListViewModel.emailProperty());
        setTable();
    }

    public void setTable() {
        resetLabel();

        List<User> userList = adminUserListViewModel.getAllUsers();
        data = FXCollections.observableArrayList(userList);

        username = new TableColumn<>("Username");
        username.setCellValueFactory(new PropertyValueFactory<>("username"));

        email = new TableColumn<>("Email");
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

        address = new TableColumn<>("Address");
        address.setCellValueFactory(new PropertyValueFactory<>("address"));

        country = new TableColumn<>("Country");
        country.setCellValueFactory(new PropertyValueFactory<>("country"));

        table.getColumns().setAll(username, email, address, country);
        table.setItems(data);
    }

    public void findUserByEmail() {
        resetLabel();

        if (adminUserListViewModel.findUserByEmail(searchUserByEmailTextField.getText()) != null) {

            User user = adminUserListViewModel.findUserByEmail(searchUserByEmailTextField.getText());
            data = FXCollections.observableArrayList(user);

            username = new TableColumn<>("Username");
            username.setCellValueFactory(new PropertyValueFactory<>("username"));

            email = new TableColumn<>("Email");
            email.setCellValueFactory(new PropertyValueFactory<>("email"));

            address = new TableColumn<>("Address");
            address.setCellValueFactory(new PropertyValueFactory<>("address"));

            country = new TableColumn<>("Country");
            country.setCellValueFactory(new PropertyValueFactory<>("country"));

            table.getColumns().setAll(username, email, address, country);
            table.setItems(data);

        } else errorLabel.setText("No user found!");
    }

    public void deleteUser() {
        resetLabel();
        adminUserListViewModel.deleteUser(table.getSelectionModel().getSelectedItem());
    }

    public void storeClicked(MouseEvent mouseEvent) {
        resetLabel();
        viewHandler.openAdminMainShopView();
    }

    public void openLogInView(MouseEvent mouseEvent) {
        viewHandler.openLoginView();
    }

    private void resetLabel() {
        errorLabel.setText("");
    }
}
