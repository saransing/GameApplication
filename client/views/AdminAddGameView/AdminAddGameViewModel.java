package GameApp.client.views.AdminAddGameView;

import GameApp.client.model.ClientModelManagerFactory;
import javafx.scene.control.Label;

/**
 * A model view class.
 *
 * @author Adrian Bugiel, Andreea Asimine, Kevin Kluka, Saran Singh
 * @version 1.0
 */
public class AdminAddGameViewModel {

    private ClientModelManagerFactory clientModelManagerFactory;

    /**
     * 1 argument constructor.
     * @param clientModelManagerFactory client's model manager factory
     */
    public AdminAddGameViewModel(ClientModelManagerFactory clientModelManagerFactory) {

        this.clientModelManagerFactory = clientModelManagerFactory;
    }

    /**
     * Saves game to the database.
     * @param errorLabel label error
     * @param title game title
     * @param description game description
     * @param genre game genre
     * @param price game price
     */
    public void saveGame(Label errorLabel, String title, String description, String genre, String price) {
        try {
            if (clientModelManagerFactory.getGamesByTitle(title).size() == 0) {

                clientModelManagerFactory.create(title, genre, description, Double.parseDouble(price));
                errorLabel.setText("Game Saved!");

            } else errorLabel.setText("Game already exist in database!");

        } catch (RuntimeException e) {
            errorLabel.setText("Wrong price format!");
        }
    }
}
