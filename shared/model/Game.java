package GameApp.shared.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A class represents a Game object.
 *
 * @author Adrian Bugiel
 * @version 1.0
 */
public class Game implements Serializable {

    private int gameId;
    private String gameTitle;
    private ArrayList<String> gameGenre;
    private String gameDescription;
    private double gamePrice;

    /**
     * 5 arguments constructor.
     *
     * @param gameId          an id of a game
     * @param gameTitle       a title of a game
     * @param gameGenre       a genre a game
     * @param gameDescription a description of a game
     * @param gamePrice       a price of a game
     */
    public Game(int gameId, String gameTitle, String gameGenre, String gameDescription, double gamePrice) {

        this.gameId = gameId;
        this.gameTitle = gameTitle;

        this.gameGenre = new ArrayList<>();
        this.gameGenre.add(gameGenre);

        this.gameDescription = gameDescription;
        this.gamePrice = gamePrice;
    }

    /**
     * Gets a game's id of a Game object.
     *
     * @return Integer representation of a game's id
     */
    public int getGameId() {
        return gameId;
    }

    /**
     * Gets a title of a Game object.
     *
     * @return String representation of a game's title
     */
    public String getGameTitle() {
        return gameTitle;
    }

    /**
     * Gets a genre of a Game object from first position.
     *
     * @return String representation of the game's genre from first position
     */
    public String getGameGenre() {
        return gameGenre.get(0);
    }

    /**
     * Gets a List of a game's genres.
     *
     * @return String in List representation of the game's genres
     */
    public List<String> getGameGenres() {
        return gameGenre;
    }

    /**
     * Gets a description of a Game object.
     *
     * @return String representation of the game's title
     */
    public String getGameDescription() {
        return gameDescription;
    }

    public double getGamePrice() {
        return gamePrice;
    }

    /**
     * Gets an image location of a Game object.
     *
     * @return String representation of the game's url address
     */
    public String getPictureURL() {
        return ("@../../GameApp/client/views/images/") + gameId + ".jpg";
    }

    /**
     * Gets true if two compared objects have the same game id.
     *
     * @param obj an object that is being compared
     * @return Boolean representation of a two objects' game ids comparison
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Game other = (Game) obj;
        return gameId == other.getGameId();
    }
}






