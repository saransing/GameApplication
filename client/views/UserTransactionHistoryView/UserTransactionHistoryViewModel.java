package GameApp.client.views.UserTransactionHistoryView;

import GameApp.client.model.ClientModelManagerFactory;
import GameApp.shared.model.Transaction;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * A model view class.
 *
 * @author Adrian Bugiel, Andreea Asimine, Kevin Kluka, Saran Singh
 * @version 1.0
 */
public class UserTransactionHistoryViewModel {

    private ClientModelManagerFactory clientModelManagerFactory;
    private Property<ObservableList<Transaction>> observableListProperty;
    private ObservableList<Transaction> observableList;

    /**
     * 1 argument constructor.
     * @param clientModelManagerFactory client's model manager factory
     */
    public UserTransactionHistoryViewModel(ClientModelManagerFactory clientModelManagerFactory) {
        this.clientModelManagerFactory = clientModelManagerFactory;

        observableListProperty = new SimpleObjectProperty();

        List<Transaction> dummyList = new ArrayList<>();
        observableList = FXCollections.observableList(dummyList);
        observableListProperty.setValue(observableList);

        clientModelManagerFactory.addListener("UserLoggedIn", this::updateObservableList);
        clientModelManagerFactory.addListener("TransactionMade", this::updateObservableList);
    }

    public ObservableList<Transaction> observableList()
    {
        return observableList;
    }

    public Property<ObservableList<Transaction>> observableListProperty()
    {
        return observableListProperty;
    }

    public void updateObservableList(PropertyChangeEvent propertyChangeEvent)
    {
        List<Transaction> transactions = clientModelManagerFactory.getAllTransactionsForThisClient();
        if (transactions!=null)
        {
            observableList = FXCollections.observableList(
                clientModelManagerFactory.getAllTransactionsForThisClient());
            observableListProperty.setValue(observableList);
        }
    }

    public void showAll()
    {
        observableList = FXCollections.observableList(clientModelManagerFactory.getAllTransactionsForThisClient());
        observableListProperty.setValue(observableList);
    }

    public void searchId(String id, Label errorLabel) {
        try {
            List<Transaction> transactions = clientModelManagerFactory.getAllTransactionsForThisClient();
            List<Transaction> filteredTransactions = new ArrayList<>();
            for (int i = 0; i < transactions.size(); i++)
            {
                if (transactions.get(i).getTransactionId() == parseInt(id))
                {
                    filteredTransactions.add(transactions.get(i));
                }
            }
            observableList = FXCollections.observableList(filteredTransactions);
            observableListProperty.setValue(observableList);

        } catch (RuntimeException e) {
            errorLabel.setText("Incorrect id or id format!");
        }
    }
}
