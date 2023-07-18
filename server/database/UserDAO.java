package GameApp.server.database;

import GameApp.shared.model.User;

import java.sql.SQLException;
import java.util.List;

/**
 * An UserDAOImpl's Interface.
 *
 * @author Kevin Kluka, Adrian Bugiel, Andreea Asimine
 * @version 1.0
 */
public interface UserDAO {

    /**
     * Adds user to a database.
     *
     * @param user added to the database
     */
    User create(User user) throws SQLException;

    /**
     * Gets all user's usernames.
     *
     * @return List of String objects
     */
    List<User> readByUsername(String username) throws SQLException;

    /**
     * Gets user from database by an email.
     *
     * @param email user's email
     * @return User object
     */
    User findUserByEmail(String email) throws SQLException;

    /**
     * Gets all the registered users from the database.
     *
     * @return List of User objects
     */
    List<String> getAllUsernames() throws SQLException;

    /**
     * Updates user's information in the database.
     *
     * @param user User object
     */
    void update(User user) throws SQLException;

    /**
     * Bans user from the system.
     *
     * @param user User object
     */
    void delete(User user) throws SQLException;

    /**
     * True if email is present in the database.
     *
     * @param email user's email
     * @return Boolean representation of an email presence in a database
     */
    boolean loginCon(String email, String password) throws SQLException;

    /**
     * Gets all the registered users from the database.
     *
     * @return List of User objects
     */
    List<User> getAllUsers();

    /**
     * Gets a user which is currently logged in.
     *
     * @param email    user's email
     * @param password user's password
     * @return User object
     */
    User getLoggedUser(String email, String password) throws SQLException;
}
