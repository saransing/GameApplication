package GameApp.client.network;

import GameApp.shared.model.Game;
import GameApp.shared.model.ShoppingCart;
import GameApp.shared.model.Transaction;
import GameApp.shared.model.User;
import GameApp.shared.networking.ClientCallback;
import GameApp.shared.networking.RMIServer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * A Client Class for a remote method invocation.
 *
 * @author Adrian Bugiel, Andreea Asimine, Kevin Kluka, Saran Singh
 * @version 1.0
 */
public class RMIClient implements Client, ClientCallback {

    private RMIServer server;
    public PropertyChangeSupport support;
    private User user;

    private ShoppingCart shoppingCart;

    /**
     * 0 arguments constructor.
     */
    public RMIClient() {
        support = new PropertyChangeSupport(this);
        shoppingCart = new ShoppingCart();

    }

    /**
     * Starts the client and accesses remote server.
     */
    @Override
    public void startClient() {
        Registry registry = null;
        try {
            UnicastRemoteObject.exportObject(this, 0);
            registry = LocateRegistry.getRegistry("localhost", 2910);
            server = (RMIServer) registry.lookup("ShopServer");
            server.registerCallback(this);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Searches through the database with given game id and if game is found it is added to the ArrayList.
     *
     * @param game_id game's id
     */
    public void addGameToShoppingCart(int game_id) {
        Game addedGame = readByID(game_id);
        if (addedGame != null) {

            if (!shoppingCart.contains(addedGame)) {
                shoppingCart.addGame(addedGame);
            }
        }
    }

    /**
     * Searches through the database with given game id and if game is found it is removed from the ArrayList.
     *
     * @param game_id game's id
     */
    public void removeGameFromShoppingCart(int game_id) {
        if (readByID(game_id) != null) shoppingCart.removeGame(readByID(game_id));
    }

    /**
     * Searches through the database with given game object and if game is found it is removed from the ArrayList.
     *
     * @param game Game object
     */
    public void removeGameFromShoppingCart(Game game) {
        shoppingCart.removeGame(game);
    }

    /**
     * Clears the shopping card.
     */
    public void removeAllGamesFromCart() {
        shoppingCart.clearCart();
    }

    /**
     * Gets the total price of the shopping card's items.
     *
     * @return Double representation of total value
     */
    public double getShoppingCartValue() {
        return shoppingCart.getShoppingCartValue();
    }

    /**
     * Gets List of a games from a database game table by alike title.
     *
     * @param title a title of a game
     * @return List of the Game objects
     */
    @Override
    public List<Game> getGamesByTitle(String title) {
        try {
            return server.getGamesByTitle(title);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets a List which holds Game objects.
     *
     * @return List contains Game objects
     */
    public List<Game> getAllGamesFromShoppingCart() {
        return shoppingCart.getGames();
    }

    /**
     * Gets List of games from a database game table by a genre.
     *
     * @param genre a genre a game
     * @return List of the Game objects
     */
    @Override
    public List<Game> getGamesByGenre(String genre) {
        try {
            return server.getGamesByGenre(genre);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates observer when new game is added.
     */
    @Override
    public void update() {
        support.firePropertyChange("NewGameAdded", null, 1);
    }

    /**
     * Adds an observer.
     *
     * @param eventName name of the event
     * @param listener  provided listener
     */
    @Override
    public void addListener(String eventName,
                            PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    /**
     * Removes an observer.
     *
     * @param eventName name of the event
     * @param listener  provided listener
     */
    @Override
    public void removeListener(String eventName,
                               PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }

    /**
     * Adds user to a database.
     *
     * @param user added to the database
     */
    @Override
    public void addUser(User user) {
        try {
            server.addUser(user);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * True if email is present in the database.
     *
     * @param email user's email
     * @return Boolean representation of an email presence in a database
     */
    @Override
    public boolean checkEmail(String email) {
        try {
            return server.checkEmail(email);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
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
        try {
            return server.create(title, genre, description, price);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets all the games from the database.
     *
     * @return List of the Game objects
     */
    public List<Game> getAllGames() {
        try {
            return server.getAllGames();
        } catch (RemoteException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Searches for a matching game id and gets the matching object from a database.
     *
     * @param game_id an id of a game
     * @return Game object
     */
    public Game readByID(int game_id) {
        try {
            return server.readByID(game_id);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Confirms that the user is in the database and then sets the user.
     *
     * @param email    user's email
     * @param password user's password
     */
    public void setUser(String email, String password) {
        try {
            if (server.login(email, password))
                user = server.findUserByEmail(email);

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets user from database by an email.
     *
     * @param email user's email
     * @return User object
     */
    public User findUserByEmail(String email) {
        try {
            return server.findUserByEmail(email);
        } catch (RemoteException e) {
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
    public boolean login(String email, String password) {

        user = getLoggedUser(email, password);
        try {
            return server.login(email, password);
        } catch (RemoteException e) {
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
            return server.getLoggedUser(email, password);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the user.
     *
     * @return User object
     */
    public User getUser() {
        return user;
    }

    /**
     * Updates user's information in the database.
     *
     * @param user User object
     */
    public void editUser(User user) {
        try {
            server.editUser(user);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets all the registered users from the database.
     *
     * @return List of User objects
     */
    public List<User> getAllUsers() {
        try {
            return server.getAllUsers();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Bans user from the system.
     *
     * @param user User object
     */
    public void deleteUser(User user) {
        try {
            server.deleteUser(user);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
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
        try {
            return server.create(usersEmail, games);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets list of games in the database by a user's email.
     *
     * @param email user's email
     * @return List of the Game objects
     */
    @Override
    public List<Game> getGamesByEmail(String email) {
        try {
            return server.getGamesByEmail(email);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
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
        try {
            return server.searchLikeTitleForEmail(title, email);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes the transaction from the database.
     *
     * @param transaction Transaction object
     */
    @Override
    public void delete(Transaction transaction) {
        try {
            server.delete(transaction);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets list of transactions from the database.
     *
     * @return List of the Transaction objects
     */
    @Override
    public List<Transaction> getAllTransactions() {
        try {
            return server.getAllTransactions();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets all the transaction for a user from the database.
     *
     * @param email user's email
     * @return List of the Transaction objects
     */
    @Override
    public List<Transaction> getAllTransactionsByEmail(String email) {
        try {
            return server.getAllTransactionsByEmail(email);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the transaction by transaction id.
     *
     * @param transactionId id of a transaction
     * @return Transaction object
     */
    @Override
    public Transaction getTransactionByTransactionId(int transactionId) {
        try {
            return server.getTransactionByTransactionId(transactionId);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    //TRANSACTION METHODS ENDS
}
