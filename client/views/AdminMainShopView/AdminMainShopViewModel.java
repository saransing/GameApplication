package GameApp.client.views.AdminMainShopView;

import GameApp.client.model.ClientModelManagerFactory;
import GameApp.shared.model.Game;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;

/**
 * A model view class.
 *
 * @author Adrian Bugiel, Andreea Asimine, Kevin Kluka, Saran Singh
 * @version 1.0
 */public class AdminMainShopViewModel {
    private ClientModelManagerFactory clientModelManagerFactory;
    private Property<ObservableList<Game>> observableListProperty;
    private ObservableList<Game> observableList;

    /**
     * 1 argument constructor.
     * @param clientModelManagerFactory client's model manager factory
     */
    public AdminMainShopViewModel(ClientModelManagerFactory clientModelManagerFactory)
    {
        this.clientModelManagerFactory = clientModelManagerFactory;
        clientModelManagerFactory.addListener("NewGameAdded", this::updateObservableList);
        observableListProperty = new SimpleObjectProperty();
        observableList = FXCollections.observableList(clientModelManagerFactory.getAllGames());
        observableListProperty.setValue(observableList);
    }

    /**
     * Gets observable list
     * @return Observable list
     */
    public ObservableList<Game> observableList()
    {
        return observableList;
    }

    /**
     * Gets observable list property
     * @return Observable list
     */
    public Property<ObservableList<Game>> observableListProperty()
    {
        return observableListProperty;
    }

    /**
     * Updates observable list
     */
    public void updateObservableList(PropertyChangeEvent propertyChangeEvent)
    {
        observableList = FXCollections.observableList(clientModelManagerFactory.getAllGames());
        observableListProperty.setValue(observableList);
    }

    /**
     * Updates observable list
     */
    public void updateObservableList()
    {
        observableList = FXCollections.observableList(clientModelManagerFactory.getAllGames());
        observableListProperty.setValue(observableList);
    }

    /**
     * Searchs for a game.
     *
     * @param title game title
     */
    public void searchForGame(String title)
    {
        observableList = FXCollections.observableList(clientModelManagerFactory.getGamesByTitle(title));
        observableListProperty.setValue(observableList);
    }
}
