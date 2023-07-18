package GameApp.client.model;

import GameApp.shared.model.Game;
import GameApp.shared.model.Transaction;
import GameApp.shared.model.User;
import GameApp.shared.util.Subject;

import java.util.List;

/**
 * An interface for the ClientModelManager.
 *
 * @author Adrian Bugiel, Andreea Asimine, Kevin Kluka, Saran Singh
 * @version 1.0
 */
public interface ClientModelManagerFactory extends Subject {

    /**
     * Adds user to a database.
     *
     * @param email    user's email
     * @param country  user's country
     * @param address  user's  address
     * @param username user's username
     * @param password user's password
     * @param isAdmin  if user is admin
     */
    void addUser(String email, String country, String address, String username, String password, boolean isAdmin);

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
     * Gets all the games from the database.
     *
     * @return List of the Game objects
     */
    List<Game> getAllGames();

    /**
     * Updates user.
     *
     * @param user User object
     */
    void userEdit(User user);

    /**
     * True if email is present in the database.
     *
     * @param email user's email
     * @return Boolean representation of an email presence in a database
     */
    boolean checkEmail(String email);

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
     * Gets the user.
     *
     * @return User object
     */
    User getUser();

    /**
     * Gets all the registered users from the database.
     *
     * @return List of User objects
     */
    List<User> getAllUsers();

    /**
     * Gets user from database by an email.
     *
     * @param email user's email
     * @return User object
     */
    User findUserByEmail(String email);

    /**
     * Bans user from the system.
     *
     * @param user User object
     */
    void deleteUser(User user);

    /**
     * Let observer know which image has been clicked.
     *
     * @param id game id
     */
    void setSelectedId(int id);

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
     * Gets all the transaction for a logged-in user from the database.
     *
     * @return List of the Transaction objects
     */
    List<Transaction> getAllTransactionsForThisClient();

    /**
     * Gets the transaction by transaction id.
     *
     * @param transactionId id of a transaction
     * @return Transaction object
     */
    Transaction getTransactionByTransactionId(int transactionId);

    /**
     * Gets selected image id.
     *
     * @return image id
     */
    int getSelectedPictureId();

    /**
     * Gets the total price of the shopping card's items.
     *
     * @return Double representation of total value
     */
    double getShoppingCartValue();

    /**
     * Searches through the database with given game id and if game is found it is added to the ArrayList.
     */
    void addGameToShoppingCart();

    /**
     * Searches through the database with given game id and if game is found it is removed from the ArrayList.
     *
     * @param id game's id
     */
    void removeGameFromShoppingCart(int id);

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

    /**
     * Gets List of a games from a database game table by alike title.
     *
     * @param title a title of a game
     * @return List of the Game objects
     */
    List<Game> getGamesByTitle(String title);
}
