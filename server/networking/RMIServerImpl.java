package GameApp.server.networking;

import GameApp.server.model.GameServerModelManagerFactory;
import GameApp.server.model.TransactionServerModelManagerFactory;
import GameApp.server.model.UserServerModelManagerFactory;
import GameApp.shared.model.Game;
import GameApp.shared.model.Transaction;
import GameApp.shared.model.User;
import GameApp.shared.networking.ClientCallback;
import GameApp.shared.networking.RMIServer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMIServerImpl implements RMIServer {
    private GameServerModelManagerFactory gameServerModelManagerFactory;
    private UserServerModelManagerFactory userServerModelManagerFactory;
    private TransactionServerModelManagerFactory transactionServerModelManagerFactory;

    public RMIServerImpl(GameServerModelManagerFactory gameServerModelManagerFactory,
                         UserServerModelManagerFactory userServerModelManagerFactory, TransactionServerModelManagerFactory transactionServerModelManagerFactory)
            throws RemoteException {
        UnicastRemoteObject.exportObject(this, 2910);

        this.gameServerModelManagerFactory = gameServerModelManagerFactory;
        this.userServerModelManagerFactory = userServerModelManagerFactory;
        this.transactionServerModelManagerFactory = transactionServerModelManagerFactory;
    }

    public void startServer() {
        Registry registry = null;
        try {
            registry = LocateRegistry.createRegistry(2910);
            registry.bind("ShopServer", this);
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registerCallback(ClientCallback ccb)
            throws RemoteException {
        gameServerModelManagerFactory.addListener("NewGameAdded", evt -> {
            try {
                ccb.update();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }
    @Override
    public void addUser(User user) {
        userServerModelManagerFactory.addUser(user);
    }
    @Override
    public boolean checkEmail(String email) {

        return userServerModelManagerFactory.checkEmail(email);
    }
    @Override
    public Game readByID(int game_id) {
        return gameServerModelManagerFactory.readByID(game_id);
    }
    @Override
    public List<Game> getAllGames() {
        return gameServerModelManagerFactory.getAllGames();
    }
    @Override
    public User findUserByEmail(String email) {
        return userServerModelManagerFactory.findUserByEmail(email);
    }
    @Override
    public boolean login(String email, String password) {
        return userServerModelManagerFactory.login(email, password);
    }
    @Override
    public User getLoggedUser(String email, String password) {
        return userServerModelManagerFactory.getLoggedUser(email, password);
    }
    @Override
    public void editUser(User user) {
        userServerModelManagerFactory.editUser(user);
    }
    @Override
    public List<User> getAllUsers()
    {
        return userServerModelManagerFactory.getAllUsers();
    }
    @Override
    public void deleteUser(User user) {
        userServerModelManagerFactory.deleteUser(user);
    }

    @Override
    public Game create(String title, String genre, String description, double price) {
        return gameServerModelManagerFactory.create(title,genre,description,price);
    }

    //TRANSACTION METHODS
    @Override
    public Transaction create(User usersEmail, List<Game> games) {
        return transactionServerModelManagerFactory.create(usersEmail, games);
    }

    @Override
    public List<Game> getGamesByEmail(String email) {
        return transactionServerModelManagerFactory.getGamesByEmail(email);
    }

    @Override
    public List<Game> searchLikeTitleForEmail(String title, String email) {
        return transactionServerModelManagerFactory.searchLikeTitleForEmail(title, email);
    }

    @Override
    public void delete(Transaction transaction) {
        transactionServerModelManagerFactory.delete(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionServerModelManagerFactory.getAllTransactions();
    }

    @Override
    public List<Transaction> getAllTransactionsByEmail(String email) {
        return transactionServerModelManagerFactory.getAllTransactionsByEmail(email);
    }

    @Override
    public Transaction getTransactionByTransactionId(int transactionId) {
        return transactionServerModelManagerFactory.getTransactionByTransactionId(transactionId);
    }

    @Override
    public List<Game> getGamesByGenre(String genre) {
        return gameServerModelManagerFactory.getGamesByGenre(genre);
    }

    @Override
    public List<Game> getGamesByTitle(String title) {
        return gameServerModelManagerFactory.getGamesByTitle(title);
    }
    //TRANSACTION METHODS END
}