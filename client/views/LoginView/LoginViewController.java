package GameApp.client.views.LoginView;

import GameApp.client.core.ViewHandler;
import GameApp.client.core.ViewModelFactory;
import GameApp.client.views.ViewController;
import GameApp.shared.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.Objects;

/**
 * A view controller class.
 *
 * @author Andreea Asimine, Kevin Kluka, Saran Singh, Lucija Domljan
 * @version 1.0
 */
public class LoginViewController implements ViewController {

    private LoginViewModel loginViewModel;
    private ViewHandler vh;

    @FXML
    private TextField emailField;
    @FXML
    private Label errorLabel;
    @FXML
    private PasswordField passwordField;

    /**
     * Initialization.
     *
     * @param vh  view handler
     * @param vmf view model factory
     */
    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.vh = vh;
        this.loginViewModel = vmf.getLoginViewModel();
        emailField.textProperty().bindBidirectional(loginViewModel.getEmail());
        passwordField.textProperty().bindBidirectional(loginViewModel.getPassword());

    }

    @FXML
    private void register() {
        vh.openRegisterView();
    }


    @FXML
    private void signIn(ActionEvent actionEvent) {

        if (emailField.getText() == null && passwordField.getText() == null || Objects.equals(emailField.getText(), "") && Objects.equals(passwordField.getText(), "")) {
            emailField.setStyle("-fx-border-color: red; -fx-border-width:2px; -fx-border-radius: 3px;");
            passwordField.setStyle("-fx-border-color: red; -fx-border-width:2px; -fx-border-radius: 3px;");
            errorLabel.textProperty().set("The username and the password cannot be empty!");
        } else if (passwordField.getText() == null && emailField.getText() != null || !Objects.equals(emailField.getText(), "") && Objects.equals(passwordField.getText(), "")) {
            passwordField.setStyle("-fx-border-color: red; -fx-border-width:2px;-fx-border-radius: 3px;");
            errorLabel.textProperty().set("The password cannot be empty!");
            emailField.setStyle(null);

        } else if (passwordField.getText() != null && emailField.getText() == null || Objects.equals(emailField.getText(), "") && !Objects.equals(passwordField.getText(), "")) {
            emailField.setStyle("-fx-border-color: red; -fx-border-width:2px; -fx-border-radius: 3px;");
            errorLabel.textProperty().set("The username cannot be empty!");
            passwordField.setStyle(null);

        } else {
            errorLabel.textProperty().set("");
            emailField.setStyle(null);
            passwordField.setStyle(null);
            if (loginViewModel.login()) {
                User user = loginViewModel.setLoggedUser();
                if (user.getIsAdmin()) vh.openAdminMainShopView();
                else
                    vh.openMainShopView();
            } else {

                errorLabel.setText("Incorrect email or password!");
            }
        }
    }

    public void resetTextField(MouseEvent mouseEvent) {
        emailField.setStyle(null);
        passwordField.setStyle(null);
        errorLabel.setText("");
    }
}


