package GameApp.client.views.AdminMyAccountView;

import GameApp.client.core.ViewHandler;
import GameApp.client.core.ViewModelFactory;
import GameApp.client.views.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.beans.PropertyChangeEvent;

/**
 * A view controller class.
 *
 * @author Adrian Bugiel, Andreea Asimine, Kevin Kluka, Saran Singh
 * @version 1.0
 */
public class AdminMyAccountViewController implements ViewController {
    @FXML
    private TextField userNameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField countryField;
    @FXML
    private Label errorLabel;

    private ViewHandler viewHandler;
    private AdminMyAccountViewModel adminMyAccountViewModel;

    /**
     * Initialization.
     *
     * @param vh  view handler
     * @param vmf view model factory
     */
    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        viewHandler = vh;
        adminMyAccountViewModel = vmf.getAdminMyAccountViewModel();
        adminMyAccountViewModel.addListener("setFields", this::setFields);
    }

    /**
     * Sets field
     *
     * @param event PropertyChangeEvent
     */
    private void setFields(PropertyChangeEvent event) {
        adminMyAccountViewModel.setFields(emailField, userNameField, addressField, countryField);
    }

    /**
     * Opens new view.
     *
     * @param mouseEvent Mouse event
     */
    public void openLoginView(MouseEvent mouseEvent) {
        viewHandler.openLoginView();
    }

    /**
     * Saves changes.
     *
     * @param mouseEvent Mouse event
     */
    public void saveChanges(MouseEvent mouseEvent) {
        resetLabel();
        adminMyAccountViewModel.changeDetail(userNameField, passwordField,
                confirmPasswordField, addressField, countryField, errorLabel);
    }

    /**
     * Opens new view.
     *
     * @param mouseEvent Mouse event
     */
    public void openStoreView(MouseEvent mouseEvent) {
        resetLabel();
        viewHandler.openAdminMainShopView();
    }

    /**
     * Resets label.
     */
    public void resetLabel() {
        errorLabel.setText("");
    }
}

