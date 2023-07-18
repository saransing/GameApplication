package GameApp.client.core;

import GameApp.client.network.Client;
import GameApp.client.network.RMIClient;

/**
 * A Class returns instance of the Client object.
 *
 * @author Adrian Bugiel
 * @version 1.0
 */
public class ClientFactory {
    private static ClientFactory instance;


    static {
        instance = new ClientFactory();
    }

    /**
     * Gets ClientFactory instance.
     *
     * @return ClientFactory instance
     */
    public static ClientFactory getInstance() {
        return instance;
    }

    private Client client;

    /**
     * Private Constructor.
     */
    private ClientFactory() {
    }

    /**
     * Gets single instance of the Client.
     *
     * @return single Client instance
     */
    public Client getClient() {
        if (client == null) {
            client = new RMIClient();
        }
        return client;
    }
}
