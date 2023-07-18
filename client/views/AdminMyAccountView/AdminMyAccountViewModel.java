package GameApp.client.views.AdminMyAccountView;

import GameApp.client.model.ClientModelManagerFactory;
import GameApp.shared.model.User;
import GameApp.shared.util.Subject;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * A model view class.
 *
 * @author Adrian Bugiel, Andreea Asimine, Kevin Kluka, Saran Singh
 * @version 1.0
 */
public class AdminMyAccountViewModel implements Subject {

    private ClientModelManagerFactory clientModelManagerFactory;
    private PropertyChangeSupport support;

    /**
     * 1 argument constructor.
     * @param clientModelManagerFactory client's model manager factory
     */
    public AdminMyAccountViewModel(ClientModelManagerFactory clientModelManagerFactory) {
        this.clientModelManagerFactory = clientModelManagerFactory;

        support = new PropertyChangeSupport(this);

        clientModelManagerFactory.addListener("UserLoggedIn", this::setUserFields);
    }

    /**
     * Sets user fields
     * @param event PropertyChangeEvent
     */
    private void setUserFields(PropertyChangeEvent event) {
        support.firePropertyChange("setFields", null, 1);
    }

    public void setFields(TextField emailField, TextField userNameField,
                          TextField addressField, TextField countryField) {

        User user = clientModelManagerFactory.getUser();

        emailField.setPromptText(user.getEmail());
        userNameField.setText(user.getUsername());
        addressField.setText(user.getAddress());
        countryField.setText(user.getCountry());
    }

    public void changeDetail(TextField userNameField, TextField passwordField, TextField confirmPasswordField,
                             TextField addressField, TextField countryField, Label errorLabel) {


        if (passwordField.getText().equals(confirmPasswordField.getText())) {

            if (userNameField.getText().equals("") || addressField.getText().equals("") ||
                    countryField.getText().equals("")) {

                errorLabel.setText("Fields cannot be Empty!");

            } else {
                User user = clientModelManagerFactory.getUser();

                if (passwordField.getText().equals("")) {

                    clientModelManagerFactory.userEdit(new User(user.getEmail(), countryField.getText(), addressField.getText(),
                            userNameField.getText(), user.getPassword(), user.getIsAdmin()));

                    errorLabel.setText("Account Information updated!");

                } else {


                    clientModelManagerFactory.userEdit(new User(user.getEmail(), countryField.getText(), addressField.getText(),
                            userNameField.getText(), passwordField.getText(), user.getIsAdmin()));

                    errorLabel.setText("Account and Password updated!");
                }
            }
        } else {
            errorLabel.setText("Passwords do not match!");
        }
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }
}
