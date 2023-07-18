package GameApp.shared.model;

import java.util.ArrayList;

/**
 * A class represents a ShoppingCart object which stores Game objects in ArrayList.
 *
 * @author Adrian Bugiel
 * @version 1.0
 */
public class ShoppingCart {

    private ArrayList<Game> games;

    /**
     * 0 arguments constructor.
     */
    public ShoppingCart() {
        games = new ArrayList<>();
    }

    /**
     * Gets a ArrayList which holds Game object.
     *
     * @return Arraylist contains Game objects
     */
    public ArrayList<Game> getGames() {
        return games;
    }

    /**
     * Adds a Game object to an ArrayList.
     *
     * @param game a Game object
     */
    public void addGame(Game game) {
        games.add(game);
    }

    /**
     * Removes a Game object from am ArrayList.
     *
     * @param game a Game object
     */
    public void removeGame(Game game) {
        games.remove(game);
    }

    /**
     * Removes all Game objects from a ArrayList.
     */
    public void clearCart() {
        games.clear();
    }

    /**
     * Gets purchased price value for all Games object from an ArrayList.
     *
     * @return Double representation summation of all Game objects prices
     */
    public double getShoppingCartValue() {
        double value = 0;
        for (Game game : games) {
            value += game.getGamePrice();
        }
        return value;
    }

    /**
     * Gets true if ArrayList objects contains all the same elements.
     *
     * @param obj an object that is to be compared
     * @return Boolean representation of two ArrayList objects comparison
     */
    public boolean contains(Object obj) {
        if (obj instanceof Game) {
            Game temporary = (Game) obj;
            for (Game game : games) {
                if (temporary.getGameTitle().equals(game.getGameTitle())
                        && temporary.getGamePrice() == game.getGamePrice()
                        && temporary.getGameDescription().equals(game.getGameDescription())
                        && temporary.getGameGenre().equals(game.getGameGenre())) {
                    return true;
                }
            }
        }
        return false;
    }

}
