package GameApp.server.model;

import GameApp.shared.model.Game;
import GameApp.shared.util.Subject;

import java.util.List;

/**
 * A GameServerModelManager's interface.
 *
 * @author Andreea Asimine, Kevin Kluka, Saran Singh
 * @version 1.0
 */
public interface GameServerModelManagerFactory extends Subject {

    /**
     * Inserts attributes to the game table.
     *
     * @param title       a title of a game
     * @param genre       a genre a game
     * @param description a genre a game
     * @param price       a price of a game
     * @return Game object
     */
    Game create(String title, String genre, String description, double price);

    /**
     * Gets List of games from a database game table by a genre.
     *
     * @param genre a genre a game
     * @return List of the Game objects
     */
    List<Game> getGamesByGenre(String genre);

    /**
     * Gets List of a games from a database game table by alike title.
     *
     * @param title a title of a game
     * @return List of the Game objects
     */
    List<Game> getGamesByTitle(String title);

    /**
     * Gets all the games from a game table.
     *
     * @return List of Game objects
     */
    List<Game> getAllGames();

    /**
     * Searches for a matching game id and gets the matching object from a game table.
     *
     * @param game_id an id of a game
     * @return Game object
     */
    Game readByID(int game_id);
}
