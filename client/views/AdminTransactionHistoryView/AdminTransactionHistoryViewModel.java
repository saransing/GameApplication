package GameApp.client.views.AdminTransactionHistoryView;

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

/**
 * A model view class.
 *
 * @author Adrian Bugiel, Andreea Asimine, Kevin Kluka, Saran Singh
 * @version 1.0
 */
public class AdminTransactionHistoryViewModel {

    private ClientModelManagerFactory clientModelManagerFactory;
    private Property<ObservableList<Transaction>> observableListProperty;
    private ObservableList<Transaction> observableList;

    /**
     * 1 argument constructor.
     * @param clientModelManagerFactory client's model manager factory
     */
    public AdminTransactionHistoryViewModel(ClientModelManagerFactory clientModelManagerFactory) {
        this.clientModelManagerFactory = clientModelManagerFactory;

        observableListProperty = new SimpleObjectProperty<>();
        observableList = FXCollections.observableList(clientModelManagerFactory.getAllTransactions());
        observableListProperty.setValue(observableList);
    }

    public ObservableList<Transaction> observableList() {
        return observableList;
    }

    public Property<ObservableList<Transaction>> observableListProperty() {
        return observableListProperty;
    }

    public void updateObservableList(PropertyChangeEvent propertyChangeEvent) {
        observableList = FXCollections.observableList(clientModelManagerFactory.getAllTransactions());
        observableListProperty.setValue(observableList);
    }

    public void showAll() {
        observableList = FXCollections.observableList(clientModelManagerFactory.getAllTransactions());
        observableListProperty.setValue(observableList);
    }

    public void searchId(String id, Label errorLabel) {
        try {
            List<Transaction> transaction = new ArrayList<>();
            transaction.add(clientModelManagerFactory.getTransactionByTransactionId(Integer.parseInt(id)));
            observableList = FXCollections.observableList(transaction);
            observableListProperty.setValue(observableList);

        } catch (RuntimeException e) {
            errorLabel.setText("Incorrect id or id format!");
        }
    }
}
