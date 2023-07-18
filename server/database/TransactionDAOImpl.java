package GameApp.server.database;

import GameApp.shared.model.Game;
import GameApp.shared.model.GameInTransaction;
import GameApp.shared.model.Transaction;
import GameApp.shared.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 * A class responsible for the transaction tables communication in the database.
 *
 * @author Kevin Kluka
 * @version 1.0
 */
public class TransactionDAOImpl implements TransactionDAO {

    /**
     * 0 arguments constructor.
     */
    public TransactionDAOImpl() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
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
        try (Connection connection = getConnection()) {

            long millis = System.currentTimeMillis();
            Date today = new Date(millis);

            PreparedStatement statement = connection.prepareStatement("INSERT INTO transaction(email, date_of_purchase) VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setString(1, usersEmail.getEmail());
            statement.setDate(2, today);
            statement.executeUpdate();


            ResultSet keys = statement.getGeneratedKeys();

            ArrayList<GameInTransaction> gamesInTransaction = new ArrayList<>();


            if (keys.next()) {

                Transaction transaction = new Transaction(keys.getInt(1), usersEmail.getEmail(), gamesInTransaction, today);

                for (Game game : games) {

                    statement = connection.prepareStatement("INSERT INTO game_in_transaction(game_id, transaction_id, purchased_price) VALUES (?,?,?)");

                    statement.setInt(1, game.getGameId());
                    statement.setInt(2, transaction.getTransactionId());
                    statement.setDouble(3, game.getGamePrice());
                    statement.executeUpdate();

                    gamesInTransaction.add(new GameInTransaction(game.getGameId(), transaction.getTransactionId(), game.getGamePrice()));
                }

                transaction.setGames(gamesInTransaction);

                return transaction;

            } else {
                throw new SQLException("No key has been generated");
            }
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

        try (Connection connection = getConnection()) {

            PreparedStatement statement = connection.prepareStatement("SELECT gt.game_id, g.title, d.description, g2.genre, gt.purchased_price \n" +
                    "FROM game_in_transaction gt\n" +
                    "         join transaction t on t.transaction_id = gt.transaction_id\n" +
                    "         join game g on gt.game_id = g.game_id\n" +
                    "         join description d on g.game_id = d.game_id\n" +
                    "join genre g2 on g.game_id = g2.game_id\n" +
                    "WHERE email = ?\n" +
                    "GROUP BY gt.game_id, g.title, d.description, g2.genre, gt.purchased_price;");

            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            ArrayList<Game> games = new ArrayList<>();

            while (resultSet.next()) {

                int id = resultSet.getInt("game_id");
                String genre = resultSet.getString("genre");
                String name = resultSet.getString("title");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("purchased_price");

                games.add(new Game(id, name, genre, description, price));
            }
            if (games.size() == 0) {
                return null;
            }
            return games;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets list of transactions from the database.
     *
     * @return List of the Transaction objects
     */
    @Override
    public List<Transaction> getAllTransactions()  {

        try (Connection connection = getConnection()) {

            List<Transaction> transactions = new ArrayList<>();

            PreparedStatement statement1 = connection.prepareStatement(
                    "SELECT *\n" +
                            "FROM transaction t");

            ResultSet resultSet1 = statement1.executeQuery();

            while (resultSet1.next()) {

                List<GameInTransaction> gamesInTransaction = new ArrayList<>();

                int transaction_id = resultSet1.getInt("transaction_id");
                String email = resultSet1.getString("email");
                Date purchasedDate = resultSet1.getDate("date_of_purchase");

                PreparedStatement statement2 = connection.prepareStatement("" +
                        "SELECT *\n" +
                        "FROM game_in_transaction gt\n" +
                        "where gt.transaction_id = ?;");
                statement2.setInt(1, transaction_id);

                ResultSet resultSet2 = statement2.executeQuery();

                while (resultSet2.next()) {
                    int game_id = resultSet2.getInt("game_id");
                    double purchased_price = resultSet2.getDouble("purchased_price");

                    gamesInTransaction.add(new GameInTransaction(game_id, transaction_id, purchased_price));
                }

                transactions.add(new Transaction(transaction_id, email, gamesInTransaction, purchasedDate));
            }
            if (transactions.size() == 0) {
                return null;
            }
            return transactions;
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
    public List<Transaction> getAllTransactionsByEmail(String email)  {

        try (Connection connection = getConnection()) {

            List<Transaction> transactions = new ArrayList<>();

            PreparedStatement statement1 = connection.prepareStatement(
                    "select *\n" +
                            "from transaction t\n" +
                            "where t.email= ?");

            statement1.setString(1, email);

            ResultSet resultSet1 = statement1.executeQuery();

            while (resultSet1.next()) {

                List<GameInTransaction> gamesInTransaction = new ArrayList<>();

                int transaction_id = resultSet1.getInt("transaction_id");
                Date purchasedDate = resultSet1.getDate("date_of_purchase");

                PreparedStatement statement2 = connection.prepareStatement("" +
                        "SELECT *\n" +
                        "FROM game_in_transaction gt\n" +
                        "where gt.transaction_id = ?;");
                statement2.setInt(1, transaction_id);

                ResultSet resultSet2 = statement2.executeQuery();

                while (resultSet2.next()) {
                    int game_id = resultSet2.getInt("game_id");
                    double purchased_price = resultSet2.getDouble("purchased_price");

                    gamesInTransaction.add(new GameInTransaction(game_id, transaction_id, purchased_price));
                }

                transactions.add(new Transaction(transaction_id, email, gamesInTransaction, purchasedDate));
            }
            if (transactions.size() == 0) {
                return null;
            }
            return transactions;
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
    public Transaction getTransactionByTransactionId(int transactionId)  {

        try (Connection connection = getConnection()) {

            PreparedStatement statement1 = connection.prepareStatement(
                    "select *\n" +
                            "from transaction t\n" +
                            "where t.transaction_id = ?;");

            statement1.setInt(1, transactionId);

            ResultSet resultSet1 = statement1.executeQuery();

            if (resultSet1.next()) {

                List<GameInTransaction> gamesInTransaction = new ArrayList<>();

                String email = resultSet1.getString("email");
                Date purchasedDate = resultSet1.getDate("date_of_purchase");

                PreparedStatement statement2 = connection.prepareStatement("" +
                        "SELECT *\n" +
                        "FROM game_in_transaction gt\n" +
                        "where gt.transaction_id = ?;");

                statement2.setInt(1, transactionId);

                ResultSet resultSet2 = statement2.executeQuery();

                while (resultSet2.next()) {
                    int game_id = resultSet2.getInt("game_id");
                    double purchased_price = resultSet2.getDouble("purchased_price");

                    gamesInTransaction.add(new GameInTransaction(game_id, transactionId, purchased_price));
                }

                return new Transaction(transactionId, email, gamesInTransaction, purchasedDate);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
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
        try (Connection connection = getConnection()) {

            PreparedStatement statement = connection.prepareStatement("SELECT game_in_transaction.game_id, g.title, g2.genre, d.description\n" +
                    "FROM game_in_transaction\n" +
                    "         inner join game g on g.game_id = game_in_transaction.game_id\n" +
                    "         inner join description d on g.game_id = d.game_id\n" +
                    "         inner join genre g2 on g.game_id = g2.game_id\n" +
                    "         inner join transaction t on game_in_transaction.transaction_id = t.transaction_id\n" +
                    "where g.title like ?\n" +
                    "  and t.email = ?\n" +
                    "GROUP BY game_in_transaction.game_id, g.title, g2.genre, d.description");

            statement.setString(1, "%" + title + "%");
            statement.setString(2, email);

            ResultSet resultSet = statement.executeQuery();

            List<Game> games = new ArrayList<>();

            while (resultSet.next()) {

                int id = resultSet.getInt("game_id");
                String genre = resultSet.getString("genre");
                String name = resultSet.getString("title");
                String description = resultSet.getString("description");

                games.add(new Game(id, name, genre, description, 0));
            }
            if (games.size() == 0) {
                return null;
            }
            return games;
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
    public void delete(Transaction transaction)  {

        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM transaction WHERE transaction_id = ?");

            statement.setInt(1, transaction.getTransactionId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets a connection details to a database.
     *
     * @return Connection  to a database
     * @throws SQLException if a connection to a database fails
     */
    private Connection getConnection() throws SQLException {

        return ConnectDatabase.getConnection();
    }
}

