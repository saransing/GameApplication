package GameApp.client.views.GameView;

import GameApp.client.core.ViewHandler;
import GameApp.client.core.ViewModelFactory;
import GameApp.client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * A view controller class.
 *
 * @author Adrian Bugiel, Andreea Asimine, Kevin Kluka, Saran Singh
 * @version 1.0
 */
public class GameViewController implements ViewController {
    private GameViewModel gameViewModel;
    private ViewHandler viewHandler;
    public ImageView listingImage;
    public Label descriptionLabel;
    public Label nameOfGameLabel;
    public Label priceOfGameLabel;
    public Label genreLabel;

    /**
     * Initialization.
     *
     * @param vh  view handler
     * @param vmf view model factory
     */
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.viewHandler = vh;
        this.gameViewModel = vmf.getGameViewModel();

        descriptionLabel.textProperty().bind(gameViewModel.descriptionTextFieldProperty());
        nameOfGameLabel.textProperty().bind(gameViewModel.titleLabelProperty());
        priceOfGameLabel.textProperty().bind(gameViewModel.priceLabelProperty());
        listingImage.imageProperty().bind(gameViewModel.pictureProperty());
        genreLabel.textProperty().bind(gameViewModel.genreLabelProperty());
    }

    public void backToMainShopView(ActionEvent actionEvent) {
        viewHandler.openMainShopView();
    }

    public void myLibrary(ActionEvent actionEvent) {
        viewHandler.openMyLibraryView();
    }

    public void myAccount(ActionEvent event) {
        viewHandler.openMyAccountView();
    }

    public void openShoppingCart(MouseEvent mouseEvent) {
        viewHandler.openShopCartView();
    }

    public void pressAddToCartButton(ActionEvent mouseEvent) {
        gameViewModel.pressAddToCartButton();
        viewHandler.openShopCartView();
    }

    public void logOut(MouseEvent mouseEvent) {
        viewHandler.openLoginView();
    }
}