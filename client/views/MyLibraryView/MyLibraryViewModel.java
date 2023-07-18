package GameApp.client.views.MyLibraryView;

import GameApp.client.model.ClientModelManagerFactory;
import GameApp.shared.model.Game;
import GameApp.shared.util.Subject;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

/**
 * A model view class.
 *
 * @author Adrian Bugiel, Andreea Asimine, Kevin Kluka, Saran Singh
 * @version 1.0
 */
public class MyLibraryViewModel implements Subject {
    private ClientModelManagerFactory clientModelManagerFactory;

    private PropertyChangeSupport support;

    private int countColumns;
    private int countRows;

    /**
     * 1 argument constructor.
     * @param clientModelManagerFactory client's model manager factory
     */
    public MyLibraryViewModel(ClientModelManagerFactory clientModelManagerFactory) {

        support = new PropertyChangeSupport(this);

        clientModelManagerFactory.addListener("UserLoggedIn", this::Refreshed);
        clientModelManagerFactory.addListener("TransactionMade", this::Refreshed);

        this.clientModelManagerFactory = clientModelManagerFactory;
        countColumns = 0;
        countRows = 0;
    }

    private void Refreshed(PropertyChangeEvent event) {
        support.firePropertyChange("Refresh", null, 1);
    }

    public void insertGame(GridPane gridPane) throws SQLException, RemoteException {

        countColumns = 0;
        countRows = 0;
        gridPane.getChildren().clear();

        List<Game> games = clientModelManagerFactory.getGamesByEmail(clientModelManagerFactory.getUser().getEmail());
        addGamesToGridPane(gridPane, games);
    }

    //kevoboc@gmail.com
    public void searchGames(GridPane gridPane, String title) {

        countColumns = 0;
        countRows = 0;
        gridPane.getChildren().clear();

        List<Game> games = clientModelManagerFactory.searchLikeTitleForEmail(title, clientModelManagerFactory.getUser().getEmail());

        addGamesToGridPane(gridPane, games);
    }

    private void addGamesToGridPane(GridPane gridPane, List<Game> games) {

        if (games != null) {

            for (Game game : games) {

                AnchorPane anchorPane = new AnchorPane();

                ImageView imageView = new ImageView();
                Image image = null;

                try {
                    image = new Image("@../../GameApp/client/views/images/" + game.getGameId() + ".jpg");
                } catch (RuntimeException e) {
                    image = new Image("@../../GameApp/client/views/images/image_not_found.jpg");
                }

                imageView.setImage(image);

                Label labelTitle = new Label(game.getGameTitle());
                Label labelDescription = new Label(game.getGameDescription());

                imageView.setFitWidth(128);
                imageView.setFitHeight(158);

                anchorPane.getChildren().add(imageView);
                anchorPane.getChildren().add(labelTitle);
                anchorPane.getChildren().add(labelDescription);

                labelTitle.layoutYProperty().setValue(-20);
                labelTitle.layoutXProperty().setValue(140);
                labelTitle.setPrefHeight(60);
                labelTitle.setPrefWidth(270);
                labelTitle.setFont(new Font("Century Gothic", 18));
                labelTitle.setWrapText(true);

                labelDescription.layoutYProperty().setValue(30);
                labelDescription.layoutXProperty().setValue(140);
                labelDescription.setPrefHeight(120);
                labelDescription.setPrefWidth(270);
                labelDescription.setFont(new Font("Century Gothic", 10));
                labelDescription.setWrapText(true);

                gridPane.add(anchorPane, countColumns, countRows);
                gridPane.setVgap(10);

                countColumns = (countColumns + 1) % 2;

                if (countColumns % 2 == 0) {

                    countRows++;
                }
            }
        } else {
            Label label = new Label("                                                                                                                 NO GAMES FOUND");
            label.setFont(new Font("Century Gothic", 12));
            gridPane.add(label, 0, 2);
        }
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }
}