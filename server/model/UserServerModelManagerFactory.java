package GameApp.server.model;

import GameApp.shared.model.User;

import java.util.List;

/**
 * An UserModelManager Interface.
 *
 * @author Kevin Kluka, Adrian Bugiel, Andreea Asimine
 * @version 1.0
 */
public interface UserServerModelManagerFactory {

    /**
     * Adds user to a database.
     *
     * @param user added to the database
     */
    void addUser(User user);

    /**
     * Gets all the user's usernames.
     *
     * @return List of String objects
     */
    List<String> getUsersToListView();

    /**
     * True if email is present in the database.
     *
     * @param email user's email
     * @return Boolean representation of an email presence in a database
     */
    Boolean checkEmail(String email);

    /**
     * Gets user from database by an email.
     *
     * @param email user's email
     * @return User object
     */
    User findUserByEmail(String email);

    /**
     * True if email is present in the database with the correct password.
     *
     * @param email    user's email
     * @param password user's password
     * @return Boolean representation of user's email with password in the database
     */
    boolean login(String email, String password);

    /**
     * Gets a user which is currently logged in.
     *
     * @param email    user's email
     * @param password user's password
     * @return User object
     */
    User getLoggedUser(String email, String password);

    /**
     * Updates user's information in the database.
     *
     * @param user User object
     */
    void editUser(User user);

    /**
     * Gets all the registered users from the database.
     *
     * @return List of User objects
     */
    List<User> getAllUsers();

    /**
     * Bans user from the system.
     *
     * @param user User object
     */
    void deleteUser(User user);
}
