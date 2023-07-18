package GameApp.client.views.RegisterView;

import GameApp.client.core.ViewHandler;
import GameApp.client.core.ViewModelFactory;
import GameApp.client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

/**
 * A view controller class.
 *
 * @author Adrian Bugiel, Andreea Asimine, Kevin Kluka, Saran Singh
 * @version 1.0
 */
public class RegisterViewController implements ViewController {
    private RegisterViewModel registerViewModel;
    private ViewHandler vh;
    @FXML
    private TextField emailField, confirmEmailField, countryField, usernameField, addressField, passwordField, confirmPasswordField;
    @FXML
    private Label errorLabel;
    @FXML
    private CheckBox notRobotCheckBox, yearsCheckBox, adminCheckBox;

    /**
     * Initialization.
     *
     * @param vh  view handler
     * @param vmf view model factory
     */
    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.vh = vh;
        this.registerViewModel = vmf.getRegisterViewModel();

        emailField.textProperty().bindBidirectional(registerViewModel.emailProperty());
        confirmEmailField.textProperty().bindBidirectional(registerViewModel.confirmEmailProperty());
        countryField.textProperty().bindBidirectional(registerViewModel.countryProperty());
        addressField.textProperty().bindBidirectional(registerViewModel.addressProperty());
        usernameField.textProperty().bindBidirectional(registerViewModel.usernameProperty());
        passwordField.textProperty().bindBidirectional(registerViewModel.passwordProperty());
        confirmPasswordField.textProperty().bindBidirectional(registerViewModel.confirmPasswordProperty());
    }

    @FXML
    private void backToLogin() {
        vh.openLoginView();
        emailField.clear();
        confirmEmailField.clear();
        countryField.clear();
        addressField.clear();
        usernameField.clear();
        passwordField.clear();
        confirmPasswordField.clear();
        notRobotCheckBox.selectedProperty().setValue(false);
        yearsCheckBox.selectedProperty().setValue(false);
        errorLabel.textProperty().set("");
        adminCheckBox.selectedProperty().setValue(false);
    }

    private void isAdmin() {
        registerViewModel.setIsAdmin(adminCheckBox.isSelected());
    }

    @FXML
    private void saveInfo() throws SQLException {
        if (notRobotCheckBox.isSelected() && yearsCheckBox.isSelected()) {
            isAdmin();
            errorLabel.textProperty().bindBidirectional(registerViewModel.errorProperty());
            registerViewModel.addUser(vh);
        } else {
            errorLabel.textProperty().setValue(("CheckBoxes ERROR!"));
        }
    }

    public void resetLabel(ActionEvent actionEvent) {
        errorLabel.setText("");
    }
}
