package GameApp.server.database;

import GameApp.shared.model.Game;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * A class responsible for the game tables communication in the database.
 *
 * @author Kevin Kluka, Adrian Bugiel, Andreea Asimine
 * @version 1.0
 */
public class GameDAOImpl implements GameDAO {

    /**
     * 0 arguments constructor.
     */
    public GameDAOImpl() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
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

    /**
     * Gets all the games from a game table.
     *
     * @return List of Game objects
     */
    @Override
    public List<Game> getAllGames() {

        try (Connection connection = getConnection()) {

            PreparedStatement statement = connection.prepareStatement(
                    "(SELECT g.game_id, g.title, d.description, g2.genre, g.price\n" +
                            "FROM game g\n" +
                            "         join description d on g.game_id = d.game_id\n" +
                            "         join genre g2 on g.game_id = g2.game_id\n" +
                            "GROUP BY g.game_id, g.title, d.description, g2.genre, g.price)");
            ArrayList<Game> games = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int game_id = resultSet.getInt("game_id");
                String title = resultSet.getString("title");
                String genre = resultSet.getString("genre");
                String description = resultSet.getString("description");
                Double price = resultSet.getDouble("price");


                Game game = new Game(game_id, title, genre, description, price);
                games.add(game);
            }
            return games;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Inserts attributes to the game table.
     *
     * @param title       a title of a game
     * @param genre       a genre a game
     * @param description a genre a game
     * @param price       a price of a game
     * @return Game object
     * @throws SQLException if a connection to a database fails or query statement fails
     */
    @Override
    public Game create(String title, String genre, String description, double price) {
        try (Connection connection = getConnection()) {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO game(title, price) VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setString(1, title);
            statement.setDouble(2, price);
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {

                int id = statement.getGeneratedKeys().getInt(1);

                statement = connection.prepareStatement("INSERT INTO description(game_id, description) VALUES (?,?)");

                statement.getGeneratedKeys().next();
                statement.setInt(1, id);
                statement.setString(2, description);
                statement.executeUpdate();

                statement = connection.prepareStatement("INSERT INTO genre(game_id, genre) VALUES (?,?)");

                statement.getGeneratedKeys().next();
                statement.setInt(1, id);
                statement.setString(2, genre);
                statement.executeUpdate();

                return new Game(id, title, genre, description, price);

            } else {
                throw new SQLException("Keys has not been generated!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Searches for a matching game id and gets the matching object from a game table.
     *
     * @param game_id an id of a game
     * @return Game object
     * @throws SQLException if a connection to a database fails or query statement fails
     */
    @Override
    public Game readByID(int game_id) {

        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT g.game_id, g.title, d.description, g2.genre, g.price\n" +
                    "FROM game g\n" +
                    "         join description d on g.game_id = d.game_id\n" +
                    "         join genre g2 on g.game_id = g2.game_id\n" +
                    "WHERE g.game_id = ?\n" +
                    "GROUP BY g.game_id, g.title, d.description, g2.genre, g.price");

            statement.setInt(1, game_id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                int id = resultSet.getInt("game_id");
                String genre = resultSet.getString("genre");
                String name = resultSet.getString("title");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");

                return new Game(id, name, genre, description, price);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Edits a game attributes if a Game object's game id exist in the database.
     *
     * @param game Game's object
     * @throws SQLException if a connection to a database fails or query statement fails
     */
    @Override
    public void update(Game game) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE game SET title = ?, genre =?,description = ?, price = ? WHERE game_id= ?");

            statement.setString(1, game.getGameTitle());
            statement.setString(2, game.getGameGenre());
            statement.setString(3, game.getGameDescription());
            statement.setDouble(4, game.getGamePrice());
            statement.setInt(5, game.getGameId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets List of games from a database game table by a genre.
     *
     * @param genre a genre a game
     * @return List of the Game objects
     * @throws SQLException if a connection to a database fails or query statement fails
     */
    @Override
    public List<Game> getGamesByGenre(String genre) throws SQLException {

        try (Connection connection = getConnection()) {

            PreparedStatement statement = connection.prepareStatement("SELECT g.game_id, g.title, d.description, g2.genre, g.price\n" +
                    "FROM game g\n" +
                    "         join description d on g.game_id = d.game_id\n" +
                    "         join genre g2 on g.game_id = g2.game_id\n" +
                    "WHERE g2.genre = ?\n" +
                    "GROUP BY g.game_id, g.title, d.description, g2.genre, g.price");

            statement.setString(1, genre);

            ResultSet resultSet = statement.executeQuery();
            ArrayList<Game> getGamesByGenre = new ArrayList<>();

            while (resultSet.next()) {

                int id = resultSet.getInt("game_id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");

                Game game = new Game(id, title, genre, description, price);
                getGamesByGenre.add(game);
            }
            return getGamesByGenre;
        }
    }

    /**
     * Gets List of a games from a database game table by alike title.
     *
     * @param title a title of a game
     * @return List of the Game objects
     * @throws SQLException if a connection to a database fails or query statement fails
     */
    @Override
    public List<Game> getGamesByTitle(String title) {

        try (Connection connection = getConnection()) {

            PreparedStatement statement = connection.prepareStatement("SELECT g.game_id, g.title, d.description, g2.genre, g.price\n" +
                    "FROM game g\n" +
                    "         join description d on g.game_id = d.game_id\n" +
                    "         join genre g2 on g.game_id = g2.game_id\n" +
                    "WHERE g.title like ?\n" +
                    "GROUP BY g.game_id, g.title, d.description, g2.genre, g.price");

            statement.setString(1, "%" + title + "%");

            ResultSet resultSet = statement.executeQuery();
            ArrayList<Game> getGamesByGenre = new ArrayList<>();

            while (resultSet.next()) {

                int id = resultSet.getInt("game_id");
                String realTitle = resultSet.getString("title");
                String genre = resultSet.getString("genre");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");

                Game game = new Game(id, realTitle, genre, description, price);
                getGamesByGenre.add(game);
            }
            return getGamesByGenre;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
