package GameApp.client.views.MyAccountView;

import GameApp.client.core.ViewHandler;
import GameApp.client.core.ViewModelFactory;
import GameApp.client.views.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * A view controller class.
 *
 * @author Adrian Bugiel, Andreea Asimine, Kevin Kluka, Saran Singh
 * @version 1.0
 */
public class MyAccountViewController implements ViewController {

    private MyAccountViewModel myAccountViewModel;
    private ViewHandler vha;

    @FXML
    private TextField confirmPasswordField;
    @FXML
    private TextField userNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField countryField;
    @FXML
    private Label errorLabel;

    /**
     * Initialization.
     *
     * @param vh  view handler
     * @param vmf view model factory
     */
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.vha = vh;
        this.myAccountViewModel = vmf.getMyAccountViewModel();


        userNameField.textProperty().bindBidirectional(myAccountViewModel.userNameProperty());
        emailField.textProperty().bindBidirectional(myAccountViewModel.emailProperty());
        passwordField.textProperty().bindBidirectional(myAccountViewModel.passwordProperty());
        addressField.textProperty().bindBidirectional(myAccountViewModel.addressProperty());
        countryField.textProperty().bindBidirectional(myAccountViewModel.countryProperty());
    }


    public void storeClicked(MouseEvent mouseEvent) {
        errorLabel.setText("");
        vha.openMainShopView();
    }


    public void saveChanges(MouseEvent mouseEvent) {

        if (!confirmPasswordField.getText().equals("")) {

            if (confirmPasswordField.getText().equals(passwordField.getText())) {
                myAccountViewModel.updateUserAccount();
                errorLabel.setText("Account Updated!");
            } else {
                errorLabel.setText("Passwords does not match!");
            }
        } else {
            errorLabel.setText("Fill confirmation Password!");
        }
    }

    public void openShoppingCart(MouseEvent mouseEvent) {
        errorLabel.setText("");
        vha.openShopCartView();
    }

    public void libraryView(MouseEvent mouseEvent) {
        errorLabel.setText("");
        vha.openMyLibraryView();
    }

    public void openLoginView(MouseEvent mouseEvent) {
        errorLabel.setText("");
        vha.openLoginView();
    }

    public void updateMyAccountView(MouseEvent mouseEvent) {
        errorLabel.setText("");
        vha.openMyAccountView();
    }

    public void clearLabel(MouseEvent mouseEvent) {
        errorLabel.setText("");
    }

    public void transactionHistoryView(MouseEvent mouseEvent)
    {
        vha.openUserTransactionHistoryView();
    }
}