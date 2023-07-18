package GameApp.client.views.RegisterView;

import GameApp.client.core.ViewHandler;
import GameApp.client.model.ClientModelManagerFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.SQLException;

/**
 * A model view class.
 *
 * @author Adrian Bugiel, Andreea Asimine, Kevin Kluka, Saran Singh
 * @version 1.0
 */
public class RegisterViewModel {
    private ClientModelManagerFactory clientModelManagerFactory;
    private StringProperty email, confirmEmail, country, address, username, password, confirmPassword, error;
    private boolean isAdmin;

    /**
     * 1 argument constructor.
     * @param clientModelManagerFactory client's model manager factory
     */
    public RegisterViewModel(ClientModelManagerFactory clientModelManagerFactory) {
        this.clientModelManagerFactory = clientModelManagerFactory;
        email = new SimpleStringProperty();
        confirmEmail = new SimpleStringProperty();
        country = new SimpleStringProperty();
        address = new SimpleStringProperty();
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        confirmPassword = new SimpleStringProperty();
        error = new SimpleStringProperty();
    }

    public void addUser(ViewHandler viewHandler) throws SQLException {
        error.set("");
        String email = this.email.get();
        String confirmEmail = this.confirmEmail.get();
        String country = this.country.get();
        String address = this.address.get();
        String username = this.username.get();
        String password = this.password.get();
        String confirmPassword = this.confirmPassword.get();
        boolean isAdmin = getIsAdmin();

        if (email != null && !"".equals(email) && confirmEmail != null && !"".equals(confirmEmail) && country != null && !"".equals(country)
                && address != null && !"".equals(address) && username != null && !"".equals(username) && password != null && !"".equals(password)
                && confirmPassword != null && !"".equals(confirmPassword)) {

            if (!email.equals(confirmEmail)) {
                error.set("The emails are not matching!");
            } else {
                if (checkEmail(email)) {
                    error.set("Email already used in the system! Please introduce another email");
                } else {
                    if (password.equals(confirmPassword)) {
                        clientModelManagerFactory.addUser(email, country, address, username, password, isAdmin);

                        error.set("Account saved!");
                        viewHandler.openLoginView();
                    } else {
                        error.set("The passwords are not matching!");
                    }
                }
            }
        } else {
            error.set("ERROR: Input cannot be empty");
        }
    }


    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty confirmEmailProperty() {
        return confirmEmail;
    }

    public StringProperty countryProperty() {
        return country;
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public StringProperty confirmPasswordProperty() {
        return confirmPassword;
    }

    public StringProperty errorProperty() {
        return error;
    }

    public boolean checkEmail(String email) {
        return clientModelManagerFactory.checkEmail(email);
    }
}
