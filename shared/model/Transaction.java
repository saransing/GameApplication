package GameApp.shared.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * A class represents a Transaction object which stores information about a transaction and GameInTransaction objects in a List.
 *
 * @author Kevin Kluka
 * @version 1.0
 */
public class Transaction implements Serializable {

    private List<GameInTransaction> games;
    private int transactionId;
    private String usersEmail;
    private Date dateOfPurchase;

    /**
     * 4 arguments constructor.
     *
     * @param transactionId  an id of a game in transaction
     * @param usersEmail     an email of a user
     * @param games          a list of games inside a transaction
     * @param dateOfPurchase a date of transaction's purchase
     */
    public Transaction(int transactionId, String usersEmail, List<GameInTransaction> games, Date dateOfPurchase) {
        this.games = games;
        this.transactionId = transactionId;
        this.usersEmail = usersEmail;
        this.dateOfPurchase = dateOfPurchase;
    }

    /**
     * Gets List of games in a transaction.
     *
     * @return a List of a GameInTransaction objects
     */
    public List<GameInTransaction> getGames() {
        return games;
    }

    /**
     * Sets List of games to a transaction.
     *
     * @param games an object of a GameInTransaction
     */
    public void setGames(List<GameInTransaction> games) {
        this.games = games;
    }

    /**
     * Gets an id of a transaction.
     *
     * @return Integer representation of a transaction's id
     */
    public int getTransactionId() {
        return transactionId;
    }

    /**
     * Sets an id of a transaction.
     *
     * @param transactionId id of a transaction
     */
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Gets email of a user.
     *
     * @return String representation of an email
     */
    public String getUsersEmail() {
        return usersEmail;
    }

    /**
     * Sets an email of a user.
     *
     * @param usersEmail an email of a user
     */
    public void setUsersEmail(String usersEmail) {
        this.usersEmail = usersEmail;
    }

    /**
     * Gets a purchase date of a transaction.
     *
     * @return Date object representation of a date of purchase
     */
    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    /**
     * Gets a total price of all the games in transaction.
     *
     * @return Double representation of total price of all GameInTransaction objects
     */
    public double getTotalTransactionPrice() {
        double total = 0;
        for (int i = 0; i < games.size(); i++) {
            total += games.get(i).getPurchasedPrice();
        }
        return total;
    }

    /**
     * Gets all the games in transaction game's ids in a String.
     *
     * @return String representation of all GameInTransaction object's game's ids from a List
     */
    public String getTransactionDetails() {
        String details = "";
        for (GameInTransaction game : games) {
            details += "GameID: " + game.getGameId() + "; ";
        }
        return details;
    }
}
