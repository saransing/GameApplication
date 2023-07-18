package GameApp.shared.networking;

import GameApp.shared.model.Game;
import GameApp.shared.model.Transaction;
import GameApp.shared.model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

/**
 * An interface responsible for setting up remote server.
 *
 * @author Adrian Bugiel, Andreea Asimine, Kevin Kluka, Saran Singh
 * @version 1.0
 */
public interface RMIServer extends Remote {

    /**
     * Registers call back client.
     *
     * @param ccb client to register for call back
     * @throws RemoteException if a connection between RMI serve and client fails
     */
    void registerCallback(ClientCallback ccb) throws RemoteException;

    /**
     * Adds user to a database.
     *
     * @param user added to the database
     * @throws RemoteException if a connection between RMI serve and client fails
     */
    void addUser(User user) throws RemoteException;

    /**
     * True if email is present in the database.
     *
     * @param email user's email
     * @return Boolean representation of an email presence in a database
     * @throws RemoteException if a connection between RMI serve and client fails
     */
    boolean checkEmail(String email) throws RemoteException;

    /**
     * Searches for a matching game id and gets the matching object from a database.
     *
     * @param game_id an id of a game
     * @return Game object
     * @throws SQLException if a connection to a database fails or query statement fails
     */
    Game readByID(int game_id) throws RemoteException;

    /**
     * Gets all the games from the database.
     *
     * @return List of the Game objects
     * @throws RemoteException if a connection between RMI serve and client fails
     */
    List<Game> getAllGames() throws RemoteException;

    /**
     * Gets user from database by an email.
     *
     * @param email user's email
     * @return User object
     * @throws RemoteException if a connection between RMI serve and client fails
     */
    User findUserByEmail(String email) throws RemoteException;

    /**
     * True if email is present in the database with the correct password.
     *
     * @param email    user's email
     * @param password user's password
     * @return Boolean representation of user's email with password in the database
     * @throws RemoteException if a connection between RMI serve and client fails
     */
    boolean login(String email, String password) throws RemoteException;

    /**
     * Updates user's information in the database.
     *
     * @param user User object
     * @throws RemoteException if a connection between RMI serve and client fails
     */
    void editUser(User user) throws RemoteException;

    /**
     * Gets a user which is currently logged in.
     *
     * @param email    user's email
     * @param password user's password
     * @return User object
     * @throws RemoteException if a connection between RMI serve and client fails
     */
    User getLoggedUser(String email, String password) throws RemoteException;

    /**
     * Gets all the registered users from the database.
     *
     * @return List of User objects
     * @throws RemoteException if a connection between RMI serve and client fails
     */
    List<User> getAllUsers() throws RemoteException;

    /**
     * Bans user from the system.
     *
     * @param user User object
     * @throws RemoteException if a connection between RMI serve and client fails
     */
    void deleteUser(User user) throws RemoteException;

    /**
     * Inserts game into the database.
     *
     * @param title       game's title
     * @param genre       game's genre
     * @param description game's description
     * @param price       game's price
     * @return a Game object
     * @throws RemoteException if a connection between RMI serve and client fails
     */
    Game create(String title, String genre, String description, double price) throws RemoteException;

    /**
     * Insets transaction inside the database.
     *
     * @param usersEmail user's email
     * @param games      Game object
     * @return a Transaction Object
     * @throws RemoteException if a connection between RMI serve and client fails
     */
    Transaction create(User usersEmail, List<Game> games) throws RemoteException;

    /**
     * Gets list of games in the database by a user's email.
     *
     * @param email user's email
     * @return List of the Game objects
     * @throws RemoteException if a connection between RMI serve and client fails
     */
    List<Game> getGamesByEmail(String email) throws RemoteException;

    /**
     * Gets a list of games from the database transaction table by alike game's title.
     *
     * @param title game's title
     * @param email user's email
     * @return List of the Game objects
     * @throws RemoteException if a connection between RMI serve and client fails
     */
    List<Game> searchLikeTitleForEmail(String title, String email) throws RemoteException;

    /**
     * Deletes the transaction from the database.
     *
     * @param transaction Transaction object
     * @throws RemoteException if a connection between RMI serve and client fails
     */
    void delete(Transaction transaction) throws RemoteException;

    /**
     * Gets list of transactions from the database.
     *
     * @return List of the Transaction objects
     * @throws RemoteException if a connection between RMI serve and client fails
     */
    List<Transaction> getAllTransactions() throws RemoteException;

    /**
     * Gets all the transaction for a user from the database.
     *
     * @param email user's email
     * @return List of the Transaction objects
     * @throws RemoteException if a connection between RMI serve and client fails
     */
    List<Transaction> getAllTransactionsByEmail(String email) throws RemoteException;

    /**
     * Gets the transaction by transaction id.
     *
     * @param transactionId id of a transaction
     * @return Transaction object
     * @throws RemoteException if a connection between RMI serve and client fails
     */
    Transaction getTransactionByTransactionId(int transactionId) throws RemoteException;

    /**
     * Gets List of games from a database game table by a genre.
     *
     * @param genre a genre a game
     * @return List of the Game objects
     * @throws RemoteException if a connection between RMI serve and client fails
     */
    List<Game> getGamesByGenre(String genre) throws RemoteException;

    /**
     * Gets List of a games from a database game table by alike title.
     *
     * @param title a title of a game
     * @return List of the Game objects
     * @throws RemoteException if a connection between RMI serve and client fails
     */
    List<Game> getGamesByTitle(String title) throws RemoteException;
}
