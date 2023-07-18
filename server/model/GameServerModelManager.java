package GameApp.server.model;

import GameApp.server.database.GameDAO;
import GameApp.server.database.GameDAOImpl;
import GameApp.shared.model.Game;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.List;

/**
 * A class responsible for passing the GameDAO methods.
 *
 * @author Andreea Asimine, Kevin Kluka, Saran Singh
 * @version 1.0
 */
public class GameServerModelManager implements GameServerModelManagerFactory {
    private PropertyChangeSupport support;
    private GameDAO gameDAO;

    /**
     * 0 arguments constructor.
     */
    public GameServerModelManager() {
        gameDAO = new GameDAOImpl();
        support = new PropertyChangeSupport(this);
    }

    /**
     * Gets all the games from a game table.
     *
     * @return List of Game objects
     */
    @Override
    public List<Game> getAllGames() {
        try {
            return gameDAO.getAllGames();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Searches for a matching game id and gets the matching object from a game table.
     *
     * @param game_id an id of a game
     * @return Game object
     */
    @Override
    public Game readByID(int game_id) {
        try {
            return gameDAO.readByID(game_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds an observer.
     *
     * @param eventName name of the event
     * @param listener  provided listener
     */
    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    /**
     * Removes an observer.
     *
     * @param eventName name of the event
     * @param listener  provided listener
     */
    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }

    /**
     * Inserts attributes to the game table.
     *
     * @param title       a title of a game
     * @param genre       a genre a game
     * @param description a genre a game
     * @param price       a price of a game
     * @return Game object
     */
    @Override
    public Game create(String title, String genre, String description, double price) {
        Game gameCreated = null;
        try {
            gameCreated = gameDAO.create(title, genre, description, price);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        support.firePropertyChange("NewGameAdded", null, gameCreated);
        return gameCreated;
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
            return gameDAO.getGamesByGenre(genre);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
            return gameDAO.getGamesByTitle(title);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
