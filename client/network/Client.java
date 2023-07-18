package GameApp.client.network;

import GameApp.shared.model.Game;
import GameApp.shared.model.Transaction;
import GameApp.shared.model.User;
import GameApp.shared.util.Subject;

import java.util.List;

/**
 * An interface to provide method signatures for RMIClient Class.
 *
 * @author Adrian Bugiel, Andreea Asimine, Kevin Kluka, Saran Singh
 * @version 1.0
 */
public interface Client extends Subject {

    /**
     * Starts the client and accesses remote server.
     */
    void startClient();

    /**
     * Adds user to a database.
     *
     * @param user added to the database
     */
    void addUser(User user);

    /**
     * True if email is present in the database.
     *
     * @param email user's email
     * @return Boolean representation of an email presence in a database
     */
    boolean checkEmail(String email);

    /**
     * Inserts game into the database.
     *
     * @param title       game's title
     * @param genre       game's genre
     * @param description game's description
     * @param price       game's price
     * @return a Game object
     */
    Game create(String title, String genre, String description, double price);

    /**
     * Searches for a matching game id and gets the matching object from a database.
     *
     * @param game_id an id of a game
     * @return Game object
     */
    Game readByID(int game_id);

    /**
     * Gets user from database by an email.
     *
     * @param email user's email
     * @return User object
     */
    User findUserByEmail(String email);

    /**
     * Gets the user.
     * @return User object
     */
    User getUser();

    /**
     * Updates user's information in the database.
     *
     * @param user User object
     */
    void editUser(User user);

    /**
     * Gets all the games from the database.
     *
     * @return List of the Game objects
     */
    List<Game> getAllGames();

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

    /**
     * Insets transaction inside the database.
     *
     * @param usersEmail user's email
     * @param games      Game object
     * @return a Transaction Object
     */
    Transaction create(User usersEmail, List<Game> games);

    /**
     * Gets list of games in the database by a user's email.
     *
     * @param email user's email
     * @return List of the Game objects
     */
    List<Game> getGamesByEmail(String email);

    /**
     * Gets a list of games from the database transaction table by alike game's title.
     *
     * @param title game's title
     * @param email user's email
     * @return List of the Game objects
     */
    List<Game> searchLikeTitleForEmail(String title, String email);

    /**
     * Deletes the transaction from the database.
     *
     * @param transaction Transaction object
     */
    void delete(Transaction transaction);

    /**
     * Gets list of transactions from the database.
     *
     * @return List of the Transaction objects
     */
    List<Transaction> getAllTransactions();

    /**
     * Gets all the transaction for a user from the database.
     *
     * @param email user's email
     * @return List of the Transaction objects
     */
    List<Transaction> getAllTransactionsByEmail(String email);

    /**
     * Gets the transaction by transaction id.
     *
     * @param transactionId id of a transaction
     * @return Transaction object
     */
    Transaction getTransactionByTransactionId(int transactionId);

    /**
     * Searches through the database with given game id and if game is found it is added to the ArrayList.
     *
     * @param game_id game's id
     */
    void addGameToShoppingCart(int game_id);

    /**
     * Searches through the database with given game id and if game is found it is removed from the ArrayList.
     *
     * @param game_id game's id
     */
    void removeGameFromShoppingCart(int game_id);

    /**
     * Searches through the database with given game object and if game is found it is removed from the ArrayList.
     *
     * @param game Game object
     */
    void removeGameFromShoppingCart(Game game);

    /**
     * Clears the shopping card.
     */
    void removeAllGamesFromCart();

    /**
     * Gets the total price of the shopping card's items.
     *
     * @return Double representation of total value
     */
    double getShoppingCartValue();

    /**
     * Gets List of a games from a database game table by alike title.
     *
     * @param title a title of a game
     * @return List of the Game objects
     */
    List<Game> getGamesByTitle(String title);

    /**
     * Gets a List which holds Game objects.
     *
     * @return List contains Game objects
     */
    List<Game> getAllGamesFromShoppingCart();

    /**
     * Gets List of games from a database game table by a genre.
     *
     * @param genre a genre a game
     * @return List of the Game objects
     */
    List<Game> getGamesByGenre(String genre);
}
