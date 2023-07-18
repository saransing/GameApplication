package GameApp.client.views.PaymentView;

import GameApp.client.model.ClientModelManagerFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;

/**
 * A model view class.
 *
 * @author Adrian Bugiel, Andreea Asimine, Kevin Kluka, Saran Singh
 * @version 1.0
 */
public class PaymentViewModel {
    private ClientModelManagerFactory clientModelManagerFactory;

    private StringProperty totalPrice;

    /**
     * 1 argument constructor.
     * @param clientModelManagerFactory client's model manager factory
     */
    public PaymentViewModel(ClientModelManagerFactory clientModelManagerFactory)
    {
        this.clientModelManagerFactory = clientModelManagerFactory;
        totalPrice = new SimpleStringProperty("0");

        clientModelManagerFactory.addListener("NewItemInShoppingCart", this:: setTotalPrice);
        clientModelManagerFactory.addListener("ItemDeletedFromShoppingCart", this:: setTotalPrice);
    }

    public void setTotalPrice(PropertyChangeEvent evt)
    {
        totalPrice.setValue(Double.toString(clientModelManagerFactory.getShoppingCartValue()));
    }

    public StringProperty getTotalPrice()
    {
        return totalPrice;
    }

    public void createTransaction()
    {
        clientModelManagerFactory.create(
            clientModelManagerFactory.getUser(),
            clientModelManagerFactory.getAllGamesFromShoppingCart());
    }

    public void clearShoppingCart()
    {
        clientModelManagerFactory.removeAllGamesFromCart();
    }
}
