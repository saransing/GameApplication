package GameApp.shared.networking;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * A interface to send a call back to all registered clients.
 *
 * @author Adrian Bugiel
 * @version 1.0
 */
public interface ClientCallback extends Remote, Serializable {

    /**
     * Updates all the registered clients.
     *
     * @throws RemoteException if a connection between RMI serve and client fails
     */
    void update() throws RemoteException;
}

