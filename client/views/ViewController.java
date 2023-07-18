package GameApp.client.views;

import GameApp.client.core.ViewHandler;
import GameApp.client.core.ViewModelFactory;

/**
 * An interface to provide method signatures for ViewControllers Classes.
 *
 * @author Adrian Bugiel
 * @version 1.0
 */
public interface ViewController {

    /**
     * Initialization.
     *
     * @param vh  view handler
     * @param vmf view model factory
     */
    void init(ViewHandler vh, ViewModelFactory vmf);
}
