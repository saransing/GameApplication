package GameApp.server;

import GameApp.server.model.GameServerModelManager;
import GameApp.server.model.TransactionServerModelManager;
import GameApp.server.model.UserServerModelManager;
import GameApp.server.networking.RMIServerImpl;

import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * A Class responsible for running the server.
 *
 * @author Adrian Bugiel
 * @version 1.0
 */
public class RunServer
{
  public static void main(String[] args) throws RemoteException, SQLException {

    RMIServerImpl server = new RMIServerImpl(new GameServerModelManager(), new UserServerModelManager(), new TransactionServerModelManager());
    server.startServer();
  }
}
