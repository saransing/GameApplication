package GameApp.client.views.MainShopView;

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
import java.util.ArrayList;
import java.util.List;

/**
 * A model view class.
 *
 * @author Adrian Bugiel, Andreea Asimine, Kevin Kluka, Saran Singh
 * @version 1.0
 */
public class MainShopViewModel implements Subject {

    private final ClientModelManagerFactory clientModelManagerFactory;

    private int countColumns;
    private int countRows;

    private List<Game> gamesInTransaction;

    private final PropertyChangeSupport support;

    /**
     * 1 argument constructor.
     * @param clientModelManagerFactory client's model manager factory
     */
    public MainShopViewModel(ClientModelManagerFactory clientModelManagerFactory) {
        this.clientModelManagerFactory = clientModelManagerFactory;
        support = new PropertyChangeSupport(this);
        clientModelManagerFactory.addListener("UserLoggedIn", this::setListOfGames);
        clientModelManagerFactory.addListener("TransactionMade", this::setListOfGames);


        countColumns = 0;
        countRows = 0;
    }

    private void setListOfGames(PropertyChangeEvent event) {
        gamesInTransaction = clientModelManagerFactory.getGamesByEmail(clientModelManagerFactory.getUser().getEmail());
        support.firePropertyChange("refreshShop", null, 1);
    }

    public Game readByID(int gameId) throws SQLException, RemoteException {
        return clientModelManagerFactory.readByID(gameId);
    }

    public void setSelectedId(int id) throws SQLException, RemoteException {
        clientModelManagerFactory.setSelectedId(id);
    }

    public void insertGames(GridPane gridPane) {

        countColumns = 0;
        countRows = 0;
        gridPane.getChildren().clear();

        List<Game> games = clientModelManagerFactory.getAllGames();


        addGamesToGridPane(gridPane, games);
    }


    private void addGamesToGridPane(GridPane gridPane, List<Game> games) {


        List<Game> notMyGames = new ArrayList<>();

        if (gamesInTransaction != null) {

            if (games != null) {

                for (Game game : games) {
                    if (!gamesInTransaction.contains(game)) {
                        notMyGames.add(game);
                    }
                }
            }

        } else {
            notMyGames = games;
        }


        if (games != null) {

            if (notMyGames.size() != 0) {
                for (Game game : notMyGames) {
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
                    Label labelGenre = new Label("Genre: " + game.getGameGenre());
                    Label labelPrice = new Label("$" + game.getGamePrice());

                    imageView.setFitWidth(128);
                    imageView.setFitHeight(158);

                    anchorPane.getChildren().add(imageView);
                    anchorPane.getChildren().add(labelTitle);
                    anchorPane.getChildren().add(labelDescription);
                    anchorPane.getChildren().add(labelGenre);
                    anchorPane.getChildren().add(labelPrice);

                    labelTitle.layoutYProperty().setValue(1.0);
                    labelTitle.layoutXProperty().setValue(150);
                    labelTitle.setPrefHeight(60);
                    labelTitle.setPrefWidth(270);
                    labelTitle.setFont(new Font("Century Gothic", 18));
                    labelTitle.setWrapText(true);

                    labelDescription.layoutYProperty().setValue(50);
                    labelDescription.layoutXProperty().setValue(150);
                    labelDescription.setPrefHeight(90);
                    labelDescription.setPrefWidth(270);
                    labelDescription.setFont(new Font("Century Gothic", 12));
                    labelDescription.setWrapText(true);

                    labelGenre.layoutYProperty().setValue(1.0);
                    labelGenre.layoutXProperty().setValue(440);
                    labelGenre.setPrefHeight(60);
                    labelGenre.setPrefWidth(270);
                    labelGenre.setFont(new Font("Century Gothic", 18));
                    labelGenre.setWrapText(true);

                    labelPrice.layoutYProperty().setValue(50);
                    labelPrice.layoutXProperty().setValue(440);
                    labelPrice.setPrefHeight(60);
                    labelPrice.setPrefWidth(270);
                    labelPrice.setFont(new Font("Century Gothic", 18));
                    labelPrice.setWrapText(true);

                    gridPane.add(anchorPane, countColumns, countRows);
                    gridPane.setVgap(10);

                    countRows++;
                }
            } else {
                Label label = new Label("                                                                                       NO GAMES FOUND");
                label.setFont(new Font("Century Gothic", 12));

                gridPane.add(label, 0, 2);
            }
        } else {
            Label label = new Label("                                                                                       NO GAMES FOUND");
            label.setFont(new Font("Century Gothic", 12));

            gridPane.add(label, 0, 2);
        }
    }

    public void showGenre(GridPane gridPane, String genre) {
        countColumns = 0;
        countRows = 0;
        gridPane.getChildren().clear();
        List<Game> games = clientModelManagerFactory.getGamesByGenre(genre);
        addGamesToGridPane(gridPane, games);
    }

    public void searchGamesByTitle(GridPane gridPane, String title) {

        countColumns = 0;
        countRows = 0;
        gridPane.getChildren().clear();
        List<Game> games = clientModelManagerFactory.getGamesByTitle(title);
        addGamesToGridPane(gridPane, games);
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

