package GameApp.server.model;

import GameApp.server.database.UserDAO;
import GameApp.server.database.UserDAOImpl;
import GameApp.shared.model.User;

import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.List;

/**
 * A class responsible for passing the UserDAO model methods.
 *
 * @author Andreea Asimine, Kevin Kluka
 * @version 1.0
 */
public class UserServerModelManager implements UserServerModelManagerFactory {

    private UserDAO userDAO;
    private PropertyChangeSupport support;

    /**
     * 0 arguments constructor.
     */
    public UserServerModelManager() {
        userDAO = new UserDAOImpl();
        support = new PropertyChangeSupport(this);
    }

    /**
     * Adds user to a database.
     *
     * @param user added to the database
     */
    @Override
    public void addUser(User user) {
        try {
            this.userDAO.create(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets all user's usernames.
     *
     * @return List of String objects
     */
    @Override
    public List<String> getUsersToListView() {
        try {
            return userDAO.getAllUsernames();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * True if email is present in the database.
     *
     * @param email user's email
     * @return Boolean representation of an email presence in a database
     */
    @Override
    public Boolean checkEmail(String email) {
        //email found in the system
        try {
            return userDAO.findUserByEmail(email) != null; //email not found in the system
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets user from database by an email.
     *
     * @param email user's email
     * @return User object
     */
    @Override
    public User findUserByEmail(String email) {
        try {
            return userDAO.findUserByEmail(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * True if email is present in the database with the correct password.
     *
     * @param email    user's email
     * @param password user's password
     * @return Boolean representation of user's email with password in the database
     */
    @Override
    public boolean login(String email, String password) {
        try {
            return userDAO.loginCon(email, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Gets a user which is currently logged in.
     *
     * @param email    user's email
     * @param password user's password
     * @return User object
     */
    @Override
    public User getLoggedUser(String email, String password) {
        try {
            return userDAO.getLoggedUser(email, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets all the registered users from the database.
     *
     * @return List of User objects
     */
    @Override
    public void editUser(User user) {

        try {
            this.userDAO.update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets all the registered users from the database.
     *
     * @return List of User objects
     */
    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    /**
     * Bans user from the system.
     *
     * @param user User object
     */
    @Override
    public void deleteUser(User user) {
        try {
            this.userDAO.delete(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
