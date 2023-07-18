package GameApp.server.database;

import GameApp.shared.model.Game;
import GameApp.shared.model.Transaction;
import GameApp.shared.model.User;

import java.sql.SQLException;
import java.util.List;

/**
 * An interface to provide method signatures for TransactionDAOImpl Class.
 *
 * @author Kevin Kluka
 * @version 1.0
 */
public interface TransactionDAO {

    /**
     * Insets transaction inside the database.
     *
     * @param usersEmail user's email
     * @param games      Game object
     * @return a Transaction Object
     */
    Transaction create(User usersEmail, List<Game> games) throws SQLException;

    /**
     * Gets list of games in the database by a user's email.
     *
     * @param email user's email
     * @return List of the Game objects
     */
    List<Game> getGamesByEmail(String email) throws SQLException;

    /**
     * Gets a list of games from the database transaction table by alike game's title.
     *
     * @param title game's title
     * @param email user's email
     * @return List of the Game objects
     */
    List<Game> searchLikeTitleForEmail(String title, String email) throws SQLException;

    /**
     * Deletes the transaction from the database.
     *
     * @param transaction Transaction object
     */
    void delete(Transaction transaction) throws SQLException;

    /**
     * Gets list of transactions from the database.
     *
     * @return List of the Transaction objects
     */
    List<Transaction> getAllTransactions() throws SQLException;

    /**
     * Gets all the transaction for a user from the database.
     *
     * @param email user's email
     * @return List of the Transaction objects
     */
    List<Transaction> getAllTransactionsByEmail(String email) throws SQLException;

    /**
     * Gets the transaction by transaction id.
     *
     * @param transactionId id of a transaction
     * @return Transaction object
     */
    Transaction getTransactionByTransactionId(int transactionId) throws SQLException;
}
