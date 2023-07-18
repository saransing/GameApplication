package GameApp.server.model;

import GameApp.server.database.TransactionDAO;
import GameApp.server.database.TransactionDAOImpl;
import GameApp.shared.model.Game;
import GameApp.shared.model.Transaction;
import GameApp.shared.model.User;

import java.sql.SQLException;
import java.util.List;

/**
 * A class responsible for passing the TransactionDAO methods.
 *
 * @author Kevin Kluka
 * @version 1.0
 */
public class TransactionServerModelManager implements TransactionServerModelManagerFactory {

    private TransactionDAO transactionDAO;

    /**
     * 0 arguments constructor.
     */
    public TransactionServerModelManager(){
        transactionDAO = new TransactionDAOImpl();
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
            return transactionDAO.create(usersEmail, games);
        } catch (SQLException e) {
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
            return transactionDAO.getGamesByEmail(email);
        } catch (SQLException e) {
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
            return transactionDAO.searchLikeTitleForEmail(title, email);
        } catch (SQLException e) {
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
            this.transactionDAO.delete(transaction);
        } catch (SQLException e) {
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
            return transactionDAO.getAllTransactions();
        } catch (SQLException e) {
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
            return transactionDAO.getAllTransactionsByEmail(email);
        } catch (SQLException e) {
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
            return transactionDAO.getTransactionByTransactionId(transactionId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
