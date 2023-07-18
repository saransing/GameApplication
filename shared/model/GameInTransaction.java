package GameApp.shared.model;

import java.io.Serializable;

/**
 * A class represents a GameInTransaction object to be stored in Transaction Object.
 *
 * @author Kevin Kluka
 * @version 1.0
 */
public class GameInTransaction implements Serializable {

    private int gameId;
    private int transactionId;
    private double purchasedPrice;

    /**
     * 3 arguments constructor .
     *
     * @param gameId         an id of a game in transaction
     * @param transactionId  an id of a transaction
     * @param purchasedPrice a purchased price of a game in transaction
     */
    public GameInTransaction(int gameId, int transactionId, double purchasedPrice) {
        this.gameId = gameId;
        this.transactionId = transactionId;
        this.purchasedPrice = purchasedPrice;
    }

    /**
     * Sets a transaction's id of a GameInTransaction object.
     *
     * @param transactionId an id of a transaction
     */
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Gets a game's id of a GameInTransaction object.
     *
     * @return Integer representation of a game's id
     */
    public int getGameId() {
        return gameId;
    }

    /**
     * Gets a transaction's id of a GameInTransaction object.
     *
     * @return Integer representation of a transaction's id
     */
    public int getTransactionId() {
        return transactionId;
    }

    /**
     * Gets a purchased price of a GameInTransaction object.
     *
     * @return Integer representation of a purchased price
     */
    public double getPurchasedPrice() {
        return purchasedPrice;
    }
}
