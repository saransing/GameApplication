package GameApp.client.model;

import GameApp.client.network.Client;
import GameApp.shared.model.Game;
import GameApp.shared.model.Transaction;
import GameApp.shared.model.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;


/**
 * A model class responsible for passing the methods.
 *
 * @author Adrian Bugiel, Andreea Asimine, Kevin Kluka, Saran Singh
 * @version 1.0
 */
public class ClientModelManager implements ClientModelManagerFactory {
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private Client client;
    private int selectedPictureId;

    /**
     * 1 argument constructor.
     *
     * @param client
     */
    public ClientModelManager(Client client) {
        this.client = client;
        client.startClient();

        client.addListener("NewGameAdded", this::update);
    }

    /**
     * Updates observer when new game is added.
     *
     * @param event PropertyChangeEvent
     */
    public void update(PropertyChangeEvent event) {
        support.firePropertyChange("NewGameAdded", null, 1);
    }

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
    @Override
    public void addUser(String email, String country, String address, String username, String password, boolean isAdmin) {
        client.addUser(new User(email, country, address, username, password, isAdmin));
    }

    /**
     * Inserts game into the database.
     *
     * @param title       game's title
     * @param genre       game's genre
     * @param description game's description
     * @param price       game's price
     * @return a Game object
     */
    @Override
    public Game create(String title, String genre, String description, double price) {
        return client.create(title, genre, description, price);
    }

    /**
     * Gets all the games from the database.
     *
     * @return List of the Game objects
     */
    @Override
    public List<Game> getAllGames() {
        return client.getAllGames();
    }

    /**
     * Searches for a matching game id and gets the matching object from a database.
     *
     * @param game_id an id of a game
     * @return Game object
     */
    @Override
    public Game readByID(int game_id) {
        return client.readByID(game_id);
    }

    /**
     * Updates user.
     *
     * @param user User object
     */
    @Override
    public void userEdit(User user) {
        client.editUser(user);
    }

    /**
     * True if email is present in the database.
     *
     * @param email user's email
     * @return Boolean representation of an email presence in a database
     */
    @Override
    public boolean checkEmail(String email) {
        return client.checkEmail(email);
    }

    /**
     * Adds an observer.
     *
     * @param eventName name of the event
     * @param listener  provided listener
     */
    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    /**
     * Removes an observer.
     *
     * @param eventName name of the event
     * @param listener  provided listener
     */
    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }

    /**
     * Let observer know which image has been clicked.
     *
     * @param id game id
     */
    @Override
    public void setSelectedId(int id) {
        this.selectedPictureId = id;
        support.firePropertyChange("NewPictureSelected", null, selectedPictureId);
    }

    /**
     * Gets selected image id.
     *
     * @return image id
     */
    @Override
    public int getSelectedPictureId() {
        return selectedPictureId;
    }

    /**
     * Searches through the database with given game id and if game is found it is added to the ArrayList.
     */
    @Override
    public void addGameToShoppingCart() {
        client.addGameToShoppingCart(selectedPictureId);
        support.firePropertyChange("NewItemInShoppingCart", null, 1);
    }

    /**
     * Searches through the database with given game id and if game is found it is removed from the ArrayList.
     *
     * @param id game's id
     */
    @Override
    public void removeGameFromShoppingCart(int id) {
        client.removeGameFromShoppingCart(id);
    }

    /**
     * Searches through the database with given game object and if game is found it is removed from the ArrayList.
     *
     * @param game Game object
     */
    @Override
    public void removeGameFromShoppingCart(Game game) {
        client.removeGameFromShoppingCart(game);
        support.firePropertyChange("ItemDeletedFromShoppingCart", null, 1);
    }

    @Override
    public void removeAllGamesFromCart() {
        client.removeAllGamesFromCart();
    }

    /**
     * Clears the shopping card.
     */
    @Override
    public double getShoppingCartValue() {
        return client.getShoppingCartValue();
    }

    /**
     * Gets a List which holds Game objects.
     *
     * @return List contains Game objects
     */
    @Override
    public List<Game> getAllGamesFromShoppingCart() {
        return client.getAllGamesFromShoppingCart();
    }

    /**
     * Gets List of games from a database game table by a genre.
     *
     * @param genre a genre a game
     * @return List of the Game objects
     */
    @Override
    public List<Game> getGamesByGenre(String genre) {
        return client.getGamesByGenre(genre);
    }

    /**
     * Gets List of a games from a database game table by alike title.
     *
     * @param title a title of a game
     * @return List of the Game objects
     */
    @Override
    public List<Game> getGamesByTitle(String title) {
        return client.getGamesByTitle(title);
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
        return client.login(email, password);
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
        support.firePropertyChange("UserLoggedIn", null, 1);
        return client.getLoggedUser(email, password);
    }

    /**
     * Gets the user.
     *
     * @return User object
     */
    @Override
    public User getUser() {
        return client.getUser();
    }

    /**
     * Gets all the registered users from the database.
     *
     * @return List of User objects
     */
    @Override
    public List<User> getAllUsers() {
        return client.getAllUsers();
    }

    /**
     * Gets user from database by an email.
     *
     * @param email user's email
     * @return User object
     */
    @Override
    public User findUserByEmail(String email) {
        return client.findUserByEmail(email);
    }

    /**
     * Bans user from the system.
     *
     * @param user User object
     */
    public void deleteUser(User user) {

        client.deleteUser(user);

    }

    /**
     * Insets transaction inside the database.
     *
     * @param usersEmail user's email
     * @param games      Game object
     * @return a Transaction Object
     */
    @Override
    public Transaction create(User usersEmail, List<Game> games) {

        Transaction transaction = client.create(usersEmail, games);
        support.firePropertyChange("TransactionMade", null, 1);

        return transaction;
    }

    /**
     * Gets list of games in the database by a user's email.
     *
     * @param email user's email
     * @return List of the Game objects
     */
    @Override
    public List<Game> getGamesByEmail(String email) {
        return client.getGamesByEmail(email);
    }


    /**
     * Gets a list of games from the database transaction table by alike game's title.
     *
     * @param title game's title
     * @param email user's email
     * @return List of the Game objects
     */
    @Override
    public List<Game> searchLikeTitleForEmail(String title, String email) {
        return client.searchLikeTitleForEmail(title, email);
    }

    /**
     * Deletes the transaction from the database.
     *
     * @param transaction Transaction object
     */
    @Override
    public void delete(Transaction transaction) {
        client.delete(transaction);
    }

    /**
     * Gets list of transactions from the database.
     *
     * @return List of the Transaction objects
     */
    @Override
    public List<Transaction> getAllTransactions() {
        return client.getAllTransactions();
    }

    /**
     * Gets all the transaction for a user from the database.
     *
     * @param email user's email
     * @return List of the Transaction objects
     */
    @Override
    public List<Transaction> getAllTransactionsByEmail(String email) {
        return client.getAllTransactionsByEmail(email);
    }

    /**
     * Gets all the transaction for a logged-in user from the database.
     *
     * @return List of the Transaction objects
     */
    @Override
    public List<Transaction> getAllTransactionsForThisClient() {
        if (getUser().getEmail() != null) {
            return client.getAllTransactionsByEmail(getUser().getEmail());
        }
        return new ArrayList<Transaction>();
    }

    /**
     * Gets the transaction by transaction id.
     *
     * @param transactionId id of a transaction
     * @return Transaction object
     */
    @Override
    public Transaction getTransactionByTransactionId(int transactionId) {
        return client.getTransactionByTransactionId(transactionId);
    }
}
