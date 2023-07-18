package GameApp.client.core;

import GameApp.client.model.ClientModelManager;
import GameApp.client.model.ClientModelManagerFactory;

/**
 * A Class returns instance of the ModelFactory object.
 *
 * @author Adrian Bugiel
 * @version 1.0
 */
public class ModelFactory {
    private static ModelFactory instance = new ModelFactory();

    /**
     * Gets ModelFactory instance.
     *
     * @return ModelFactory instance
     */
    public static ModelFactory getInstance() {
        return instance;
    }

    private ClientModelManagerFactory clientModelManagerFactory;

    /**
     * Private constructor.
     */
    private ModelFactory() {
    }


    /**
     * Creates a single instance with set of RMIClient methods.
     * @return a ClientModelManagerFactory object
     */
    public ClientModelManagerFactory getClientModelManagerFactory() {
        if (clientModelManagerFactory == null)
            clientModelManagerFactory = new ClientModelManager(ClientFactory.getInstance().getClient());
        return clientModelManagerFactory;
    }
}
