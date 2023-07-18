package GameApp.client.views.GameView;

import GameApp.client.model.ClientModelManagerFactory;
import GameApp.shared.model.Game;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.ImageView;

import java.beans.PropertyChangeEvent;

/**
 * A model view class.
 *
 * @author Adrian Bugiel, Andreea Asimine, Kevin Kluka, Saran Singh
 * @version 1.0
 */
public class GameViewModel {

    private StringProperty descriptionTextField;
    private StringProperty titleLabel;
    private StringProperty priceLabel;
    private String pictureURL;
    private StringProperty genreLabel;
    private ObjectProperty pictureProperty;

    private ClientModelManagerFactory clientModelManagerFactory;

    /**
     * 1 argument constructor.
     * @param clientModelManagerFactory client's model manager factory
     */
    public GameViewModel(ClientModelManagerFactory clientModelManagerFactory) {
        this.clientModelManagerFactory = clientModelManagerFactory;
        clientModelManagerFactory.addListener("NewPictureSelected", this::onNewImageClicked);

        descriptionTextField = new SimpleStringProperty("TestDescription");
        titleLabel = new SimpleStringProperty("TestTitle");
        priceLabel = new SimpleStringProperty("0");
        pictureURL = ("@../../GameApp/client/views/images/1.jpg");
        genreLabel = new SimpleStringProperty("TestGenre");
        pictureProperty = new SimpleObjectProperty(new ImageView(pictureURL).imageProperty().getValue());
    }

    public void onNewImageClicked(PropertyChangeEvent evt) {
        Game selectedGame;
        selectedGame = clientModelManagerFactory.readByID((int) evt.getNewValue());
        if (selectedGame != null) {
            titleLabel.setValue(selectedGame.getGameTitle());
            descriptionTextField.setValue(selectedGame.getGameDescription());
            priceLabel.setValue("$" + selectedGame.getGamePrice());
            pictureURL = selectedGame.getPictureURL();
            pictureProperty.setValue(new ImageView(pictureURL).imageProperty().getValue());
            genreLabel.setValue(selectedGame.getGameGenre());
        }
    }

    public StringProperty titleLabelProperty() {
        return titleLabel;
    }

    public StringProperty descriptionTextFieldProperty() {
        return descriptionTextField;
    }

    public StringProperty priceLabelProperty() {
        return priceLabel;
    }

    public ObjectProperty pictureProperty() {

        return pictureProperty;
    }

    public StringProperty genreLabelProperty() {
        return genreLabel;
    }

    public void pressAddToCartButton() {
        clientModelManagerFactory.addGameToShoppingCart();
    }


}

