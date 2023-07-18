package GameApp.server.database;

import GameApp.shared.model.Game;

import java.sql.SQLException;
import java.util.List;

/**
 * An interface to provide method signatures for GameDAOImpl Class.
 *
 * @author Andreea Asimine, Kevin Kluka, Saran Singh
 * @version 1.0
 */
public interface GameDAO {

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
    Game create(String title, String genre, String description, double price) throws SQLException;

    /**
     * Gets all the games from a game table.
     *
     * @return List of Game objects
     * @throws SQLException if a connection to a database fails or query statement fails
     */
    List<Game> getAllGames() throws SQLException;

    /**
     * Searches for a matching game id and gets the matching object from a game table.
     *
     * @param game_id an id of a game
     * @return Game object
     * @throws SQLException if a connection to a database fails or query statement fails
     */
    Game readByID(int game_id) throws SQLException;

    /**
     * Edits a game attributes if a Game object's game id exist in the database.
     *
     * @param game Game's object
     * @throws SQLException if a connection to a database fails or query statement fails
     */
    void update(Game game) throws SQLException;

    /**
     * Gets List of games from a database game table by a genre.
     *
     * @param genre a genre a game
     * @return List of the Game objects
     * @throws SQLException if a connection to a database fails or query statement fails
     */
    List<Game> getGamesByGenre(String genre) throws SQLException;

    /**
     * Gets List of a games from a database game table by alike title.
     *
     * @param title a title of a game
     * @return List of the Game objects
     * @throws SQLException if a connection to a database fails or query statement fails
     */
    List<Game> getGamesByTitle(String title) throws SQLException;
}
