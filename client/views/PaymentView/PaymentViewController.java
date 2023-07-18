package GameApp.client.views.PaymentView;

import GameApp.client.core.ViewHandler;
import GameApp.client.core.ViewModelFactory;
import GameApp.client.views.ViewController;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * A view controller class.
 *
 * @author Adrian Bugiel, Andreea Asimine, Kevin Kluka, Saran Singh
 * @version 1.0
 */
public class PaymentViewController implements ViewController {

    private PaymentViewModel paymentViewModel;
    private ViewHandler vh;

    public TextField totalPrice;

    /**
     * Initialization.
     *
     * @param vh  view handler
     * @param vmf view model factory
     */
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.vh = vh;
        this.paymentViewModel = vmf.getPaymentViewModel();

        totalPrice.textProperty().bind(paymentViewModel.getTotalPrice());
    }

    public void openShoppingCart() {
        vh.openShopCartView();
    }

    public void openMyAccountView(MouseEvent mouseEvent) {
        vh.openMyAccountView();
    }

    public void openStoreView(MouseEvent mouseEvent) {
        vh.openMainShopView();
    }

    public void openLibraryView(MouseEvent mouseEvent) {
        vh.openMyLibraryView();
    }

    public void logout() {
        vh.openLoginView();
    }

    public void createTransaction()
    {
        paymentViewModel.createTransaction();
        paymentViewModel.clearShoppingCart();
        vh.openMyLibraryView();
    }
}
